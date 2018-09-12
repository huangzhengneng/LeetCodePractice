package com.hzn;

import java.util.HashMap;
import java.util.Map;

/**
 * leetCode第一题
 * 
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * 
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 * 
 * 示例:
 * 
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 
 * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 * 
 * @author yfax-android-zhengneng
 *
 */
public class Algorithms_1 {

	public static void main(String[] args) {
		twoSum2(new int[] { 1, 2, 3, 4, 5, 6, 1 }, 2);
		twoSum(new int[] { 1, 2, 3, 4, 5, 6, 1 }, 2);
//		twoSum2(new int[] { 2, 7, 11, 15 }, 9);
	}

	/**
	 * 给定 nums = [2, 7, 11, 15], target = 9
	 * 
	 * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
	 * 
	 * 只得出一组就行了
	 * 
	 * @param nums
	 * @param target
	 * @return
	 * 
	 * 		时间复杂度为O(n2)
	 */
	public static int[] twoSum(int[] nums, int target) {
		long start = System.currentTimeMillis();
		if (nums == null || nums.length < 2) {
			return new int[] {};
		}
		int[] result = new int[2];
		boolean isContinue = true;
		for (int i = 0; i < nums.length - 1; i++) {
			if (!isContinue) {
				break;
			}
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					result[0] = i;
					result[1] = j;
					isContinue = false;
					break;
				}
			}
		}
		System.out.println("结果是：" + result[0] + ", " + result[1] + ", 耗时：" + (System.currentTimeMillis() - start));
		return result;
	}

	/**
	 * 给定 nums = [2, 7, 11, 15], target = 9
	 * 
	 * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
	 * 
	 * 只得出一组就行了
	 * 
	 * @param nums
	 * @param target
	 * @return
	 * 
	 * 		时间复杂度为O(n)
	 */
	public static int[] twoSum2(int[] nums, int target) {
		long start = System.currentTimeMillis();
		if (nums == null || nums.length < 2) {
			return new int[] {};
		}
		int[] result = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i]) && target == nums[i] * 2) {
				// 相同的值除非是target刚好等于这个值的2倍，其它都没有影响
				result[0] = map.get(nums[i]);
				result[1] = i;
				break;
			}
			map.put(nums[i], i);
			if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
				result[0] = map.get(target - nums[i]);
				result[1] = i;
				break;
			}
		}
		System.out.println("结果是：" + result[0] + ", " + result[1] + ", 耗时：" + (System.currentTimeMillis() - start));
		return result;
	}
}
