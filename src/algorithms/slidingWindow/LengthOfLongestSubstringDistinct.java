package algorithms.slidingWindow;

import java.util.*;
/*Given a string, find the length of the longest substring without repeating characters.
Examples:
Given "abcabcbb", the answer is "abc", which the length is 3.
Given "bbbbb", the answer is "b", with the length of 1.
Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, 
"pwke" is a subsequence and not a substring.*/

public class LengthOfLongestSubstringDistinct {

	public static int lengthOfLongestSubstringDistinct(String s) {
		int maxLen = 0;
		Map<Character, Integer> map = new HashMap<>();	// save character, index
		for (int head = 0, tail = 0; tail < s.length(); tail++) {
			// if duplicated, move head
			if (map.containsKey(s.charAt(tail)))
				// head cannot move back e.g. abbbbbba
				head = Math.max(head, map.get(s.charAt(tail)) + 1);
			map.put(s.charAt(tail), tail);
			maxLen = Math.max(maxLen, tail - head + 1);
		}
		return maxLen;
	}

	// optimized using space O(1)
	public static int lengthOfLongestSubstringDistinctOPT(String s) {
		int maxLen = 0;
		int[] bin = new int[26];	// case only alphabet, save index
		for (int head = 0, tail = 0; tail < s.length(); tail++) {
			head = Math.max(head, bin[s.charAt(tail) - 'a']);	// maybe 0
			bin[s.charAt(tail) - 'a'] = tail - 1;
			maxLen = Math.max(maxLen, tail - head + 1);
		}
		return maxLen;
	}
}
