package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LabyrinthPanelMouseListener extends MouseAdapter {
	
	private final LabyrinthApp labyrinthApp;
	
	public LabyrinthPanelMouseListener(LabyrinthApp labyrinthApp) {
		this.labyrinthApp = labyrinthApp ;
	}

	@SuppressWarnings("unused")
	private void println(	String message,
							MouseEvent event) {
		System.out.print(message);
		System.out.print(": (" + event.getX() + "," + event.getY() + ")") ;
		System.out.println();
	}
	
	// MouseListener
	
	@Override
	public void mouseClicked(MouseEvent e) {
		labyrinthApp.getLabyrinthAppModel().locateCurrentHexagon(e.getX(),e.getY()) ;
	}

}
