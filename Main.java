package sudokuXYsolver;

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Main {

	static LinkedList<Puzzle> givenPuzzles = new LinkedList<Puzzle>();
	static SudokuUi sudokuGui = new SudokuUi();
	static int noOfPuzzles = 0;

	public static void main (String[] args) {
		sudokuGui.showGui();
	}

	public static void readInputFile(String file) {
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			noOfPuzzles = Integer.parseInt(reader.readLine().trim());

			// save all given puzzles and save it to an arraylist of puzzles
			for (int puzzle = 0; puzzle < noOfPuzzles; puzzle++) {
				int subgrid = Integer.parseInt(reader.readLine().trim());
				int sudokuSize = subgrid*subgrid;
				int[][] tempSudoku = new int[sudokuSize][sudokuSize];

				for (int row = 0; row < sudokuSize; row++) {
					String tempLine[] = reader.readLine().split(" ");
					for (int col = 0; col < sudokuSize; col++) {
						tempSudoku[row][col] = Integer.parseInt(tempLine[col]);
					} 
				}
				// add current sudoku to arraylist
				givenPuzzles.add(new Puzzle(tempSudoku, sudokuSize, subgrid));
			}
			reader.close();
		}catch(Exception err) {
			System.out.println(err.toString());
		}
	}

	public static void solveAllGivenPuzzles() {
		for (int p = 0; p < givenPuzzles.size(); p++) {
			System.out.println("puzzle #"+(p+1));
			givenPuzzles.get(p).printPuzzle();

			givenPuzzles.get(p).solveCurrentPuzzle(0);
			givenPuzzles.get(p).solveCurrentPuzzle(1);
			givenPuzzles.get(p).solveCurrentPuzzle(2);
			givenPuzzles.get(p).solveCurrentPuzzle(3);
			
			System.out.println("\n---------REGULAR SUDOKU---------\n");
			givenPuzzles.get(p).printSolutions(0);
			System.out.println("\n---------SUDOKU X---------\n");
			givenPuzzles.get(p).printSolutions(1);
			System.out.println("\n---------SUDOKU Y---------\n");
			givenPuzzles.get(p).printSolutions(2);
			System.out.println("\n---------SUDOKU XY---------\n");
			givenPuzzles.get(p).printSolutions(3);

			System.out.println("regular sudoku: "+givenPuzzles.get(p).getSolutionsCount(0));
			System.out.println("sudoku X: "+givenPuzzles.get(p).getSolutionsCount(1));
			System.out.println("sudoku Y: "+givenPuzzles.get(p).getSolutionsCount(2));
			System.out.println("sudoku XY: "+givenPuzzles.get(p).getSolutionsCount(3));
		}		
	}

	public static void printAllGivenPuzzles() {
		for (int p = 0; p < givenPuzzles.size(); p++) {
			System.out.println("puzzle #"+(p+1));
			givenPuzzles.get(p).printPuzzle();
		}
	}

	public static void writeOutputFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

			for (int p = 0; p < givenPuzzles.size(); p++) {
				writer.write("puzzle #"+(p+1));
				Puzzle currPuzzle = givenPuzzles.get(p); 
				// write current puzzle
				writer.write("\n");
				for (int r = 0; r < currPuzzle.getSize(); r++) {
					for (int c = 0; c < currPuzzle.getSize(); c++) 
						writer.write((currPuzzle.getPuzzle())[r][c]+" ");
					writer.write("\n");
				}

				// write regular sudoku solutions
				for (int a = 0; a < currPuzzle.getSolutionsCount(0); a++) {
					Integer[][] currAnswer = currPuzzle.getSolutions(0).get(a);
					writer.write("puzzle #"+(p+1)+" regular sudoku solution #"+(a+1));
					writer.write("\n");
					for (int r = 0; r < currPuzzle.getSize(); r++) {
						for (int c = 0; c < currPuzzle.getSize(); c++) 
							writer.write(currAnswer[r][c]+" ");
						writer.write("\n");
					}					
				}

				// write sudokuX solutions
				for (int a = 0; a < currPuzzle.getSolutionsCount(1); a++) {
					Integer[][] currAnswer = currPuzzle.getSolutions(1).get(a);
					writer.write("puzzle #"+(p+1)+" sudokuX solution #"+(a+1));
					writer.write("\n");
					for (int r = 0; r < currPuzzle.getSize(); r++) {
						for (int c = 0; c < currPuzzle.getSize(); c++) 
							writer.write(currAnswer[r][c]+" ");
						writer.write("\n");
					}					
				}


				// write sudokuY solutions
				for (int a = 0; a < currPuzzle.getSolutionsCount(2); a++) {
					Integer[][] currAnswer = currPuzzle.getSolutions(2).get(a);
					writer.write("puzzle #"+(p+1)+" sudokuY solution #"+(a+1));
					writer.write("\n");
					for (int r = 0; r < currPuzzle.getSize(); r++) {
						for (int c = 0; c < currPuzzle.getSize(); c++) 
							writer.write(currAnswer[r][c]+" ");
						writer.write("\n");
					}					
				}


				// write sudokuX3 solutions
				for (int a = 0; a < currPuzzle.getSolutionsCount(3); a++) {
					Integer[][] currAnswer = currPuzzle.getSolutions(3).get(a);
					writer.write("puzzle #"+(p+1)+" sudokuXY solution #"+(a+1));
					writer.write("\n");
					for (int r = 0; r < currPuzzle.getSize(); r++) {
						for (int c = 0; c < currPuzzle.getSize(); c++) 
							writer.write(currAnswer[r][c]+" ");
						writer.write("\n");
					}					
				}
				writer.write("\nregular sudoku: "+givenPuzzles.get(p).getSolutionsCount(0));
				writer.write("\nsudoku X: "+givenPuzzles.get(p).getSolutionsCount(1));
				writer.write("\nsudoku Y: "+givenPuzzles.get(p).getSolutionsCount(2));
				writer.write("\nsudoku XY: "+givenPuzzles.get(p).getSolutionsCount(3));
			}

			writer.close();
		}catch(Exception err){
			System.out.println(err.toString());
		}
	}

	public static Puzzle getGivenPuzzleAt(int index) {
		return givenPuzzles.get(index);
	}
}