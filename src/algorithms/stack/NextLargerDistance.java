package algorithms.stack;

import java.util.Stack;

public class NextLargerDistance {
	public static int[] nextDistance(int[] nums) {
		if (nums == null || nums.length == 0)
			return new int[0];
		int[] result = new int[nums.length];
		Stack<Integer> stack = new Stack<>();	// save index
		for (int i = 0; i < nums.length; i++) {
			while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
				int idx = stack.pop();
				result[idx] = nums[i];
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			result[stack.pop()] = -1;
		}
		return result;
	}
}
