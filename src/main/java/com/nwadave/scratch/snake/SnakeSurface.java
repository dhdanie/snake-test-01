package com.nwadave.scratch.snake;

import javax.swing.*;
import java.awt.*;

public class SnakeSurface extends JPanel implements TickListener {
	private Snake snake;

	public SnakeSurface( Snake snake ) {
		GameClockManager.addTickListener( this );

		this.snake = snake;
	}

	@Override
	protected void paintComponent( Graphics graphics ) {
		super.paintComponent( graphics );

		Graphics2D g2d = (Graphics2D)graphics;

		for( Shape snakeShape : this.snake.toShapes() ) {
			g2d.fill( snakeShape );
		}
	}

	@Override
	public void onTick( long currentTime ) {
		this.snake.moveSnake();

		this.repaint();
	}
}
