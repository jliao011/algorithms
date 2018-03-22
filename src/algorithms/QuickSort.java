package algorithms;

public class QuickSort {
	public static void quickSort(int[] list) {
		quickSort(list, 0, list.length - 1);
	}

	private static void quickSort(int[] list, int first, int last) {
		if (first < last) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}

	private static void swap(int[] list, int i, int j) {
		int temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}

	private static int partition(int[] list, int first, int last) {
		int pivot = list[last];
		int wall = first - 1;
		for (int i = first; i < last; i++) {
			if (list[i] <= pivot)
				swap(list, i, ++wall);
		}
		swap(list, last, ++wall);
		return wall;

//		int pivot = list[first];
//		int low = first + 1;
//		int high = last;
//		while (low < high) {
//			while (low <= high && list[low] <= pivot)
//				low++;
//			while (low <= high && list[high] > pivot)
//				high--;
//			if (low < high) {
//				int temp = list[high];
//				list[high] = list[low];
//				list[low] = temp;
//			}
//		}
//		while (high > first && list[high] >= pivot)
//			high--;
//		if (pivot > list[high]) {
//			list[first] = list[high];
//			list[high] = pivot;
//			return high;
//		} else
//			return first;
	}
}
