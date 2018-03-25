package algorithms.stack;

import java.util.Stack;

public class LargestHistogramRectangle {
	public static int largestHigtogramRectangle(int[] heights) {
		Stack<Integer> idxStack = new Stack<>();
		int maxArea = 0;
		for (int i = 0; i <= heights.length; i++) {
			int height = i == heights.length ? -1 : heights[i];
			while (!idxStack.isEmpty() && heights[idxStack.peek()] >= height) {
				int h = heights[idxStack.pop()];
				int w = idxStack.isEmpty() ? i : i - 1 - idxStack.peek();
				maxArea = Math.max(maxArea, h * w);
			}
			idxStack.push(i);
		}
		return maxArea;
	}
}
