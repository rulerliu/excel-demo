package com.mayikt.test;

import java.text.DateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mayikt.exception.ResourceException;

public class MyTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MyTest.class);
	
	private static Integer value = 1;

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
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		DateFormat df = DateFormat.getDateTimeInstance();
		System.out.println(df.format(new Date()));
		System.out.println(new Date().toLocaleString());
		
		System.out.println(System.currentTimeMillis() - start);
		
		Integer l = null;
		System.out.println(l == 1);
		
//		try {
//			Long a = null;
//			System.out.println(a == 1L);
//		} catch (Exception e2) {
//			e2.printStackTrace();
//			System.out.println("hahahahahha----" + e2.getMessage());
//			throw new ResourceException("0001", "hahahahahha----" + e2.getMessage());
//		}
		
	}

}
