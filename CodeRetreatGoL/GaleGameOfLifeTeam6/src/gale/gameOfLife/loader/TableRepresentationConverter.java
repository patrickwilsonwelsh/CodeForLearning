package gale.gameOfLife.loader;

import gale.gameOfLife.CellState;

public final class TableRepresentationConverter {
	public static String DELIMITER = "<br>";
	
	private static char deadChar = '_';
	private static char liveChar = 'X';
	
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

	public static CellState[][] convertWonkTo2DArray(String bigString) {
		String[] rowsOfStrings = bigString.split(DELIMITER);
		int numberOfRows = rowsOfStrings.length;
		int numberOfColumns = rowsOfStrings[0].length();
		
		CellState[][] result = new CellState[numberOfRows][numberOfColumns];
		
		for (int rowIndex=0; rowIndex<rowsOfStrings.length; rowIndex++) {
			String row = rowsOfStrings[rowIndex];
			result[rowIndex] = convertRow(row);
		}
		return result;
	}

	private static CellState[] convertRow(String row) {
		char[] cellsForRow = row.toCharArray();
		CellState[] statesForRow = new CellState[cellsForRow.length];
		
		for (int cellIndex=0; cellIndex<statesForRow.length; cellIndex++) {
			statesForRow[cellIndex] = convertFrom(cellsForRow[cellIndex]);
		}
		return statesForRow;
	}

	private static CellState convertFrom(char character) {
		if (character == deadChar) return CellState.Dead;
		if (character == liveChar) return CellState.Alive;
		throw new IllegalStateException("What, are you a zombie? " + character);
	}

	private static String convertTo(CellState cell) {
		if (cell == CellState.Dead) return String.valueOf(deadChar);
		if (cell == CellState.Alive) return String.valueOf(liveChar);
		throw new IllegalStateException("What, are you a zombie? " + cell);
	}
	
	public static String convert2DArrayToWonk(CellState[][] unConverted) {
		StringBuffer converted = new StringBuffer();
		
		String wonkyDelimiter = DELIMITER;
		for(CellState[] row : unConverted) {
			for (CellState cell : row) {
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
