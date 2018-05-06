package sudokuXYsolver;

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;

public class SudokuUi {
	private JFrame mainFrame = new JFrame("Sudoku XY solver");
	private Container frame = mainFrame.getContentPane();

	private JPanel titlePanel = new JPanel();
	private JPanel gridPanel = new JPanel();

	private JButton browseButton = new JButton("Open Input File");

	private int currentPuzzlePointer = 0;
	private int currentSolutionPointer = 0;

	public SudokuUi() {
		init();
	}

	private void init() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setPreferredSize(new Dimension(1040, 600));

		frame.setLayout(new BorderLayout());

		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(gridPanel, BorderLayout.CENTER);
		
		titlePanel.add(browseButton);
		
		// browse button action listener
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fName = chooseFile();
				if (fName != "") {
					System.out.println("\nOpen " + fName);
					Main.readInputFile(fName);
					Main.solveAllGivenPuzzles();
					// clear previous tables and print all stuff again
				}				
			}
		});


		// display given puzzle at the top (also include back and next button)
		// display larger table showing solution at the bottom
		// include back and next buttons
		// 	(back and next will increment and decrement the pointer)
		// 	(this will be used for printing the current solution for the given puzzle or the current puzzle itself)


		// show number of solutions for regular, X, Y, XY for the current puzzle

		mainFrame.pack();
	}

	public void displayCurrentPuzzle(Puzzle currentPuzzle) {

	}

	public void displayCurrentSolution(Puzzle currentPuzzle) {

	}

	public void showGui() {
		mainFrame.setVisible(true);
	}

	public String chooseFile() {
		JFileChooser fileOpener = new JFileChooser();
		String fileName = "";
		int result = fileOpener.showOpenDialog(new JFrame());

		fileOpener.setCurrentDirectory(new File(System.getProperty("user.home")));

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileOpener.getSelectedFile();
			fileName = selectedFile.getAbsolutePath();
		}
		return fileName;
	}
}