package com.nwadave.scratch.snake;

public class ThreadExample2 {
	public static class Renderer implements Runnable {
		private String currentValue;
		private String previousValue;

		public Renderer() {
			this.currentValue = "initial";
			this.previousValue = "initial";
		}

		public void setCurrentValue( String currentValue ) {
			this.currentValue = currentValue;
		}

		@Override
		public void run() {
			while( ! this.currentValue.equals( "stop" ) ) {
				if( ! this.currentValue.equals( this.previousValue ) ) {
					System.out.println( "New value detected - " + this.currentValue );
					this.previousValue = this.currentValue;
				}

				try {
					Thread.sleep( 500 );
				} catch( InterruptedException e ) {
					//Interrupted so finish up
					break;
				}
			}
		}
	}

	public static void main( String [] args ) throws Exception {
		Renderer renderer = new Renderer();

		new Thread( renderer ).start();

		for( int i = 0; i < 10; i++ ) {
			String sendString = "value-" + i;

			System.out.println( "Sending '" + sendString + "'" );

			renderer.setCurrentValue( sendString );

			Thread.sleep( 1000 );
		}

		renderer.setCurrentValue( "stop" );

		System.out.println( "Program finished" );
	}
}
