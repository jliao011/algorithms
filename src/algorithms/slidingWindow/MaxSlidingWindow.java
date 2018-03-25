package algorithms.slidingWindow;

import java.util.*;

public class MaxSlidingWindow {
	// Window position Max
	// --------------- -----
	// [1 3 -1] -3 5 3 6 7 3
	// 1 [3 -1 -3] 5 3 6 7 3
	// 1 3 [-1 -3 5] 3 6 7 5
	// 1 3 -1 [-3 5 3] 6 7 5
	// 1 3 -1 -3 [5 3 6] 7 6
	// 1 3 -1 -3 5 [3 6 7] 7
	// use dequeue, always keep max at front
	public static int[] maxSlidingWindow(int[] nums, int k) {
		int[] result = new int[nums.length - k + 1];
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 0; i < k - 1; i++)
			inQueue(deque, nums[i]);
		for (int i = k - 1; i < nums.length; i++) {
			inQueue(deque, nums[i]);
			result[i - k + 1] = deque.peekFirst();
			outQueue(deque, nums[i - k + 1]);
		}
		return result;
	}

	private static void inQueue(Deque<Integer> deque, int num) {
		while (!deque.isEmpty() && deque.peekLast() < num)
			deque.pollLast();
		deque.offerLast(num);
	}

	private static void outQueue(Deque<Integer> deque, int num) {
		if (deque.peekFirst() == num)
			deque.pollFirst();
	}
}
