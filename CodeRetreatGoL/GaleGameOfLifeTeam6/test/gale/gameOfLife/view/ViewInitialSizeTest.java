package gale.gameOfLife.view;

import junit.framework.Assert;

import org.junit.Test;


public class ViewInitialSizeTest {
  @Test
  public void testWidthCellCount() {
	  LifeBoardView boardView = new LifeBoardView();
	  
	  Assert.assertEquals(44, boardView.getWidthCellCount());
  }
  
  @Test
  public void testHeightCellCount() {
	  LifeBoardView boardView = new LifeBoardView();
	  
	  Assert.assertEquals(22, boardView.getHeightCellCount());
  }
  
}
