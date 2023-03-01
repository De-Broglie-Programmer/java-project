package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import model.LabyrinthAppModel;

public class ExitMenuItem extends JMenuItem implements ActionListener {
	
	private final LabyrinthApp labyrinthApp;

	public ExitMenuItem(LabyrinthApp labyrinthApp) {
		super("Exit");
		this.labyrinthApp = labyrinthApp;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		LabyrinthAppModel labyrinthAppModel = labyrinthApp.getLabyrinthAppModel();

		if (labyrinthAppModel.isModified()) {
			int response = JOptionPane.showInternalOptionDialog(this, "Labyrinth not saved. Save it ?",
					"Quit application", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
					null);
			if (response == JOptionPane.CANCEL_OPTION) 
				return;
			else if(response == JOptionPane.OK_OPTION){
				JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        		chooser.setDialogTitle("Save Labyrinth");
        		int result = chooser.showOpenDialog(this);
        		if (result == JFileChooser.APPROVE_OPTION) {
            		File selectedDir = chooser.getSelectedFile();
					String selectedFilePath = selectedDir.getAbsolutePath();
					labyrinthAppModel.saveToFile(selectedFilePath);
				}
			}
			else if(response == JOptionPane.NO_OPTION){
				// do not save
			}
		}
		labyrinthApp.dispose();
		labyrinthApp.setVisible(false);
		System.exit(0) ;
	}
}
