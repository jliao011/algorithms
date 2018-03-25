package algorithms.sort;

import java.util.Stack;

// numbers are in descending order in the result
public class StackSort {
	public static void stackSort(Stack<Integer> stack) {
		Stack<Integer> helper = new Stack<>();
		while (!stack.isEmpty()) {
			int curr = stack.pop();
			while (!helper.isEmpty() && helper.peek() < curr)
				stack.push(helper.pop());
			helper.push(curr);
		}
		while (!helper.isEmpty())
			stack.push(helper.pop());
	}
}
