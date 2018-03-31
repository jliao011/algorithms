package algorithms.binarySearch;

public class MedianOfTwoSortedArray {
	public static double medianOfTwoSortedArray(int[] nums1, int[] nums2) {
		// 1. A left count i: 0 ~ i-1, right count m-i: i ~ m-1;
		// 2. B left count j: 0 ~ j-1, right count n-j: j ~ n-1;
		// 3. condition A.left + B.left == A.right + B.right
		// i + j == m - i + n - j => j = (m+n)/2 - i
		// 4. condition i -> [0,m], j>=0 => m <= n
		int m = nums1.length, n = nums2.length;
		// m must less than n
		if (m > n)
			return medianOfTwoSortedArray(nums2, nums1);
		int iMin = 0, iMax = m;
		while (iMin <= iMax) {
			int i = iMin + (iMax - iMin) / 2;
			int j = (m + n + 1) / 2 - i;    // +1 to ensure left max
			// case i too small, increase i
			if (i < m && nums2[j - 1] > nums1[i]) {
				iMin = i + 1;
				// case i too large, decrease i
			} else if (i > 0 && nums1[i - 1] > nums2[j]) {
				iMax = i - 1;
				// perfect condition
			} else {
				// find left.max first, if odd return left.max
				int left = 0;
				if (i == 0)
					// nums1 all after median
					left = nums2[j - 1];
				else if (j == 0)
					// nums2 all after median
					left = nums1[i - 1];
				else
					left = Math.max(nums1[i - 1], nums2[j - 1]);
				// if odd return left
				if ((m + n) % 2 == 1)
					return left;
				// find right.min then
				int right = 0;
				if (i == m)
					// nums1 all before median
					right = nums2[j];
				else if (j == n)
					// nums2 all before median
					right = nums1[i];
				else
					right = Math.min(nums1[i], nums2[j]);

				return (double) (left + right) / 2;
			}
		}
		return 0.0;
	}
}
