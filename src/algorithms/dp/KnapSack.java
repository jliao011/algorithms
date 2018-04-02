package algorithms.dp;

public class KnapSack {
	/*
	 * Input: amount = 5, coins = [1, 2, 5]
	 * Output: 4
	 * Explanation: there are four ways to make up the amount:
	 * 5=5
	 * 5=2+2+1
	 * 5=2+1+1+1
	 * 5=1+1+1+1+1
	 */
	public static int coinChange2D(int amount, int[] coins) {
		int[][] dp = new int[coins.length + 1][amount + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= coins.length; i++) {
			dp[i][0] = 1;	// amount 0, count 1
			for (int j = 1; j <= amount; j++) {
				dp[i][j] = dp[i - 1][j] + (j - coins[i - 1] >= 0 ? dp[i][j - coins[i - 1]] : 0);
			}
		}
		return dp[coins.length][amount];
	}

	public static int coinChange1D(int amount, int[] coins) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for (int i = 0; i < coins.length; i++) {
			for (int j = 1; j <= amount; j++) {
				dp[j] += j - coins[i] >= 0 ? dp[j - coins[i]] : 0;
			}
		}
		return dp[amount];
	}

	/*
	 * Given n items with size Ai, an integer m denotes the size of a backpack.
	 * How full you can fill this backpack?
	 * 背包问题序列I
	 * 动态规划4要素
	 * State:
	 * f[i][S] “前i”个物品，取出一些能否组成和为S
	 * Function:
	 * f[i][S] = f[i-1][S - a[i]] or f[i-1][S]
	 * Initialize:
	 * f[i][0] = true; f[0][1..target] = false
	 * Answer:
	 * 检查所有的f[n][j]
	 */
	public static int backPack2D(int m, int[] A) {
		boolean[][] dp = new boolean[A.length + 1][m + 1];
		dp[0][0] = true;
		for (int i = 1; i <= A.length; i++) {
			for (int j = 0; j <= m; j++) {
				dp[i][j] = dp[i - 1][j] || (j - A[i - 1] >= 0 && dp[i - 1][j - A[i - 1]]);
			}
		}
		for (int j = m; j >= 0; j--) {
			if (dp[A.length][j]) {
				return j;
			}
		}
		return 0;
	}

	public int backPack1D(int m, int[] A) {
		if (A.length == 0)
			return 0;
		boolean[] dp = new boolean[m + 1];
		dp[0] = true;
		for (int i = 1; i <= A.length; i++) {
			for (int j = m; j >= 0; j--) {
				if (j - A[i - 1] >= 0 && dp[j - A[i - 1]]) {
					dp[j] = dp[j - A[i - 1]];
				}
			}
		}
		for (int i = m; i >= 0; i--) {
			if (dp[i])
				return i;
		}
		return 0;
	}
}
