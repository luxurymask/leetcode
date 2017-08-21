package string;

/**
 * 43. Multiply Strings
 * 
 * @author liuxl
 */
//TODO
public class Solution43_NOTDONEYET {
	public static String multiply(String num1, String num2) {
		if(num1.equals("0") || num2.equals("0")){
			return "0";
		}
		StringBuffer resultBuffer = new StringBuffer();
		int length1 = num1.length();
		int length2 = num2.length();
		int i = 1;
		int cache = 0;
		while (i <= Math.min(length1, length2)) {
			int sum = 0;
			for (int j = 1; j <= i; j++) {
				sum += Character.getNumericValue(num1.charAt(length1 - 1 - j + 1))
						* Character.getNumericValue(num2.charAt(length2 - 1 - i + j));
			}
			sum += cache;
			cache = sum / 10;
			int current = sum % 10;
			resultBuffer.insert(0, current);
			i++;
		}
		if(i <= length1){
			int currentNum = Character.getNumericValue(num1.charAt(length1 - i));
			for(int j = length2 - 1;j >= 0;j--){
				int sum = currentNum * Character.getNumericValue(num2.charAt(j)) + cache;
				cache = sum / 10;
				int current = sum % 10;
				resultBuffer.insert(0, current);
			}
		}
		if(i <= length2){
			int currentNum = Character.getNumericValue(num2.charAt(length2 - i));
			int sum = 0;
			for(int j = length1 - 1;j >= 0;j--){
				sum += currentNum * Character.getNumericValue(num1.charAt(j)) + cache;
				cache = sum / 10;
				int current = sum % 10;
				resultBuffer.insert(0, current);
			}
		}
		if(cache != 0) resultBuffer.insert(0, cache);
		return resultBuffer.toString();
	}

	public static void main(String[] args) {
		System.out.println(multiply("123", "456"));
	}
}
