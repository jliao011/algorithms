package core;

import java.util.*;

import util.*;

public class Core {
	public static void main(String[] args) {
		int[] list = { 2, 5, 1, 8, 9, 6, 3, 7, 4 };
		Test.mergeSort(list);
		Test.quickSort(list);
		Test.sortColors("abcbcbacbacbaabccb");
		Test.findKthLargest(list, 1);
		Test.searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 5);
		Test.findClosestElements(new int[] { 1, 2, 3, 4, 5 }, 4, 3);

		Test.stackSort(Tool.getStack(10));

	}

}
