package com.hzn;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 
 * 示例:
 * 
 * 输入: [   1->4->5,   1->3->4,   2->6 ] 输出: 1->1->2->3->4->4->5->6
 *
 * 
 * @Author: huangzhengneng
 * @Email: zhengnenghuang@gmail.com
 * @Descriotion: TODO
 * @Date:2019年6月24日
 */
public class Algorithms_23 {

	public static class ListNode {
		int val;
		ListNode next;

		public ListNode(int val) {
			this.val = val;
		}
		
		@Override
			public String toString() {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("[").append(val);
				ListNode curr = this;
				while (curr.next != null) {
					curr = curr.next;
					stringBuilder.append(", ").append(curr.val);
				}
				stringBuilder.append("]");
				return stringBuilder.toString();
			}
	}

	public static void main(String[] args) {
		ListNode[] lists = new ListNode[5];
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			ListNode listNode = new ListNode(random.nextInt(100));
			lists[i] = listNode;
			for(int j = 0; j < 10; j++) {
				listNode.next = new ListNode(listNode.val + 2);
				listNode = listNode.next;
			}
		}
		long start = System.nanoTime();
		ListNode listNode = mergeKLists1(lists);
		System.out.println("耗时：" + (System.nanoTime() - start));
		System.out.println(listNode.toString());
	}

	/**
	 * 小顶堆，插入时间复杂度logk，遍历n个节点，时间复杂度：nlogk， 空间复杂度：n
	 * 
	 * @param lists
	 * @return
	 */
	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new Comparator<ListNode>() {

			@Override
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		});
		for (ListNode listNode : lists) {
			queue.offer(listNode);
		}
		ListNode head = new ListNode(0);
		ListNode currListNode = head;
		while (!queue.isEmpty()) {
			ListNode listNode = queue.poll();
			currListNode.next = new ListNode(listNode.val);
			currListNode = currListNode.next;
			if (listNode.next != null) {
				listNode = listNode.next;
				queue.offer(listNode);
			}
		}
		return head.next;
	}

	/**
	 * 分治---时间复杂度也是nlogk，但是实际执行时间比堆快，空间复杂度：1
	 * 
	 * @param lists
	 * @return
	 */
	public static ListNode mergeKLists1(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		int len = lists.length;
		return mergeKLists2(lists, 0, len - 1);
	}

	public static ListNode mergeKLists2(ListNode[] lists, int l, int r) {
		if (l == r) {
			return lists[l];
		}
		int mid = (r + l) / 2;
		ListNode listNode1 = mergeKLists2(lists, l, mid);
		ListNode listNode2 = mergeKLists2(lists, mid + 1, r);
		return mergeTwoLists(listNode1, listNode2);
	}

	public static ListNode mergeTwoLists(ListNode node1, ListNode node2) {
		if (node1 == null && node2 == null) {
			return null;
		}
		ListNode resultIntNode = null;
		ListNode tmIntNode = resultIntNode;
		while (node1 != null && node2 != null) {
			ListNode node;
			if (node1.val <= node2.val) {
				if (tmIntNode == null) {
					tmIntNode = new ListNode(node1.val);
					resultIntNode = tmIntNode;
				} else {
					tmIntNode.val = node1.val;
				}
				node = node1;
				node1 = node1.next;
			} else {
				if (tmIntNode == null) {
					tmIntNode = new ListNode(node2.val);
					resultIntNode = tmIntNode;
				} else {
					tmIntNode.val = node2.val;
				}
				node = node2;
				node2 = node2.next;
			}
			if (node1 != null && node2 != null) {
				tmIntNode.next = node;
				tmIntNode = tmIntNode.next;
			}
		}
		if (node1 != null) {
			if (tmIntNode == null) {
				tmIntNode = node1;
				resultIntNode = tmIntNode;
			} else {
				tmIntNode.next = node1;
			}
		}
		if (node2 != null) {
			if (tmIntNode == null) {
				tmIntNode = node2;
				resultIntNode = tmIntNode;
			} else {
				tmIntNode.next = node2;
			}
		}
		return resultIntNode;
	}
}
