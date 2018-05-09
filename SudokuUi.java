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
	private JPanel contentsPanel = new JPanel(new BorderLayout());
	private JPanel givenPanel = new JPanel();
	private JPanel optionsPanel = new JPanel();
	private JPanel answersPanel = new JPanel();

	private JButton browseButton = new JButton("Open Input File");

	private JLabel currentPuzzleLabel = new JLabel("");
	private JTable currGivenTable;

	private int currentPuzzlePointer = 0;
	private int currentSolutionPointer = 0;

	private boolean loaded = false;

	public SudokuUi() {
		init();
	}

	private void init() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setPreferredSize(new Dimension(1040, 600));

		frame.setLayout(new BorderLayout());
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(contentsPanel, BorderLayout.CENTER);
		
		titlePanel.add(browseButton);
		
		// browse button action listener
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fName = chooseFile();
				if (fName != "") {
					System.out.println("\nOpen " + fName);
					Main.readInputFile(fName);
					Main.solveAllGivenPuzzles();
					Main.writeOutputFile();
					displayCurrentPuzzle();
					// clear previous tables and print all stuff again
				}				
			}
		});

		contentsPanel.add(givenPanel, BorderLayout.WEST);
		contentsPanel.add(optionsPanel, BorderLayout.CENTER);
		contentsPanel.add(answersPanel, BorderLayout.EAST);

		givenPanel.add(currentPuzzleLabel);
		// contentsPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN,5));
		// givenPanel.setBorder(BorderFactory.createLineBorder(Color.RED,5));
		// answersPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,5));
		// optionsPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW,5));
		mainFrame.pack();
	}

	public void displayCurrentPuzzle() {
		currentPuzzleLabel.setText("Puzzle #"+Integer.toString(currentPuzzlePointer+1));

		Puzzle currentGivenPuzzle = Main.getGivenPuzzleAt(currentPuzzlePointer);
		currGivenTable = new JTable(currentGivenPuzzle.getSize(), currentGivenPuzzle.getSize()); 

		// add values in table
		for (int r = 0; r < currentGivenPuzzle.getSize(); r++) {
			for (int c = 0; c < currentGivenPuzzle.getSize(); c++) {
				currGivenTable.getModel().setValueAt(currentGivenPuzzle.getPuzzle()[r][c], r, c);
				currGivenTable.getColumnModel().getColumn(c).setPreferredWidth(30);
			}
		}

		givenPanel.add(currGivenTable);
	}

	public void displayCurrentSolution(Puzzle currentPuzzle, int index) {

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
			loaded = true;
		}
		return fileName;
	}
}