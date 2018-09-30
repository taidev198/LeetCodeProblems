package com.company.Algorithms.String;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class StringInterview {

    static int myAtoi(String str) {
        str = str.trim();//delete white space
        if(str.length() == 0) return  0;
        char[] tmp = str.toCharArray();
        int i=0;
        int start  =0;
        if (!Character.toString(str.charAt(0)).matches("[0-9-+]*") )
           return 0;
        if (tmp[0] == '+' || tmp[0] == '-'){
            start=1;
            i=1;
        }
        //remove 0's
        while(start< str.length() && (str.charAt(start) == '0' ) ){
            start ++;
        }
        i= start;
        while(i < str.length()){
            if(Character.toString(tmp[i]).matches("[0-9]*"))
                i++;
            else break;
        }
        if(start > i)
            return 0;
        String temp = str.substring(start,i);
        if (tmp[0] == '-')
            temp = "-" + temp;
        if(temp.equals("") ||( !Character.toString(temp.charAt(0)).matches("[0-9]*") && temp.length() ==1 )) return 0;
        if(tmp[0] == '-'  ){
            if ( temp.length() > Integer.toString(Integer.MIN_VALUE).length())
                return Integer.MIN_VALUE;
            else if (Long.valueOf(temp) < Integer.MIN_VALUE )
                return Integer.MIN_VALUE;
        }else {
            if (temp.length() > 10)
                return Integer.MIN_VALUE;
            else if (Long.valueOf(temp) > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
        }
        return Integer.valueOf(temp);
    }

    static int firstUniqChar(String s){

        HashSet<Character> hashSet = new HashSet<>();
        hashSet.add('a');
        hashSet.add('a');
        System.out.println(hashSet.size());

        return 0;
    }

    static boolean isIsomorphic(String s, String t) {

        for (int i = 0; i <s.length() ; i++) {
            int index = s.indexOf(s.charAt(i), i+1);
            if (t.indexOf(t.charAt(i), i+1) != index) return false;
        }
        return true;
    }


    /**Input:
     ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
     Output:
     Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].*/
    static int compress(char[] chars) {
        int count =1;
        int k=0;
        for(int i= 1;i<chars.length;i++){

            if(chars[i] == chars[i-1]) count++;
            else{
                if(count == 1) chars[k++] = chars[i-1];
                else{
                    char[] num = Integer.toString(count).toCharArray();
                    chars[k++] = chars[i-1];
                    for(int itor = 0; itor<num.length;itor++){
                        chars[k++] = num[itor];
                    }
                }
                count =1;
            }
        }
        if(count == 1) chars[k++] = chars[chars.length-1];
        else{
            char[] num = Integer.toString(count).toCharArray();
            chars[k++] = chars[chars.length-1];
            for(int itor = 0; itor<num.length;itor++){
                chars[k++] = num[itor];
            }
        }
        return k;
    }

    /**Input: ["flower","flow","flight"]
     Output: "fl"*/
    static String longestCommonPrefix(String[] strs) {
        if(strs.length ==0) return "";
        boolean ok =true;
        int minLen = strs[0].length();
        for(int i=1;i<strs.length;i++){
            if(strs[i].length() <minLen)
                minLen = strs[i].length();
        }
        if(minLen ==0) return "";
        int i=0;
        StringBuilder ans = new StringBuilder();
        while(i < minLen){

            for(int j=1; j< strs.length;j++){
                if(strs[j].charAt(i) != strs[j-1].charAt(i))
                    ok = false;
            }
            if(ok)
                ans.append(strs[0].charAt(i));
            i++;
        }
        return ans.toString();
    }

    static String toGoatLatin(String S) {
        String[] res = S.split(" ");
        StringBuilder ans = new StringBuilder();
        StringBuilder charA = new StringBuilder("a");
        for(int i =0;i<res.length;i++){
            StringBuilder tmp = new StringBuilder(res[i]);
            if(Character.toString(res[i].charAt(0)).matches("[aeiouAEIOU]*"))
                tmp.append("ma").append(charA);
            else{
                tmp.append(tmp.charAt(0)).append("ma").append(charA);
                tmp.delete(0,1);
            }
            ans.append(tmp).append(" ");
            charA.append('a');
        }
        return ans.toString().trim();
    }

    /**Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
    *Note: For the purpose of this problem, we define empty string as valid palindrome.
    *Input: "A man, a plan, a canal: Panama"
    *Output: true*/
    static  boolean isPalindrome(String s) {
        if (s.length() ==0 || s.length() == 1) return true;
        int left =0;
        int right = s.length()-1;
        while (left <= right){
            char charLeft = s.charAt(left);
            char charRight = s.charAt(right);
            if (!Character.toString(charLeft).matches("[A-Za-z0-9]*")) left++;
            else if (!Character.toString(charRight).matches("[A-Za-z0-9]*")) right--;
            else if ( (Character.toString(charLeft).matches("[A-Za-z0-9]*")) &&
                    (Character.toString(charLeft).matches("[A-Za-z0-9]*"))){
                if (Character.toString(charLeft).compareToIgnoreCase(Character.toString(charRight)) != 0)
                    return  false;
                left++;
                right--;
            }
        }
        return true;
    }

    /**Input: haystack = "hello", needle = "ll"
     Output: 2
     Input: haystack = "aaaaa", needle = "bba"
     Output: -1*/
    static int strStr(String haystack, String needle) {
        if(needle.length() == 0) return 0;
        int n = haystack.length();
        int m = needle.length();
        for(int i=0;i<=n-m;i++){
            if(haystack.charAt(i) == needle.charAt(0)){
                int j = 1;
                while(j<m && haystack.charAt(i+j) == needle.charAt(j)){
                    j++;
                }
                if(j == m) return i;
            }
        }
        return -1;
    }

    static boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        int dist = 1;
        while(dist <=len/2){
            boolean isMatch = true;
            for(int i=0;i<len/dist; i+=dist){
                if(!s.substring(i, dist).equals(s.substring(i+dist,dist*i))){
                    isMatch = false;
                    break;
                }
            }
            if(isMatch) return true;
            dist++;
        }
        return false;
    }


    /**Input: a = "11", b = "1"
     *Output: "100"
     *Input: a = "1010", b = "1011"
     *Output: "10101"
     * */
    static String addBinary(String a, String b) {
        int nA = a.length();
        int nB = b.length();
        char[] ans = new  char[nA+nB];
        Arrays.fill(ans, '0');
        int i = nA-1;
        int j = nB-1;
        int k = ans.length-1;
        while(i>=0 && j>=0){
            int tmp = (a.charAt(i) -'0') + (b.charAt(j) -'0')+(ans[k]-'0');
            if( tmp < 2)
                ans[k--] =  (char) (tmp +'0');
            else if (tmp == 2){
                ans[k--] = '0';
                ans[k] = '1';
            }else  {
                ans[k--] =  '1';
                ans[k] = '1';
            }
            i--;
            j--;
        }
        while(i>=0){
            int tmp = (a.charAt(i) -'0') +(ans[k]-'0');
            if(tmp<2)
                ans[k--] = (char) (tmp +'0');
            else{
                ans[k--] = '0';
                ans[k] = '1';
            }
            i--;
        }
        while(j>=0){
            int tmp = (b.charAt(j) -'0') +(ans[k]-'0');
            if(tmp<2)
                ans[k--] = (char) (tmp +'0');
            else{
                ans[k--] = '0';
                ans[k] = '1';
            }
            j--;
        }
        if (ans[k] == '0')
        return new String(Arrays.copyOfRange(ans, k+1, ans.length));
        return new String(Arrays.copyOfRange(ans, k, ans.length));
    }

    /**Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
     *Note:
     *The length of both num1 and num2 is < 5100.
     *Both num1 and num2 contains only digits 0-9.
     *Both num1 and num2 does not contain any leading zero.
     *You must not use any built-in BigInteger library or convert the inputs to integer directly.*/
    static String addStrings(String num1, String num2) {
        int nA = num1.length();
        int nB = num2.length();
        char[] ans = new  char[Math.max(nA, nB)+1];
        Arrays.fill(ans, '0');
        int i = nA-1;
        int j = nB-1;
        int k = ans.length-1;
        while(i>=0 && j>=0){
            int tmp = (num1.charAt(i) -'0') + (num2.charAt(j) -'0')+(ans[k]-'0');
            if( tmp < 10)
                ans[k--] =  (char) (tmp +'0');
           else  {
                ans[k--] =  (char) (tmp%10 +'0');
                ans[k] = '1';
            }
            i--;
            j--;
        }
        while(i>=0){
            int tmp = (num1.charAt(i) -'0') +(ans[k]-'0');
            if(tmp<10)
                ans[k--] = (char) (tmp +'0');
            else{
                ans[k--] = (char) (tmp%10 +'0');
                ans[k] = '1';
            }
            i--;
        }
        while(j>=0){
            int tmp = (num2.charAt(j) -'0') +(ans[k]-'0');
            if(tmp<10)
                ans[k--] = (char) (tmp +'0');
            else{
                ans[k--] = (char) (tmp%10 +'0');
                ans[k] = '1';
            }
            j--;
        }
        if (ans[k] == '0')
            return new String(Arrays.copyOfRange(ans, k+1, ans.length));
        return new String(Arrays.copyOfRange(ans, k, ans.length));

    }

    static  boolean canConstruct(String ransomNote, String magazine) {
        int len = ransomNote.length();
        int lenMagazine = magazine.length();
        if(len == 0) return true;
        StringBuilder tmp= new StringBuilder(magazine);
        int i=0;
        while(i < len){
            int index = tmp.indexOf(Character.toString(ransomNote.charAt(i)));
            if(index == -1)
                return false;
            tmp.delete(index,index+ 1);
            i++;
        }
        return true;
    }

    static int lengthOfLastWord(String s) {
        if( s.length() ==0 ||(s.charAt(0) ==' ' &&s.charAt(s.length()-1) ==' '))
        return 0;
        for(int i= s.length()-1;i>=0;i--){
            if(s.charAt(i) ==' '){
                if(i != s.length()-1)
                    return s.length()-1-i;
                return i;
            }
        }
        return s.length();
    }

    /**
     * Compare two version numbers version1 and version2.
     * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
     * Input: version1 = "7.5.2.4", version2 = "7.5.3"
     * Output: -1
     * 1.0 and 1 output: 0*/

    static int compareVersion(String version1, String version2) {
        String[] ver1 = version1.split("\\.");
        String[] ver2 = version2.split("\\.");
        int lenVer1 = ver1.length;
        int lenVer2 = ver2.length;
        for(int i=0,j =0; i< lenVer1 && j < lenVer2;i++,j++){
            if(Integer.valueOf(ver1[i]) < Integer.valueOf(ver2[i])) return -1;
            else if(Integer.valueOf(ver1[i]) > Integer.valueOf(ver2[i])) return 1;
        }
        if(lenVer1 != lenVer2){
           if (lenVer1 > lenVer2){
               if (Integer.valueOf(ver1[lenVer1-1]) == 0)
                   return 0;
               else return 1;
           }else {
               if (Integer.valueOf(ver2[lenVer2-1]) == 0)
                   return 0;
               else return -1;
           }
        }
        return 0;
    }

    static  String shiftingLetters(String S, int[] shifts) {
        int lenShifts = shifts.length;
        int lenS = S.length();
        StringBuilder tmp = new StringBuilder(S);
        for (int i = 0; i < lenShifts; i++) {
            char[] chars = tmp.toString().substring(0,i+1).toCharArray();
            int numShifts = shifts[i];
            for (int j = 0; j <= i; j++) {
                chars[j] = (char)((chars[j] -'a'+ numShifts) %26 +'a');
            }
            tmp.replace(0,i+1,new String(chars));
        }
        return tmp.toString();
    }

    static boolean buddyStrings(String A, String B) {
        int n = A.length();
        int m = B.length();
        if(n != m) return false;
        int count =0;
        int left = 0;
        while(left < n){
            if(A.charAt(left) != B.charAt(left)) count++;
            left++;
        }
        return count == 2;
    }

    static int findMinDifference(List<String> timePoints) {
        int min = 0;
        int n = timePoints.size();

        for (int i = 0; i < n; i++) {
            String time1 = timePoints.get(i);
            int hour1 = Integer.parseInt(time1.substring(0,2));
            int min1 = Integer.parseInt(time1.substring(3,5));
            for (int j = 0; j < n; j++) {
                if (i!=j){
                    String time2 = timePoints.get(j);
                    int hour2 = Integer.parseInt(time1.substring(0,2));
                    int min2 = Integer.parseInt(time1.substring(3,5));

                }
            }
        }
        return min;
    }

    /**You are given a string representing an attendance record for a student.
     * The record only contains the following three characters:
     'A' : Absent.
     'L' : Late.
     'P' : Present.
     A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or
     more than two continuous 'L' (late).*/
    static boolean checkRecord(String s) {

        return countAbsent(s)<=1 && findConsecutiveLs(s);
    }
    public static int countAbsent(String s){
        int count =0;
        for(int i=0;i< s.length();i++){
            if(s.charAt(i) =='A') count ++;
        }
        return count;
    }
    public static boolean findConsecutiveLs(String s){

        int i=0,n = s.length();
        while(i<n){
            if(s.charAt(i) == 'L'){
                int j=i+1;
                while(j<n && s.charAt(j) == 'L'){
                    j++;
                }
                if(j-i >=3) return false;
                i=j;
            }
            i++;
        }

        return true;
    }


    /**Initially, there is a Robot at position (0, 0). Given a sequence of its moves,
     * judge if this robot makes a circle, which means it moves back to the original place.
     * The move sequence is represented by a string. And each move is represent by a character.
     * The valid robot moves are R (Right), L (Left), U (Up) and D (down).
     * The output should be true or false representing whether the robot makes a circle.*/
    static boolean judgeCircle(String moves) {
        int countHorizontal =0;
        int countVertical =0;
        for(int i=0;i< moves.length();i++){
            switch( moves.charAt(i)){
                case 'U':countVertical++;break;
                case 'D':countVertical--;break;
                case 'L':countHorizontal++;break;
                case 'R':countHorizontal--;break;
            }
        }
        return countHorizontal==0 && countVertical ==0;
    }
    public static void main(String...args){
      //  System.out.println(shiftingLetters("abc", new int[]{3,5,9}));
   // System.out.println(buddyStrings("aaaaaaabc", "aaaaaaacb"));
        System.out.println(addStrings("111","111"));
    }
}
