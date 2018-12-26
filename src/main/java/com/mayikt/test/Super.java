package com.mayikt.test;

public class Super {
	
	static {
		System.out.println("Super static代码块...");
	}
	
	{
		System.out.println("Super 普通代码块...");
	}
	
	public Super() {
		System.out.println("Super 无参构造方法...");
	}
	
	public Super(int a) {
		System.out.println("Sub 有参构造方法..." + a);
	}
	
}
