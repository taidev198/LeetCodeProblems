package com.company.Algorithms.String;

import java.util.*;

public class StringInterview2 {


    static void permutationString(char[] s, int left, int right){
        if (left > right)
            return;

        if (right<s.length) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;System.out.println(s);
            permutationString(s,left,right+1);

        }
        else {
            permutationString(s,left+ 1,s.length-1);
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;System.out.println(s);

        }
    }

    static boolean checkInclusion(String s1, String s2) {
        List<String> ans= new ArrayList<>();
        permute(s1.toCharArray(), 0, s1.length()-1, ans);
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
            if (s2.contains(ans.get(i)))
                return true;
        }

        return false;
    }

    static  void permute(char[] s, int left, int right ,List<String> list) {
        int i;
        if (left == right) {
            list.add(new String(s));
        }
        else {
            for (i = left; i <= right; i++) {
                char tmp = s[left];
                s[left] = s[i];
                s[i] = tmp;
                permute(s, left+1, right, list);
                 tmp = s[left];
                s[left] = s[i];
                s[i] = tmp; //backtrack
            }
        }
    }

    /**Given a string, find the length of the longest substring without repeating characters.
     * Example 1:
     * Input: "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.*/
    static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int max =0;
        int start =0;
        HashSet<Character> hashSet = new HashSet<>();
        for(int i=0; i< len ;i++){
            char ch = s.charAt(i);
            if(!hashSet.add(ch)){
                max = Math.max(max, hashSet.size());
                hashSet.clear();
                i = start;
                start++;
            }
        }
        return max;

    }


    static String longestPalindrome(String s) {

        int len = s.length();
        String maxLen = "";
        for(int i = len -1; i >= 0; i--){
            char ch = s.charAt(i);
            int j = 0;
            while(j <i && s.charAt(j) != ch)
                j++;
            if (j == i) continue;
            boolean isPalindrome = true;
            int left = i-1, right = j+1;
            while(left > right){
                if(s.charAt(left) != s.charAt(right)){
                    isPalindrome = false;
                    break;
                }
                left--;
                right++;
            }

            if(isPalindrome){
                if(i-j+1 > maxLen.length())
                    maxLen = s.substring(j, i-j+2);
            }
        }
        return maxLen;
    }

    /**Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
     * Examples:
     * s = "leetcode"
     * return 0.
     * s = "loveleetcode",
     * return 2.*/
    static int firstUniqChar(String s){
        if(s.length() == 0) return -1;
        Map<Character, Integer> hashtable = new LinkedHashMap<>();//Using LinkedHashMap to
        for (int i = 0; i < s.length(); i++) {//avoid sorted key.
            char ch = s.charAt(i);
            if (!hashtable.containsKey(ch))
                hashtable.put(ch, i);
            else {
                hashtable.replace(ch, (int)hashtable.get(ch), -1);
            }
        }

        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(hashtable.entrySet());
        for(Map.Entry<Character, Integer> entry:
                entries){
            if(entry.getValue() != -1)
                return entry.getValue();
        }
        return -1;
    }

    static String frequencySort(String s) {
        StringBuilder ans = new StringBuilder();
        int len = s.length();

        int[] bucketSort = new int['z' - 'A' + 1];
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) <= 'z' && s.charAt(i) >='a')
            bucketSort[Math.abs(s.charAt(i)-'a')]++;
            else bucketSort[Math.abs(s.charAt(i)-'A' + 26)]++;
        }
        for (int i = 0; i < bucketSort.length; i++) {
            if (bucketSort[i] == 0)
                continue;
            int maxIdx = i;
            for (int k = 0; k < len; k++) {
                if (bucketSort[k] == 0)
                    continue;
                if (bucketSort[maxIdx] < bucketSort[k])
                    maxIdx = k;

            }
            for (int j = 0; j <bucketSort[maxIdx] ; j++) {
                if ( maxIdx < 26)
                ans.append((char) (maxIdx +'a') );
                else ans.append((char) (maxIdx -26 +'A') );
            }
            bucketSort[maxIdx] = -1;
        }

        return ans.toString();
    }

    static  String reverseOnlyLetters(String S) {
        int len = S.length();
        char[] charArray = S.toCharArray();
        int left = 0, right = len -1;
        while(left < right){
            while(left < right && !Character.toString( charArray[left]).matches("[a-zA-Z]") ) left++;
            while( right >left&& !Character.toString( charArray[right]).matches("[a-zA-Z]") ) right--;

            char temp = charArray[right];
            charArray[right] = charArray[left];
            charArray[left] = temp;
            left++;
            right--;
        }

        return new String(charArray);
    }

    static int[] bestSeat(int[][] a) {
        int[] ans = new int[]{-1,-1};
        int r = a.length;
        int c = a[0].length;
        int max =-9, rMax =9999, cMax = 9999;
        for(int i= 0;i< r; i++){
            for( int j = 0; j< c; j++){
                int temp = 0;
                if(a[i][j] == -1){
                    if(j < c - 1 && a[i][j+1] > 0)
                        temp += a[i][j+1];
                    if(j >0  && a[i][j-1] > 0)
                        temp += a[i][j-1];
                    if(i < r - 1 && a[i+1][j] > 0)
                        temp += a[i+1][j];
                    if(i >0 && a[i-1][j] > 0)
                        temp += a[i-1][j];

                    if(j < c - 1 && i < r -1 && a[i+1][j+1] > 0)
                        temp += a[i+1][j+1];
                    if(j >0 && i>0 && a[i-1][j-1] > 0)
                        temp += a[i-1][j-1];
                    if(i >0 && j< c - 1 && a[i-1][j+1] > 0)
                        temp += a[i-1][j+1];
                    if(i < r -1 && j >0 && a[i+1][j-1] > 0)
                        temp += a[i+1][j-1];
                    if(temp >= max){
                        if(temp == max &&((rMax > i && cMax == j) ||(cMax > j && rMax == i) ) ){
                            rMax = i;
                            cMax = j;
                        }else if(temp > max){
                            max = temp;
                            rMax = i;
                            cMax = j;
                        }
                        ans[0] = rMax;
                        ans[1] = cMax;
                    }

                }
            }
        }
        return ans;
    }

    /**Example:
     * Input: words = ["gin", "zen", "gig", "msg"]
     * Output: 2
     * Explanation:
     * The transformation of each word is:
     * "gin" -> "--...-."
     * "zen" -> "--...-."
     * "gig" -> "--...--."
     * "msg" -> "--...--."
     * There are 2 different transformations, "--...-." and "--...--.".*/
    static int uniqueMorseRepresentations(String[] words) {
        String[] encode = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        HashSet<String> ans = new HashSet<>();//using hashset to avoid duplicate value
        for(int i = 0; i < words.length; i++){
            String temp = "";//using string instead StringBuilder because StringBuilder has no contains method
            for(int j = 0; j< words[i].length(); j++)//to check two strings are whether equal.
                temp += encode[words[i].charAt(j) -'a'];
            ans.add(temp);
        }
        return ans.size();
    }

    public static void main(String...args){
        List<Character> characters = new ArrayList<>();

        //System.out.println(frequencySort("Mymommaalwayssaid,\"Lifewaslikeaboxofchocolates.Youneverknowwhatyou'regonnaget."));
//        System.out.println(Arrays.toString(bestSeat(new int[][]{
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 3, 6, 0, 3, -1, 0, 2, 3, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 8, 3, 0, 8, 8, 0, 9, 7, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, -1, 3, 0, 5, 1, 0, 8, 1, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 2, -1, 0, 3, 4, 0, 4, 5, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0,0}})));

        System.out.println(uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
        System.out.println( "--...-.".contains( "--...-."));
    }

     class test{
        private test(){};
    }
    class test1 extends test{

    }
}
