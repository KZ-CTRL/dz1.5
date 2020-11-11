package com.company;

import java.util.concurrent.CountDownLatch;

public class Uploader extends Thread {
    private int size = 500;
    private int speed = 20;
    private CountDownLatch cdlUp;

    public Uploader(String name, CountDownLatch cdlUp) {
        super(name);
        this.cdlUp = cdlUp;
    }

    @Override
    public void run() {
        try {
            System.out.println("файл скачивается из сервера");
            sleep(size / speed);
            System.out.println(" Файл скачался");
            cdlUp.countDown();
        } catch (Exception ignore) {

        }
    }
}
