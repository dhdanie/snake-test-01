package com.nwadave.scratch.snake;

public interface SnakeItemCollisionHandler {
	void handleSnakeItemCollision( int col, int row, GridItem item, Snake snake ) throws GameOverException;
}
