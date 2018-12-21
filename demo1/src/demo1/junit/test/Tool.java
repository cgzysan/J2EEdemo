package demo1.junit.test;

import java.util.Arrays;

import org.junit.Test;

public class Tool {
	
	public static void main(String[] args) {
		
	}
	
	@Test
	public static int getMax() {
		int a = 3;
		int b = 19;
		int max = a > b ? a : b;
//		System.out.println("×î´óÖµ =  " + max);
		return max;
	}
	
	@Test
	public static void sort() {
		int[] arr = {12, 3, 6, 20, 1};
		System.out.println(Arrays.toString(arr));
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}
}
