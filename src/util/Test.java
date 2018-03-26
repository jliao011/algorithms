package util;

import java.util.*;

import algorithms.binarySearch.*;
import algorithms.slidingWindow.LengthOfLongestSubstringTwoDistinct;
import algorithms.slidingWindow.MaxSlidingWindow;
import algorithms.slidingWindow.MinWindowSubstring;
import algorithms.sort.*;
import algorithms.stack.*;

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

	public static void stackSort(Stack<Integer> stack) {
		System.out.println("Stack sort:");
		Tool.print(stack);
		StackSort.stackSort(stack);
		Tool.print(stack);
	}

	public static void largestHistogramRectangle(int[] heights) {
		System.out.println("Largest Rectangle");
		Tool.print(heights);
		int result = LargestHistogramRectangle.largestHigtogramRectangle(heights);
		System.out.println(result);
	}

	public static void maxSlidingWindow(int[] nums, int k) {
		System.out.println("Max Sliding Window Size " + k);
		Tool.print(nums);
		int[] result = MaxSlidingWindow.maxSlidingWindow(nums, k);
		Tool.print(result);
	}

	public static void lengthOfLongestSubStringTwoDistinct(String s) {
		System.out.println("Length of longest substring two distince");
		System.out.println(s);
		int result = LengthOfLongestSubstringTwoDistinct.lengthOfLongestSubStringTwoDistinct(s);
		System.out.println("result is " + result);
	}

	public static void minWindowSubstring(String s, String t) {
		System.out.println("Minimum Window Substring: " + s + ", " + t);
		String result = MinWindowSubstring.minWindowSubstring(s, t);
		System.out.println(result);
	}

	public static void kthSmallestSortedMatrix(int k) {
		System.out.println(k + " th smallest element in sorted matrix:");
		int[][] matrix = new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
		Tool.print(matrix);
		int result = KthSmallestInSortedMatrix.kthSmallestInSortedMatrix(matrix, k);
		int result2 = KthSmallestInSortedMatrix.kthSmallest(matrix, k);
		System.out.println("Heap: " + result + " BinarySearch: " + result2);

	}
}
