package string;

/**
 * 647. Palindromic Substring
 * @author liuxl
 */
public class Solution647 {
	
	/**
	 * 231ms Solution........
	 * @param s
	 * @return
	 */
	public static int countSubstrings(String s) {
		int count = 0;
        for(int i = 0;i < s.length();i++){
        	count += palindromicCount(s, i);
        }
        return count;
    }
	
	public static int palindromicCount(String s, int i){
		int count = 0;
		for(int j = 0;j <= i;j++){
			int low, high;
			for(low = j, high = i;low < high;low++, high--){
				if(s.charAt(low) != s.charAt(high)){
					break;
				}
			}
			if(low >= high) count++;
		}
		return count;
	}
	
	public static void main(String[] args){
		System.out.println(countSubstrings("aaa"));
	}
}
