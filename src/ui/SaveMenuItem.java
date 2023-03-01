package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import model.LabyrinthAppModel;

public class SaveMenuItem extends JMenuItem implements ActionListener {
    private final LabyrinthApp labyrinthApp;

	public SaveMenuItem(LabyrinthApp labyrinthApp) {
		super("Save labyrinth");
		this.labyrinthApp = labyrinthApp;
		addActionListener(this);
	}
    public void actionPerformed(ActionEvent evt){
        LabyrinthAppModel labyrinthAppModel = labyrinthApp.getLabyrinthAppModel();
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setDialogTitle("Save Labyrinth");
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDir = chooser.getSelectedFile();
			String selectedFilePath = selectedDir.getAbsolutePath();
			labyrinthAppModel.saveToFile(selectedFilePath);
		}
    }
}