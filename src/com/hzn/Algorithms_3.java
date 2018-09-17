package com.hzn;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * 
 * 示例 1:
 * 
 * 输入: "abcabcbb" 输出: 3 解释: 无重复字符的最长子串是 "abc"，其长度为 3。 示例 2:
 * 
 * 输入: "bbbbb" 输出: 1 解释: 无重复字符的最长子串是 "b"，其长度为 1。 示例 3:
 * 
 * 输入: "pwwkew" 输出: 3 解释: 无重复字符的最长子串是 "wke"，其长度为 3。 请注意，答案必须是一个子串，"pwke" 是一个子序列
 * 而不是子串。
 * 
 * @author yfax-android-zhengneng
 *
 */
public class Algorithms_3 {

	public static void main(String[] args) {
		int i = lengthOfLongestSubstring("asdfergfsadsa");
	}

//	public static int lengthOfLongestSubstring(String s) {
//        int length = s.length();
//		Set<String> set = new HashSet<>();
//		int result = 0, i = 0, j = 0;
//		
//		while (i < length && j < length) {
//			if (!set.contains(String.valueOf(s.charAt(j)))) {
//				set.add(String.valueOf(s.charAt(j++)));
//				result = Math.max(result, j - i);
//			} else {
//				set.remove(String.valueOf(s.charAt(i++)));
//			}
//		}
//		return result;
//    }
	
	public static int lengthOfLongestSubstring(String str) {
		int[] index = new int[128];
		int ans = 0, len = str.length();
		for (int j = 0,i = 0; j < len; j++) {
			i = Math.max(index[str.charAt(j)], i);
			ans = Math.max(ans, j - i + 1);
			index[str.charAt(j)] = j + 1;
		}
		System.out.println(ans);
		return ans;
	}
}
