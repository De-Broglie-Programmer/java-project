package ui;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.LabyrinthAppModel;

public class LabyrinthApp extends JFrame implements ChangeListener{
    
    private final WindowPanel windowPanel;

	private LabyrinthAppModel labyrinthAppModel;

    public LabyrinthApp() {
		super("Labyrinth Application");

		setLabyrinthAppModel(new LabyrinthAppModel());  // we set model first
		setJMenuBar(new LabyrinthMenuBar(this));
		setContentPane(windowPanel = new WindowPanel(this));  // in this step, we can set the labyrinth size according to panel size

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

    public LabyrinthAppModel getLabyrinthAppModel() {
		return labyrinthAppModel;
	}

	public WindowPanel getWindowPanel(){
		return windowPanel;
	}

    public void setLabyrinthAppModel(LabyrinthAppModel labyrinthAppModel) {
		this.labyrinthAppModel = labyrinthAppModel;
		this.labyrinthAppModel.addObserver(this);
	}

    @Override
	public void stateChanged(ChangeEvent evt) {
		windowPanel.notifyForUpdate();
	}
}
