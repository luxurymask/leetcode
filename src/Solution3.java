import java.util.HashMap;
import java.util.Map;

public class Solution3 {
	public static int lengthOfLongestSubstring(String s) {
		if(s.equals("")) return 0;
		char[] array = s.toCharArray();
		int max = 1;
		Map<Character, Integer> map = new HashMap<>();
		int length = array.length;
		int currentCount = 0;
		int start = 0;
		for(int i = 0;i < length;i++){
			char c = array[i];
			if(map.containsKey(c)){
				if(max < currentCount) max = currentCount;
				start = Math.max(start, map.get(c));
				currentCount = i - start;
			}else{
				currentCount++;
			}
			map.put(c, i);
		}
		if(max < currentCount) max = currentCount;
		return max;
	}
	
	public static void main(String[] args){
		String s = "avva";
		System.out.println(lengthOfLongestSubstring(s));
	}
}
