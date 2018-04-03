package algorithms.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreak {
	/*
	 * s = "catsanddog",
	 * dict = ["cat", "cats", "and", "sand", "dog"].
	 * 
	 * A solution is ["cats and dog", "cat sand dog"].
	 */
	public static List<String> wordBreak(String s, List<String> wordDict) {
		return backtrack(s, wordDict, 0, new HashMap<>());
	}

	private static List<String> backtrack(String s, List<String> wordDict, int head, Map<Integer, List<String>> map) {
		if (map.containsKey(head))
			return map.get(head);
		List<String> result = new ArrayList<>();
		if (head == s.length()) {
			result.add("");
		}
		for (int tail = head + 1; tail <= s.length(); tail++) {
			if (wordDict.contains(s.substring(head, tail))) {
				List<String> list = backtrack(s, wordDict, tail, map);
				for (String word : list) {
					result.add(s.substring(head, tail) + (word.equals("") ? "" : " ") + word);
				}
			}
		}
		map.put(head, result);
		return result;
	}
}
