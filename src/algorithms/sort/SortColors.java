package algorithms.sort;

public class SortColors {
	public static String sortColors(String s) {
		// aaa|i|bbb|j|???|k|ccc
		int i = 0, j = 0, k = s.length() - 1;
		char[] c = s.toCharArray();
		while (j <= k) {
			if (c[j] == 'a')
				swap(c, i++, j++);
			else if (c[j] == 'b')
				j++;
			else if (c[j] == 'c')
				swap(c, j, k--);
		}
		return String.valueOf(c);
	}

	private static char[] swap(char[] c, int a, int b) {
		char temp = c[a];
		c[a] = c[b];
		c[b] = temp;
		return c;
	}

}
