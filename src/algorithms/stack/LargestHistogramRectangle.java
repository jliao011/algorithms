package algorithms.stack;

import java.util.Stack;

public class LargestHistogramRectangle {
	// Maintains an increasing stack
	// encounter smaller height, check all previous areas.
	public static int largestHigtogramRectangle(int[] heights) {
		Stack<Integer> idxStack = new Stack<>();
		int maxArea = 0;
		for (int i = 0; i <= heights.length; i++) {
			// push -1 for last, to pop all previous
			int height = i == heights.length ? -1 : heights[i];
			// when increasing is broken
			while (!idxStack.isEmpty() && heights[idxStack.peek()] >= height) {
				// get previous higher one, compute previous area
				// it's all area from smaller to the highest
				int h = heights[idxStack.pop()];
				// if is empty, there is a bottom rectangle from current to head
				int w = idxStack.isEmpty() ? i : i - 1 - idxStack.peek();
				maxArea = Math.max(maxArea, h * w);
			}
			idxStack.push(i);
		}
		return maxArea;
	}
}
