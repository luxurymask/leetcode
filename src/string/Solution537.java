package string;

public class Solution537 {
	public static String complexNumberMultiply(String a, String b) {
        String[] arrayA = a.split("\\+");
        String[] arrayB = b.split("\\+");
        
        int aa = Integer.parseInt(arrayA[0]);
        String s = arrayA[1];
        int ab = Integer.parseInt(s.substring(0, s.length() - 1));
        
        int ba = Integer.parseInt(arrayB[0]);
        s = arrayB[1];
        int bb = Integer.parseInt(s.substring(0, s.length() - 1));
        
        int resultA = aa * ba - ab * bb;
        int resultB = aa * bb + ab * ba;
        
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(resultA).append("+").append(resultB).append("i");
        return stringBuffer.toString();
    }
	
	public static void main(String[] args){
		String a = "1+-1i", b = "1+-1i";
		System.out.println(complexNumberMultiply(a, b));
	}
}
