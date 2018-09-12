package com.hzn;

/**
 * leetCode算法类第16题
 * 
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数， 使得它们的和与 target
 * 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 
 * 例如， 给定数组 nums = [-1，2，1，-4], 和 target = 1. 与 target 最接近的三个数的和为 2. (-1 + 2 + 1
 * = 2).
 * 
 * @author yfax-android-zhengneng
 *
 */
public class Algorithms_16 {

	public static void main(String[] args) {
		threeSumClosest(new int[] { 1, 2, 3, 4, 5, 6, 6, 7, 8 }, 0);
		threeSumClosest(new int[] { 100, 22, 31, -42, 53, 46, -56, 67, -28 }, 0);
		threeSumClosest(new int[] { 0, 1, 2 }, 3);
	}

	/**
	 * 
	 * @param nums
	 * @param target
	 * @return
	 * 
	 * 		时间复杂度为O(n3) 优化方法为先排序，再迭代，可以做到O(n2)
	 */
	public static int threeSumClosest(int[] nums, int target) {
		if (nums == null || nums.length < 3) {
			return 0;
		}
		int result = 0;
		int len = nums.length;
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		int diff = Integer.MAX_VALUE;
		for (int i = 0; i <= len - 3; i++) {
			for (int j = i + 1; j <= len - 2; j++) {
				for (int k = j + 1; k <= len - 1; k++) {
					int tmp = nums[i] + nums[j] + nums[k];
					if (Math.abs(tmp - target) < diff) {
						result = tmp;
						diff = Math.abs(tmp - target);
						index1 = i;
						index2 = j;
						index3 = k;
					}
				}
			}
		}
		System.out.println("最接近的和为： " + result + ", 三个数的index为：" + index1 + ", " + index2 + ", " + index3);
		return result;
	}
}
