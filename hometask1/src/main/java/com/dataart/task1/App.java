package com.dataart.task1;

public class App {

    public static void main(String[] args) {
        Thread thread = new MyThread();
        printState(thread);
        thread.start();
        printState(thread);
        thread.run();
        printState(thread);
//        thread.interrupt();
        printState(thread);
    }


    private static void printState(Thread thread) {
        System.out.println("Now thread in " + thread.getState() + " state");
    }
}