package algorithms.binarySearch;

/*A sorted list A contains 1, plus some number of primes.  
Then, for every p < q in the list, we consider the fraction p/q.
What is the K-th smallest fraction considered?  
Return your answer as an array of ints, where answer[0] = p and answer[1] = q.

Examples:
Input: A = [1, 2, 3, 5], K = 3
Output: [2, 5]
Explanation:
The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, 2/3.
The third fraction is 2/5.

Input: A = [1, 7], K = 1
Output: [1, 7]*/
public class KthSmallestPrimeFraction {
	// 7 5 2 1
	// 1
	// 2
	// 5
	// 7
	// match find kth smallest in sorted matrix
	public static int[] kthSmallestPrimeFraction(int[] A, int K) {
		if (A == null || A.length == 0)
			return new int[0];
		double left = 0;
		double right = 1;
		// cannot use less than
		while (true) {
			double mid = left + (right - left) / 2;
			int count = 0, col = 0;
			int[] idx = new int[] { 0, A.length - 1 };
			for (int row = 0; row < A.length; row++) {
				// no need to reset col here
				// if larger than mid, go left
				while (col < A.length && A[row] > mid * A[col])
					col++;
				count += A.length - col;
				// find max value less than mid here
				if (col < A.length && A[idx[0]] * A[col] < A[row] * A[idx[1]]) {
					idx[0] = row;
					idx[1] = col;
				}
			}
			if (count == K)
				return new int[] { A[idx[0]], A[idx[1]] };
			else if (count < K)
				left = mid;
			else
				right = mid;
		}
	}
}
