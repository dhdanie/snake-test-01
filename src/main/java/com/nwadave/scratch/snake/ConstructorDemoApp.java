package com.nwadave.scratch.snake;

public class ConstructorDemoApp {
    public static void main( String [] args ) {
        ConstructorDemoOutputter.writeMessage();
        ConstructorDemoOutputter app = new ConstructorDemoOutputter( "Hello Bear!!!" );
        ConstructorDemoOutputter app2 = new ConstructorDemoOutputter();
        app.printMessage();
        app2.printMessage();
        ConstructorDemoOutputter.writeMessage();
    }
}
