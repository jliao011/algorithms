package algorithms.stack;

import java.util.Stack;

public class LongestAbsoluteFilePath {
	/**
	 * leetcode 388
	 */
	public static int longestAbsoluteFilePath(String input) {
		// use stack,
		// for a level, stack saves only higher level len
		// if same level exit then pop
		if (input == null)
			return 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(0);  // dummy bottom
		int maxLen = 0;
		// every \n => a folder or a file
		for (String s : input.split("\n")) {
			// define the level
			// first level -1+1=0, \t=>1, \t\t=>2
			int level = s.lastIndexOf("\t") + 1;
			// check whether stack has only higher level
			// l0=>size 1,l1=>size 2,l2=>size 3
			while (stack.size() > level + 1) // find parent
				stack.pop();
			// push curr level len to stack, remove \t, add /
			int len = stack.peek() + s.length() - level + 1;
			stack.push(len);
			// if it's a file, compute maxlen
			if (s.contains("."))
				maxLen = Math.max(maxLen, len - 1);  // remove /
		}
		return maxLen;
	}
}
