package com.hzn;


public class Alogrithms_66 {
	
	    public int[] plusOne(int[] digits) {
	    	int len = digits.length;
	        if(digits == null || len == 0) {
	            return digits;
	        }
	        boolean needCarry = false;// 是否需要进位
	        boolean needCreateNewArray = false; // 是否需要扩容数组
	        for(int i = len - 1; i >= 0; i--) {
	            if(needCarry || i == len -1) {
	                digits[i] += 1;
	            }
	            needCarry = digits[i] >= 10;
	            if(needCarry) {
	                digits[i] %= 10;
	                if (i == 0) {
						// 已经到最高位还需要进位说明需要扩容
	                	needCreateNewArray = true;
					}
	            }
	        }
	        if (needCreateNewArray) {
				int[] result = new int[len + 1];
				for(int i = 0; i < len + 1; i++) {
					if (i == 0) {
						result[i] = 1;
					} else {
						result[i] = digits[i - 1];
					}
				}
				return result;
			}
	        return digits;
	    }
	
}
