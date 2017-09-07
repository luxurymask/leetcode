package string;

public class Solution657 {
	public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for(int k = 0;k < moves.length();k++){
        	char c = moves.charAt(k);
        	switch(c){
        		case 'L' : x--; break;
        		case 'R' : x++; break;
        		case 'U' : y++; break;
        		case 'D' : y--; break;
        	}
        }
        return x == 0 && y == 0;
    }
}
