package com.mayikt.test;

public class RecursionTest {

	public static void main(String[] args) {
//		System.out.println(recursion(-1));
//		System.out.println(recursion(1));
//		System.out.println(recursion(2));
//		System.out.println(recursion(3));
//		System.out.println(recursion(4));
//		System.out.println(recursion(5));
//		System.out.println(recursion(6));
//		System.out.println(recursion(7));
//		System.out.println(recursion(8));
		
		
		System.out.println(recursion2(-1));
		System.out.println(recursion2(1));
		System.out.println(recursion2(2));
		System.out.println(recursion2(3));
		System.out.println(recursion2(4));
		System.out.println(recursion2(5));
		System.out.println(recursion2(6));
		System.out.println(recursion2(7));
		System.out.println(recursion2(8));
	}
	
	/**
	 * 1 1 2 3 5 8 13 21 ...
	 * @param index 索引，从1开始
	 */
	private static Integer recursion(int index) {
		if (index <= 0) {
			return null;
		}
		if (index <= 2) {
			return 1;
		}
		return recursion(index - 1) + recursion(index - 2);
	}
	
	/**
	 * 0 1 1 2 3 5 8 13 21 ...
	 * @param index 索引，从1开始
	 */
	private static Integer recursion2(int index) {
		if (index <= 0) {
			return null;
		}
		if (index == 1) {
			return 0;
		}
		if (index <= 3) {
			return 1;
		}
		return recursion2(index - 1) + recursion2(index - 2);
	}
	
}
