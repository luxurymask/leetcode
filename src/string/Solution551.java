package string;

public class Solution551 {
	public static boolean checkRecord(String s) {
        char[] charArray = s.toCharArray();
        boolean absent = false;
        int late = 0;
        for(char c : charArray){
        	if(c == 'A'){
        		if(absent == true) return false;
        		absent = true;
        		late = 0;
        	}else if(c == 'L'){
        		if(late == 2) return false;
        		late++;
        	}else{
        		late = 0;
        	}
        }
        return true;
    }
	
	public static void main(String[] args){
		String stringTrue = "PPALLP";
		String stringFalse = "PPALLL";
		System.out.println(checkRecord(stringTrue));
		System.out.println(checkRecord(stringFalse));
	}
}
