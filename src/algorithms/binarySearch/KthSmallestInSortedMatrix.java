package algorithms.binarySearch;

import java.util.*;

//Given a n x n matrix where each of the rows and columns are sorted in ascending order, 
//find the kth smallest element in the matrix.
//Note that it is the kth smallest element in the sorted order, not the kth distinct element.
//Example:
//matrix = [
//   [ 1,  5,  9],
//   [10, 11, 13],
//   [12, 13, 15]
//],
//k = 8,
//return 13.
public class KthSmallestInSortedMatrix {
	// binary search and dijkstra's
	public static int kthSmallest(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int row = matrix.length, col = matrix[0].length;
		int low = matrix[0][0], high = matrix[row - 1][col - 1];
		while (low < high) {
			int mid = low + (high - low) / 2;
			int count = 0;  // count smallest num
			for (int i = 0; i < row; i++) {
				int j = col - 1;
				while (j >= 0 && matrix[i][j] > mid)
					j--;
				count += j + 1; // count <= mid
			}
			if (count < k) // mid too small
				low = mid + 1;
			else
				high = mid;
		}
		return low;
	}

	public static int kthSmallestInSortedMatrix(int[][] matrix, int k) {
		// use dijkstra's algorithm idea, expand from left upper corner
		// can use binary search as well
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return -1;
		int row = matrix.length, col = matrix[0].length;
		boolean[][] visited = new boolean[row][col];
		PriorityQueue<Cell> heap = new PriorityQueue<>(k, new Comparator<Cell>() {
			@Override
			public int compare(Cell o1, Cell o2) {
				if (o1.val == o2.val)
					return 0;
				return o1.val < o2.val ? -1 : 1;
			}
		});
		heap.offer(new Cell(0, 0, matrix[0][0]));
		visited[0][0] = true;
		for (int i = 0; i < k - 1; i++) {
			Cell curr = heap.poll();
			int r = curr.row, c = curr.col;
			if (r + 1 < row && !visited[r + 1][c]) {
				heap.offer(new Cell(r + 1, c, matrix[r + 1][c]));
				visited[r + 1][c] = true;
			}
			if (c + 1 < col && !visited[r][c + 1]) {
				heap.offer(new Cell(r, c + 1, matrix[r][c + 1]));
				visited[r][c + 1] = true;
			}
		}
		return heap.peek().val;
	}
}

class Cell {
	int row;
	int col;
	int val;

	public Cell(int row, int col, int val) {
		this.row = row;
		this.col = col;
		this.val = val;
	}
}
