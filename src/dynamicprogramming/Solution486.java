package dynamicprogramming;

public class Solution486 {
	/**
	 * recursive solution.
	 * @param nums
	 * @return
	 */
	public static boolean predictTheWinner(int[] nums) {
		return predictTheWinner(nums, 0, nums.length - 1) >= 0;
    }
	
	public static int predictTheWinner(int[] nums, int i, int j){
		if(i == j){
			return nums[i];
		}
		return Math.max(nums[i] - predictTheWinner(nums, i + 1, j), nums[j] - predictTheWinner(nums, i, j - 1));
	}
	
	/**
	 * DP solution.
	 * No data compression.
	 * 
	 * @param nums
	 * @return
	 */
	public static boolean predictTheWinnerDP(int[] nums){
		int n = nums.length;
		int[][] dp = new int[n][n];
		for(int i = 0;i < n;i++){
			dp[i][i] = nums[i];
		}
		
		for(int j = 1;j < n;j++){
			for(int i = 0;i < n - j;i++){
				dp[i][i + j] = Math.max(nums[i] - dp[i + 1][i + j], nums[i + j] - dp[i][i + j - 1]);
			}
		}
		
		return dp[0][n - 1] >= 0;
	}
	
	public static boolean predictTheWinnerDPWithDataCompression(int[] nums){
		//TODO
		return true;
	}
	
	public static void main(String[] args){
		int[] nums = new int[]{1, 5, 2};
		System.out.println(predictTheWinnerDP(nums));
	}
}
