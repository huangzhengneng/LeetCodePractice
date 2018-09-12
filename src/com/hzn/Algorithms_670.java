package com.hzn;

import java.util.Random;

/**
 * leetCode算法类第670题
 * 
 * 至多交换一次两个数组，使输出的数字值最大。就是输入一个数，返回是否需要交换，如果需要，返回交换的两个数字
 * 
 * @author yfax-android-zhengneng
 *
 */
public class Algorithms_670 {

	public static void main(String[] args) {
		exchange(150);
		exchange(12332);
		exchange(2736);
		exchange(9973);
		exchange(new Random().nextInt(1000));
		exchange(new Random().nextInt(10000));
		exchange(new Random().nextInt(100000));
		exchange(new Random().nextInt(1000000));
		exchange(new Random().nextInt(10000000));
		exchange(new Random().nextInt(100000000));
	}

	/**
	 * 至多交换一次两个数组，使输出的数字值最大。就是输入一个数，返回是否需要交换，如果需要，返回交换的两个数字
	 * 
	 * @param target
	 *            非负整数
	 *            
	 *            时间复杂度为O(n2)
	 */
	private static int exchange(int target) {
		System.out.println("输入的数是：" + target);
		if (target <= 11) {
			System.out.println("输入的数不需要交换");
			return target;
		}
		String targetStr = String.valueOf(target);
		int len = targetStr.toCharArray().length;
		int leftIndex = 0;
		int rightIndex = 0;
		int result = target;
		for (int i = len - 1; i >= 1; i--) {
			int value = (int) (target / Math.pow(10, i)) % 10;
			for (int j = i - 1; j >= 0; j--) {
				int right = (int) (target / Math.pow(10, j)) % 10;
				// System.out.println(right + ", " + j);
				if (right >= value) {
					int tmp = ex(targetStr, i, j);
					// System.out.println(tmp + ", " + result);
					if (tmp > result) {
						leftIndex = i;
						rightIndex = j;
						result = tmp;
					}
				}
				// System.out.println("=============");
			}
		}
		if (leftIndex > 0) {
			char[] chars = targetStr.toCharArray();
			int lenx = chars.length - 1;
			char tmp = chars[lenx - leftIndex];
			chars[lenx - leftIndex] = chars[lenx - rightIndex];
			chars[lenx - rightIndex] = tmp;
			System.out.println("输入的数需要交换,交换" + chars[lenx - leftIndex] + "和" + chars[lenx - rightIndex] + "交换后："
					+ String.valueOf(chars));
			return Integer.parseInt(String.valueOf(chars));
		} else {
			System.out.println("输入的数不需要交换");
			return target;
		}
	}

	private static int ex(String str, int left, int right) {
		char[] chars = str.toCharArray();
		int len = chars.length - 1;
		char tmp = chars[len - left];
		chars[len - left] = chars[len - right];
		chars[len - right] = tmp;
		return Integer.parseInt(String.valueOf(chars));
	}
}
