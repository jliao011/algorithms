package algorithms.bitManipulation;

public class SingleNumber {
	/*
	 * general case for single number II
	 * “Given an array of integers, every element appears k (k > 1) times except for
	 * one,
	 * which appears p times (p >= 1, p % k != 0). Find that single one.”
	 */
	public static int singleNumberK(int[] nums) {
		// https://leetcode.com/problems/single-number-ii/discuss/43295/Detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers
		return 0;
	}

	/*
	 * Given an array of integers, every element appears twice except for one. Find
	 * that single one.
	 */
	public static int singleNumber(int[] nums) {
		int result = 0;
		for (int num : nums)
			result ^= num;
		return result;
	}

	/*
	 * Given an array of integers, every element appears three times except for one,
	 * which appears exactly once. Find that single one.
	 */
	public static int singleNumberII(int[] nums) {
		int one = 0, two = 0;
		// if a number appear 0,1,2, the pattern is 00,01,10
		for (int num : nums) {
			one = (one ^ num) & ~two;	// == 01 when appear once
			two = (two ^ num) & ~one;	// == 10 when appear twice
		}
		return one;
	}

	/*
	 * Given an array of numbers nums, in which exactly two elements appear only
	 * once
	 * and all the other elements appear exactly twice.
	 * Find the two elements that appear only once.
	 */
	public static int[] singleNumberIII(int[] nums) {
		// xor all numbers, result diff is A ^ B
		int diff = 0;
		for (int num : nums)
			diff ^= num;
		// diff has more than 1 set bit, find one of them
		// according to 2's complement, following has always 1 bit set
		// because diff is not 0,
		diff &= ~(diff - 1);	// can be -diff, the same
		int[] result = new int[] { 0, 0 };
		for (int num : nums) {
			// check whether that bit is set
			// because A and B's that bit is different
			// all other num ^= 0
			if ((num & diff) == 0)
				result[0] ^= num;
			else
				result[1] ^= num;
		}
		return result;
	}
}
