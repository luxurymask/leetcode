package string;

public class Solution657 {
	public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        char[] array = moves.toCharArray();
        for(int k = 0;k < array.length;k++){
        	char c = array[k];
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
