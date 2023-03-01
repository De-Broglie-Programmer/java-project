package ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class WindowPanel extends JPanel {

	private final LabyrinthPanel labyrinthgPanel;
	
	private final ButtonsPanel buttonsPanel;

	public WindowPanel(LabyrinthApp labyrinthApp) {
		setLayout(new BorderLayout());

		add(labyrinthgPanel = new LabyrinthPanel(labyrinthApp), BorderLayout.CENTER);
		add(buttonsPanel = new ButtonsPanel(labyrinthApp), BorderLayout.SOUTH);
	}
	
	public void notifyForUpdate() {
		labyrinthgPanel.notifyForUpdate();
		buttonsPanel.notifyForUpdate();
	}
}
