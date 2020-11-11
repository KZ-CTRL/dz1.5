package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloander extends Thread {
    private int speed = 100;
    private int size = 500;
    private Semaphore semaphore;
    private CountDownLatch cdlUp;

    public Downloander(String name, Semaphore semaphore, CountDownLatch cdlUp, CountDownLatch cdlDown) {
        super(name);
        this.semaphore = semaphore;
        this.cdlUp = cdlUp;
        this.cdlDown = cdlDown;
    }

    private CountDownLatch cdlDown;

    @Override
    public void run() {
        try {
            cdlUp.await();
            semaphore.acquire();
            System.out.println(getName() + " Начал скачивать");
            sleep(size / speed);
            System.out.println(getName() + "Скачал");
            semaphore.release();
            cdlDown.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
