package com.nwadave.scratch.snake;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SnakeSegment {
	public static final float LOCATION_MULTIPLIER = 10;

	public static final float SNAKE_SEG_WIDTH  = 1;
	public static final float SNAKE_SEG_HEIGHT = 1;

	private int col;
	private int row;
	private int remainingLife;

	private SnakeSegment nextSegment;

	public SnakeSegment( int col, int row, int remainingLife, SnakeSegment nextSegment ) {
		this.col = col;
		this.row = row;
		this.remainingLife = remainingLife;
		this.nextSegment = nextSegment;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public int getRemainingLife() {
		return remainingLife;
	}

	public SnakeSegment getNextSegment() {
		return nextSegment;
	}

	public void ageSegment() {
		this.remainingLife = this.remainingLife - 1;
	}

	public void setNextSegment( SnakeSegment nextSegment ) {
		this.nextSegment = nextSegment;
	}

	public Shape toShape() {
		float widthActual = SNAKE_SEG_WIDTH * LOCATION_MULTIPLIER;
		float heightActual = SNAKE_SEG_HEIGHT * LOCATION_MULTIPLIER;

		return new Rectangle2D.Float( this.col * LOCATION_MULTIPLIER, this.row * LOCATION_MULTIPLIER, widthActual, heightActual );
	}
}
