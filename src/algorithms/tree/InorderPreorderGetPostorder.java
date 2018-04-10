package algorithms.tree;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.*;

public class InorderPreorderGetPostorder {
	private final static Logger logger = LoggerFactory.getLogger(InorderPreorderGetPostorder.class);

	public static void getPostorder(int[] inorder, int[] preorder) {
		List<Integer> result = new ArrayList<>();
		helper(result, preorder, inorder, 0, 0, inorder.length - 1);

	}

	private static void helper(List<Integer> result, int[] inorder, int[] preorder, int preHead, int inHead,
			int inTail) {
		if (preHead >= preorder.length || inHead > inTail)
			return;
		int root = preorder[preHead];
		int idx = 0;
		for (int i = inHead; i <= inTail; i++)
			if (inorder[i] == root)
				idx = i;
		int left = idx - inHead;
		helper(result, inorder, preorder, preHead + 1, inHead, idx - 1);
		helper(result, inorder, preorder, preHead + left + 1, idx + 1, inTail);
		logger.debug("preHead {},inHead {},inTail {},root {}", preHead, inHead, inTail, root);
		result.add(root);
		logger.info("result is {}", result);
	}
}