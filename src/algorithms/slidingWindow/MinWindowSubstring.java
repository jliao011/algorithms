package algorithms.slidingWindow;

public class MinWindowSubstring {
	/*
	 * Given a string S and a string T, find the minimum window in S which will
	 * contain all the characters in T in complexity O(n).
	 * 
	 * For example,
	 * S = "ADOBECODEBANC"
	 * T = "ABC"
	 * Minimum window is "BANC".
	 * 
	 * Note:
	 * If there is no such window in S that covers all characters in T, return the
	 * empty string "".
	 * 
	 * If there are multiple such windows, you are guaranteed that there will always
	 * be only one unique minimum window in S.
	 */
	public static String minWindowSubstring(String s, String t) {
		int[] bin = new int[256];
		int minLen = s.length() + 1, count = t.length();
		int head = 0, minHead = 0;
		for (char c : t.toCharArray())
			bin[c]++;
		for (int tail = 0; tail < s.length(); tail++) {
			if (bin[s.charAt(tail)] > 0)
				count--;
			bin[s.charAt(tail)]--;
			// while meet the requirement
			while (count == 0) {
				if (tail - head + 1 < minLen) {
					minHead = head;
					minLen = tail - head + 1;
				}
				if (bin[s.charAt(head)] == 0)
					count++;
				bin[s.charAt(head)]++;
				head++;
			}
		}
		return minLen > s.length() ? "" : s.substring(minHead, minHead + minLen);
	}
}
