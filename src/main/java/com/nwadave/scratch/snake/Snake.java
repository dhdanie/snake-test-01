package com.nwadave.scratch.snake;

public class Snake {
	private Direction currentDirection;
	private int currentLength;
	private SnakeSegment head;
	private SnakeGrid grid;

	public Snake( Direction currentDirection, int col, int row, int initialLength ) {
		this.currentDirection = currentDirection;
		this.currentLength = initialLength;
		this.grid = SnakeGridFactory.getSnakeGrid();

		for( int i = 1; i <= initialLength; i++ ) {
			int placeRow = row + (initialLength - i);

			SnakeSegment prevHead = this.head;
			this.head = new SnakeSegment( col, placeRow, i, prevHead );

			this.grid.placeItem( col, placeRow, GridItem.SNAKE_SEGMENT );
		}
	}

	public void setDirection( Direction direction ) {
		if( this.isPermittedDirectionChange( direction ) ) {
			this.currentDirection = direction;
		}
	}

	public void moveSnake() throws GameOverException {
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

		//Only complete move if target col/row is within bounds and unoccupied by snake segment
		if(
				nextCol < 0
				|| nextCol >= AppContext.COL_COUNT
				|| nextRow < 0
				|| nextRow >= AppContext.ROW_COUNT
		) {
			//Off board so die
			throw new GameOverException( "Game Over - Tried to move off board" );
		}

		SnakeSegment prevHead = this.head;

		SnakeSegment segment = prevHead;
		while( segment != null ) {
			segment.ageSegment();
			if( segment.getRemainingLife() < 1 ) {
				SnakeSegment newTail = segment;
				while( (segment != null) && (segment.getRemainingLife() < 1) ) {
					this.grid.removeItemAt( segment.getCol(), segment.getRow() );
					segment = segment.getNextSegment();
				}
				newTail.setNextSegment( null );
				break;
			} else {
				segment = segment.getNextSegment();
			}
		}
		//dead segments pruned, all segments aged
		//Check to see if element exists at nextCol/nextRow
		//If so, bad or good?  Bad - Game Over, Good - TBD...?
		GridItem existingItem = this.grid.itemAt( nextCol, nextRow );
		if( existingItem != null ) {
			SnakeItemCollisionHandler collisionHandler = this.getCollisionHandlerForItemType( existingItem );

			collisionHandler.handleSnakeItemCollision( nextCol, nextRow, existingItem, this );
		}

		this.head = new SnakeSegment( nextCol, nextRow, this.currentLength, prevHead );
		this.grid.placeItem( nextCol, nextRow, GridItem.SNAKE_SEGMENT );
	}

	public void elongate( int amount ) {
		this.currentLength += amount;

		SnakeSegment segment = this.head;
		while( segment != null ) {
			segment.setRemainingLife( segment.getRemainingLife() + amount );

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

	private SnakeItemCollisionHandler getCollisionHandlerForItemType( GridItem item ) throws GameOverException {
		return CollisionHandlerFactory.getCollisionHandlerForItem( item );
	}
}
