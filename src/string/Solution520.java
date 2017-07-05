package string;

public class Solution520 {
	public static boolean detectCapitalUse(String word) {
        char[] charArray = word.toCharArray();
        char last = charArray[charArray.length - 1];
        boolean flag;
        if(last >= 'a'){
        	flag = true;
        }else{
        	flag = false;
        	if(charArray[0] >= 'a') return false;
        }
        
        for(int i = 1;i < charArray.length - 1;i++){
        	if(charArray[i] < 'a' == flag){
        		return false;
        	}
        }
        return true;
    }
	
	public static void main(String[] args){
		String word = "FlaG";
		System.out.println(detectCapitalUse(word));
	}
}
