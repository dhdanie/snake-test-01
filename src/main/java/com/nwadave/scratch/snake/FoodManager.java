package com.nwadave.scratch.snake;

import java.util.Random;

public class FoodManager implements SnakeItemCollisionHandler, TickListener {
	private SnakeGrid grid;
	private int minFoodAmount;
	private Random random;

	public FoodManager( SnakeGrid grid, int minFoodAmount ) {
		this.grid = grid;
		this.minFoodAmount = minFoodAmount;

		this.random = new Random( System.currentTimeMillis() );

		GameClockManager.addTickListener( this );
	}

	@Override
	public void handleSnakeItemCollision( int col, int row, GridItem item, Snake snake ) {
		if( item != GridItem.FOOD ) {
			return;
		}

		snake.elongate( 1 );

		this.grid.removeItemAt( col, row );
	}

	@Override
	public void onTick( long currentTime ) {
		int currentCount = this.grid.getItemCount( GridItem.FOOD );
		while( currentCount < this.minFoodAmount ) {
			this.addFoodItem();

			currentCount++;
		}
	}

	private void addFoodItem() {
		int addCol = this.getAddCol();
		int addRow = this.getAddRow();
		while( ! this.grid.placeItem( addCol, addRow, GridItem.FOOD ) ) {
			addCol = this.getAddCol();
			addRow = this.getAddRow();
		}
	}

	private int getAddRow() {
		return this.random.nextInt( this.grid.getNumRows() );
	}

	private int getAddCol() {
		return this.random.nextInt( this.grid.getNumCols() );
	}
}
