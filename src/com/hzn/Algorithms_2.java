package com.hzn;

import javax.swing.text.AbstractDocument.LeafElement;

/**
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * 
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 
 * 示例：
 * 
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
 * 
 * @author yfax-android-zhengneng
 *
 */
public class Algorithms_2 {

	public static void main(String[] args) {

		ListNode node1 = new Algorithms_2().new ListNode(2);
		ListNode node2 = new Algorithms_2().new ListNode(4);
		ListNode node3 = new Algorithms_2().new ListNode(5);
		ListNode node4 = new Algorithms_2().new ListNode(3);
		ListNode node5 = new Algorithms_2().new ListNode(6);
		ListNode node6 = new Algorithms_2().new ListNode(4);
		ListNode node7 = new Algorithms_2().new ListNode(6);
		ListNode node8 = new Algorithms_2().new ListNode(2);
		ListNode node9 = new Algorithms_2().new ListNode(1);

		node1.next = node2;
		node2.next = node4;
//		node4.next = node7;

		node3.next = node5;
		node5.next = node6;
//		node6.next = node8;
//		node8.next = node9;

		System.out.println("输入的数是：" + getNumbers(node1) + ", 和: " + getNumbers(node3));

		ListNode node = addTwoNumbers(node1, node3);

		System.out.println("输出的数是： " + getNumbers(node));

	}

	private static String getNumbers(ListNode node) {
		String n1 = "" + node.val;
		while (node.next != null) {
			node = node.next;
			n1 = n1 + "-" + node.val;
		}
		return n1;
	}

	/**
	 * 思路： 1.做链表反转 2.两个链表按位数相加，没有就取0 3.得到的链表再做反转得出结果
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//		l1 = reverse(l1);
//		l2 = reverse(l2);
		Algorithms_2 bean = new Algorithms_2();
		ListNode sumNode = bean.new ListNode(0);
		ListNode tmp = sumNode;
		boolean isNeedAdd = false;
		while (true) {
			int val1 = (l1 == null ? 0 : l1.val), val2 = (l2 == null ? 0 : l2.val);
			sumNode.val = val1 + val2 + (isNeedAdd ? 1 : 0);
			isNeedAdd = sumNode.val >= 10;
			if (sumNode.val >= 10) { // 过10进1
				sumNode.val = sumNode.val % 10;
			}
			l1 = (l1 == null ? null : l1.next);
			l2 = (l2 == null ? null : l2.next);
			if (!(l1 != null || l2 != null)) {
				if (isNeedAdd) {
					ListNode last = bean.new ListNode(1);
					sumNode.next = last;
				}
				break;
			}
			ListNode node = bean.new ListNode(0);
			sumNode.next = node;
			sumNode = node;
		}
		return tmp;
	}

	public static ListNode reverse(ListNode target) {
		ListNode pre = target, tmp = target, next = target.next;
		target.next = null;
		while (next != null) {
			tmp = next;
			next = next.next;
			tmp.next = pre;
			pre = tmp;
		}
		return tmp;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
