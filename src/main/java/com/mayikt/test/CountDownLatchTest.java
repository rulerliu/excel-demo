package com.mayikt.test;
import java.util.concurrent.CountDownLatch;

/**
 * 主要方法：countDown()、await()
 * downLatch.countDown(); //数量-1
 * downLatch.await();//等待计数器归零，然后再往下执行
 * 每次线程调用countDown()数量-1，假设计数器变成0，downLatch.await()就会被唤醒，继续执行！
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "出去了");
                downLatch.countDown();
            }, String.valueOf(i)).start();
        }
        downLatch.await();
        System.out.println("主线程关门！");
    }

}
