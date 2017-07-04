package dynamicprogramming;

public class Solution494 {
	/**
	 * recursive solution.
	 * @param nums
	 * @param S
	 * @return
	 */
	public static int findTargetSumWays(int[] nums, int S) {
        return findTargetSumWays(nums, 0, S);
    }
	
	public static int findTargetSumWays(int[] nums, int i, int S){
		if(i == nums.length){
			if(S == 0){
				return 1;
			}else{
				return 0;
			}
		}
		return findTargetSumWays(nums, i + 1, S - nums[i]) + findTargetSumWays(nums, i + 1, S + nums[i]);
	}
	
	//TODO DP Solution.
	
	public static void main(String[] args){
		int[] nums = new int[]{1, 1, 1, 1, 1};
		System.out.println(findTargetSumWays(nums, 3));
	}
}
