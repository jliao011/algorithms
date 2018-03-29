package util.structure;

import util.Tool;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int val) {
		this.val = val;
		this.next = null;
	}

	public static ListNode getList(int size, int range) {
		int[] nums = Tool.getIntArray(size, range);
		ListNode dummy = new ListNode(0), prev = dummy;
		for (int num : nums) {
			prev.next = new ListNode(num);
			prev = prev.next;
		}
		return dummy.next;
	}

	public static ListNode getList(int size, int start, int diff, boolean duplicated) {
		int[] nums = Tool.getIntArray(size, start, diff, duplicated);
		ListNode dummy = new ListNode(0), prev = dummy;
		for (int num : nums) {
			prev.next = new ListNode(num);
			prev = prev.next;
		}
		return dummy.next;
	}

	public ListNode copy() {
		ListNode dummy = new ListNode(0), prev = dummy, head = this;
		while (head != null) {
			prev.next = new ListNode(head.val);
			prev = prev.next;
			head = head.next;
		}
		return dummy.next;
	}
}
