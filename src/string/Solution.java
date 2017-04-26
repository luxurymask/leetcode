package string;

public class Solution {
	
	public static String originalDigits(String s) {
        int[] result = new int[10];
        for(int i = 0;i < s.length();i++){
        	switch(s.charAt(i)){
        	case 'z':
        		result[0]++;
        		break;
        	case 'w':
        		result[2]++;
        		break;
        	case 'u':
        		result[4]++;
        		break;
        	case 'x':
        		result[6]++;
        		break;
        	case 'g':
        		result[8]++;
        		break;
        	case 'o':
        		result[1]++;
        		break;
        	case 'h':
        		result[3]++;
        		break;
        	case 'f':
        		result[5]++;
        		break;
        	case 's':
        		result[7]++;
        		break;
        	case 'i':
        		result[9]++;
        		break;
        	}
        }
        
        result[1] -= (result[0] + result[2] + result[4]);
        result[3] -= result[8];
        result[5] -= result[4];
        result[7] -= result[6];
        result[9] -= (result[8] + result[6] + result[5]);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < 10;i++){
        	for(int j = 0;j < result[i];j++){
        		sb.append(i);
        	}
        }
        return sb.toString();
    }
	
	/**
	 * 541. Reverse String II
	 * rubbish solution.
	 * @param s
	 * @param k
	 * @return
	 */
    public String reverseStr(String s, int k) {
        StringBuilder result = new StringBuilder();
        while(s.length() != 0){
        	if(s.length() < k){
        		result.append(reverseString(s.substring(0)));
        		break;
        	}else if(s.length() < 2 * k){
            	result.append(reverseString(s.substring(0, k))).append(s.substring(k));
            	break;
        	}
        	result.append(reverseString(s.substring(0, k))).append(s.substring(k, 2 * k));
        	s = s.substring(2 * k);
        }
        return result.toString();
    }
    
    /**
     * 344. Reverse String
     * @param s
     * @return
     */
    public String reverseString(String s) {
        char temp = '0';
        char[] reversed = s.toCharArray();
        int len = s.length();
        for(int i = 0;i < len/2;i++){
            temp = reversed[i];
            reversed[i] = reversed[len - i - 1];
            reversed[len - i - 1] = temp;
        }
        return new String(reversed);
    }
    
    public static void main(String[] args){
    	String s = "zerotwoone";
    	System.out.println(originalDigits(s));
    }
}
