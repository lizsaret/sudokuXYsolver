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
		// show gui to read file
		// read/open given file (save to arraylist)
		// solve all given puzzles
		// display outputs and write to file
		// writeOutputFile();
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

	public static void printAllGivenPuzzles() {
		for (int p = 0; p < givenPuzzles.size(); p++) {
			System.out.println("puzzle #"+(p+1));
			givenPuzzles.get(p).printPuzzle();
		}
	}

	public static void writeOutputFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

			// write all necessary shits to file

			writer.close();
		}catch(Exception err){
			System.out.println(err.toString());
		}
	}
}