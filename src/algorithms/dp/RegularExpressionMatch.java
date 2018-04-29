package algorithms.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegularExpressionMatch {
	private static final Logger logger = LoggerFactory.getLogger(RegularExpressionMatch.class);

	/**
	 * similar dp string comparison
	 * @param string
	 * @param pattern
	 * @return whether match
	 * @author lattes
	 */
	public static boolean regularExpressionMatch(String s, String p) {
		if (s == null || p == null)
			return false;
		logger.debug("String length {}, pattern length {}", s.length(), p.length());
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;
		// initial the first line
		for (int j = 1; j < p.length(); j++) {
			if (p.charAt(j - 1) == '*')
				dp[0][j] = dp[0][j - 2];
		}
		// dp conditions:
		// (1) p.char == . || p.char == s.char
		// (2) p.char == *:
		// 1. treat as 0 : check i, j-2
		// 2. if p[j-1] == s.char || .: check i-1,j
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= p.length(); j++) {
				if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1))
					dp[i][j] = dp[i - 1][j - 1];
				else if (p.charAt(j - 1) == '*') {
					// check * is 0
					dp[i][j] = dp[i][j - 2];
					if (dp[i][j])
						continue;
					// if p.prev equal or .
					if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
			logger.debug("{} th row: {}", i, dp[i]);
		}
		return dp[s.length()][p.length()];
	}

	/*
	 * '?' Matches any single character.
	 * '*' Matches any sequence of characters (including the empty sequence).
	 * The matching should cover the entire input string (not partial).
	 * The function prototype should be:
	 * bool isMatch(const char *s, const char *p)
	 * Some examples:
	 * isMatch("aa","a") → false
	 * isMatch("aa","aa") → true
	 * isMatch("aaa","aa") → false
	 * isMatch("aa", "*") → true
	 * isMatch("aa", "a*") → true
	 * isMatch("ab", "?*") → true
	 * isMatch("aab", "c*a*b") → false
	 */
	// same as previous
	public static boolean wildCardMatching(String s, String p) {
		// 1 p char == s char || p char is ?
		// 2 p char is *
		// * is "" check i,j-1
		// * is multi check i-1,j
		if (s == null || p == null)
			return false;
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;
		// init first row
		for (int j = 1; j <= p.length(); j++)
			if (p.charAt(j - 1) == '*')
				dp[0][j] = dp[0][j - 1];
		// dp
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= p.length(); j++) {
				// p char == s char || p char is ?
				if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?')
					dp[i][j] = dp[i - 1][j - 1];
				else if (p.charAt(j - 1) == '*') {
					// * is "" || 8 is "??????"
					dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
				}
			}
		}
		return dp[s.length()][p.length()];
	}

	/*
	 * Given a string S and a string T,
	 * count the number of distinct subsequences of S which equals T.
	 * 
	 * A subsequence of a string is a new string which is formed from
	 * the original string by deleting some (can be none) of the characters
	 * without disturbing the relative positions of the remaining characters.
	 * (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
	 */
	public static int distinctSubsequences(String s, String t) {
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

	/*
	 * Given two words word1 and word2, find the minimum number of
	 * operations required to convert word1 to word2.
	 * You have the following 3 operations permitted on a word:
	 * Insert a character
	 * Delete a character
	 * Replace a character
	 */
	public int editDistance(String word1, String word2) {
		// similar regex
		if (word1 == null || word2 == null)
			return 0;
		// row word1, col word2
		int[][] dp = new int[word2.length() + 1][word1.length() + 1];
		// init first row: word1 to ""
		for (int j = 0; j < word1.length(); j++)
			dp[0][j + 1] = dp[0][j] + 1;
		// init first col: "" to word2
		for (int i = 0; i < word2.length(); i++)
			dp[i + 1][0] = dp[i][0] + 1;
		// the other
		for (int i = 0; i < word2.length(); i++) {
			for (int j = 0; j < word1.length(); j++) {
				// char1 != char2:
				// 1. insert: dp[i][j] = dp[i-1][j]+1;
				// 2. replace: dp[i][j] = dp[i-1][j-1]+1;
				// 3. delete: dp[i][j] = dp[i][j-1]+1;
				if (word2.charAt(i) != word1.charAt(j)) {
					dp[i + 1][j + 1] = 1 + Math.min(dp[i][j], Math.min(dp[i][j + 1], dp[i + 1][j]));
				} else // case char1 == char2
					dp[i + 1][j + 1] = dp[i][j];
			}
		}
		return dp[word2.length()][word1.length()];
	}

	/*
	 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
	 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
	 * Output: true
	 */
	public static boolean interleavingString(String s1, String s2, String s3) {
		if (s1 == null || s2 == null || s3 == null)
			return false;
		if (s1.length() + s2.length() != s3.length())
			return false;
		// s2 row, s1 col
		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		dp[0][0] = true; // "" + "" == ""
		// init first row: check j-1 && char==char for s2
		for (int j = 0; j < s2.length(); j++)
			dp[0][j + 1] = dp[0][j] && s2.charAt(j) == s3.charAt(j);
		// init first col: check i-1 && char==char for s1
		for (int i = 0; i < s1.length(); i++)
			dp[i + 1][0] = dp[i][0] && s1.charAt(i) == s3.charAt(i);
		// dp
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				// case1, add s1.char at tail
				// [i-1][j] && s1.char==s3.char
				// case2, add s2.char at tail
				// [i][j-1] && s2.char==s3.char
				char c1 = s1.charAt(i);
				char c2 = s2.charAt(j);
				char c3 = s3.charAt(i + j + 1);
				dp[i + 1][j + 1] = (dp[i][j + 1] && c1 == c3) || (dp[i + 1][j] && c2 == c3);
				logger.debug("dp[{}][{}] = (dp[{}][{}] && c1:{} == c3:{}) || " + "(dp[{}][{}] && c2:{} == c3:{});{}",
						i + 1, j + 1, i, j + 1, c1, c3, i + 1, j, c2, c3, dp[i + 1][j + 1]);
			}
		}
		for (int i = 0; i <= s1.length(); i++)
			logger.debug("{}", dp[i]);
		return dp[s1.length()][s2.length()];
	}
}
