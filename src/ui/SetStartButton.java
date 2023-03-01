package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SetStartButton extends JButton implements ActionListener {

	private final LabyrinthApp labyrinthApp;

	public SetStartButton(LabyrinthApp labyrinthApp) {
		super("Set Start");
		this.labyrinthApp = labyrinthApp;
		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt) {
		labyrinthApp.getLabyrinthAppModel().setStart();
	}
	
	public void notifyForUpdate() {
		boolean isEmpty = labyrinthApp.getLabyrinthAppModel().getIsEmpty();
		setEnabled(isEmpty == false);
	}
}