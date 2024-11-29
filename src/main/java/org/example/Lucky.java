package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Lucky {
    static int x = 0;
    static int count = 0;
    static final Object lock = new Object();
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    /*static final CommonResource commonResource = new CommonResource();
    static class CommonResource{
        int x = 0;
    }*/

    static class LuckyThread extends Thread {
        //static class LuckyThread implements Runnable {
        @Override
        public void run() {
            synchronized(lock){
                while (x < 999999) {
                    x++;
                    if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
                            % 10 + (x / 10000) % 10 + (x / 100000) % 10) {
                        System.out.println(x);
                        count++;
                        atomicInteger.incrementAndGet();
                        //commonResource.x++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*Runnable r = ()->{
        while (x < 999999) {
            x++;
            if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
                    % 10 + (x / 10000) % 10 + (x / 100000) % 10) {
                System.out.println(x);

                count++;
            }
        }
        };*/

        /*Thread t1 = new Thread(r,"t1");
        Thread t2 = new Thread(r,"t2");
        Thread t3 = new Thread(r,"t3");*/

        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();

        /*Thread t1 = new Thread(new LuckyThread());
        Thread t2 = new Thread(new LuckyThread());
        Thread t3 = new Thread(new LuckyThread());*/

        t1.start();
        t2.start();
        t3.start();
        t3.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count + "\n" + atomicInteger);
    }
}