package com.nwadave.scratch.snake;

public class SnakeSegment {

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

	public void setRemainingLife( int remainingLife ) {
		this.remainingLife = remainingLife;
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
}
