package core;

import java.lang.reflect.Array;
import java.util.*;

import algorithms.dp.KnapSack;
import algorithms.personal.ReverseWordsInString;
import algorithms.personal.StringDeduplication;
import util.structure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.*;

public class Core {
	private final static Logger logger = LoggerFactory.getLogger(Core.class);

	public static void main(String[] args) {
		logger.debug("Core main function runs");
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
//		Test.mergeKSortedLists(10);
//		Test.mergeKSortedArray(3);
		arrayNesting(new int[] { 5, 4, 0, 3, 1, 6, 2 });
	}

	public static int arrayNesting(int[] nums) {
		int result = 0;
		// only visit once, could modify nums
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == -1)
				continue;
			int count = 0, curr = i;
			while (nums[curr] != -1) {
				int idx = nums[curr];
				logger.debug("i={},idx={},curr={}", i, idx, curr);
				nums[curr] = -1;
				curr = idx;
				count++;
			}
			result = Math.max(result, count);
			count = 0;
		}
		return result;
	}

}
