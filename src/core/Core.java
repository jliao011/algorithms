package core;

import java.lang.reflect.Array;
import java.util.*;

import algorithms.dp.KnapSack;
import algorithms.personal.ReverseWordsInString;
import algorithms.personal.StringDeduplication;
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

	}

	public List<String> wordBreak(String s, Set<String> wordDict) {
		return word_Break(s, wordDict, 0);
	}

	HashMap<Integer, List<String>> map = new HashMap<>();

	public List<String> word_Break(String s, Set<String> wordDict, int start) {
		if (map.containsKey(start)) {
			return map.get(start);
		}
		LinkedList<String> res = new LinkedList<>();
		if (start == s.length()) {
			res.add("");
		}
		for (int end = start + 1; end <= s.length(); end++) {
			if (wordDict.contains(s.substring(start, end))) {
				List<String> list = word_Break(s, wordDict, end);
				for (String l : list) {
					res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
				}
			}
		}
		map.put(start, res);
		return res;
	}

}
