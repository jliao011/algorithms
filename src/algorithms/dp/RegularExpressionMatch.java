package algorithms.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegularExpressionMatch {
	private static final Logger logger = LoggerFactory.getLogger(RegularExpressionMatch.class);

	/**
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
	public static boolean isMatch(String s, String p) {
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
}
