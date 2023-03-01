package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SetDestinationButton extends JButton implements ActionListener {

	private final LabyrinthApp labyrinthApp;

	public SetDestinationButton(LabyrinthApp labyrinthApp) {
		super("Set Destinatioin");
		this.labyrinthApp = labyrinthApp;
		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt) {
		labyrinthApp.getLabyrinthAppModel().setDestination();;
	}
	
	public void notifyForUpdate() {
		boolean isEmpty = labyrinthApp.getLabyrinthAppModel().getIsEmpty();
		setEnabled(isEmpty == false);
	}
}
