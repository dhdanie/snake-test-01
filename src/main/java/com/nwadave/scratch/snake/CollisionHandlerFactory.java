package com.nwadave.scratch.snake;

import java.util.HashMap;
import java.util.Map;

public class CollisionHandlerFactory {
	private static Map<GridItem,SnakeItemCollisionHandler> handlers = null;

	public static SnakeItemCollisionHandler getCollisionHandlerForItem( GridItem gridItem ) throws GameOverException {
		if( handlers == null ) {
			initializeHandlers();
		}

		SnakeItemCollisionHandler handler = handlers.get( gridItem );
		if( handler == null ) {
			throw new GameOverException( "No collision handler available for grid item type " + gridItem );
		}
		return handlers.get( gridItem );
	}

	private static void initializeHandlers() {
		handlers = new HashMap<>();

		handlers.put( GridItem.APPLE, FoodManagerFactory.getFoodManager() );
		handlers.put( GridItem.BANANA, FoodManagerFactory.getFoodManager() );
		handlers.put( GridItem.SNAKE_SEGMENT, null );//TODO
	}
}
