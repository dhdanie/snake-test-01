package com.nwadave.scratch.snake;

import javax.swing.*;
import java.awt.*;

public class SnakeSurface extends JPanel implements TickListener {
	private boolean gameOver;
	private Snake snake;
	private SnakeGrid grid;

	public SnakeSurface( Snake snake ) {
		GameClockManager.addTickListener( this );

		this.snake = snake;
		this.gameOver = false;
		this.grid = SnakeGridFactory.getSnakeGrid();
		FoodManagerFactory.getFoodManager();
	}

	@Override
	protected void paintComponent( Graphics graphics ) {
		super.paintComponent( graphics );

		Graphics2D g2d = (Graphics2D)graphics;

		for( int i = 0; i < this.grid.getNumCols(); i++ ) {
			for( int j = 0; j < this.grid.getNumRows(); j++ ) {
				GridItem itemAt = this.grid.itemAt( i, j );
				if( itemAt != null ) {
					Shape shape = ShapeFactory.getShapeForItem( itemAt, i, j );
					g2d.fill( shape );
				}
			}
		}

		if( this.gameOver ) {
			GameClockManager.stopGameClock();
		}
	}

	@Override
	public void onTick( long currentTime ) {
		try {
			this.snake.moveSnake();
		} catch( GameOverException e ) {
			this.gameOver = true;
		}

		this.repaint();
	}
}
