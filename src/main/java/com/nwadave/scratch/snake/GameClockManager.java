package com.nwadave.scratch.snake;

public class GameClockManager {
	private static GameClockManager gameClockManager = new GameClockManager();

	public static void startGameClock() {
		gameClockManager._startGameClock();
	}

	public static void stopGameClock() {
		gameClockManager._stopGameClock();
	}

	public static void addTickListener( TickListener tickListener ) {
		gameClockManager._addTickListener( tickListener );
	}

	private GameClock gameClock;

	private GameClockManager() {
		this.gameClock = new GameClock( -1, AppContext.TICK_FREQUENCY );
	}

	private void _addTickListener( TickListener tickListener ) {
		this.gameClock.addTickListener( tickListener );
	}

	private void _startGameClock() {
		if( this.gameClock.isStarted() ) {
			return;
		}

		this.gameClock.reset();
		this.gameClock.start();
	}

	private void _stopGameClock() {
		if( ! this.gameClock.isStarted() ) {
			return;
		}
		this.gameClock.shutdown();
	}
}
