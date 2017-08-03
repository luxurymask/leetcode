package hashtable;

import java.util.HashMap;
import java.util.Map;

public class Solution535 {
	private Map<Integer, String> map = new HashMap<Integer, String>();
	// Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int hashCode = longUrl.hashCode();
        map.put(hashCode, longUrl);
        return "http://" + hashCode;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String hashCode = shortUrl.substring(7);
        return map.get(Integer.parseInt(hashCode));
    }
    
    public static void main(String[] args){
    	Solution535 codec = new Solution535();
    	System.out.println(codec.decode(codec.encode("http://www.baidu.com/")));
    }
}
