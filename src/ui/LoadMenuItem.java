package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import model.LabyrinthAppModel;

public class LoadMenuItem extends JMenuItem implements ActionListener {
    private final LabyrinthApp labyrinthApp;

	public LoadMenuItem(LabyrinthApp labyrinthApp) {
		super("Load labyrinth");
		this.labyrinthApp = labyrinthApp;
		addActionListener(this);
	}
	
    public void actionPerformed(ActionEvent evt){
        LabyrinthAppModel labyrinthAppModel = labyrinthApp.getLabyrinthAppModel();
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setDialogTitle("Load Labyrinth");
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
			String selectedFilePath = selectedFile.getAbsolutePath();
			labyrinthAppModel.loadFromFile(selectedFilePath);
		}	
    }
}