package com.mayikt.test;

public class RectangleTest {

	public static void main(String[] args) {
		print(5);
		System.out.println("----------------");
		print2(5);
	}

	/**
	 * 正三角
	 * @param rowCount 行数
	 */
	private static void print(int rowCount) {
		for (int i = 0; i < rowCount; i++) {
			// 0 2
			// 1 1
			// 2 0
			// 打印空格
			for (int j = 0; j < rowCount - i - 1; j++) {
				System.out.print(" ");
			}

			// 0 1	(0 * 2 + 1)
			// 1 3	(1 * 2 + 1)
			// 2 5	(2 * 2 + 1)
			// 打印*
			for (int j = 0; j < i * 2 + 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	/**
	 * 倒三角
	 * @param rowCount 行数
	 */
	private static void print2(int rowCount) {
		for (int i = 0; i < rowCount; i++) {
			// 0 0
			// 1 1
			// 2 2
			// 打印空格
			for (int j = 0; j < i; j++) {
				System.out.print(" ");
			}

			// 0 5	 (3 * 2 - 1)
			// 1 3   (2 * 2 - 1)
			// 2 1   (1 * 2 - 1)
			// 打印*
			for (int j = 0; j < (rowCount - i) * 2 - 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
