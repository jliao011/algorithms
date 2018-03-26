package algorithms.slidingWindow;

public class LengthOfLongestSubstringTwoDistinct {
	/*
	 * Given a string, find the length of the longest substring T that contains at
	 * most 2 distinct characters.
	 * For example, Given s = “eceba”,
	 * T is "ece" which its length is 3.
	 */
	public static int lengthOfLongestSubStringTwoDistinct(String s) {
		int[] bin = new int[256];
		int maxLen = 0, head = 0, count = 0;
		for (int tail = 0; tail < s.length(); tail++) {
			// if distinct, then count
			if (bin[s.charAt(tail)] == 0)
				count++;
			bin[s.charAt(tail)]++;
			// case more than 2 distinct, move head
			while (count > 2) {
				if (bin[s.charAt(head)] == 1)
					count--;
				bin[s.charAt(head)]--;
				head++;
			}
			maxLen = Math.max(maxLen, tail - head + 1);
		}
		return maxLen;
	}
}
