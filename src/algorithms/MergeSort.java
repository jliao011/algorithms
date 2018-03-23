package algorithms;

import main.Core;

public class MergeSort {
	public static void mergeSort(int[] list) {
		if (list.length <= 1)
			return;
		mergeSort(list, 0, list.length - 1);

	}

	public static void test(int[] list) {
		System.out.println("Merge sort:");
		int[] result = list.clone();
		mergeSort(result);
		Core.print(list);
		Core.print(result);
	}

	private static void mergeSort(int[] list, int left, int right) {
		if (left >= right)
			return;
		int mid = left + (right - left) / 2;
		mergeSort(list, left, mid);
		mergeSort(list, mid + 1, right);
		merge(list, left, mid, right);
	}

	private static void merge(int[] list, int left, int mid, int right) {
		int[] R = new int[right - mid];
		for (int i = 0; i < R.length; i++)
			R[i] = list[mid + 1 + i];
		int i = mid - left, j = R.length - 1;
		while (i >= 0 && j >= 0) {
			list[right--] = list[left + i] > R[j] ? list[left + i--] : R[j--];
		}
		while (j >= 0)
			list[right--] = R[j--];
	}
}
