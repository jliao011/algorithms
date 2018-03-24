package algorithms.binarySearch;

import core.Core;

public class SearchRange {
	public static int[] searchRange(int[] nums, int target) {
		// first appear and last appear
		// notice last appear check right first
		int[] result = new int[2];
		result[0] = result[1] = -1;
		if (nums == null || nums.length == 0)
			return new int[] { -1, -1 };
		result[0] = firstAppear(nums, target);
		if (result[0] == -1)
			return result;
		result[1] = lastAppear(nums, target);
		return result;
	}

	private static int firstAppear(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (nums[mid] >= target)
				right = mid;
			else
				left = mid;
		}
		if (nums[left] == target)
			return left;
		else if (nums[right] == target)
			return right;
		return -1;
	}

	private static int lastAppear(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (nums[mid] <= target)
				left = mid;
			else
				right = mid;
		}
		if (nums[right] == target)
			return right;
		else if (nums[left] == target)
			return left;
		return -1;
	}
}
