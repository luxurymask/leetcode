package dynamicprogramming;

/**
 * 72. Edit Distance (编辑距离)
 * @author liuxl
 */
public class Solution72 {
	public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] cost = new int[m + 1][n + 1];
        
        for(int i = 0;i <= m;i++){
        	cost[i][0] = i;
        }
        
        for(int i = 0;i <= n;i++){
        	cost[0][i] = i;
        }
        
        for(int i = 1;i <= m;i++){
        	for(int j = 1;j <= n;j++){
        		char c1 = word1.charAt(i - 1);
        		char c2 = word2.charAt(j - 1);
        		int min = cost[i - 1][j - 1];
        		if(cost[i - 1][j] < min) min = cost[i - 1][j];
        		if(cost[i][j - 1] < min) min = cost[i][j - 1];
        		cost[i][j] = min + 1;
        		if(min == cost[i - 1][j - 1] && c1 == c2){
        			cost[i][j]--;
        		}
        	}
        }
        
        return cost[m][n];
    }
	
	public static void main(String[] args){
		System.out.println(minDistance("ab", "a"));
	}
}
