package sudokuXYsolver;

import java.io.*;
import java.util.*;

public class Blank {
	private int row;
	private int col;
	private int possibleValuesCount;
	private int[] possibleValues;

	public Blank(int blankRow, int blankCol, int blankPossValsCount, int[] blankPossVals) {
		this.row = blankRow;
		this.col = blankCol;
		this.possibleValuesCount = blankPossValsCount;
		this.possibleValues = Arrays.copyOf(blankPossVals, blankPossValsCount);

		for (int i = 0; i < possibleValuesCount; i++)
			this.possibleValues[i] = blankPossVals[i];
	}

	public void setPossVals(int[] blankPossVals) {
		this.possibleValues = Arrays.copyOf(blankPossVals, possibleValuesCount);
	}

	public void setRowCol(int blankRow, int blankCol) {
		this.row = blankRow;
		this.col = blankCol;
	}

	public void setPossValsCount(int blankPossValsCount) {
		this.possibleValuesCount = blankPossValsCount;
	}

	public int[] getPossVals() {
		return this.possibleValues;
	}

	public int getRow() {
		return this.row;		
	}

	public int getCol() {
		return this.col;		
	}

	public int getPossValsCount() {
		return this.possibleValuesCount;		
	}

	public int getValueAt(int index) {
		return this.possibleValues[index];
	}
}