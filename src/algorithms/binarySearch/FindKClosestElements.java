package algorithms.binarySearch;

import java.util.*;

public class FindKClosestElements {
	public static List<Integer> findKClosestElements(int[] arr, int k, int x) {
		List<Integer> result = new ArrayList<>();
		if (arr == null || arr.length == 0)
			return result;
		// find largest small than location first
		int left = largestSmallerOrEqual(arr, x) + 1; // init larger than
		int right = left + k - 1;
		while (right > arr.length - 1 || left - 1 >= 0 && Math.abs(arr[left - 1] - x) <= Math.abs(arr[right] - x)) {
			left--;
			right--;
		}
		for (int i = left; i <= right; i++)
			result.add(arr[i]);
		return result;
	}

	private static int largestSmallerOrEqual(int[] arr, int target) {
		int left = 0, right = arr.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (arr[mid] == target)
				return mid;
			else if (arr[mid] < target)
				left = mid;
			else
				right = mid;
		}
		if (arr[right] <= target)
			return right;
		else if (arr[left] <= target)
			return left;
		return -1;
	}
}
