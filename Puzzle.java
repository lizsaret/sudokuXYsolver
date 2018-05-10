package sudokuXYsolver;

import java.io.*;
import java.util.*;

public class Puzzle {
	private int size = 0;
	private int subgrid = 0;
	private int noOfSolutionsR = 0;
	private int noOfSolutionsX = 0;
	private int noOfSolutionsY = 0;
	private int noOfSolutionsXY = 0;
	private int[][] sudoku;
	private LinkedList<Integer[][]> solutionsR = new LinkedList<Integer[][]>();
	private LinkedList<Integer[][]> solutionsX = new LinkedList<Integer[][]>();
	private LinkedList<Integer[][]> solutionsY = new LinkedList<Integer[][]>();
	private LinkedList<Integer[][]> solutionsXY = new LinkedList<Integer[][]>();
	private boolean solved = false;

	public Puzzle(int[][] puzzleSudoku, int puzzleSize, int puzzleSubgrid) {
		this.size = puzzleSize;
		this.subgrid = puzzleSubgrid;
		this.sudoku = new int[size][size];
		for (int r = 0; r < size; r++){
            for (int c = 0; c < size; c++){
                this.sudoku[r][c] = puzzleSudoku[r][c];
            }
        } 
	}

	// checks if this puzzle is full
	private boolean puzzleFull() {
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (sudoku[r][c] == 0)
					return false;		
			}
		}
		return true;
	}

	private boolean isRightDiagonal(int row, int col) {
		if (row == col) return true;
		else return false;
	}

	private boolean isLeftDiagonal(int row, int col) {
		if (row + col == size - 1) return true;
		else return false;		
	}

	// places all possible values in unappeared[] and returns count
	private int findPossibleValues(int row, int col, int[] unappeared, int xyFlag) {
		int count = 0;
		int[] options = new int[size+1];
		
		// check if appeared in current row
		for (int c = 0; c < size; c++)
			options[sudoku[row][c]] = 1;

		// check if appeared in current column
		for (int r = 0; r < size; r++) 
			options[sudoku[r][col]] = 1;

		// check options for sudokuX
		if (xyFlag == 1 || xyFlag ==3) {
			if (isRightDiagonal(row, col) == true) {
				for(int r = 0; r < size; r++) 
					options[sudoku[r][r]] = 1;
			}

			if (isLeftDiagonal(row, col) == true) {
				int c = size - 1;
				for (int r = 0; r < size; r++) 
					options[sudoku[r][c--]] = 1;
			}
		}

		// check options for sudokuY
		if (xyFlag == 2 || xyFlag == 3) {
			if (size%2 != 0) {
				if (isRightDiagonal(row, col) == true) {
					for (int r = 0; r < (size/2); r++)
						options[sudoku[r][r]] = 1;

					for (int r = (size/2); r < size; r++)
						options[sudoku[r][(size/2)]] = 1;
				}

				if (isLeftDiagonal(row, col) == true) {
					int c = size - 1;

					for (int r = 0; r < (size/2); r++) 
						options[sudoku[r][c--]] = 1;

					for (int r = (size/2); r < size; r++)
						options[sudoku[r][(size/2)]] = 1;
				}
			}
		}

		// check if appeared in current block
		row = (row/subgrid)*subgrid;
		col = (col/subgrid)*subgrid;
		for (int r = row; r < (row+subgrid); r++) {
			for (int c = col; c < (col +subgrid); c++) {
				options[sudoku[r][c]] = 1;
			}
		}

		// place safe values inside array unappeared[]
		for (int r = size; r >= 1; r--) {
			if (options[r] == 0) 
				unappeared[count++] = r;
		}

		return count;
	}

	// returns number of blanks in the puzzle
	private int countZeros() {
		int zeroCount = 0;

		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (sudoku[r][c] == 0)
					zeroCount++;
			}
		}
		return zeroCount;
	}

	// will check if a number is still valid for a given position
	private boolean isValid(int row, int col, int value, int xyFlag) {
		int[] temp = new int[size];
		int count;

		count = findPossibleValues(row, col, temp, xyFlag);

		for (int i = 0; i < count; i++)
			if (value == temp[i]) return true;
		
		return false;
	}

	// main function that will solve the puzzle through backtracking algorithm
	public void solveCurrentPuzzle(int xyFlag) {
		int zeroCount = countZeros();
		int[][] options = new int[zeroCount+2][size+2];
		int[] nopts = new int[zeroCount+2];
		int start, move, count;
		Blank[] blankVals = new Blank[zeroCount+2];

		// noOfSolutions = 0;
		// solutions.clear();
		// store the locations and possible values of empty cells inside an array
		count = 1;
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (sudoku[r][c] == 0) {
					int[] temp = new int[size];
					blankVals[count++] = new Blank(r, c, findPossibleValues(r, c, temp, xyFlag), temp);
				}
			}
		}

		// perform backtracking to find all possible solutions to the given sudoku puzzle
		move = start = 0;
		nopts[start] = 1;

		while (nopts[start] > 0) {
			if (nopts[move] > 0) {
				move++;
				nopts[move] = 0;

				if (move == zeroCount+1) {
					if (puzzleFull() == true) {
						// solution found
						// noOfSolutions += 1;
						solved = true;
						// System.out.println("solution number: "+noOfSolutions);
						// printPuzzle();

						// add current solution to list of solutions
						Integer[][] answer = new Integer[size][size];
						for (int r = 0; r < size; r++) {
							for (int c = 0; c < size; c++)
								answer[r][c] = sudoku[r][c];
						}
						if (xyFlag == 0) solutionsR.add(answer);
						else if (xyFlag == 1) solutionsX.add(answer);
						else if (xyFlag == 2) solutionsY.add(answer);
						else if (xyFlag == 3) solutionsXY.add(answer);
					}
				} else {
					// populate stacks
					boolean updateGridFlag = false;
					// push values that are still valid for the given position
					for (int i = 0; i < blankVals[move].getPossValsCount(); i++) {
						if (isValid(blankVals[move].getRow(), blankVals[move].getCol(), blankVals[move].getValueAt(i), xyFlag)) {
							nopts[move]++;
							options[move][nopts[move]] = blankVals[move].getValueAt(i);
							updateGridFlag = true;
						}
						if (updateGridFlag == true) {
							sudoku[blankVals[move].getRow()][blankVals[move].getCol()] = options[move][nopts[move]];
						}
					}
				}
			} else {
				// backtrack
				move--;
				nopts[move]--;
				if (move > 0) sudoku[blankVals[move].getRow()][blankVals[move].getCol()] = options[move][nopts[move]];
			}
		}
	}

	// prints this puzzle
	public void printPuzzle() {
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				System.out.print(this.sudoku[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println("----------------------------------------");
	}

	public void printSolutions(int xyFlag) {
		LinkedList<Integer[][]> solutions = new LinkedList<Integer[][]>();

		if (xyFlag == 0) solutions.addAll(solutionsR);
		else if (xyFlag == 1) solutions.addAll(solutionsX);
		else if (xyFlag == 2) solutions.addAll(solutionsY);
		else if (xyFlag == 3) solutions.addAll(solutionsXY);
							
		for (int i = 0; i < solutions.size(); i++) {
			System.out.println("Solution number: "+(i+1));
			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++)
					System.out.print((solutions.get(i))[r][c]+" ");
				System.out.println();
			}
		}
	}

	public Integer[][] getSolutionAt(int xyFlag, int index) {
		LinkedList<Integer[][]> solutions = new LinkedList<Integer[][]>();

		if (xyFlag == 0) solutions.addAll(solutionsR);
		else if (xyFlag == 1) solutions.addAll(solutionsX);
		else if (xyFlag == 2) solutions.addAll(solutionsY);
		else if (xyFlag == 3) solutions.addAll(solutionsXY);

		if (index <= solutions.size()) 
			return solutions.get(index);
		else 
			return solutions.get(solutions.size());
	}
	
	public int getSolutionsCount(int xyFlag) {
		if (xyFlag == 0) return this.solutionsR.size();
		else if (xyFlag == 1) return this.solutionsX.size();
		else if (xyFlag == 2) return this.solutionsY.size();
		else return this.solutionsXY.size();
	}

	public int getSize() {
		return this.size;
	}

	public LinkedList<Integer[][]> getSolutions(int xyFlag) {
		if (xyFlag == 0) return this.solutionsR;
		else if (xyFlag == 1) return this.solutionsX;
		else if (xyFlag == 2) return this.solutionsY;
		else return this.solutionsXY;
	} 

	public int[][] getPuzzle() {
		return this.sudoku;
	}
}