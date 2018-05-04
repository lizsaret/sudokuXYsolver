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
		this.possibleValues = new int[blankPossValsCount+2];

		for (int i = 0; i < possibleValuesCount; i++)
			possibleValues[i] = blankPossValsCount[i];
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
}