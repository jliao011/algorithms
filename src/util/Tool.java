package util;

import java.util.List;

public class Tool {
	public static void print(int[] list) {
		for (int i : list)
			System.out.print(i + " ");
		System.out.println();
	}

	public static void print(List<Integer> list) {
		for (int i : list)
			System.out.print(i + " ");
		System.out.println();
	}

}
