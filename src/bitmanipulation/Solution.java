package bitmanipulation;

import java.util.HashSet;
import java.util.Set;

public class Solution {
	/**
	 * 136. Single Number
	 * using extra space.
	 * @param nums
	 * @return
	 */
	public int singleNumberUsingExtraSpace(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i : nums){
        	if(set.contains(i)){
        		set.remove(i);
        	}else{
        		set.add(i);
        	}
        }
        return set.iterator().next();
    }
	
	/**
	 * 136. Single Number
	 * without using extra space.
	 * 今日头条面试官提示使用抑或。
	 * @param nums
	 * @return
	 */
	public int singleNumber(int[] nums){
		int result = 0;
		for(int i : nums){
			result ^= i;
		}
		return result;
	}
	
	/**
	 * 260. Single Number III
	 * 今日头条面试题，没做出来。用Set做面试官不算你会做。
	 * @param nums
	 * @return
	 */
	public int[] singleNumberIII(int[] nums){
		int[] result = new int[2];
		int tResult = 0;
		for(int i : nums){
			tResult ^= i;
		}
		
		tResult &= -tResult;
		
		for(int i : nums){
			if((tResult & i) != 0){
				result[0] ^= i;
			}else{
				result[1] ^= i;
			}
		}
		
		return result;
	}
	
	/**
	 * 137. Single Number II
	 * @author yfcheng 's idea.
	 * @param nums
	 * @return
	 */
	public int singleNumberII(int[] nums) {
		int result = 0;
		for(int i = 0;i < 32;i++){
			int count = 0;
			for(int num : nums){
				if(((num >> i) & 1) != 0) count++;
			}
			if(count % 3 != 0){
				result |= (1 << i);
			}
		}
		return result;
    }

	public static void main(String[] args){
		Solution s = new Solution();
		int[] array = new int[]{1};
		int result = s.singleNumberII(array);
		System.out.println(result);
	}
}
