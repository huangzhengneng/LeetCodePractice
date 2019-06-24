package com.hzn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 * 
 * 示例 1:
 * 
 * 输入: [3,2,3] 输出: 3 示例 2:
 * 
 * 输入: [2,2,1,1,1,2,2] 输出: 2
 * 
 * @Author: huangzhengneng
 * @Email: zhengnenghuang@gmail.com
 * @Descriotion: TODO
 * @Date:2019年6月24日
 */
public class Algorithms_169 {
	public static void main(String[] args) {
		int[] nums = {2, 3, 1, 2, 2, 2,2};
		int majority = majorityElement(nums);
		System.out.println(majority);
	}
	
	/**
	 * 排序。 复杂度=排序方法的复杂度
	 * @param nums
	 * @return
	 */
	public static int majorityElement1(int[] nums) {
	     Arrays.sort(nums);
	    return nums[nums.length / 2];
	 }
	
	/**
	 * hashmap：时间复杂度：O(n)
	 * @param nums
	 * @return
	 */
	 public static int majorityElement(int[] nums) {
	     Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	     for (int i = 0; i < nums.length; i++) {
	    	 int key = nums[i];
			Integer val = map.get(key);
			map.put(key, val == null ? 1 : ++val);
		}
	    Set<Entry<Integer, Integer>> entries =  map.entrySet();
	    Entry<Integer, Integer> majorityEntry = null;
	    for (Entry<Integer, Integer> entry : entries) {
			if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
				majorityEntry = entry;
			}
		}
	    return majorityEntry.getKey();
	 }
}
