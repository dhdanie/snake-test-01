package com.nwadave.scratch.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SnakeFrame extends JFrame {
	private Snake snake;
	private SnakeSurface snakeSurface;

	private SnakeFrame() {
		this.initUi();
	}

	private void initUi() {
		this.snake = new Snake( Direction.UP, 25, 25, 4 );
		this.snakeSurface = new SnakeSurface( snake );

		this.add( this.snakeSurface );

		this.setTitle( "Snake" );
		this.setSize( 500, 500 );
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
