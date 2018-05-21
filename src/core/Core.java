package core;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import algorithms.dp.KnapSack;
import algorithms.dp.RegularExpressionMatch;
import algorithms.personal.GenericBST;
import algorithms.personal.GenericTreeNode;
import algorithms.personal.Other;
import algorithms.personal.ReverseWordsInString;
import algorithms.personal.StringDeduplication;
import util.structure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.*;

public class Core {
	private final static Logger logger = LoggerFactory.getLogger(Core.class);

	public static void main(String[] args) throws IOException {
		logger.info("Core main function runs");
		tests();

	}

	private static void tests() {
		logger.info("test function runs");
//		RegularExpressionMatch.interleavingString("dbbca", "aabcc", "aadbbcbcac");
		// Test.mergeSort(Tool.getIntArray(10, 100));
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
//		arrayNesting(new int[] { 5, 4, 0, 3, 1, 6, 2 });
//		Test.regularExpressionMatch("xabyc", "xa*b.*");
//		Test.inorderPreorderGetPostorder(new int[] { 1, 2, 3, 4, 5, 6, 7 }, new int[] { 4, 2, 1, 3, 6, 5, 7 });
//		List<String> list = Arrays.asList("ah", "aa", "ac", "az", "aj");
//		GenericBST<String> tree = new GenericBST<>(list);
//		List<String> result = tree.preorder();
//		logger.info("{}", result);
//		GTree.buildTree();
		logger.info("{}", squareArray(new int[] { -4, -3, -2, -1 }));
	}

	public static int[] squareArray(int[] nums) {
		int[] result = new int[nums.length];
		int i = 0, j = nums.length - 1, k = j;
		while (k >= 0) {
			if (Math.abs(nums[i]) >= Math.abs(nums[j])) {
				result[k--] = nums[i] * nums[i];
				i++;
			} else {
				result[k--] = nums[j] * nums[j];
				j--;
			}
		}
		return result;
	}

}
