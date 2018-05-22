package algorithms.stack;

import java.util.Stack;

public class LongestValidParentheses {
	public int longestValidParentheses(String s) {
		if (s == null || s.length() == 0)
			return 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1); // for first pair if ()
		int maxLen = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				stack.push(i);
			else {
				stack.pop();
				if (stack.isEmpty()) {
					stack.push(i);  // first not valid
				} else {
					maxLen = Math.max(maxLen, i - stack.peek());
				}
			}
		}
		return maxLen;
	}
}
