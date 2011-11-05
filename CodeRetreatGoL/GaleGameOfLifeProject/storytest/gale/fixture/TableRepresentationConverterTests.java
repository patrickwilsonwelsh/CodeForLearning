package gale.fixture;

import static org.junit.Assert.*;
import static gale.gameOfLife.CellState.Dead;
import static gale.gameOfLife.CellState.Alive;
import junit.framework.Assert;
import gale.gameOfLife.CellState;
import static gale.fixture.TableRepresentationConverter.*;

import org.junit.Test;

public class TableRepresentationConverterTests {
	
	@Test
	public void canConvertFromWonkToArrayOfCellStates() {
		String wonkyTableRepresentation = "___" + DELIMITER + "_X_" + DELIMITER + "___";
		CellState[][] converted = new CellState[][]{{Dead,Dead,Dead},{Dead,Alive,Dead},{Dead,Dead,Dead}};
		assertArraysAreEqual(converted, TableRepresentationConverter.convertWonkTo2DArray(wonkyTableRepresentation));
	}
	
	@Test
	public void canConvertFromArrayOfCellStatesToWonk() {
		String wonkyTableRepresentation = "___" + DELIMITER + "_X_" + DELIMITER + "___";
		CellState[][] converted = new CellState[][]{{Dead,Dead,Dead},{Dead,Alive,Dead},{Dead,Dead,Dead}};
		assertEquals(wonkyTableRepresentation, TableRepresentationConverter.convert2DArrayToWonk(converted));
	}
	
	@Test
	public void canAssertTwoArraysAreEqual() {
		CellState[][] converted = new CellState[][]{{Dead,Dead,Dead},{Dead,Alive,Dead},{Dead,Dead,Dead}};
		assertArraysAreEqual(converted, converted);
	}
	
	@Test
	public void roundTrip() {
		String wonkyTableRepresentation = "___" + DELIMITER + "_X_" + DELIMITER + "___";
		assertEquals(wonkyTableRepresentation, TableRepresentationConverter.convert2DArrayToWonk(
				TableRepresentationConverter.convertWonkTo2DArray(wonkyTableRepresentation)));
	}

	private void assertArraysAreEqual(CellState[][] converted,
			CellState[][] convertWonkTo2DArray) {
		int rowIndex = -1;
		int colIndex = -1;
		
		for (CellState[] row : converted) {
			rowIndex++;
			
			for (CellState cell : row) {
				colIndex++;
				Assert.assertEquals("col=" + colIndex + " row=" + rowIndex, cell, convertWonkTo2DArray[rowIndex][colIndex]);
			}
			colIndex=-1;
		}
	}
}
