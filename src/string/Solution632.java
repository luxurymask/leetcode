package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 632. Smallest Range
 * @author liuxl
 *
 */
public class Solution632 {
	/**
	 * TLE.
	 * @param nums
	 * @return
	 */
	public static int[] smallestRange(List<List<Integer>> nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int minIndex = 0;
        int[] indexes = new int[nums.size()];
        int minDifference = Integer.MAX_VALUE;
        int resultMax = max;
        int resultMin = min;
        
        while(indexes[minIndex] < nums.get(minIndex).size()){
            for(int i = 0;i < nums.size();i++){
            	int currentNum = nums.get(i).get(indexes[i]);
            	if(currentNum < min){
            		min = currentNum;
            		minIndex = i;
            	}
            	if(currentNum > max) max = currentNum;
            }
            if(minDifference > max - min){
                minDifference = max - min;
            	resultMax = max;
            	resultMin = min;
            }
            indexes[minIndex]++;
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
        }
        return new int[]{resultMin, resultMax};
    }
	
	public static void main(String[] args){
		List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(4,10,15,24,26));
		List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(0,9,12,20));
		List<Integer> list3 = new ArrayList<Integer>(Arrays.asList(5,18,22,30));
		List<List<Integer>> list = new ArrayList<List<Integer>>(Arrays.asList(list1, list2, list3));
		System.out.println(smallestRange(list)[0] + "" + smallestRange(list)[1]);
	}
}
