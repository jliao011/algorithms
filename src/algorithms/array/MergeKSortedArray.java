package algorithms.array;

import java.util.*;

public class MergeKSortedArray {
	public static int[] mergeKSortedArray(List<int[]> arrays) {
		if (arrays == null || arrays.size() == 0)
			return new int[0];
		int[] result = partition(arrays, 0, arrays.size() - 1);
		return result;
	}

	private static int[] partition(List<int[]> arrays, int left, int right) {
		if (left >= right)
			return arrays.get(right);
		int mid = left + (right - left) / 2;
		int[] a = partition(arrays, left, mid);
		int[] b = partition(arrays, mid + 1, right);
		return merge(a, b);
	}

	private static int[] merge(int[] a, int[] b) {
		int[] result = new int[a.length + b.length];
		int i = a.length - 1, j = b.length - 1, k = result.length - 1;
		while (i >= 0 && j >= 0)
			result[k--] = a[i] > b[j] ? a[i--] : b[j--];
		while (k > 0)
			result[k--] = i == 0 ? b[j--] : a[i--];
		return result;
	}
}
