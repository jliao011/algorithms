package algorithms.personal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericBST<T extends Comparable<T>> {
	private static final Logger logger = LoggerFactory.getLogger(GenericBST.class);
	public GenericTreeNode<T> root;

	public GenericBST(Collection<T> collection) {
		for (T value : collection) {
			if (root == null) {
				root = new GenericTreeNode<>(value);
			} else {
				insert(root, value);
			}
		}
	}

	public void insert(GenericTreeNode<T> root, T value) {
		if (root == null)
			return;
		if (root.value.compareTo(value) > 0) {
			if (root.left == null)
				root.left = new GenericTreeNode<>(value);
			else
				insert(root.left, value);
		} else {
			if (root.right == null)
				root.right = new GenericTreeNode<>(value);
			else
				insert(root.right, value);
		}
	}

	public GenericTreeNode<T> search(T value) {
		if (this.root == null)
			return null;
		return search(this.root, value);
	}

	private GenericTreeNode<T> search(GenericTreeNode<T> root, T value) {
		if (root == null)
			return null;
		if (root.value.compareTo(value) == 0)
			return root;
		if (root.value.compareTo(value) > 0)
			return search(root.left, value);
		else
			return search(root.right, value);
	}

	public List<T> bst() {
		if (this.root == null)
			return null;
		Queue<GenericTreeNode<T>> queue = new LinkedList<>();
		List<T> result = new ArrayList<>();
		queue.offer(this.root);
		while (!queue.isEmpty()) {
			GenericTreeNode<T> node = queue.poll();
			result.add(node.value);
			logger.info("{}", node.value);
			if (node.left != null)
				queue.offer(node.left);
			if (node.right != null)
				queue.offer(node.right);
		}
		return result;
	}

	public List<String> preorder() {
		List<String> result = new ArrayList<>();
		preorder(this.root, result);
		return result;
	}

	private void preorder(GenericTreeNode<T> root, List<String> result) {
		if (root == null) {
			result.add("null");
			return;
		}
		result.add(root.value.toString());
		if (root.left == null && root.right == null)
			return;
		preorder(root.left, result);
		preorder(root.right, result);
	}

}
