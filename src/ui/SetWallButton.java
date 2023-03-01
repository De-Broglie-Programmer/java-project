package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SetWallButton extends JButton implements ActionListener {

	private final LabyrinthApp labyrinthApp;

	public SetWallButton(LabyrinthApp labyrinthApp) {
		super("Set Wall");
		this.labyrinthApp = labyrinthApp;
		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt) {
		labyrinthApp.getLabyrinthAppModel().setWall();
	}
	
	public void notifyForUpdate() {
		boolean isEmpty = labyrinthApp.getLabyrinthAppModel().getIsEmpty();
		setEnabled(isEmpty == false);
	}
}
