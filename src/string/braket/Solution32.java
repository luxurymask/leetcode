package string.braket;

import java.util.Stack;

public class Solution32 {
	public static int longestValidParentheses(String s) {
		char[] array = s.toCharArray();
		Stack<Integer> stack = new Stack<>();
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			if(array[i] == '('){
				stack.push(i);
			}else{
				if (!stack.isEmpty() && array[stack.peek()] == '(') {
					stack.pop();
				} else {
					stack.push(i);
				}
			}
		}
		
		int end = s.length() - 1;
		int start = 0;
		while(!stack.isEmpty()){
			start = stack.pop();
			max = Math.max(max, end - start);
			end = start - 1;
		}
		max = Math.max(max, end + 1);
		return max;
	}
	
	public static int longestValidParenthesesWithDP(String s) {
		int[] dp = new int[s.length() + 1];
		dp[0] = 0;
		char[] array = s.toCharArray();
		int result = 0;
		for(int i = 0;i < array.length;i++){
			if(array[i] == '('){
				dp[i + 1] = 0;
			}else{
				if(i != 0 && array[i - 1] == '('){
					dp[i + 1] = dp[i - 1] + 2;
				}else if(i != 0 && array[i - 1] == ')' && i - dp[i] > 0 && array[i - dp[i] - 1] == '('){
					dp[i + 1] = dp[i] + 2 + ((i - dp[i] - 1 >= 0) ? dp[i - dp[i] - 1] : 0);
				}
				result = Math.max(dp[i + 1], result);
			}
		}
		return result;
	}
	
	public static void main(String[] args){
		String s = "())";
		System.out.println(longestValidParenthesesWithDP(s));
	}
}
