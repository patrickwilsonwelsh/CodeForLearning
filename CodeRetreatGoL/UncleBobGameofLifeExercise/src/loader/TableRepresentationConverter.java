package loader;

import game.Cell;
import game.CellLocation;
import game.CellState;

public final class TableRepresentationConverter {
	public static String DELIMITER = "<br>";
	
	private static char deadChar = '_';
	private static char liveChar = 'X';
	private static int x;
	private static int y;
	
	public static char getDeadChar() {
		return deadChar;
	}

	public static void setDeadChar(char deadChar) {
		TableRepresentationConverter.deadChar = deadChar;
	}

	public static char getLiveChar() {
		return liveChar;
	}

	public static void setLiveChar(char liveChar) {
		TableRepresentationConverter.liveChar = liveChar;
	}

	public static void setDelimiter(String delimiter) {
		DELIMITER = delimiter;
	}

	public static Cell[][] convertWonkTo2DArray(String bigString) {
		String[] rowsOfStrings = bigString.split(DELIMITER);
		int numberOfRows = rowsOfStrings.length;
		int numberOfColumns = rowsOfStrings[0].length();
		
		Cell[][] result = new Cell[numberOfRows][numberOfColumns];
		
		for (int rowIndex=0; rowIndex<rowsOfStrings.length; rowIndex++) {
			y = rowIndex;
			String row = rowsOfStrings[rowIndex];
			result[rowIndex] = convertRow(row);
		}
		return result;
	}

	private static Cell[] convertRow(String row) {
		char[] cellsForRow = row.toCharArray();
		Cell[] statesForRow = new Cell[cellsForRow.length];
		
		for (int cellIndex=0; cellIndex<statesForRow.length; cellIndex++) {
			x = cellIndex;
			statesForRow[cellIndex] = convertFrom(cellsForRow[cellIndex]);
		}
		return statesForRow;
	}

	private static Cell convertFrom(char character) {
		if (character == deadChar) return new Cell(CellState.Dead, new CellLocation(x, y));
		if (character == liveChar) return new Cell(CellState.Alive, new CellLocation(x, y));
		throw new IllegalStateException("What, are you a zombie? " + character);
	}

	private static String convertTo(Cell cell) {
		if (cell.isAlive()) return String.valueOf(liveChar);
		else return String.valueOf(deadChar);
	}
	
	public static String convert2DArrayToWonk(Cell[][] unConverted) {
		StringBuffer converted = new StringBuffer();
		
		String wonkyDelimiter = DELIMITER;
		for(Cell[] row : unConverted) {
			for (Cell cell : row) {
				converted.append(convertTo(cell));
			}
			converted.append(wonkyDelimiter);
		}
		
		return removeLastBitOfWonk(converted, wonkyDelimiter);
	}

	private static String removeLastBitOfWonk(StringBuffer converted,
			String wonkyDelimiter) {
		String convertedString = converted.toString();
		if (convertedString.endsWith(wonkyDelimiter)) {
			convertedString = convertedString.substring(0, convertedString.length()-DELIMITER.length());
		}
		return convertedString;
	}

}
