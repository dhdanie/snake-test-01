package com.nwadave.scratch.snake;

public class ConstructorDemoOutputter {
    private static String message;

    public ConstructorDemoOutputter() {
        this.message = "Hello World!!!";
    }

    public ConstructorDemoOutputter( String message ) {
        this.setMessage( message );
    }

    public void printMessage() {
        System.out.println( this.message );
    }

    protected void setMessage( String message ) {
        this.message = message;
    }

    public static void writeMessage() {
        System.out.println( message );
    }
}
