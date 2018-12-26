package com.mayikt.test;

public class ExtendTest {
	
	static {
		System.out.println("主类static代码块...");
	}
	
	{
		System.out.println("主类普通代码块...");
	}
	
	public ExtendTest() {
		System.out.println("主类构造方法...");
	}

	public static void main(String[] args) {
		Super b = new Sub(5);
		System.out.println("---------------");
		System.out.println("main方法中的函数1");
		{
			System.out.println("main方法中的普通代码块1");
		}
		System.out.println("main方法中的函数2");
		{
			System.out.println("main方法中的普通代码块2");
		}
	}
	
}
