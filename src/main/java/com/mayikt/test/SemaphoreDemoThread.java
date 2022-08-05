package com.mayikt.test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class SemaphoreDemoThread implements Runnable {
    private Random rnd = new Random();

    private String id;
    private Semaphore semaphore;

    public SemaphoreDemoThread(String id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("Thread " + id + " is working");
            Thread.sleep(rnd.nextInt(1000));
            semaphore.release();
            System.out.println("Thread " + id + " is over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Semaphore smp = new Semaphore(3);
        //注意我创建的线程池类型，
        ExecutorService se = Executors.newCachedThreadPool();
        se.submit(new SemaphoreDemoThread("a", smp));
        se.submit(new SemaphoreDemoThread("b", smp));
        se.submit(new SemaphoreDemoThread("c", smp));
        se.submit(new SemaphoreDemoThread("d", smp));
        se.submit(new SemaphoreDemoThread("e", smp));
        se.submit(new SemaphoreDemoThread("g", smp));
        se.submit(new SemaphoreDemoThread("h", smp));
        se.submit(new SemaphoreDemoThread("i", smp));
        se.shutdown();
    }

}