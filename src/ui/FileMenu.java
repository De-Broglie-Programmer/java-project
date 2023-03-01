package ui;

import javax.swing.JMenu;

public class FileMenu extends JMenu {
	
	public FileMenu(LabyrinthApp labyrinthApp) {
		super("File");

		add(new NewMenuItem(labyrinthApp));
		add(new LoadMenuItem(labyrinthApp));
		add(new SaveMenuItem(labyrinthApp));
		add(new ExitMenuItem(labyrinthApp));
	}
}
