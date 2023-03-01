package ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {

	private final SetStartButton setStart;
	private final SetDestinationButton setDestination;
	private final SetWallButton setWall;
	private final SetEmptyButton setEmpty;
    private final RunDijkstraButton runDijkstra;
    private final ErasePathButton erasePath;

	public ButtonsPanel(LabyrinthApp labyrinthApp) {
		setLayout(new GridLayout(1, 6));

		add(setStart = new SetStartButton(labyrinthApp));
		add(setDestination = new SetDestinationButton(labyrinthApp));
		add(setWall = new SetWallButton(labyrinthApp));
		add(setEmpty = new SetEmptyButton(labyrinthApp));
        add(runDijkstra = new RunDijkstraButton(labyrinthApp));
        add(erasePath = new ErasePathButton(labyrinthApp));
	}
	
	public void notifyForUpdate() {
		setStart.notifyForUpdate();
		setDestination.notifyForUpdate();
		setWall.notifyForUpdate();
		setEmpty.notifyForUpdate();
        runDijkstra.notifyForUpdate();
        erasePath.notifyForUpdate();
	}
}
