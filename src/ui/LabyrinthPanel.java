package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LabyrinthPanel extends JPanel {

	private final LabyrinthApp labyrinthApp;

	public LabyrinthPanel(LabyrinthApp labyrinthApp) {
		this.labyrinthApp = labyrinthApp;
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(500, 700));

		LabyrinthPanelMouseListener drawingPanelMouseListener = new LabyrinthPanelMouseListener(labyrinthApp);

		addMouseListener(drawingPanelMouseListener);
	}

	public void notifyForUpdate() {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);  // set the paint panel visible
		labyrinthApp.getLabyrinthAppModel().paintHexagons(graphics);
	}
}