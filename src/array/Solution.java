package array;

import java.util.Arrays;

public class Solution {
	/**
	 * 561. Array Partition I
	 * @param nums
	 * @return
	 */
	public static int arrayPairSum(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0;i < length;i += 2){
        	sum += nums[i];
        }
        return sum;
    }

    public static void main(String[] args){
    	int[] A = {1, 4, 3, 2};
    	System.out.println(arrayPairSum(A));
    }
}
