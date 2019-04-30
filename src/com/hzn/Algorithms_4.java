package com.hzn;

import java.util.function.IntPredicate;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 
 * 示例 1:
 * 
 * nums1 = [1, 3] nums2 = [2]
 * 
 * 则中位数是 2.0 示例 2:
 * 
 * nums1 = [1, 2] nums2 = [3, 4]
 * 
 * 则中位数是 (2 + 3)/2 = 2.5
 * 
 * 思路1，暴力法： 1. 合并两个有序数组 2. m + n 如果是奇数，取中间一个，如果是偶数，取中间两个
 * 		时间复杂度：O(max(m, n))
 * 		空间复杂度：O(m+n)
 * 思路2：1.记住两个数组长度和，按这个次数去循环
 * 		 2.比较两个数组当前循环到的元素大小，小的往前走一步，下一次循环
 * 		 3.当有一个数组循环完毕，直接循环另一个数组
 * 		 4.如果长度是奇数，循环到 (m+n)/2 就返回这个数
 * 		 5.如果长度是偶数，循环到 (m+n)/2 就返回 当前循环的这个数和上一个数 相加除以2
 * 		时间复杂度：O(m + n)
 * 		空间复杂度：O(1)
 * 思路3：在第一个数组中寻找一个下标i，求出在第二个数组中对应的下标j，使得 i + j = m - i + n - j
 * 			即arr1[0~i-1].lenght + arr2[0~j-1].lenght == arr1[i~m].lenght + arr2[j~n].lenght,
 * 			这样当 arr1[i-1] <= arr2[j] 并且 arr1[i] >= arr2[j-1] 时，如果m+n是奇数，就返回max(arr1[i-1], arr2[j-1]),
 * 			如果m+n是偶数，就返回 (max(arr1[i-1], arr2[j-1]) + min(arr1[i], arr2[j])) / 2.0
 * 		时间复杂度：O(log(min(m, n)))
 * 		空间复杂度：O(1)
 * 
 * 
 * 要求时间复杂度为log(m+n), 即合并的操作时间复杂度是log(m+n)
 * 
 * @author yfax-android-zhengneng
 *
 */
public class Algorithms_4 {
	public static void main(String[] args) {
		float result = findMedianSortedArrays3(new int[] {1,3  }, new int[] { 2 });
		System.out.println(result);
	}
	
	/**
	 * 时间复杂度：O(log(min(m, n)))
	 * 空间复杂度：O(1)
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static float findMedianSortedArrays3(int[] arr1, int[] arr2) {
		int m = arr1.length;
		int n = arr2.length;
		if (m > n) { // 确保m<=n
			int[] tmp = arr1;
			arr1 = arr2;
			arr2 = tmp;
			int len = m;
			m = n;
			n = len;
		}
		int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
		while (iMin <= iMax) { // 每一次循环，查找i值，查找范围缩小一半  因此循环 log2为底，min(m, n)的对数 遍
			int i = (iMin + iMax) / 2;
			int j = halfLen - i; // arr2的中位数
			if (i < iMax && arr1[i] < arr2[j - 1]) {
				iMin = i + 1;
			} else if (i > iMin && arr2[j] < arr1[i - 1]){
				iMax = i - 1;
			} else {
				int maxLeft = i == 0 ? arr2[j - 1] : (j == 0 ? arr1[i - 1] : Math.max(arr1[i - 1], arr2[j - 1]));
				if ((m + n) % 2 == 1) {
					return maxLeft;
				}
				int minRight = i == m ? arr2[j] : (j == n ? arr1[i] : Math.min(arr1[i], arr2[j]));
				return (maxLeft + minRight) / 2.0f;
			}
		}
		return 0.0f;
	}
	
	public static float findMedianSortedArrays4(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0f;
            }
        }
        return 0.0f;
    }

	/**
	 * 时间复杂度: O(m+n)
	 * 空间复杂度：O(1)
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static float findMedianSortedArrays2(int[] arr1, int[] arr2) {
//		if (isEmptyArray(arr1) && isEmptyArray(arr2)) {
//			return 0.0f;
//		}
		if (isEmptyArray(arr1)) {
			// 取第二个数组的中位数
			int lenght = arr2.length;
			return lenght % 2 == 0 ? (arr2[lenght / 2 - 1] + arr2[lenght / 2]) / 2.0f : arr2[lenght / 2];
		}
		if (isEmptyArray(arr2)) {
			// 取第二个数组的中位数
			int lenght = arr1.length;
			return lenght % 2 == 0 ? (arr1[lenght / 2 - 1] + arr1[lenght / 2]) / 2.0f : arr1[lenght / 2];
		}
		int lenght = arr1.length + arr2.length;
		int index1 = 0, index2 = 0, count = 0;
		int tmp = 0, pre = 0;
		while (count < lenght) {
			if (index1 == arr1.length) {
				tmp = arr2[index2++];
			} else if (index2 == arr2.length) {
				tmp = arr1[index1++];
			} else {
				if (arr1[index1] <= arr2[index2]) {
					tmp = arr1[index1++];
				} else {
					tmp = arr2[index2++];
				}
			}
			if (count == lenght / 2) {
				return lenght % 2 == 1 ? (float) tmp : (tmp + pre) / 2.0f;
			}
			count++;
			pre = tmp;
		}
		return 0.0f;
	}

	/**
	 * 时间复杂度：O(Max(m, n))
	 * 空间复杂度：O(m+n), 需要开辟临时数组
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static float findMedianSortedArrays1(int[] arr1, int[] arr2) {
		if (isEmptyArray(arr1) && isEmptyArray(arr2)) {
			return 0;
		}
		int[] array = merge(arr1, arr2);
		int lenght = array.length;
		if (lenght % 2 == 0) {
			return (array[lenght / 2 - 1] + array[lenght / 2]) / 2.0f;
		} else {
			return array[lenght / 2];
		}
	}

	private static boolean isEmptyArray(int[] arr) {
		return arr == null || arr.length == 0;
	}

	private static int[] merge(int[] arr1, int[] arr2) {
		int lenght = (arr1 == null ? 0 : arr1.length) + (arr2 == null ? 0 : arr2.length);
		if (isEmptyArray(arr1)) {
			return arr2;
		}
		if (isEmptyArray(arr2)) {
			return arr1;
		}
		int[] result = new int[lenght];
		int index1 = 0, index2 = 0, index = 0;
		while (index1 <= arr1.length - 1 && index2 <= arr2.length - 1) {
			if (arr1[index1] <= arr2[index2]) {
				result[index++] = arr1[index1++];
			} else {
				result[index++] = arr2[index2++];
			}
		}
		if (index1 == arr1.length) {
			for (int i = index2, len = arr2.length; i <= len - 1; i++) {
				result[index++] = arr2[i];
			}
		}
		if (index2 == arr2.length) {
			for (int i = index1, len = arr1.length; i <= len - 1; i++) {
				result[index++] = arr1[i];
			}
		}
		return result;
	}
}
