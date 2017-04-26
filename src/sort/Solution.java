package sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
	
	/**
	 * Tool for 524.
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean isSub(String s1, String s2){
		if(s1.equals("")){
			return false;
		}
		int j = 0;
		for(int i = 0;i < s2.length();i++){
			char c = s2.charAt(i);
			for(;j < s1.length();j++){
				if(c == s1.charAt(j)){
					j++;
					break;
				}

				if(j == (s1.length() - 1)){
					return false;
				}
			}
			if(i != s2.length() - 1 && j == s1.length()){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 524. Longest Word in Dictionary through Deleting
	 * rubbish solution.
	 * @param s
	 * @param d
	 * @return
	 */
	public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, new Comparator<String>(){

			@Override
			public int compare(String s1, String s2) {
				if(s1.length() < s2.length()){
					return 1;
				}else if(s1.length() > s2.length()){
					return -1;
				}else{
					return s1.compareTo(s2);
				}
			}
        	
        });
        for(String sInD : d){
        	if(isSub(s, sInD)){
        		return sInD;
        	}
        }
        return "";
    }
	
	/**
	 * 324. Wiggle Sort II //TODO
	 * @param nums
	 */
	public static void wiggleSort(int[] nums) {
        int temp;
        int i = 0;
        int j = i + 1;
        int k = j + 1;
        int prei = -1, prej = -1, prek = 0;
        while(k < nums.length){
        	if(nums[i] < nums[j]){
        		if(nums[j] < nums[k]){
        			temp = nums[k];
        			nums[k] = nums[j];
        			nums[j] = temp;
        		}else if(nums[j] > nums[k]){
        			//do nothing.
        		}else{
        			if(prej != -1){
        				if(nums[j] < nums[prej]){
            				temp = nums[j];
            				nums[j] = nums[i];
            				nums[i] = temp;
        				}else if(nums[j] > nums[prej]){
        					temp = nums[j];
        					nums[j] = nums[prej];
        					nums[prej] = temp;
        					temp = nums[k];
        					nums[k] = nums[j];
        					nums[j] = temp;
        					continue;
        				}
        			}
        			int l = k + 1;
        			for(;l < nums.length;l++){
        				if(nums[l] > nums[j]){
        					temp = nums[l];
        					nums[l] = nums[j];
        					nums[j] = temp;
        					break;
        				}
        			}
        			if(l == nums.length - 1){
        				
        			}
        		}
        	}else if(nums[i] > nums[j]){
    			temp = nums[j];
    			nums[j] = nums[i];
    			nums[i] = temp;
        		if(nums[i] < nums[k]){
        			temp = nums[k];
        			nums[k] = nums[j];
        			nums[j] = temp;
        		}
        	}else{
        		if(nums[i] < nums[k]){
        			temp = nums[k];
        			nums[k] = nums[j];
        			nums[j] = temp;
        		}else{
        			for(int l = k + 1;l < nums.length;l++){
        				if(nums[l] > nums[j]){
        					temp = nums[l];
        					nums[l] = nums[j];
        					nums[j] = temp;
        					break;
        				}
        			}
        		}
        	}
        	prei = i;
        	prej = j;
        	prek = k;
        	i = k;
        	j = i + 1;
        	k = j + 1;
        }
    	if(j == nums.length - 1 && nums[i] > nums[j]){
    		temp = nums[j];
    		nums[j] = nums[i];
    		nums[i] = temp;
    	}
    }
	
	public static void main(String[] args){
		String s1 = "abc";
		String s2 = "abc";
		System.out.println(isSub(s1, s2));
	}
}
