/**
 * Copyright 1996-2004 Edwin Martin <edwin@bitstorm.nl>
 * @author Edwin Martin
 */

package org.coderetreat.gameoflife.ui;

/**
 * Exception for shapes (too big, not found...).
 * 
 * @author Edwin Martin
 */
public class ShapeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Constructs a ShapeException.
	 */
	public ShapeException() {
		super();
	}


	/**
	 * Constructs a ShapeException with a description.
	 */
	public ShapeException(String s) {
		super(s);
	}
}