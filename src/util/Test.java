package util;

import java.util.List;

import algorithms.binarySearch.*;
import algorithms.sort.*;

public class Test {

	public static void findKthLargest(int[] nums, int k) {
		int[] clone = nums.clone();
		int result = FindKthLargest.findKthLargest(clone, k);
		System.out.println("Find " + k + "th Largest is " + result + " in:");
		Tool.print(nums);
	}

	public static void mergeSort(int[] list) {
		System.out.println("Merge sort:");
		int[] result = list.clone();
		MergeSort.mergeSort(result);
		Tool.print(list);
		Tool.print(result);
	}

	public static void quickSort(int[] list) {
		System.out.println("Quick sort:");
		int[] result = list.clone();
		QuickSort.quickSort(result);
		Tool.print(list);
		Tool.print(result);
	}

	public static void sortColors(String s) {
		System.out.println("Color sort:");
		System.out.println(s);
		System.out.println(SortColors.sortColors(s));
	}

	public static void searchRange(int[] nums, int target) {
		System.out.println("Search Range: " + target);
		Tool.print(nums);
		int[] result = SearchRange.searchRange(nums, target);
		Tool.print(result);
	}

	public static void findClosestElements(int[] arr, int k, int x) {
		System.out.println("Find " + k + " Closest Elements: " + x);
		Tool.print(arr);
		List<Integer> result = FindKClosestElements.findKClosestElements(arr, k, x);
		Tool.print(result);
	}
}
