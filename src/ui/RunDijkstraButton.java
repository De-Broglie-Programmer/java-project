package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RunDijkstraButton extends JButton implements ActionListener {

	private final LabyrinthApp labyrinthApp;

	public RunDijkstraButton(LabyrinthApp labyrinthApp) {
		super("Run Dijkstra");
		this.labyrinthApp = labyrinthApp;
		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt) {
		labyrinthApp.getLabyrinthAppModel().runDijkstra();;
	}
	
	public void notifyForUpdate() {
		boolean isComplete = labyrinthApp.getLabyrinthAppModel().getIsComplete();
		setEnabled(isComplete == true);
	}
}
