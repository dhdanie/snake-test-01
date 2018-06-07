package com.nwadave.scratch.snake;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static com.nwadave.scratch.snake.AppContext.*;

public class ShapeFactory {
	public static Shape getShapeForItem( GridItem item, int col, int row ) {
		switch( item ) {
			case SNAKE_SEGMENT:
				return getShapeForSnakeSegment( col, row );
			case APPLE:
				return getShapeForFood( col, row );
			case BANANA:
				return getShapeForFood( col, row );
			default:
				return null;
		}
	}

	private static Shape getShapeForFood( int col, int row ) {
		float widthActual = FOOD_WIDTH * LOCATION_MULTIPLIER;
		float heightActual = FOOD_HEIGHT * LOCATION_MULTIPLIER;

		return new Rectangle2D.Float( col * LOCATION_MULTIPLIER, row * LOCATION_MULTIPLIER, widthActual, heightActual );
	}

	public static Shape getShapeForSnakeSegment( int col, int row ) {
		float widthActual = SNAKE_SEG_WIDTH * LOCATION_MULTIPLIER;
		float heightActual = SNAKE_SEG_HEIGHT * LOCATION_MULTIPLIER;

		return new Rectangle2D.Float( col * LOCATION_MULTIPLIER, row * LOCATION_MULTIPLIER, widthActual, heightActual );
	}
}
