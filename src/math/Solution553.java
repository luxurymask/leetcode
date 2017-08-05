package math;

public class Solution553 {
	public static String optimalDivision(int[] nums) {
		if(nums.length == 1) return nums[0] + "";
		if(nums.length == 2) return nums[0] + "/" + nums[1];
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(nums[0]).append("/(");
        int i = 1;
        for(;i < nums.length - 1;i++){
        	stringBuffer.append(nums[i]).append("/");
        }
        stringBuffer.append(nums[i]).append(")");
        return stringBuffer.toString();
    }
	
	public static void main(String[] args){
		int[] nums = new int[]{1000,100,10,2};
		System.out.println(optimalDivision(nums));
	}
	
}
