package com.mayikt.test;

import java.text.DateFormat;
import java.util.Date;

public class MyTest {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		try {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		try {
//			Thread.sleep(1000);
//			System.out.println(Thread.currentThread().getName());
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		new Thread(() -> {
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		DateFormat df = DateFormat.getDateTimeInstance();
		System.out.println(df.format(new Date()));
		System.out.println(new Date().toLocaleString());
		
		System.out.println(System.currentTimeMillis() - start);
	}

}
