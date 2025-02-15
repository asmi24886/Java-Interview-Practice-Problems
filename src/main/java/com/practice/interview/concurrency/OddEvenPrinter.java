package com.practice.interview.concurrency;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class OddEvenPrinter {
    private AtomicInteger count = new AtomicInteger(1);

    public synchronized void printOdd() throws InterruptedException {

        while (count.get() < 20) {
            if(count.get()%2 == 0) {
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " -> " + count.getAndIncrement());
            Thread.sleep(1000);
            notifyAll();
        }

       // notifyAll();
    }

    public synchronized void printEven() throws InterruptedException {

        while (count.get() <= 20) {
            if(count.get()%2 == 1) {
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " -> " + count.getAndIncrement());
            Thread.sleep(1000);
            notifyAll();
        }

        //notifyAll();
    }

    public static void main(String [] args) {
        OddEvenPrinter printer = new OddEvenPrinter();
        Thread oddThread = new Thread( () -> {
            try {
                printer.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "odd thread");

        Thread evenThread = new Thread( () -> {
            try {
                printer.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "even thread");

        oddThread.start();
        evenThread.start();
    }
}
