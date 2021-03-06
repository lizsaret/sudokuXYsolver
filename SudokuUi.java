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
	private JPanel puzzleLabelPanel = new JPanel();
	private JPanel prevPuzzleButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel nextPuzzleButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel returnPuzzleButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel solutionButtonPanel = new JPanel();
	private JPanel restartButtonPanel = new JPanel();
	private JPanel submitButtonPanel = new JPanel();
	private JPanel nextSolutionButtonPanel = new JPanel();
	private JPanel prevSolutionButtonPanel = new JPanel();
	private JPanel puzzlePanel = new JPanel();
	private JPanel puzzleBottomPanel = new JPanel();

	private JTextField[][] textfieldHolder;

	private JButton browseButton = new JButton("Open Input File");
	private JButton restartButton = new JButton("Restart Grid");
	private JButton solutionButton = new JButton("Show Solution");
	private JButton prevPuzzleButton = new JButton("Previous Puzzle");
	private JButton nextPuzzleButton = new JButton("Next Puzzle");
	private JButton returnPuzzleButton = new JButton("Return");
	private JButton nextSolutionButton = new JButton("Next Solution");
	private JButton prevSolutionButton = new JButton("Previous Solution");
	private JButton submitButton = new JButton("Submit");

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
	private JLabel currentSolutionLabel = new JLabel("");

	private int currentPuzzlePointer = 0;
	private int currentSolutionPointer = 0;
	private int solutionFlag = 0;
	private int answerBox = 0;


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

		sudokuPanel1.setLayout(new BorderLayout());
		sudokuPanel1.setPreferredSize(new Dimension(810, 500));
		sudokuPanel1.add(sudokuPanel2, BorderLayout.CENTER);
		sudokuPanel1.add(puzzleBottomPanel, BorderLayout.SOUTH);
		
		puzzlePanel.add(prevPuzzleButtonPanel);
		puzzlePanel.add(puzzleLabelPanel);
		puzzlePanel.add(nextPuzzleButtonPanel);
		puzzleLabelPanel.add(currentPuzzleLabel);

		prevPuzzleButtonPanel.add(prevPuzzleButton);
		nextPuzzleButtonPanel.add(nextPuzzleButton);
		prevPuzzleButton.setEnabled(false);
		prevSolutionButton.setEnabled(false);

		returnPuzzleButtonPanel.add(returnPuzzleButton);
		puzzlePanel.add(returnPuzzleButtonPanel);
		returnPuzzleButtonPanel.setVisible(false);

		submitButtonPanel.add(submitButton);
		restartButtonPanel.add(restartButton);
		puzzleBottomPanel.add(restartButtonPanel);
		puzzleBottomPanel.add(submitButtonPanel);

		nextSolutionButtonPanel.add(nextSolutionButton);
		prevSolutionButtonPanel.add(prevSolutionButton);
		puzzleBottomPanel.add(prevSolutionButtonPanel);
		puzzleBottomPanel.add(currentSolutionLabel);
		puzzleBottomPanel.add(nextSolutionButtonPanel);

		puzzleBottomPanel.setVisible(false);
		puzzleBottomPanel.setOpaque(false);

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

		solutionNoRegular.setOpaque(false);
		solutionNoRegular.setForeground(Color.WHITE);			
		solutionNoX.setOpaque(false);
		solutionNoX.setForeground(Color.WHITE);
		solutionNoY.setOpaque(false);
		solutionNoY.setForeground(Color.WHITE);
		solutionNoXY.setOpaque(false);
		solutionNoXY.setForeground(Color.WHITE);

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

		browseButton.setBackground(Color.BLACK);
		browseButton.setForeground(Color.WHITE);
		restartButton.setBackground(Color.BLACK);
		restartButton.setForeground(Color.WHITE);
		solutionButton.setBackground(Color.BLACK);
		solutionButton.setForeground(Color.WHITE);
		prevPuzzleButton.setBackground(Color.BLACK);
		prevPuzzleButton.setForeground(Color.WHITE);
		nextPuzzleButton.setBackground(Color.BLACK);
		nextPuzzleButton.setForeground(Color.WHITE);
		returnPuzzleButton.setBackground(Color.BLACK);
		returnPuzzleButton.setForeground(Color.WHITE);
		nextSolutionButton.setBackground(Color.BLACK);
		nextSolutionButton.setForeground(Color.WHITE);
		prevSolutionButton.setBackground(Color.BLACK);
		prevSolutionButton.setForeground(Color.WHITE);
		submitButton.setBackground(Color.BLACK);
		submitButton.setForeground(Color.WHITE);

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

					sudokuPanel1.add(puzzlePanel, BorderLayout.NORTH);
					puzzleBottomPanel.setVisible(true);
					unsetSolutionDisplay();
					displayCurrentPuzzle1();
					displayNoOfSolutions();
				}				
			}
		});

		solutionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayCurrentSolution();
				currentSolutionPointer = 0;
				if (Main.getGivenPuzzleAt(currentPuzzlePointer).getSolutionsCount(solutionFlag) > 0) {
					currentSolutionLabel.setText("Solution "+Integer.toString(currentSolutionPointer+1)+"/"
						+Integer.toString(Main.getGivenPuzzleAt(currentPuzzlePointer).getSolutionsCount(solutionFlag)));
					setSolutionDisplay();
				}
			}
		});

		nextSolutionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println(Main.getGivenPuzzleAt(currentPuzzlePointer).getSolutionsCount(solutionFlag));
				if (currentSolutionPointer < Main.getGivenPuzzleAt(currentPuzzlePointer).getSolutionsCount(solutionFlag) - 1) {
					currentSolutionPointer++;
					currentSolutionLabel.setText("Solution "+Integer.toString(currentSolutionPointer+1)+"/"
					+Integer.toString(Main.getGivenPuzzleAt(currentPuzzlePointer).getSolutionsCount(solutionFlag)));
					displayCurrentSolution();
					prevSolutionButton.setEnabled(true);
					nextSolutionButton.setEnabled(true);
				}

				if (currentSolutionPointer == Main.getGivenPuzzleAt(currentPuzzlePointer).getSolutionsCount(solutionFlag) - 1) {
					nextSolutionButton.setEnabled(false);
				}
			}
		});
		
		prevSolutionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentSolutionPointer > 0) {
					currentSolutionPointer--;
					currentSolutionLabel.setText("Solution "+Integer.toString(currentSolutionPointer+1)+"/"
					+Integer.toString(Main.getGivenPuzzleAt(currentPuzzlePointer).getSolutionsCount(solutionFlag)));
					displayCurrentSolution();
					prevSolutionButton.setEnabled(true);
					nextSolutionButton.setEnabled(true);
				}

				if (currentSolutionPointer == 0) {
					prevSolutionButton.setEnabled(false);
				}
			}
		});

		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayUnfilledGrid();
			}
		});

		nextPuzzleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentPuzzlePointer < Main.getPuzzleCount() - 1) {
					currentSolutionPointer = 0;
					currentPuzzlePointer++;
					sudokuPanel2.removeAll();
					displayCurrentPuzzle1();
					displayNoOfSolutions();
					prevPuzzleButton.setEnabled(true);
					nextPuzzleButton.setEnabled(true);
				} 

				if (currentPuzzlePointer == Main.getPuzzleCount() - 1) {
					nextPuzzleButton.setEnabled(false);
				}

			}
		});

		prevPuzzleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentPuzzlePointer > 0) {
					currentSolutionPointer = 0;
					currentPuzzlePointer--;
					sudokuPanel2.removeAll();
					displayCurrentPuzzle1();
					displayNoOfSolutions();
					prevPuzzleButton.setEnabled(true);
					nextPuzzleButton.setEnabled(true);
				} 

				if (currentPuzzlePointer == 0) {
					prevPuzzleButton.setEnabled(false);
				}

			}
		});

		returnPuzzleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sudokuPanel2.removeAll();
				displayCurrentPuzzle1();
				unsetSolutionDisplay();
			}
		}); 

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkSolutionGrid();
			}
		});

		mainFrame.pack();
	}

	public void displayNoOfSolutions() {
		Puzzle currentGivenPuzzle = Main.getGivenPuzzleAt(currentPuzzlePointer);

		solutionNoRegular.setText("Sudoku Solutions: "+Integer.toString(currentGivenPuzzle.getSolutionsCount(0)));
		solutionNoX.setText("Sudoku X Solutions: "+Integer.toString(currentGivenPuzzle.getSolutionsCount(1)));
		solutionNoY.setText("Sudoku Y Solutions: "+Integer.toString(currentGivenPuzzle.getSolutionsCount(2)));
		solutionNoXY.setText("Sudoku XY Solutions: "+Integer.toString(currentGivenPuzzle.getSolutionsCount(3)));
	}

	public void displayCurrentSolution() {
		Puzzle currentGivenPuzzle = Main.getGivenPuzzleAt(currentPuzzlePointer);

		if (currentGivenPuzzle.getSolutionsCount(solutionFlag) != 0) {
			Integer[][] currentSolution = currentGivenPuzzle.getSolutionAt(solutionFlag, currentSolutionPointer);

			for (int r = 0; r < currentGivenPuzzle.getSize(); r++) {
				for (int c = 0; c < currentGivenPuzzle.getSize(); c++) {

					if (currentGivenPuzzle.getPuzzle()[r][c] == 0) {
						textfieldHolder[r][c].setText(Integer.toString(currentSolution[r][c]));
						textfieldHolder[r][c].setHorizontalAlignment(JTextField.CENTER);
						textfieldHolder[r][c].setEnabled(true);
						textfieldHolder[r][c].setEditable(false);		
						textfieldHolder[r][c].setBackground(Color.WHITE);
						textfieldHolder[r][c].setForeground(Color.BLACK);
					} else {
						textfieldHolder[r][c].setBackground(Color.BLACK);
						textfieldHolder[r][c].setForeground(Color.WHITE);
					}
				}
			}						
		} else {
			displayUnfilledGrid();
			unsetSolutionDisplay();
			JOptionPane.showMessageDialog(frame,
		    "No possible solution for current puzzle.",
		    "Message",
		    JOptionPane.ERROR_MESSAGE);
		}
	}

	public void displayCurrentPuzzle1() {
		currentPuzzleLabel.setText("Puzzle #"+Integer.toString(currentPuzzlePointer+1));
		Puzzle currentGivenPuzzle = Main.getGivenPuzzleAt(currentPuzzlePointer);
		answerBox = 0;

		currGivenTable = new JPanel(new GridLayout(currentGivenPuzzle.getSize(), currentGivenPuzzle.getSize()));
		currGivenTable.setPreferredSize(new Dimension(400, 400));
		currGivenTable.setBackground(Color.GRAY);
		currGivenTable.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));

		textfieldHolder = new JTextField[currentGivenPuzzle.getSize()][currentGivenPuzzle.getSize()];
		// add values in table
		for (int r = 0; r < currentGivenPuzzle.getSize(); r++) {
			for (int c = 0; c < currentGivenPuzzle.getSize(); c++) {
				textfieldHolder[r][c] = new JTextField("");
				textfieldHolder[r][c].setHorizontalAlignment(JTextField.CENTER);

				if ((c % currentGivenPuzzle.getSubgrid()) == 0 && c != 0) {
					textfieldHolder[r][c].setBorder(BorderFactory.createMatteBorder(1, 3, 1, 1, Color.GRAY));
					if ((r % currentGivenPuzzle.getSubgrid()) == 0 && r != 0) {
						textfieldHolder[r][c].setBorder(BorderFactory.createMatteBorder(3, 3, 1, 1, Color.GRAY));
					}
				} else if ((r % currentGivenPuzzle.getSubgrid()) == 0 && r != 0) {
					textfieldHolder[r][c].setBorder(BorderFactory.createMatteBorder(3, 1, 1, 1, Color.GRAY));
				} else {
					textfieldHolder[r][c].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
				}

				if (currentGivenPuzzle.getPuzzle()[r][c] != 0) {
					textfieldHolder[r][c].setText(Integer.toString(currentGivenPuzzle.getPuzzle()[r][c]));
					textfieldHolder[r][c].setEnabled(true);	
					textfieldHolder[r][c].setEditable(false);
					textfieldHolder[r][c].setBackground(Color.BLACK);
					textfieldHolder[r][c].setForeground(Color.WHITE);
				} else {
					answerBox++;
				}
				currGivenTable.add(textfieldHolder[r][c]); 
			}
		}

		sudokuPanel2.add(currGivenTable, BorderLayout.CENTER);
	}

	public void displayUnfilledGrid() {
		Puzzle currentGivenPuzzle = Main.getGivenPuzzleAt(currentPuzzlePointer);

		for (int r = 0; r < currentGivenPuzzle.getSize(); r++) {
			for (int c = 0; c < currentGivenPuzzle.getSize(); c++) {
				textfieldHolder[r][c].setText("");
				textfieldHolder[r][c].setHorizontalAlignment(JTextField.CENTER);

				if (currentGivenPuzzle.getPuzzle()[r][c] != 0) {
					textfieldHolder[r][c].setText(Integer.toString(currentGivenPuzzle.getPuzzle()[r][c]));
					textfieldHolder[r][c].setEnabled(true);
					textfieldHolder[r][c].setEditable(false);
					textfieldHolder[r][c].setBackground(Color.BLACK);
					textfieldHolder[r][c].setForeground(Color.WHITE);
				} else {
					textfieldHolder[r][c].setBackground(Color.WHITE);
					textfieldHolder[r][c].setForeground(Color.BLACK);
				}
			}
		}
	}

	public void unsetSolutionDisplay() {
			nextSolutionButtonPanel.setVisible(false);
			prevSolutionButtonPanel.setVisible(false);
			currentSolutionLabel.setVisible(false);
			returnPuzzleButtonPanel.setVisible(false);
			submitButtonPanel.setVisible(true);
			restartButtonPanel.setVisible(true);
			nextPuzzleButtonPanel.setVisible(true);
			prevPuzzleButtonPanel.setVisible(true);		
	}

	public void setSolutionDisplay() {
		Puzzle currentGivenPuzzle = Main.getGivenPuzzleAt(currentPuzzlePointer);

		if(Main.getGivenPuzzleAt(currentPuzzlePointer).getSolutionsCount(solutionFlag) == 1) {						
			prevSolutionButton.setEnabled(false);
			nextSolutionButton.setEnabled(false);
		} else {
			nextSolutionButton.setEnabled(true);
		}

		nextSolutionButtonPanel.setVisible(true);
		prevSolutionButtonPanel.setVisible(true);
		currentSolutionLabel.setVisible(true);
		returnPuzzleButtonPanel.setVisible(true);
		submitButtonPanel.setVisible(false);
		restartButtonPanel.setVisible(false);
		nextPuzzleButtonPanel.setVisible(false);
		prevPuzzleButtonPanel.setVisible(false);
	}

	public void checkSolutionGrid() {
		int answerChecker = 0;
		Puzzle currentGivenPuzzle = Main.getGivenPuzzleAt(currentPuzzlePointer);
		Puzzle answersGivenPuzzle = new Puzzle(currentGivenPuzzle.getPuzzle(), currentGivenPuzzle.getSize(), currentGivenPuzzle.getSubgrid());

		for (int r = 0; r < currentGivenPuzzle.getSize(); r++) {
			for (int c = 0; c < currentGivenPuzzle.getSize(); c++) {
				if (textfieldHolder[r][c].getText().equals("")) {
					textfieldHolder[r][c].setBackground(Color.decode("#ffb9b9"));
					answersGivenPuzzle.getPuzzle()[r][c] = 0;
				} else if (Integer.parseInt(textfieldHolder[r][c].getText()) == currentGivenPuzzle.getPuzzle()[r][c]) {
					continue;
				} else {
					int answer = Integer.parseInt(textfieldHolder[r][c].getText());
					
					if (answersGivenPuzzle.isValid(r, c, answer, solutionFlag) == true) {
						textfieldHolder[r][c].setBackground(Color.decode("#b5ffba"));
						answerChecker++;
					} else {
						textfieldHolder[r][c].setBackground(Color.decode("#ffb9b9"));
					}
					
					answersGivenPuzzle.getPuzzle()[r][c] = Integer.parseInt(textfieldHolder[r][c].getText());
				}
			}
		}
		System.out.println(answerBox);

		if (answerChecker == answerBox) {
			JOptionPane.showMessageDialog(frame,
		    "Congratulations, You Win!",
		    "Message",
		    JOptionPane.INFORMATION_MESSAGE);
		}
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