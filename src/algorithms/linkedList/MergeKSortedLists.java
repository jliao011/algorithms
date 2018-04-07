package algorithms.linkedList;

import java.util.Comparator;
import java.util.PriorityQueue;

import util.structure.ListNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MergeKSortedLists {
	private final static Logger logger = LoggerFactory.getLogger(MergeKSortedLists.class);

	public static ListNode mergeKSortedListsI(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
		return partition(lists, 0, lists.length - 1);
	}

	private static ListNode partition(ListNode[] lists, int left, int right) {
		logger.debug("left pointer {}, right pointer {}", left, right);
		if (left > right)
			return null;
		if (left == right)
			return lists[left];
		int mid = left + (right - left) / 2;
		ListNode l1 = partition(lists, left, mid);
		ListNode l2 = partition(lists, mid + 1, right);
		return merge(l1, l2);
	}

	private static ListNode merge(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		if (l1.val < l2.val) {
			l1.next = merge(l1.next, l2);
			return l1;
		} else {
			l2.next = merge(l1, l2.next);
			return l2;
		}
	}

	public static ListNode mergeKSortedListII(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
//		PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
//			@Override
//			public int compare(ListNode o1, ListNode o2) {
//				return o1.val - o2.val;
//			}
//		});
		PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);
		ListNode dummy = new ListNode(0), prev = dummy, curr = null;
		for (ListNode head : lists)
			if (head != null)
				heap.offer(head);
		while (!heap.isEmpty()) {
			curr = heap.poll();
			if (curr.next != null)
				heap.offer(curr.next);
			prev.next = curr;
			prev = prev.next;
		}
		return dummy.next;
	}
}
