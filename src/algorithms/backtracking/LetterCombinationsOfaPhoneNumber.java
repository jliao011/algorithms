package algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfaPhoneNumber {
	private final String[] lookup = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public List<String> letterCombinations(String digits) {
		// backtracking
		List<String> result = new ArrayList<>();
		if (digits == null || digits.length() == 0)
			return result;
		backtrack(result, digits, new StringBuilder(), 0);
		return result;
	}

	private void backtrack(List<String> result, String digits, StringBuilder temp, int idx) {
		if (idx == digits.length()) {
			result.add(temp.toString());
			return;
		}
		String letters = lookup[digits.charAt(idx) - '0'];
		for (char c : letters.toCharArray()) {
			temp.append(c);
			backtrack(result, digits, temp, idx + 1);
			temp.deleteCharAt(temp.length() - 1);
		}
	}
}
