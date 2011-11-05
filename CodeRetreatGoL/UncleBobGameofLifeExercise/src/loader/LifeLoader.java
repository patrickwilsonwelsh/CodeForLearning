package loader;

import game.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LifeLoader {

	static Board readBoard(String boardString) {
		TableRepresentationConverter.setDeadChar('.');
		TableRepresentationConverter.setDelimiter("\n");
		
		Board lifeBoard = new Board(2,2);
		lifeBoard.setGridState(TableRepresentationConverter.convertWonkTo2DArray(boardString));
		return lifeBoard;
	}

	static String readFileFromClasspath(String string) throws IOException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(string);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder builder = readEachLine(reader);
		return builder.toString();
	}

	private static StringBuilder readEachLine(BufferedReader reader)
			throws IOException {
		String line;
		StringBuilder builder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			builder.append(line).append('\n');			
		}
		return builder;
	}

	public static Board loadBoard(String string) throws IOException {
		String str = readFileFromClasspath(string);
		return readBoard(str);
	}

}
