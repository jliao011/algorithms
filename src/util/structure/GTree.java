package util.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GTree<T> {

	private static final Logger logger = LoggerFactory.getLogger(GTree.class);
	GTreeNode<T> root;

	public GTree() {
	}

	public GTree(GTreeNode<T> root) {
		this.root = root;
	}

	public void levelOrder() {
		if (this.root == null)
			return;
		List<List<T>> result = new ArrayList<>();
		Queue<GTreeNode<T>> queue = new LinkedList<>();
		queue.offer(this.root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<T> list = new ArrayList<>();
			result.add(list);
			for (int i = 0; i < size; i++) {
				GTreeNode<T> node = queue.poll();
				list.add(node.value);
				if (node.childs == null)
					continue;
				for (GTreeNode<T> child : node.childs) {
					if (child != null)
						queue.offer(child);
				}
			}
		}
		for (List<T> list : result) {
			logger.info("{}", list);
		}
	}

	public void levelOrderDFSRecursion() {
		List<List<T>> result = new ArrayList<>();
		if (this.root == null)
			return;
		levelOrderDFSRecursion(result, this.root, 0);
		for (List<T> list : result)
			logger.info("{}", list);
	}

	private void levelOrderDFSRecursion(List<List<T>> result, GTreeNode<T> root, int level) {
		if (root == null)
			return;
		if (level + 1 > result.size())
			result.add(new ArrayList<>());
		result.get(level).add(root.value);
		if (root.childs == null)
			return;
		for (GTreeNode<T> child : root.childs)
			levelOrderDFSRecursion(result, child, level + 1);
	}

	public void levelOrderDFSIteration() {
		List<List<T>> result = new ArrayList<>();
		if (root == null)
			return;
		Stack<GTreeNode<T>> stack = new Stack<>();
		Map<GTreeNode<T>, Integer> map = new HashMap<>();
		stack.push(root);
		map.put(root, 0);
		while (!stack.isEmpty()) {
			GTreeNode<T> node = stack.pop();
			logger.info("{}", node.hashCode());
			int level = map.get(node);
			if (level + 1 > result.size())
				result.add(new ArrayList<>());
			result.get(level).add(node.value);
			if (node.childs == null)
				continue;
			List<GTreeNode<T>> childs = node.childs;
			for (int i = childs.size() - 1; i >= 0; i--) {
				stack.push(childs.get(i));
				map.put(childs.get(i), level + 1);

			}
		}
		for (List<T> list : result)
			logger.info("{}", list);
	}

	public static void buildTree() {
		List<GTreeNode<String>> list = new ArrayList<>();
		for (int i = 0; i <= 10; i++)
			list.add(new GTreeNode<String>(String.valueOf((char) (i + 'a'))));
		GTree<String> tree = new GTree<>(list.get(0));
		list.get(0).childs = Arrays.asList(list.get(1), list.get(2), list.get(3), list.get(4));
		list.get(1).childs = Arrays.asList(list.get(5), list.get(6));
		list.get(2).childs = Arrays.asList(list.get(7), list.get(8));
		list.get(3).childs = Arrays.asList(list.get(9));
		list.get(4).childs = Arrays.asList(list.get(10));
		tree.levelOrder();
		tree.levelOrderDFSRecursion();
		tree.levelOrderDFSIteration();
		List[] lists = new List[2];

	}
}

class GTreeNode<T> {

	T value;
	List<GTreeNode<T>> childs;

	GTreeNode(T value) {
		this.value = value;
	}
}