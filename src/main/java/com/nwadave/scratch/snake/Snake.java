package com.nwadave.scratch.snake;

import java.awt.*;
import java.util.ArrayList;

public class Snake {
	private Direction currentDirection;
	private int currentLength;
	private SnakeSegment head;

	public Snake( Direction currentDirection, int col, int row, int initialLength ) {
		this.currentDirection = currentDirection;
		this.currentLength = initialLength;

		for( int i = 1; i <= initialLength; i++ ) {
			int placeRow = row + (initialLength - i);

			SnakeSegment prevHead = this.head;
			this.head = new SnakeSegment( col, placeRow, i, prevHead );
		}
	}

	public ArrayList<Shape> toShapes() {
		ArrayList<Shape> snakeShapes = new ArrayList<>();

		SnakeSegment segment = this.head;
		while( segment != null ) {
			snakeShapes.add( segment.toShape() );

			segment = segment.getNextSegment();
		}

		return snakeShapes;
	}

	public void setDirection( Direction direction ) {
		if( this.isPermittedDirectionChange( direction ) ) {
			this.currentDirection = direction;
		}
	}

	public void moveSnake() {
		int nextCol;
		int nextRow;
		switch( this.currentDirection ) {
			case UP:
				nextCol = this.head.getCol();
				nextRow = this.head.getRow() - 1;
				break;
			case DOWN:
				nextCol = this.head.getCol();
				nextRow = this.head.getRow() + 1;
				break;
			case LEFT:
				nextCol = this.head.getCol() - 1;
				nextRow = this.head.getRow();
				break;
			case RIGHT:
				nextCol = this.head.getCol() + 1;
				nextRow = this.head.getRow();
				break;
			default:
				nextCol = this.head.getCol();
				nextRow = this.head.getRow();
				break;
		}

		SnakeSegment prevHead = this.head;

		this.head = new SnakeSegment( nextCol, nextRow, this.currentLength, prevHead );

		SnakeSegment segment = prevHead;
		SnakeSegment prevSegment = this.head;
		while( segment != null ) {
			segment.ageSegment();
			if( segment.getRemainingLife() < 1 ) {
				prevSegment.setNextSegment( null );
				break;
			}
			prevSegment = segment;
			segment = segment.getNextSegment();
		}
	}

	private boolean isPermittedDirectionChange( Direction direction ) {
		switch( direction ) {
			case UP:
				return (this.currentDirection != Direction.DOWN);
			case DOWN:
				return (this.currentDirection != Direction.UP);
			case LEFT:
				return (this.currentDirection != Direction.RIGHT);
			case RIGHT:
				return (this.currentDirection != Direction.LEFT);
			default:
				return false;
		}
	}
}
