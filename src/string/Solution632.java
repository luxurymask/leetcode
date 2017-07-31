package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 632. Smallest Range
 * @author liuxl
 *
 */
public class Solution632 {
	public static int[] smallestRangeUsingPriorityQueue(List<List<Integer>> nums){
		int minIndex = 0;
        int k = nums.size();
        int[] indexes = new int[k];
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((i,j) -> nums.get(i).get(indexes[i]) - nums.get(j).get(indexes[j]));
//		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(new Comparator<Integer>(){
//
//			@Override
//			public int compare(Integer i1, Integer i2) {
//				return nums.get(i1).get(indexes[i1]) - nums.get(i2).get(indexes[i2]);
//			}
//			
//		});
		int minDifference = Integer.MAX_VALUE;
		int resultMax = 0;
		int resultMin = 0;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0;i < k;i++){
			minHeap.offer(i);
			max = Math.max(max, nums.get(i).get(indexes[i]));
		}
		while(true){
			max = Math.max(max, nums.get(minIndex).get(indexes[minIndex]));
			minIndex = minHeap.poll();
			min = nums.get(minIndex).get(indexes[minIndex]);
			if(minDifference > max - min){
				resultMax = max;
				resultMin = min;
				minDifference = max - min;
			}
			indexes[minIndex]++;
			if(indexes[minIndex] == nums.get(minIndex).size()) break;
			minHeap.offer(minIndex);
		}
		
		return new int[]{resultMin, resultMax};
	}
	
	/**
	 * TLE.
	 * @param nums
	 * @return
	 */
	public static int[] smallestRange(List<List<Integer>> nums) {
        int minIndex = 0;
        int k = nums.size();
        int[] indexes = new int[k];
        int minDifference = Integer.MAX_VALUE;
        int resultMax = 0;
        int resultMin = 0;
        
        while(indexes[minIndex] < nums.get(minIndex).size()){
        	int min = 100000;
        	int max = -100000;
            for(int i = 0;i < k;i++){
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
        }
        return new int[]{resultMin, resultMax};
    }
	
	public static void main(String[] args){
		List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(4,10,15,24,26));
		List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(0,9,12,20));
		List<Integer> list3 = new ArrayList<Integer>(Arrays.asList(5,18,22,30));
		List<List<Integer>> list = new ArrayList<List<Integer>>(Arrays.asList(list1, list2, list3));
		System.out.println(smallestRangeUsingPriorityQueue(list)[0] + "-" + smallestRangeUsingPriorityQueue(list)[1]);
	}
}
