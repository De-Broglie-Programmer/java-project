package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ErasePathButton extends JButton implements ActionListener {

	private final LabyrinthApp labyrinthApp;

	public ErasePathButton(LabyrinthApp labyrinthApp) {
		super("Erase path");
		this.labyrinthApp = labyrinthApp;
		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt) {
		labyrinthApp.getLabyrinthAppModel().erasePath();
	}
	
	public void notifyForUpdate() {
		boolean hasPath = labyrinthApp.getLabyrinthAppModel().getHasPath();
		setEnabled(hasPath == true);
	}
}
