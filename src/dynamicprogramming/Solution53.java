package dynamicprogramming;

/**
 * 53. Maximum Subarray
 * 
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6. 
 * 
 * @author liuxl
 */
public class Solution53 {
	public static int maxSubArray(int[] nums) {
		int length = nums.length;
		if(length == 0) return 0;
		int[] dp = new int[length];
		dp[0] = nums[0];
		int max = dp[0];
		int lastNum = max;
    	int dpValue;
    	int currentNum;
		
        for(int i = 1;i < nums.length;i++){
        	currentNum = nums[i];
        	dpValue = currentNum;
        	if(lastNum > 0) dpValue += lastNum;
        	
        	dp[i] = dpValue;
        	lastNum = dpValue;
        	
        	if(max < dpValue) max = dpValue;
        }
        
        return max;
    }
	
	public static void main(String[] args){
		int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(maxSubArray(nums));
	}
}
