package com.practice.interview.concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueue<T> {

    private Queue<T> queue;
    private int capacity;

    public MyBlockingQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait(); // Wait if queue is full
        }

        queue.offer(item);
        notifyAll(); // Notify waiting consumers
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait if queue is empty
        }

        T item = queue.poll();
        notifyAll(); // Notify waiting producers
        return item;
    }

    public synchronized int size() {
        return queue.size();
    }

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(5);

        // Producer thread
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.enqueue(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(200); // Simulate some work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Consumer thread 1
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    Integer item = queue.dequeue();
                    System.out.println("Consumed by 1: " + item);
                    Thread.sleep(300); // Simulate some work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Consumer thread 2
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    Integer item = queue.dequeue();
                    System.out.println("Consumed by 2: " + item);
                    Thread.sleep(400); // Simulate some work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}