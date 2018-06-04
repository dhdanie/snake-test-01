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

		if( this.gameOver ) {
			GameClockManager.stopGameClock();
			Font font = new Font( Font.SANS_SERIF, Font.BOLD, 20 );
			g2d.setFont( font );

			FontMetrics metrics = g2d.getFontMetrics();

			int totalWidth = 0;
			String output = "Game Over";
			for( char outchar : output.toCharArray() ) {
				totalWidth = totalWidth + metrics.charWidth( outchar );
			}

			int xcoord = 250 - (totalWidth / 2);
			int ycoord = 250 - (metrics.getHeight() / 2);
			g2d.drawString( "Game Over", xcoord, ycoord );
		} else {
			for( int i = 0; i < this.grid.getNumCols(); i++ ) {
				for( int j = 0; j < this.grid.getNumRows(); j++ ) {
					GridItem itemAt = this.grid.itemAt( i, j );
					if( itemAt != null ) {
						Shape shape = ShapeFactory.getShapeForItem( itemAt, i, j );
						g2d.fill( shape );
					}
				}
			}
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
