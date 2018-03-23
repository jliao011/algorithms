package main;

import java.util.Comparator;
import java.util.PriorityQueue;

import algorithms.*;

public class Core {
	public static void main(String[] args) {
		int[] list = { 2, 5, 1, 8, 9, 6, 3, 7, 4 };
		MergeSort.test(list);
		QuickSort.test(list);
		SortColors.test("abcbcbacbacbaabccb");
		FindKthLargest.test(list, 1);

		PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(k, new Comparator<>() {

		});
	}

	public static void print(int[] list) {
		for (int i : list)
			System.out.print(i + " ");
		System.out.println();
	}
}
