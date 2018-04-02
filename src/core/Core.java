package core;

import java.lang.reflect.Array;
import java.util.*;

import algorithms.dp.KnapSack;
import util.structure.*;

import util.*;

public class Core {
	public static void main(String[] args) {
//		Test.mergeSort(Tool.getIntArray(10, 100));
//		Test.quickSort(list);
//		Test.sortColors("abcbcbacbacbaabccb");
//		Test.findKthLargest(list, 1);
//		Test.searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 5);
//		Test.findClosestElements(new int[] { 1, 2, 3, 4, 5 }, 4, 3);
//		Test.stackSort(Tool.getIntStack(10, 10));
//		Test.largestHistogramRectangle(Tool.getIntArray(3, 4));
//		Test.maxSlidingWindow(Tool.getIntArray(10, 20), 3);
//		Test.lengthOfLongestSubStringTwoDistinct("ecebaaabbbabaabbccabbccabc");
//		Test.minWindowSubstring("ADOBECODEBANC", "ABC");
//		Test.kthSmallestSortedMatrix(4);
//		Test.kthSmallestPrimeFraction(new int[] { 1, 2, 3, 5 }, 3);
//		Test.KthSmallestPairDistance(Tool.getSortedIntArray(5, 0, 5, false), 2);
//		Test.mergeKSortedLists(5);
//		Test.mergeKSortedArray(3);
		String a = "";
		System.out.println(KnapSack.backPack2D(11, new int[] { 2, 3, 5, 7 }));
	}

	public static <K> void test(K[] array) {
		String name = array.getClass().getName();
		System.out.println(name);

	}
}
