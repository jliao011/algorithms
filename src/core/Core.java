package core;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.text.html.parser.Entity;

import java.io.*;
import algorithms.dp.KnapSack;
import algorithms.dp.MaxSubarray;
import algorithms.dp.RegularExpressionMatch;
import algorithms.personal.GenericBST;
import algorithms.personal.GenericTreeNode;
import algorithms.personal.Other;
import algorithms.personal.ReverseWordsInString;
import algorithms.personal.StringDeduplication;
import algorithms.stack.NextLargerDistance;
import algorithms.trie.Trie;
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
//		MaxSubarray.test();
		Trie.test();
	}

	private static String parse(String s) {
		if (s == null || s.length() == 0)
			return "";
		char[] C = s.toCharArray();
		int head = -1;
		for (int tail = 0; tail < s.length(); tail++) {
			if (C[tail] != '#')
				C[++head] = C[tail];
			else if (head >= 0)
				head--;
		}
		return new String(C, 0, head + 1);
	}

	public int minWinSize(char[] inArr, char[] tgtArr) {
		if (inArr == null || tgtArr == null)
			return -1;
		if (inArr.length < tgtArr.length)
			return -1;
		int[] bin = new int[256];
		for (char c : tgtArr)
			bin[c]++;
		int minLen = inArr.length + 1;
		int head = 0, count = tgtArr.length;
		for (int tail = 0; tail < inArr.length; tail++) { // n
			if (bin[inArr[tail]] > 0)
				count--;
			bin[inArr[tail]]--;
			while (count == 0) {
				minLen = Math.min(minLen, tail - head + 1);
				if (bin[inArr[head]] == 0)
					count++;
				bin[inArr[head]]++;
				head++;
			}
		}
		return minLen > inArr.length ? -1 : minLen;
	}
}
