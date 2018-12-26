package com.mayikt.test;

public class Sub extends Super {
	
	static {
		System.out.println("Sub static代码块...");
	}
	
	{
		System.out.println("Sub 普通代码块...");
	}
	
	public Sub() {
		System.out.println("Sub 无参构造方法...");
	}
	
	public Sub(int a) {
		System.out.println("Sub 有参构造方法..." + a);
	}
	
}
