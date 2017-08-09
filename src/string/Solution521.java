package string;

/**
 * 521. Longest Uncommon Subsequence I
 * @author liuxl
 */
public class Solution521 {
	public static boolean isSubsequence(String sub, String string){
		int i = 0, j = 0;
		while (i < sub.length() && j < string.length()) {
			if (sub.charAt(i) == string.charAt(j)) {
				i++;
			}
			j++;
		}
		return i == sub.length();
	}
	
	public static int findLUSlength(String a, String b) {
		int aLength = a.length();
		int bLength = b.length();
		if(aLength == 0 && bLength == 0 || (aLength == bLength && isSubsequence(a, b))) return -1;
		return Math.max(aLength, bLength);
    }
	
	public static void main(String[] args){
		System.out.println(findLUSlength("aaa", "a"));
	}
}
