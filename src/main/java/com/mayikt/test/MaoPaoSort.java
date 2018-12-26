package com.mayikt.test;

public class MaoPaoSort {

	public static void main(String[] args) {
		int[] array = new int[] {4, 3, 5, 2, 1};
		print(array);
		sort(array);
		print(array);
	}

	private static void sort(int[] array) {
		// 数组长度为n，排序n - 1次
		for (int i = 0; i < array.length - 1; i++) {
			// 第i次排序，比较n - 1 - i次
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	private static void print(int[] array) {
		boolean flag = false;
		for (int i : array) {
			if (flag) {
				System.out.print(", ");
			}
			flag = true;
			System.out.print(i);
		}
		System.out.println();
	}
	
}
