package algorithms.dp;

public class DistinctSubsequences {
	/*
	 * Given a string S and a string T,
	 * count the number of distinct subsequences of S which equals T.
	 * 
	 * A subsequence of a string is a new string which is formed from
	 * the original string by deleting some (can be none) of the characters
	 * without disturbing the relative positions of the remaining characters.
	 * (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
	 */
	public static int numDistinct(String s, String t) {
		// similar to regex 10
		// use dp
		if (s == null || t == null)
			return 0;
		// rows t, columns s, remove char from s
		int[][] dp = new int[t.length() + 1][s.length() + 1];
		// init first row: remove all to get ""
		for (int col = 0; col <= s.length(); col++)
			dp[0][col] = 1;
		// init first col: should all be 0: "" cannot get un-""
		for (int i = 0; i < t.length(); i++) {    // for t
			for (int j = 0; j < s.length(); j++) { // for s
				// 1. if same char: keep it || remove it
				// if keep dp[i][j] = dp[i-1][j-1];
				// if remove dp[i][j] = dp[i][j-1];
				if (s.charAt(j) == t.charAt(i)) {
					dp[i + 1][j + 1] = dp[i][j] + dp[i + 1][j];
				} else {  // 2. if not same: remove it
					dp[i + 1][j + 1] = dp[i + 1][j];
				}
			}
		}
		return dp[t.length()][s.length()];
	}
}
