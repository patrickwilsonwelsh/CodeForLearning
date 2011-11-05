package gale.gameOfLife.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import gale.gameOfLife.CellState;
import gale.gameOfLife.LifeBoard;

public class LifeLoader {

	static LifeBoard readBoard(String boardString) {

		TableRepresentationConverter.setDeadChar('.');
		TableRepresentationConverter.setDelimiter("\n");
		
		LifeBoard lifeBoard = new LifeBoard(2,2);
		CellState[][] state = TableRepresentationConverter.convertWonkTo2DArray(boardString);
		lifeBoard.setCellState(state);
		return lifeBoard;
	}

	static String readFileFromClasspath(String string) throws IOException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(string);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuilder builder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			builder.append(line).append('\n');			
		}
		return builder.toString();
	}

	public static LifeBoard loadBoard(String string) throws IOException {
		String str = readFileFromClasspath(string);
		return readBoard(str);
	}

}
