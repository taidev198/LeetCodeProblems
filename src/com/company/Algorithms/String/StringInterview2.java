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

    static int firstUniqChar(String s){
        Hashtable<Character, Integer> hashtable = new Hashtable<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!hashtable.containsKey(ch))
                hashtable.put(ch, i);
            else {

                hashtable.remove(ch);
            }
        }
         List<Map.Entry<Character, Integer>> entries = new ArrayList<>(hashtable.entrySet());
        return entries.get(0).getValue();
    }
    public static void main(String...args){
//        System.out.println(checkInclusion("adc","dcda"));
        List<Character> characters = new ArrayList<>();

        System.out.println(firstUniqChar("leetcode"));
    }
}
