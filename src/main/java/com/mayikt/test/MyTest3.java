package com.mayikt.test;

public class MyTest3 {

	public static void main(String[] args) {
		int a = 60; // 60 = 0011 1100
		int b = 13; // 13 = 0000 1101
		
		// 且运算符，两个都是1，结果才是1，0000 1100
		System.out.println(a & b); // 12
		
		// 或运算符，有一个1，就是1，0011 1101
		System.out.println(a | b); // 61
		
		// 异运算符，不相同，才是1，0011 0001
		System.out.println(a ^ b); // 49
		
		// 取反运算符，1100 0011
		System.out.println(~a); // -61  -64 + 2 + 1
		// 取反运算符，1111 0010
		System.out.println(~b); // -14  -64 + 32 + 16 + 2
		
		System.out.println(a << 2);
		
		System.out.println(a >> 2);
		
		System.out.println(Integer.toBinaryString(-61));
		System.out.println(Integer.toBinaryString(-14));
		
	}

}
