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
	private JPanel sudokuPanel2 = new JPanel();
	private JPanel currGivenTable;
	private JPanel optionsPanel1 = new JPanel();
	private JPanel optionsPanel2 = new JPanel();
	private JPanel optionsPanel3 = new JPanel();
	private JPanel browseButtonPanel = new JPanel();
	private JPanel solutionRadioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel solutionLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel puzzleLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel puzzleButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel solutionButtonPanel = new JPanel();
	private JPanel restartButtonPanel = new JPanel();
	private JPanel puzzlePanel = new JPanel();
	private JPanel nextPrevSolution = new JPanel(new BorderLayout());

	// private JPanel givenPanel = new JPanel();
	// private JPanel answersPanel = new JPanel();

	private TextField[][] textfieldHolder;

	private JButton browseButton = new JButton("Open Input File");
	private JButton restartButton = new JButton("Restart Grid");
	private JButton solutionButton = new JButton("Show Solution");
	private JButton puzzleButton = new JButton("Next Puzzle");
	private JButton nextSolutionButton = new JButton("Next Solution");
	private JButton prevSolutionButton = new JButton("Previous Solution");


	private JRadioButton solutionRegular = new JRadioButton("Sudoku Regular");
	private JRadioButton solutionX = new JRadioButton("Sudoku X");
	private JRadioButton solutionY = new JRadioButton("Sudoku Y");
	private JRadioButton solutionXY = new JRadioButton("Sudoku XY");
	private ButtonGroup solutionGroup = new ButtonGroup();


	private JLabel currentPuzzleLabel = new JLabel("");
	private JLabel solutionNoRegular = new JLabel("Sudoku Solutions: ");
	private JLabel solutionNoX = new JLabel("Sudoku X Solutions: ");
	private JLabel solutionNoY = new JLabel("Sudoku Y Solutions: ");
	private JLabel solutionNoXY = new JLabel("Sudoku XY Solutions: ");

	private int currentPuzzlePointer = 0;
	private int currentSolutionPointer = 0;
	private int solutionFlag = 0;


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

		nextSolutionButton.setBorderPainted(false);
		prevSolutionButton.setBorderPainted(false);

		nextSolutionButton.setVisible(false);
		prevSolutionButton.setVisible(false);

		nextPrevSolution.add(nextSolutionButton, BorderLayout.EAST);
		nextPrevSolution.add(prevSolutionButton, BorderLayout.WEST);

		sudokuPanel1.setLayout(new BorderLayout());
		sudokuPanel1.setPreferredSize(new Dimension(810, 500));
		sudokuPanel1.add(puzzlePanel, BorderLayout.NORTH);
		sudokuPanel1.add(sudokuPanel2, BorderLayout.CENTER);
		sudokuPanel1.add(nextPrevSolution, BorderLayout.SOUTH);

		puzzlePanel.add(puzzleLabelPanel);
		puzzlePanel.add(puzzleButtonPanel);

		puzzleLabelPanel.add(currentPuzzleLabel);
		puzzleButtonPanel.add(puzzleButton);

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
		solutionRegular.setSelected(true);		
		solutionX.setOpaque(false);
		solutionX.setForeground(Color.WHITE);
		solutionY.setOpaque(false);
		solutionY.setForeground(Color.WHITE);
		solutionXY.setOpaque(false);
		solutionXY.setForeground(Color.WHITE);

		solutionGroup.add(solutionRegular);
		solutionGroup.add(solutionX);
		solutionGroup.add(solutionY);
		solutionGroup.add(solutionXY);

		solutionButtonPanel.add(solutionButton);
		solutionButtonPanel.setOpaque(false);

		solutionRegular.addItemListener(new ItemListener() {
	       	public void itemStateChanged(ItemEvent e) { 
	       		if(e.getStateChange() == 1) solutionFlag = 0;
	        }           
	    });

	    solutionX.addItemListener(new ItemListener() {
	       	public void itemStateChanged(ItemEvent e) { 
	       		if(e.getStateChange() == 1) solutionFlag = 1;
	        }           
	    });

	    solutionY.addItemListener(new ItemListener() {
	       	public void itemStateChanged(ItemEvent e) { 
	       		if(e.getStateChange() == 1) solutionFlag = 2;
	        }           
	    });

	    solutionXY.addItemListener(new ItemListener() {
	       	public void itemStateChanged(ItemEvent e) { 
	       		if(e.getStateChange() == 1) solutionFlag = 3;
	        }           
	    });

		// browse button action listener
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fName = chooseFile();
				if (fName != "") {
					System.out.println("\nOpen " + fName);
					Main.readInputFile(fName);
					Main.solveAllGivenPuzzles();
					Main.writeOutputFile();
					displayCurrentPuzzle1();
					displayNoOfSolutions();
				}				
			}
		});

		solutionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayCurrentSolution();
				currentSolutionPointer = 0;
				nextSolutionButton.setVisible(true);
				prevSolutionButton.setVisible(true);
			}
		});

		nextSolutionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentSolutionPointer < Main.getGivenPuzzleAt(currentPuzzlePointer).getSolutionsCount(solutionFlag))
					currentSolutionPointer++;
				displayCurrentSolution();
			}
		});
		
		prevSolutionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentSolutionPointer > 0)
					currentSolutionPointer--;
				displayCurrentSolution();
			}
		});

		mainFrame.pack();
	}

	// public void displayCurrentPuzzle() {
	// 	currentPuzzleLabel.setText("Puzzle #"+Integer.toString(currentPuzzlePointer+1));

	// 	Puzzle currentGivenPuzzle = Main.getGivenPuzzleAt(currentPuzzlePointer);
	// 	currGivenTable = new JTable(currentGivenPuzzle.getSize(), currentGivenPuzzle.getSize()); 

	// 	// add values in table
	// 	for (int r = 0; r < currentGivenPuzzle.getSize(); r++) {
	// 		for (int c = 0; c < currentGivenPuzzle.getSize(); c++) {

	// 			// currGivenTable.getModel().setValueAt(currentGivenPuzzle.getPuzzle()[r][c], r, c);
	// 			// currGivenTable.getColumnModel().getColumn(c).setPreferredWidth(30);
	// 		}
	// 	}


	// 	sudokuPanel1.add(currGivenTable, BorderLayout.CENTER);
	// }

	public void displayNoOfSolutions() {
		Puzzle currentGivenPuzzle = Main.getGivenPuzzleAt(currentPuzzlePointer);

		solutionNoRegular.setText("Sudoku Solutions: "+Integer.toString(currentGivenPuzzle.getSolutionsCount(0)));
		solutionNoX.setText("Sudoku X Solutions: "+Integer.toString(currentGivenPuzzle.getSolutionsCount(1)));
		solutionNoY.setText("Sudoku Y Solutions: "+Integer.toString(currentGivenPuzzle.getSolutionsCount(2)));
		solutionNoXY.setText("Sudoku XY Solutions: "+Integer.toString(currentGivenPuzzle.getSolutionsCount(3)));
	}

	public void displayUnfilledGrid() {
		Puzzle currentGivenPuzzle = Main.getGivenPuzzleAt(currentPuzzlePointer);

		for (int r = 0; r < currentGivenPuzzle.getSize(); r++) {
			for (int c = 0; c < currentGivenPuzzle.getSize(); c++) {
				textfieldHolder[r][c].setText(" ");

				if (currentGivenPuzzle.getPuzzle()[r][c] != 0) {
					textfieldHolder[r][c].setText(Integer.toString(currentGivenPuzzle.getPuzzle()[r][c]));
					textfieldHolder[r][c].setEnabled(false);		
				} 
			}
		}
	}

	public void displayCurrentSolution() {
		Puzzle currentGivenPuzzle = Main.getGivenPuzzleAt(currentPuzzlePointer);
		if (currentGivenPuzzle.getSolutionsCount(solutionFlag) != 0) {
			Integer[][] currentSolution = currentGivenPuzzle.getSolutionAt(solutionFlag, currentSolutionPointer);

			for (int r = 0; r < currentGivenPuzzle.getSize(); r++) {
				for (int c = 0; c < currentGivenPuzzle.getSize(); c++) {
					textfieldHolder[r][c].setText(Integer.toString(currentSolution[r][c]));
					textfieldHolder[r][c].setEnabled(false);
				}
			}						
		} else {
			System.out.println("NO SOLUTION!! DISPLAY POPUP");
			displayUnfilledGrid();
			JOptionPane.showMessageDialog(frame,
		    "No possible solution for current puzzle.",
		    "Message",
		    JOptionPane.ERROR_MESSAGE);
		}
	}

	public void displayCurrentPuzzle1() {
		currentPuzzleLabel.setText("Puzzle #"+Integer.toString(currentPuzzlePointer+1));

		Puzzle currentGivenPuzzle = Main.getGivenPuzzleAt(currentPuzzlePointer);
		currGivenTable = new JPanel(new GridLayout(currentGivenPuzzle.getSize(), currentGivenPuzzle.getSize()));
		currGivenTable.setPreferredSize(new Dimension(400, 400));

		textfieldHolder = new TextField[currentGivenPuzzle.getSize()][currentGivenPuzzle.getSize()];
		// add values in table
		for (int r = 0; r < currentGivenPuzzle.getSize(); r++) {
			for (int c = 0; c < currentGivenPuzzle.getSize(); c++) {
				textfieldHolder[r][c] = new TextField("");
				if (currentGivenPuzzle.getPuzzle()[r][c] != 0) {
					textfieldHolder[r][c].setText(Integer.toString(currentGivenPuzzle.getPuzzle()[r][c]));
					textfieldHolder[r][c].setEnabled(false);		
				}
				currGivenTable.add(textfieldHolder[r][c]); 
			}
		}

		sudokuPanel2.add(currGivenTable);
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