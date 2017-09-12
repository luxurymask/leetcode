package string;

public class Solution468 {
	public static String validIPAddress(String IP) {
		if(IP.endsWith(".") || IP.endsWith(":")) return "Neither";
        String[] array = IP.split("\\.");
        if(array.length != 4){
        	array = IP.split(":");
        	if(array.length != 8) return "Neither";
        	for(int i = 0;i < 8;i++){
        		String s = array[i];
        		if(!isValidIPv6Num(s)) return "Neither";
        	}
        	return "IPv6";
        }
        for(int i = 0;i < 4;i++){
        	String s = array[i];
        	if(!isValidIPv4Num(s)) return "Neither";
        }
        return "IPv4";
    }
	
	public static boolean isValidIPv6Num(String s){
		if(s.equals("") || s.length() > 4) return false;
		char[] array = s.toCharArray();
		for(int i = 0;i < array.length;i++){
			char c = array[i];
			if(!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) return false;
		}
		return true;
	}
	
	public static boolean isValidIPv4Num(String s){
		int num;
		try{
			num = Integer.parseInt(s);
			if(num == 0 && s.length() == 1) return true;
			if(num > 0 && num <= 255 && s.charAt(0) != '0') return true;
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public static void main(String[] args){
		String s = "01.01.01.01";
		System.out.println(validIPAddress(s));
	}
}
