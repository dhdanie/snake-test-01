package com.nwadave.scratch.snake;

import com.nwadave.restate.ManagedStateRunnable;
import com.nwadave.restate.State;
import com.nwadave.restate.States;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameClock extends ManagedStateRunnable {
	private static final State STATE_STARTING = States.STATE_STARTING;
	private static final State STATE_RUNNING  = new State().setId( 900 ).setName( "RUNNING" );
	private static final State STATE_WAITING  = new State().setId( 910 ).setName( "WAITING" );
	private static final State STATE_STOPPING = States.STATE_STOPPING;
	private static final State STATE_STOPPED  = States.STATE_STOPPED;

	private ExecutorService executor;
	private long tickInterval;
	private long currentTime;

	private boolean startedIndicator;
	private boolean shutdownIndicator;

	private List<TickListener> tickListeners;

	private void processStateStarting() {
		this.executor = Executors.newFixedThreadPool( 10 );
		this.currentTime = 0;
		this.shutdownIndicator = false;
		this.startedIndicator  = true;

		this.setState( STATE_RUNNING );
	}

	private void processStateRunning() {
		if( this.shutdownIndicator ) {
			this.setState( STATE_STOPPING );
			return;
		}

		this.currentTime++;

		for( TickListener tickListener : this.tickListeners ) {
			this.executor.submit( () -> tickListener.onTick( GameClock.this.currentTime ) );
		}

		this.setState( STATE_WAITING );
	}

	private void processStateWaiting() {
		try {
			Thread.sleep( this.tickInterval );
		} catch( InterruptedException e ) {
			//Not a problem
		}

		this.setState( STATE_RUNNING );
	}

	private void processStateStopping() {
		this.executor.shutdown();
		while( ! this.executor.isTerminated() ) {
			try {
				Thread.sleep( 250 );
			} catch( InterruptedException e ) {
				//Not a problem
			}
		}
		this.executor = null;
		this.startedIndicator = false;
		this.setState( STATE_STOPPED );
	}

	public void addTickListener( TickListener tickListener ) {
		this.tickListeners.add( tickListener );
	}

	public void shutdown() {
		if( ! this.isStarted() ) {
			return;
		}

		this.shutdownIndicator = true;
	}

	public boolean isStarted() {
		return this.startedIndicator;
	}

	public void reset() {
		this.currentTime = 0;
	}

	public void start() {
		if( this.isStarted() ) {
			return;
		}
		new Thread( this ).start();
	}

	public GameClock( int id, long tickInterval ) {
		super( id );

		this.tickInterval = tickInterval;

		this.tickListeners = new ArrayList<>();

		this.shutdownIndicator = false;
		this.startedIndicator = false;

		this.addState( STATE_STARTING, true , false );
		this.addState( STATE_RUNNING , false, false );
		this.addState( STATE_WAITING , false, false );
		this.addState( STATE_STOPPING, false, false );
		this.addState( STATE_STOPPED , false, true  );

		this.addStateProcessor( STATE_STARTING, (i, STATE_STARTING) -> this.processStateStarting() );
		this.addStateProcessor( STATE_RUNNING , (i, STATE_RUNNING ) -> this.processStateRunning () );
		this.addStateProcessor( STATE_WAITING , (i, STATE_WAITING ) -> this.processStateWaiting () );
		this.addStateProcessor( STATE_STOPPING, (i, STATE_STOPPING) -> this.processStateStopping() );
	}

	public static void main( String [] args ) {
		GameClock clock = new GameClock( 1, 1000 );

		TickListener listener = currentTime -> {
			System.out.println( "The current time is " + currentTime );
			if( currentTime > 2 ) {
				clock.shutdown();
			}
		};

		clock.addTickListener( listener );

		new Thread( clock ).start();
	}
}
