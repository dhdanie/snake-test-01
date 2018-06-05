package com.nwadave.scratch.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static com.nwadave.scratch.snake.AppContext.*;

public class SnakeFrame extends JFrame {
	private Snake snake;
	private SnakeSurface snakeSurface;

	private SnakeFrame() {
		this.initUi();
	}

	private void initUi() {
		int midpointCol = COL_COUNT / 2;
		int midpointRow = ROW_COUNT / 2;
		this.snake = new Snake( Direction.UP, midpointCol, midpointRow, SNAKE_INITIAL_LENGTH );
		this.snakeSurface = new SnakeSurface( snake );

		this.add( this.snakeSurface );

		this.setTitle( "Snake" );

		int width = (int)((COL_COUNT + 2) * LOCATION_MULTIPLIER);
		int height = (int)((ROW_COUNT + 4) * LOCATION_MULTIPLIER);
		this.setSize( width, height );
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.addKeyListener( new KeyListener() {
			@Override
			public void keyTyped( KeyEvent keyEvent ) {

			}

			@Override
			public void keyPressed( KeyEvent keyEvent ) {
				switch( keyEvent.getKeyCode() ) {
					case 37: //left
						SnakeFrame.this.snake.setDirection( Direction.LEFT );
						break;
					case 38: //up
						SnakeFrame.this.snake.setDirection( Direction.UP );
						break;
					case 39: //right
						SnakeFrame.this.snake.setDirection( Direction.RIGHT );
						break;
					case 40: //down
						SnakeFrame.this.snake.setDirection( Direction.DOWN );
						break;
				}
			}

			@Override
			public void keyReleased( KeyEvent keyEvent ) {

			}
		} );
		this.addWindowListener( new WindowListener() {
			@Override
			public void windowOpened( WindowEvent windowEvent ) {

			}

			@Override
			public void windowClosing( WindowEvent windowEvent ) {
				GameClockManager.stopGameClock();
			}

			@Override
			public void windowClosed( WindowEvent windowEvent ) {

			}

			@Override
			public void windowIconified( WindowEvent windowEvent ) {

			}

			@Override
			public void windowDeiconified( WindowEvent windowEvent ) {

			}

			@Override
			public void windowActivated( WindowEvent windowEvent ) {

			}

			@Override
			public void windowDeactivated( WindowEvent windowEvent ) {

			}
		} );
	}

	public static void main( String [] args ) throws Exception {
		SnakeFrame snakeFrame = new SnakeFrame();
		GameClockManager.startGameClock();
		EventQueue.invokeLater( () -> {
			snakeFrame.setVisible( true );
		} );
	}
}
