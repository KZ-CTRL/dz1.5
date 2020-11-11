package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) {

        CountDownLatch cdlup = new CountDownLatch(1);
        CountDownLatch cdlDown = new CountDownLatch(10);
        Semaphore semaphore = new Semaphore(3,true);
        Uploader uploader = new Uploader("Kайрат",cdlup );
        uploader.start();


        for (int i = 1; i < 11; i++) {
            new Downloander("Человек"+ i,semaphore,cdlup,cdlDown).start();
        }

        try {
             cdlDown.await();
            System.out.println("Файл удалиться через 10 сек");
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
