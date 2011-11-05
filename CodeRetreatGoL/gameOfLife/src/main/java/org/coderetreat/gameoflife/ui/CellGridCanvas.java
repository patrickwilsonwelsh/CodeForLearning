/* This page is part of the Game of Life source code */

/**
 * Copyright 1996-2004 Edwin Martin <edwin@bitstorm.nl>
 * @author Edwin Martin
 */

package org.coderetreat.gameoflife.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Enumeration;
import java.util.Iterator;

import org.coderetreat.gameoflife.Cell;
import org.coderetreat.gameoflife.CellGrid;

/**
 * Subclass of Canvas, which makes the cellgrid visible. Communicates via
 * CellGrid interface.
 * 
 * @author Edwin Martin
 */
public class CellGridCanvas extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean cellUnderMouse;
	/**
	 * Image for double buffering, to prevent flickering.
	 */
	private Image offScreenImage;
	private Graphics offScreenGraphics;
	private Image offScreenImageDrawed;
	/**
	 * Image, containing the drawed grid.
	 */
	private Graphics offScreenGraphicsDrawed;
	private int cellSize;
	private CellGrid cellGrid;
	private int newCellSize;
	private Shape newShape;


	/**
	 * Constructs a CellGridCanvas.
	 * 
	 * @param cellGrid
	 *            the GoL cellgrid
	 * @param cellSize
	 *            size of cell in pixels
	 */
	public CellGridCanvas(CellGrid cellGrid, int cellSize) {
		this.cellGrid = cellGrid;
		this.cellSize = cellSize;

		setBackground(new Color(0x999999));

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				draw(e.getX(), e.getY());
			}


			@Override
			public void mousePressed(MouseEvent e) {
				saveCellUnderMouse(e.getX(), e.getY());
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				draw(e.getX(), e.getY());
			}
		});
		addComponentListener(new ComponentListener() {

			public void componentResized(ComponentEvent e) {
				resized();
				repaint();
			}


			public void componentMoved(ComponentEvent e) {
			}


			public void componentHidden(ComponentEvent e) {
			}


			public void componentShown(ComponentEvent e) {
			}
		});

	}


	/**
	 * Set cell size (zoom factor)
	 * 
	 * @param cellSize
	 *            Size of cell in pixels
	 */
	public void setCellSize(int cellSize) {
		this.cellSize = cellSize;
		resized();
		repaint();
	}


	/**
	 * The grid is resized (by window resize or zooming). Also apply post-resize
	 * properties when necessary
	 */
	public void resized() {
		if (newCellSize != 0) {
			cellSize = newCellSize;
			newCellSize = 0;
		}
		Dimension canvasDim = this.getSize();
		offScreenImage = null;
		offScreenImageDrawed = null;
		cellGrid.resize(canvasDim.width / cellSize, canvasDim.height / cellSize);
		if (newShape != null) {
			try {
				setShape(newShape);
			}
			catch (ShapeException e) {
				// ignore
			}
		}

	}


	/**
	 * Remember state of cell for drawing.
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 */
	public void saveCellUnderMouse(int x, int y) {
		try {
			cellUnderMouse = cellGrid.getCell(x / cellSize, y / cellSize);
		}
		catch (java.lang.ArrayIndexOutOfBoundsException e) {
			// ignore
		}
	}


	/**
	 * Draw in this cell.
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 */
	public void draw(int x, int y) {
		try {
			cellGrid.setCell(x / cellSize, y / cellSize, !cellUnderMouse);
			repaint();
		}
		catch (java.lang.ArrayIndexOutOfBoundsException e) {
			// ignore
		}
	}


	/**
	 * Use double buffering.
	 * 
	 * @see java.awt.Component#update(java.awt.Graphics)
	 */
	@Override
	public void update(Graphics g) {
		Dimension d = getSize();
		if ((offScreenImage == null)) {
			offScreenImage = createImage(d.width, d.height);
			offScreenGraphics = offScreenImage.getGraphics();
		}
		paint(offScreenGraphics);
		g.drawImage(offScreenImage, 0, 0, null);
	}


	/**
	 * Draw this generation.
	 * 
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		// Draw grid on background image, which is faster
		if (offScreenImageDrawed == null) {
			Dimension dim = getGridDimension();
			Dimension d = getSize();
			offScreenImageDrawed = createImage(d.width, d.height);
			offScreenGraphicsDrawed = offScreenImageDrawed.getGraphics();
			// draw background (MSIE doesn't do that)
			offScreenGraphicsDrawed.setColor(getBackground());
			offScreenGraphicsDrawed.fillRect(0, 0, d.width, d.height);
			offScreenGraphicsDrawed.setColor(Color.gray);
			offScreenGraphicsDrawed.fillRect(0, 0, cellSize * dim.width - 1, cellSize * dim.height - 1);
			offScreenGraphicsDrawed.setColor(getBackground());
			for (int x = 1; x < dim.width; x++) {
				offScreenGraphicsDrawed.drawLine(x * cellSize - 1, 0, x * cellSize - 1, cellSize * dim.height - 1);
			}
			for (int y = 1; y < dim.height; y++) {
				offScreenGraphicsDrawed.drawLine(0, y * cellSize - 1, cellSize * dim.width - 1, y * cellSize - 1);
			}
		}
		g.drawImage(offScreenImageDrawed, 0, 0, null);
		// draw populated cells
		g.setColor(Color.yellow);
		Iterator<Cell> oldEnum = cellGrid.getCellIterator();
		Cell c;
		while (oldEnum.hasNext()) {
			c = oldEnum.next();
			g.fillRect(c.getColumn() * cellSize, c.getRow() * cellSize, cellSize - 1, cellSize - 1);
		}
	}


	Dimension getGridDimension() {
		return new Dimension(cellGrid.getColumns(), cellGrid.getRows());
	}


	/**
	 * This is the preferred size.
	 * 
	 * @see java.awt.Component#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		Dimension dim = getGridDimension();
		return new Dimension(cellSize * dim.width, cellSize * dim.height);
	}


	/**
	 * This is the minimum size (size of one cell).
	 * 
	 * @see java.awt.Component#getMinimumSize()
	 */
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(cellSize, cellSize);
	}


	/**
	 * Settings to appy after a window-resize.
	 * 
	 * @param newShape
	 *            new shape
	 * @param newCellSize
	 *            new cellSize
	 */
	public void setAfterWindowResize(Shape newShape, int newCellSize) {
		this.newShape = newShape;
		this.newCellSize = newCellSize;
	}


	/**
	 * Draws shape in grid.
	 * 
	 * @param shape
	 *            name of shape
	 * @return true when shape fits, false otherwise
	 * @throws ShapeException
	 *             if the shape does not fit on the canvas
	 */
	public synchronized void setShape(Shape shape) throws ShapeException {
		int xOffset;
		int yOffset;
		Dimension dimShape;
		Dimension dimGrid;

		// get shape properties
		// shapeGrid = shape.getShape();
		dimShape = shape.getDimension();
		dimGrid = getGridDimension();

		if (dimShape.width > dimGrid.width || dimShape.height > dimGrid.height)
			throw new ShapeException("Shape doesn't fit on canvas (grid: " + dimGrid.width + "x" + dimGrid.height + ", shape: " + dimShape.width + "x" + dimShape.height + ")"); // shape
																																													// doesn't
		// center the shape
		xOffset = (dimGrid.width - dimShape.width) / 2;
		yOffset = (dimGrid.height - dimShape.height) / 2;
		cellGrid.clear();

		// draw shape
		Enumeration<int[]> cells = shape.getCells();
		while (cells.hasMoreElements()) {
			int[] cell = cells.nextElement();
			cellGrid.setCell(xOffset + cell[0], yOffset + cell[1], true);
		}

	}
}
