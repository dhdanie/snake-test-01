package com.nwadave.scratch.snake;

public class SnakeGridFactory {
	private static SnakeGrid snakeGrid = null;

	public static SnakeGrid getSnakeGrid() {
		if( snakeGrid == null ) {
			snakeGrid = new SnakeGrid( AppContext.COL_COUNT, AppContext.ROW_COUNT );
		}

		return snakeGrid;
	}
}
