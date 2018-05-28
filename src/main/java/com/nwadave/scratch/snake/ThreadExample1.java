package com.nwadave.scratch.snake;

@SuppressWarnings( "Duplicates" )
public class ThreadExample1 {
	public static class MyRunnable implements Runnable {
		private String threadName;
		public MyRunnable( String name ) {
			this.threadName = name;
		}

		@Override
		public void run() {
			for( int i = 0; i < 10; i++ ) {
				System.out.println( "Hello from " + this.threadName );
				try {
					Thread.sleep( 1000 );
				} catch( InterruptedException e ) {
					System.out.println( this.threadName + " was interrupted" );
				}
			}
		}
	}

	private void run() throws Exception {
		this.threadExample1();

		this.threadExample2();

		this.threadExample3();
	}

	private void threadExample1() throws Exception {
		Thread t1 = new Thread( new MyRunnable( "thread-1" ) );
		Thread t2 = new Thread( new MyRunnable( "thread-2" ) );

		t1.start();

		Thread.sleep( 500 );

		t2.start();
	}

	private void threadExample2() throws Exception {
		Thread t1 = new Thread( new Runnable() {
			@Override
			public void run() {
				for( int i = 0; i < 10; i++ ) {
					System.out.println( "Hello from thread 1" );
					try {
						Thread.sleep( 1000 );
					} catch( InterruptedException e ) {
						System.out.println( "Thread 1 was interrupted" );
					}
				}
			}
		} );
		Thread t2 = new Thread( new Runnable() {
			@Override
			public void run() {
				for( int i = 0; i < 10; i++ ) {
					System.out.println( "Hello from thread 2" );
					try {
						Thread.sleep( 1000 );
					} catch( InterruptedException e ) {
						System.out.println( "Thread 2 was interrupted" );
					}
				}
			}
		} );

		t1.start();

		Thread.sleep( 500 );

		t2.start();
	}

	private void threadExample3() throws Exception {
		Thread t1 = new Thread( () -> {
			for( int i = 0; i < 10; i++ ) {
				System.out.println( "Hello from thread 1" );
				try {
					Thread.sleep( 1000 );
				} catch( InterruptedException e ) {
					System.out.println( "Thread 1 was interrupted" );
				}
			}
		} );
		Thread t2 = new Thread( () -> {
			for( int i = 0; i < 10; i++ ) {
				System.out.println( "Hello from thread 2" );
				try {
					Thread.sleep( 1000 );
				} catch( InterruptedException e ) {
					System.out.println( "Thread 2 was interrupted" );
				}
			}
		} );

		t1.start();

		Thread.sleep( 500 );

		t2.start();
	}

	public static void main( String [] args ) throws Exception {
		new ThreadExample1().threadExample1();
	}
}
