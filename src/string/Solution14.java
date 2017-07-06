package string;

public class Solution14 {
	public static String longestCommonPrefix(String[] strs) {
		StringBuffer stringBuffer = new StringBuffer();
		int length = strs.length;
		if(length == 0) return "";
		String start = strs[0];
        for(int i = 0;i < start.length();i++){
        	char c = start.charAt(i);
        	for(int j = 1;j < length;j++){
        		String s = strs[j];
        		if(i >= s.length() || s.charAt(i) != c) return stringBuffer.toString();
        	}
        	stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }
	
	public static void main(String[] args){
		String[] strs = new String[]{"abc", "abd", "abcdef"};
		System.out.println(longestCommonPrefix(strs));
	}
}
