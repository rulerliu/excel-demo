package com.mayikt.test;

public class JieChengTest {

	public static void main(String[] args) {
		System.out.println(jc(1));
		System.out.println(jc(2));
		System.out.println(jc(3));
		System.out.println(jc(4));
		
		System.out.println(getAlljc(4));
	}
	
	/**
	 * num的阶乘
	 * @param num
	 * @return
	 */
	private static Integer jc(int num) {
		if (num == 1) {
			return 1;
		}
		return num * jc(num - 1);
	}
	
	/**
	 * 4! + 3! + 2! + 1!
	 * @param num
	 * @return
	 */
	private static Integer getAlljc(int num) {
		if (num == 1) {
			return 1;
		}
		return jc(num) + getAlljc(num - 1);
	}
	
}
