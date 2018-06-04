package com.nwadave.scratch.snake;

public class FoodManagerFactory {
	private static FoodManager foodManager = null;

	public static FoodManager getFoodManager() {
		if( foodManager == null ) {
			foodManager = new FoodManager( SnakeGridFactory.getSnakeGrid(), 4 );
		}

		return foodManager;
	}
}
