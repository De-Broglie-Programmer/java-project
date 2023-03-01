package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SetEmptyButton extends JButton implements ActionListener {

	private final LabyrinthApp labyrinthApp;

	public SetEmptyButton(LabyrinthApp labyrinthApp) {
		super("Set Empty");
		this.labyrinthApp = labyrinthApp;
		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt) {
		labyrinthApp.getLabyrinthAppModel().setEmpty();
	}
	
	public void notifyForUpdate() {
		boolean isEmpty = labyrinthApp.getLabyrinthAppModel().getIsEmpty();
		setEnabled(isEmpty == false);
	}
}
