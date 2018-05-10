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
	private JPanel sudokuPanel1 = new JPanel();
	private JPanel sudokuPanel2 = new JPanel(new BorderLayout());
	private JPanel optionsPanel1 = new JPanel();
	private JPanel optionsPanel2 = new JPanel();
	private JPanel optionsPanel3 = new JPanel();
	private JPanel browseButtonPanel = new JPanel();
	private JPanel solutionRadioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel solutionLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel solutionButtonPanel = new JPanel();
	private JPanel restartButtonPanel = new JPanel();

	// private JPanel givenPanel = new JPanel();
	// private JPanel answersPanel = new JPanel();

	private JButton browseButton = new JButton("Open Input File");
	private JButton restartButton = new JButton("Restart Grid");
	private JButton solutionButton = new JButton("Show Solution");

	private JRadioButton solutionRegular = new JRadioButton("Sudoku Regular");
	private JRadioButton solutionX = new JRadioButton("Sudoku X");
	private JRadioButton solutionY = new JRadioButton("Sudoku Y");
	private JRadioButton solutionXY = new JRadioButton("Sudoku XY");

	private JLabel currentPuzzleLabel = new JLabel("");
	private JLabel solutionNoRegular = new JLabel("Sudoku Solutions: ");
	private JLabel solutionNoX = new JLabel("Sudoku X Solutions: ");
	private JLabel solutionNoY = new JLabel("Sudoku Y Solutions: ");
	private JLabel solutionNoXY = new JLabel("Sudoku XY Solutions: ");
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
		mainFrame.setPreferredSize(new Dimension(1000, 600));

		frame.setLayout(new BorderLayout());
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(optionsPanel1, BorderLayout.WEST);
		frame.add(sudokuPanel1, BorderLayout.EAST);

		titlePanel.setBackground(Color.BLACK);
		titlePanel.setPreferredSize(new Dimension(1000, 100));

		optionsPanel1.setLayout(new BorderLayout());
		optionsPanel1.setBackground(Color.GRAY);
		optionsPanel1.setPreferredSize(new Dimension(190, 500));
		optionsPanel1.add(optionsPanel2, BorderLayout.NORTH);
		optionsPanel1.add(optionsPanel3, BorderLayout.SOUTH);

		optionsPanel2.setLayout(new BorderLayout());
		optionsPanel2.setOpaque(false);
		optionsPanel2.setPreferredSize(new Dimension(190, 180));
		optionsPanel2.add(browseButtonPanel, BorderLayout.NORTH);
		optionsPanel2.add(solutionLabelPanel);
		optionsPanel2.add(restartButtonPanel, BorderLayout.SOUTH);

		optionsPanel3.setLayout(new BorderLayout());
		optionsPanel3.setOpaque(false);
		optionsPanel3.setPreferredSize(new Dimension(190, 180));
		optionsPanel3.add(solutionRadioPanel);
		optionsPanel3.add(solutionButtonPanel, BorderLayout.SOUTH);

		browseButtonPanel.add(browseButton);
		browseButtonPanel.setOpaque(false);		

		solutionLabelPanel.add(solutionNoRegular);
		solutionLabelPanel.add(solutionNoX);
		solutionLabelPanel.add(solutionNoY);
		solutionLabelPanel.add(solutionNoXY);
		solutionLabelPanel.setOpaque(false);

		restartButtonPanel.add(restartButton);
		restartButtonPanel.setOpaque(false);

		solutionRadioPanel.add(solutionRegular);
		solutionRadioPanel.add(solutionX);
		solutionRadioPanel.add(solutionY);
		solutionRadioPanel.add(solutionXY);
		solutionRadioPanel.setOpaque(false);

		solutionRegular.setOpaque(false);
		solutionRegular.setForeground(Color.WHITE);		
		solutionX.setOpaque(false);
		solutionX.setForeground(Color.WHITE);
		solutionY.setOpaque(false);
		solutionY.setForeground(Color.WHITE);
		solutionXY.setOpaque(false);
		solutionXY.setForeground(Color.WHITE);

		solutionButtonPanel.add(solutionButton);
		solutionButtonPanel.setOpaque(false);

		
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

		sudokuPanel1.setPreferredSize(new Dimension(810, 500));
		sudokuPanel1.add(currentPuzzleLabel);
		// contentsPanel.add(givenPanel, BorderLayout.WEST);
		// contentsPanel.add(optionsPanel, BorderLayout.CENTER);
		// contentsPanel.add(answersPanel, BorderLayout.EAST);
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


		sudokuPanel1.add(currGivenTable);
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