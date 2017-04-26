package leetcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntPredicate;

import javax.swing.event.MenuListener;

public class Solution2 {
    //rubbish code
    public static int singleNumber(int[] nums) {
        for(int i = 0;i < nums.length;i++){
            for(int j = 0;j < nums.length;j++){
                if(j != nums.length - 1){
                    if(j == i){
                        continue;
                    }
                    if(nums[j] == nums[i]){
                        break;
                    }
                }else{
                    return i;
                }
            }
        }
        return -1;
    }
    
    //rubbish code2
    public static int singleNumber2(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for(int i = 0;i < nums.length;i++){
            if(result.contains(nums[i])){
                result.remove(new Integer(nums[i]));
            }else{
                result.add(nums[i]);
            }
        }
        return result.get(0);
    }
    
    public static int singleNumber3(int[] nums){
        Map<Integer, Integer> result = new HashMap<>();
        for(int i = 0;i < nums.length;i++){
            if(result.containsKey(nums[i])){
                result.remove(nums[i]);
            }else{
                result.put(nums[i], 1);
            }
        }
        
        Iterator iterator = result.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            return (int)entry.getKey();
        }
        return -1;
    }
    
    public static int addDigits(int num) {
        while(num > 9){
            String s = String.valueOf(num);
            num = 0;
            for(int i = 0;i < s.length();i++){
                num += Integer.parseInt(String.valueOf(s.charAt(i)));
            }
        }
        return num;
    }
    
    public static void moveZeroes(int[] nums) {
        int count = 0;
        for(int i = 0;i < nums.length;i++){
            if(nums[i] == 0){
                count++;
            }else{
                int temp = nums[i];
                nums[i] = nums[i - count];
                nums[i - count] = temp;
            }
        }
    }
    
    public static int findSubstringInWraproundString(String p) {
        int sum = 0;
        int[] chars = new int[26];
        int countEnd = 0;
        for(int i = 0;i < p.length();i++){
            if(i == 0 || (p.charAt(i) - p.charAt(i - 1) == 1) || ((p.charAt(i) == 'a') && (p.charAt(i - 1) == 'z'))){
                countEnd++;
            }else{
                countEnd = 1;
            }
            
            if(chars[p.charAt(i) - 'a'] < countEnd){
                chars[p.charAt(i) - 'a'] = countEnd;
            }
        }
        for(int i = 0;i < chars.length;i++){
            sum += chars[i];
        }
        return sum;
    }
    
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        int count = 0;
        for(int num : nums){
            if(!counts.containsKey(num)){
                count = 1;
                counts.put(num, 1);
            }else{
                count = counts.get(num) + 1;
                counts.put(num, count);
            }
            if(count > (nums.length / 2)){
                break;
            }
        }
        return count;
    }
    
    public static boolean isPowerOfTwo(int n) {
        int count = 0;
        if(n <= 0){
            return false;
        }
        for(int i = 0;i < 32;i++){
            if((n & 1)  == 1 && i != 31){
                count++;
            }
            if(count > 1){
                return false;
            }
            n >>= 1;
        }
        return true;
    }
    
    public static int countSegments(String s) {
        if(s.length() == 0){
            return 0;
        }
        String[] result = s.split(" ");
        int count = result.length;
        for(int i = 0;i < result.length;i++){
            System.out.println("#########" + result[i] + "#########");
            if(result[i].equals("")){
                count--;
            }
        }
        return count;
    }
    
    public static String reverseVowels(String s) {
        StringBuffer sb = new StringBuffer(s);
        int i = 0, j = s.length() - 1;
        while(i < j){
            if(!isVowel(sb.charAt(i))){
                i++;
            }else if(!isVowel(sb.charAt(j))){
                j--;
            }else{
                char temp = sb.charAt(i);
                sb.setCharAt(i, sb.charAt(j));
                sb.setCharAt(j, temp);
            }
        }
        return sb.toString();
    }
    
    public static boolean isVowel(char c){
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
            return true;
        }
        return false;
    }
    
    public static int removeElement(int[] nums, int val) {
        int i = 0, j = nums.length - 1;
        int count = 0;
        if(i == j){
            if(nums[i] == val){
                return 0;
            }else{
                return 1;
            }
        }
        while(i <= j){
            if(nums[i] != val && i < nums.length){
                i++;
            }
            if(nums[j] == val && j >= 0){
                count++;
                j--;
            }
            if(nums[i] == val && nums[j] != val){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                count++;
                i++;
                j--;
            }
        }
        return nums.length - count;
    }
    
    public static int[] plusOne(int[] digits) {
        List<Integer> resultList = new ArrayList<Integer>();
        int carry = 0;
        boolean stopAdd = false;
        for(int i = digits.length - 1;i >= 0;i--){
            if(stopAdd == false){
                if((digits[i] + carry) == 9){
                    resultList.add(0);
                    carry = 1;
                    if(i == 0){
                        resultList.add(1);
                    }
                }else{
                    resultList.add(digits[i] + carry + 1);
                    stopAdd = true;
                    carry = 0;
                }
            }else{
                resultList.add(digits[i]);
            }
        }
        int[] result = new int[resultList.size()];
        for(int i = resultList.size() - 1, j = 0;i >= 0;i--,j++){
            result[j] = resultList.get(i);
        }
        return result;
    }
    
    public static int hammingDistance(int x, int y) {
        int count = 0;
        while(x != 0 && y != 0){
            count += ((x & 1) ^ (y & 1));
            x >>>= 1;
            y >>>= 1;
        }
        return count;
    }
    
    public static int removeDuplicates(int[] nums) {
        int count = 0;
        for(int i = 0;i < nums.length - count;i++){
            if(i == nums.length - count - 1){
                break;
            }
            while(nums[i + 1] == nums[i]){
                count++;
                for(int j = i + 1;j < nums.length - count;j++){
                    nums[j] = nums[j + 1];
                }
                if(i > nums.length - count - 2){
                    break;
                }
            }
        }
        return nums.length - count;
    }
    
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i = 0;i < n;i++){
            int index = m + i - 1;
            while(index >= 0 && nums1[index] > nums2[i]){
                nums1[index + 1] = nums1[index];
                index--;
            }
            nums1[index + 1] = nums2[i];
        }
    }
    
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(sum % 2 != 0){
            return false;
        }
        sum /= 2;
        
        boolean[][] hasSubset = new boolean[sum + 1][nums.length + 1];
        for(int i = 0;i <= sum;i++){
            hasSubset[i][0] = false;
        }
        for(int i = 0;i <= nums.length;i++){
            hasSubset[0][i] = true;
        }
        for(int i = 1;i <= sum;i++){
            for(int j = 1;j <= nums.length;j++){
                hasSubset[i][j] = hasSubset[i][j - 1];
                if(i - nums[j - 1] >= 0){
                    hasSubset[i][j] = hasSubset[i][j] || hasSubset[i - nums[j - 1]][j - 1];
                }
            }
        }
        return hasSubset[sum][nums.length];
    }
    
    public static void main(String[] args){
        int[] nums1 = new int[]{3,3,3,4,5};
        System.out.println(canPartition(nums1));
    }
}
