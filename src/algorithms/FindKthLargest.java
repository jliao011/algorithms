package algorithms;

import main.Core;

public class FindKthLargest {
	public static void test(int[] nums, int k) {
		int[] clone = nums.clone();
		int result = findKthLargest(clone, k);
		System.out.println("Find " + k + "th Largest is " + result + " in:");
		Core.print(nums);
	}

	public static int findKthLargest(int[] nums, int k) {
		// use quick sort partition
		int left = 0, right = nums.length - 1;
		k--;
		while (left < right) {
			int pivot = partition(nums, left, right);
			if (k == pivot)
				break;
			else if (k < pivot)
				right = pivot - 1;
			else
				left = pivot + 1;
		}
		return nums[k];
	}

	private static int partition(int[] nums, int left, int right) {
		// swap mid for better run time
		int mid = left + (right - left) / 2;
		swap(nums, mid, right);
		int pivot = nums[right];
		int wall = left - 1;
		for (int i = left; i < right; i++) {
			if (nums[i] >= pivot)
				swap(nums, ++wall, i);
		}
		swap(nums, ++wall, right);
		return wall;
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
