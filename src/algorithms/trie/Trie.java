package algorithms.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Trie {

	/**
	 * use HashMap to save Character and child node
	 * use isEnd to indicate whether it's end of a word
	 */
	private class TrieNode {
		Map<Character, TrieNode> children;
		boolean isEnd;

		public TrieNode() {
			children = new HashMap<>();
			isEnd = false;
		}
	}

	private final static Logger logger = LoggerFactory.getLogger(Trie.class);
	private final TrieNode root;

	public Trie() {
		this.root = new TrieNode();
	}

	/**
	 * Insert a word iteratively
	 */
	public void insert(String word) {
		if (word == null || word.length() == 0)
			return;
		TrieNode curr = root;
		for (char c : word.toCharArray()) {
			TrieNode child = curr.children.get(c);
			// current character not exist
			if (child == null) {
				child = new TrieNode();
				curr.children.put(c, child);
			}
			curr = child;
		}
		// mark current node end of word
		curr.isEnd = true;
	}

	/**
	 * Insert a word recursively
	 */
	public void insertRecursive(String word) {
		if (word == null || word.length() == 0)
			return;
		this.insertRecursive(this.root, word, 0);
	}

	private void insertRecursive(TrieNode curr, String word, int idx) {
		if (idx == word.length()) {
			curr.isEnd = true;
			return;
		}
		char c = word.charAt(idx);
		TrieNode child = curr.children.get(c);
		// current char not exist, create new node
		if (child == null) {
			child = new TrieNode();
			curr.children.put(c, child);
		}
		insertRecursive(child, word, idx + 1);
	}

	/**
	 * Search a word in trie iteratively
	 * @return whether exist
	 */
	public boolean search(String word) {
		if (word == null || word.length() == 0)
			return false;
		TrieNode curr = root;
		for (char c : word.toCharArray()) {
			TrieNode child = curr.children.get(c);
			if (child == null)
				return false;
			curr = child;
		}
		// check wether reach end of a word
		return curr.isEnd;
	}

	/**
	 * Search a word in trie recursively
	 * @return whether exist
	 */
	public boolean searchRecursive(String word) {
		if (word == null || word.length() == 0)
			return false;
		return searchRecursive(this.root, word, 0);
	}

	private boolean searchRecursive(TrieNode curr, String word, int idx) {
		if (idx == word.length()) {
			return curr.isEnd;
		}
		char c = word.charAt(idx);
		TrieNode child = curr.children.get(c);
		if (child == null)
			return false;
		return searchRecursive(child, word, idx + 1);
	}

	/**
	 * Search if prefix word exist
	 */
	public boolean startWith(String word) {
		if (word == null)
			return false;
		TrieNode curr = root;
		for (char c : word.toCharArray()) {
			TrieNode child = curr.children.get(c);
			if (child == null)
				return false;
			curr = child;
		}
		return true;
	}

	/**
	 * Search if prefix word exist recursively
	 */
	public boolean startWithRecursive(String word) {
		if (word == null)
			return false;
		return startWithRecursive(root, word, 0);
	}

	private boolean startWithRecursive(TrieNode curr, String word, int idx) {
		if (idx == word.length())
			return true;
		char c = word.charAt(idx);
		TrieNode child = curr.children.get(c);
		if (child == null)
			return false;
		return startWithRecursive(child, word, idx + 1);
	}

	/**
	 * Delete a word in the trie
	 * remove a parent as well if there is no child
	 */
	public void delete(String word) {
		if (word == null || word.length() == 0)
			return;
		delete(this.root, word, 0);
	}

	/**
	 * @return whether parent node should be deleted
	 */
	private boolean delete(TrieNode curr, String word, int idx) {
		if (idx == word.length()) {
			// still have sub tree
			if (!curr.isEnd)
				return false;
			// set to false if curr has other children
			curr.isEnd = false;
			// if no child, delete parent
			return curr.children.size() == 0;
		}
		char c = word.charAt(idx);
		TrieNode child = curr.children.get(c);
		// such word not exist
		if (child == null)
			return false;
		boolean shouldDeleteChild = delete(child, word, idx + 1);
		if (shouldDeleteChild) {
			curr.children.remove(c);
			// remove as well if no child
			return curr.children.size() == 0;
		}
		return false;
	}

	public static void test() {
		Trie trie = new Trie();
		List<String> dict = Arrays.asList("abc", "abgl", "abcd", "cdef");
		for (String word : dict)
			trie.insert(word);
		for (String word : dict)
			logger.info("Search for {}: {}", word, trie.search("abc"));
		for (String word : dict) {
			trie.delete(word);
		}
		trie.insert("apple");
		logger.info("{}", trie.startWith("app"));
	}
}
