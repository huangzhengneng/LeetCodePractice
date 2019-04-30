package com.hzn;

import java.util.Arrays;

/**
 * 14. 最长公共前缀
 * 
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 
 * 如果不存在公共前缀，返回空字符串 ""。
 * 
 * 示例 1:
 * 
 * 输入: ["flower","flow","flight"] 输出: "fl" 示例 2:
 * 
 * 输入: ["dog","racecar","car"] 输出: "" 解释: 输入不存在公共前缀。 说明:
 * 
 * 所有输入只包含小写字母 a-z 。
 * 
 * @author yfax-android-zhengneng
 *
 */
public class Algorithms_14 {
	public static void main(String[] args) {
		longestCommonPrefix(new String[] { "a", "a", "a" });
	}

	public static String longestCommonPrefix(String[] strs) {

		if (strs == null || strs.length == 0) {
			return "";
		}
		String shortestStr;
		int shortestLength = Integer.MAX_VALUE, shortestIndex = 0;
		for (int i = 0, len = strs.length; i < len; i++) {
			if (strs[i].length() < shortestLength) {
				shortestLength = strs[i].length();
				shortestIndex = i;
			}
		}
		shortestStr = strs[shortestIndex];
		char[] chars = shortestStr.toCharArray();
		String commonPrefix = "", result = "";
		boolean isContinue = true;
		for (int i = 0, len = chars.length; i < len && isContinue; i++) {
			result = commonPrefix;
			commonPrefix = new String(Arrays.copyOf(chars, i + 1));
			for (int j = 0; j < strs.length; j++) {
				if (!strs[j].startsWith(commonPrefix)) {
					isContinue = false;
					break;
				}
			}
			if (isContinue && i + 1 >= len) {
				result = commonPrefix;
			}
		}
		System.out.println("公共前缀：" + result);
		return result;
	}
}
