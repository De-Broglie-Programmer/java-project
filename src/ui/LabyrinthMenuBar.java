package ui;

import javax.swing.*;

public class LabyrinthMenuBar extends JMenuBar {

	public LabyrinthMenuBar(LabyrinthApp labyrinthApp) {
		super();

		add(new FileMenu(labyrinthApp));
	}
}
