package algorithms.binarySearch;

import java.util.*;

public class KthSmallestPairDistance {
	public static int kthSmallestPairDistance(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return 0;
		Arrays.sort(nums);
		int left = 0, right = nums[nums.length - 1] - nums[0];
		while (left < right) {
			int mid = left + (right - left) / 2;
			int count = 0;
			for (int i = 1, j = 0; i < nums.length; i++) {
				while (nums[i] - nums[j] > mid)
					j++;
				count += i - j;
			}
			if (count < k)
				left = mid + 1;
			else
				right = mid;
		}
		return left;
	}
}
