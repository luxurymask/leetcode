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
	
	
}
