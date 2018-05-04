package sudokuXYsolver;

import java.io.*;
import java.util.*;

public class Puzzle {
	private int size = 0;
	private int subgrid = 0;
	private int noOfSolutions = 0;
	private int[][] sudoku;
	private LinkedList<Integer[][]> solutions = new LinkedList<Integer[][]>();
	private boolean solved = false;

	public Puzzle(int[][] puzzleSudoku, int puzzleSize, int puzzleSubgrid) {
		this.size = puzzleSize;
		this.sudoku = new int[size][size];
		for (int r = 0; r < size; r++){
            for (int c = 0; c < size; c++){
                this.sudoku[r][c] = puzzleSudoku[r][c];
            }
        } 
	}

	// checks if this puzzle is full
	private boolean puzzleFull() {

	}

	// places all possible values in unappeared[] and returns count
	private int findPossibleValues(int row, int col, int[] unappeared) {

	}

	// returns number of blanks in the puzzle
	private int countZeros() {

	}

	// will check if a number is still valid for a given position
	private boolean isValid(int row, int col, int value) {

	}

	// main function that will solve the puzzle through backtracking algorithm
	public void solveCurrentPuzzle() {

	}

	// prints this puzzle
	public void printPuzzle() {
		System.out.println("----------puzzle size: "+size+"----------");
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				System.out.print(this.sudoku[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println("----------------------------------------");
	}
}