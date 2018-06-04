package com.nwadave.scratch.snake;

public class SnakeSegmentCollisionHandler implements SnakeItemCollisionHandler {
	@Override
	public void handleSnakeItemCollision( int col, int row, GridItem item, Snake snake ) throws GameOverException {
		throw new GameOverException( "Ran in to existing snake segment" );
	}
}
