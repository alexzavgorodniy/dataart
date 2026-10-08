package com.dataart.task1;

public class MyThread extends Thread {

    @Override
    public void run() {
        synchronized (this) {
            try {
//                this.wait(2000);
                Thread.sleep(1000);
                method();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void method() throws InterruptedException {
        synchronized (this) {
            this.notifyAll();
        }
    }
}