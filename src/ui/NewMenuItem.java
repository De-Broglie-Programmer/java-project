package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.LabyrinthAppModel;

public class NewMenuItem extends JMenuItem implements ActionListener {
    private final LabyrinthApp labyrinthApp;

	public NewMenuItem(LabyrinthApp labyrinthApp) {
		super("New labyrinth");
		this.labyrinthApp = labyrinthApp;
		addActionListener(this);
	}

    public void actionPerformed(ActionEvent evt){
        LabyrinthAppModel labyrinthAppModel = labyrinthApp.getLabyrinthAppModel();
        
		String input1 = JOptionPane.showInputDialog(null, "Number of lines: ");
		int lineNum = 0;
		try {
			lineNum = Integer.parseInt(input1);
			if (lineNum <= 0 || lineNum > 30) {
				JOptionPane.showMessageDialog(null, "Invalid age. Please enter a value between 1 and 30.");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
			lineNum = 0;
		}

		String input2 = JOptionPane.showInputDialog(null, "Number of colomns: ");
		int colNum = 0;
		try {
			colNum = Integer.parseInt(input2);
			if (colNum <= 0 || colNum > 30) {
				JOptionPane.showMessageDialog(null, "Invalid age. Please enter a value between 1 and 30.");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
			colNum = 0;
		}

		labyrinthAppModel.creatLabyrinth(lineNum, colNum);
    }
}