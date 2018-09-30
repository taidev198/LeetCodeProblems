package com.company.Algorithms.String;

import java.util.*;

public class StringInterview1 {


    static  char findTheDifference(String s, String t) {
        StringBuilder tmp = new StringBuilder(t);
        for (int i = 0; i < s.length(); i++) {
            int index = tmp.indexOf(String.valueOf(s.charAt(i)));
            if (index != -1)
                tmp.delete(index, index+1);
        }

        return tmp.charAt(0);
    }

    static  boolean buddyStrings(String A, String B) {
        int n = A.length();
        int m = B.length();
        if(n != m) return false;
        int count =0;
        int left = 0;
        boolean isSame = false;
        while(left < n){
            if(left < n-1 && A.charAt(left) == A.charAt(left+1))
            isSame = true;
            if(A.charAt(left) != B.charAt(left)){
                if(left == n-1) return false;
                if(A.charAt(left) != B.charAt(left+1) || A.charAt(left+1) != B.charAt(left) )
                    return false;
                count++;
                if(count == 2) return false;
                left +=2;
            }else left++;

        }

        return count == 1 || isSame || (count == 0 && !isSame ) ;
    }

    /**Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
     * return the length of last word in the string.
     * If the last word does not exist, return 0.
     * Note: A word is defined as a character sequence consists of non-space characters only.
     * Example:
     * Input: "Hello World"
     * Output: 5*/
    static  int lengthOfLastWord(String s) {
        s = s.trim();
        if( s.length() ==0 )
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

    /**Input: "Let's take LeetCode contest"
     Output: "s'teL ekat edoCteeL tsetnoc"*/
    static String reverseWords(String s) {
        int left =0, right;
        int len = s.length();
        char[] ans = s.toCharArray();
        while(left < len){
            right = left +1;
            while(right < len && s.charAt(right) != ' ')
                right++;
            int temp = right;
            right--;
            while(left < right){
                char tmp = ans[left];
                ans[left] = ans[right];
                ans[right] = tmp;
                left++;
                right--;
            }

            left = temp+1;
        }
        return new String(ans);
    }

    static int romanToInt(String s) {
        int ans  =0;
        char[] temp = s.toCharArray();
        boolean retract = false;
        for (int i = 0; i < temp.length; i++) {
            if (retract){
                retract =false;
                continue;
            }
            switch (temp[i]){
                case 'I': if (i < temp.length-1 ){
                        if (temp[i+1] == 'X'){
                            ans += 9;
                            retract = true;//read more one character
                        }
                        else if (temp[i+1] == 'V'){
                            ans += 4;
                            retract = true;//read more one character
                        }
                    }
                    if (!retract){
                    ans += 1;
                    retract = false;
                }
                    break;
                case 'V': ans += 5;
                    break;
                case 'X': if (i < temp.length-1 ) {
                    if (temp[i + 1] == 'L'){
                        ans += 40;
                        retract = true;//read more one character
                    }

                    else if (temp[i + 1] == 'C'){
                        ans += 90;
                        retract = true;//read more one character
                    }
                }
                if (!retract){
                    ans += 10;
                    retract = false;
                }
                    break;
                case 'L': ans += 50;
                    break;
                case 'C':  if (i < temp.length-1){
                    if (temp[i+1] == 'D'){
                        ans += 400;
                        retract = true;//read more one character
                    }

                    else if (temp[i+1] == 'M'){
                        ans += 900;
                        retract = true;//read more one character
                    }
                }
                if (!retract){
                    ans += 100;
                    retract = false;
                }
                    break;
                case 'D': ans += 500;
                    break;
                case 'M':
                    ans += 1000;
                    break;
            }
        }


        return ans;
    }

    static String intToRoman(int num) {
        StringBuilder ans = new StringBuilder();
        String temp = Integer.toString(num);
        int numberDigits = temp.length();
        for (int i = 0; i < numberDigits; i++) {
            int pow = (int) Math.pow(10, numberDigits-1-i);
            int number = temp.charAt(i) -'0';
            switch (pow){
                case 1:if (number >= 5  && number < 9){
                    ans.append('V');
                    if (number >5)
                        concat(ans,'I', number - 5);
                }else if (number < 4){
                    concat(ans, 'I',number);
                }else if (number == 4){
                    ans.append("IV");
                }else ans.append("IX");
                    break;
                case 10:if (number >= 5  && number < 9){
                    ans.append('L');
                    if (number >5)
                        concat(ans,'X', number - 5);
                }else if (number < 4){
                    concat(ans, 'X',number);
                }else if (number == 4){
                    ans.append("XL");
                }else ans.append("XC");
                    break;
                case 100:if (number >= 5  && number < 9){
                    ans.append('D');
                    if (number >5)
                    concat(ans,'C', number - 5);
                }else if (number < 4){
                    concat(ans, 'C',number);
                }else if (number == 4){
                    ans.append("CD");
                }else ans.append("CM");
                    break;
                case 1000:
                   concat(ans, 'M', number);
                    break;
            }
        }
        return ans.toString();
    }

    static void concat(StringBuilder res, char ch, int number){
        for (int i = 0; i < number; i++)
            res.append(ch);
    }

    static int countBinarySubstrings(String s) {
        long startTime = System.currentTimeMillis();
        int ans = 0;
        int len = s.length();
        if(len == 2) return s.charAt(0) != s.charAt(1)? 1:0;
        for(int i=2; i<= len ;i+=2){
            for(int j= 0;j <len; j++){
                if (j+i >len)
                    continue;
                int left = j, right = i+j-1;
                boolean isZero = false;
                if (s.charAt(left) == '0')
                    isZero = true;
                while (left < right ){
                    if (isZero && s.charAt(right) == '1' && s.charAt(left) == '0'){
                        left++;
                        right--;
                    }
                    else if (!isZero && s.charAt(right) == '0'&& s.charAt(left) == '1'){
                        left++;
                        right--;
                    } else break;
                }
                if (left > right)
                    ans++;
            }
        }
        System.out.println("time:" + (System.currentTimeMillis() - startTime));
        return ans;
    }

    /**We have a string S of lowercase letters, and an integer array shifts.
     * Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
     * For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
     * Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.
     * Return the final string after all such shifts to S are applied.
     * Example 1:
     * Input: S = "abc", shifts = [3,5,9]
     * Output: "rpl"
     * Explanation:
     * We start with "abc".
     * After shifting the first 1 letters of S by 3, we have "dbc".
     * After shifting the first 2 letters of S by 5, we have "igc".
     * After shifting the first 3 letters of S by 9, we have "rpl", the answer.*/
    static  String shiftingLetters(String S, int[] shifts) {
        int lenShifts = shifts.length;
        int[] preFixSum = new int[lenShifts ];
        preFixSum[0] = shifts[lenShifts-1];
        for (int i = 1; i < lenShifts; i++) {
            preFixSum[i] = (shifts[lenShifts-i-1] %26) + preFixSum[i-1];
        }
        char[] chars = S.toCharArray();
        for (int i = 0; i < lenShifts; i++) {
                chars[i] = (char)((chars[i] -'a'+ preFixSum[lenShifts - i-1]) %26 +'a');
        }
        return new String(chars);
    }


    static  String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new Hashtable<>();
        String[] wordInParagraph = paragraph.split(" ");

        for (int i = 0; i < wordInParagraph.length; i++) {
            wordInParagraph[i] = wordInParagraph[i].toLowerCase();
            if (wordInParagraph[i].contains(".") || wordInParagraph[i].contains("!")||wordInParagraph[i].contains("?")
            || wordInParagraph[i].contains(";") || wordInParagraph[i].contains(","))
            wordInParagraph[i] = wordInParagraph[i].substring(0, wordInParagraph[i].length()-1);
            boolean isMatch = false;
            for (int j = 0; j < banned.length; j++) {
                    if (banned[j].compareToIgnoreCase(wordInParagraph[i]) == 0)
                        isMatch = true;
            }
            if (isMatch) continue;
            if (!map.containsKey(wordInParagraph[i]))
                map.put(wordInParagraph[i], 1);
            else {
                Integer temp = map.get(wordInParagraph[i]);
                map.replace(wordInParagraph[i], temp, temp+1);
            }
        }
        map = sortByValue(map);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        return list.get(list.size() -1).getKey();
    }

    static  <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
          list.sort(Map.Entry.comparingByValue());
//        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
//            @Override
//            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
//                if (((Comparable) ((Map.Entry) (o1)).getValue())
//                        .compareTo(((Map.Entry) (o2)).getValue()) ==0 )
//                    return ((Comparable) ((Map.Entry) (o1)).getKey())
//                            .compareTo(((Map.Entry) (o2)).getKey());
//                return  ((Comparable) ((Map.Entry) (o1)).getValue())
//                        .compareTo(((Map.Entry) (o2)).getValue());
//            }
//        });
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    static String reverseStr(String s, int k) {
        StringBuilder ans = new StringBuilder(s);
        int start  =0;
        int end = k;
        if(k> s.length()){
            StringBuilder temp = new StringBuilder(ans.substring(0, s.length()));
            ans.replace(0, s.length(), temp.reverse().toString());
        }
        else  if(k*2 > s.length() || k == s.length()){
            StringBuilder temp = new StringBuilder(ans.substring(0, k));
            ans.replace(0, k, temp.reverse().toString());
        }
        else{
            for (; k <= s.length(); ) {
                StringBuilder temp = new StringBuilder(ans.substring(start, start+end > s.length()? s.length(): start+end));
                ans.replace(start, start+end, temp.reverse().toString());
                k *= 2;
                start = k;
            }
        }
        return ans.toString();
    }

    static  boolean checkValidString(String s) {
        int len = s.length();
        if(len == 0) return true;
        if(len == 1){
            return s.charAt(0) == '*';
        }
        char[] chars = s.toCharArray();

        int left = 0, right = len -1;
        while (left < right){
            if ((chars[left] == ')' || chars[left] =='*') && (chars[right] == '(' || chars[right] == '*')
                    && left != 0 && right != len-1){
                left++;
                right--;
            }
            else if ((chars[left] == '*' || chars[left ] == '(') && (chars[right] == ')' || chars[right] == '*')){
                left++;
                right--;
            }else return false;
        }
        return true;
    }

    /**https://en.wikipedia.org/wiki/Look-and-say_sequence*/
    static String countAndSay(int n) {
        String[] tmp = new String[]{"1","11","21","1211","111221"};
        if(n<=5)
            return tmp[n-1];
        String temp =  "111221#";
        for(int i=1;i<n-4;i++){
            StringBuilder res = new StringBuilder();
            int count =0;
            for(int j =0; j< temp.length()-1;j++){
                if(temp.charAt(j) == temp.charAt(j+1))
                    count++;
                else{
                    res.append(count);
                    res.append(temp.charAt(j));
                    count=0;
                }
            }
            temp = res.append('#').toString();
        }
        return temp.substring(0, temp.length()-1);

    }

    /**Input:
    * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
    * Output:
    * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
    * Explanation:
    * Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
    * Notice each digit has it's own entry in the array.*/
    static int compress(char[] chars) {
        int k =0;
        int len = chars.length;
        if(len == 1) return 1;
        int count =1;
        for(int i=0; i<len-1;i++){
            if (chars[i] == chars[i+1])
                count++;
            else {
                chars[k++] = chars[i];
                if(count == 1) continue;
                char[] number = Integer.toString(count).toCharArray();
                for (int j = 0; j <number.length ; j++) {
                    chars[k++] = number[j];
                }
                count = 1;
            }
        }
        if (len >1 && chars[len-2] != chars[len-1]){
            chars[k++] = chars[len -1];
        }else {
            chars[k++] = chars[len-1];
            char[] number = Integer.toString(count).toCharArray();
            for (int j = 0; j <number.length ; j++) {
                chars[k++] = number[j];
            }
        }
        return k;
    }


    public static void main(String...args){


      //  System.out.println(countBinarySubstrings("00110011"));
       // System.out.println(mostCommonWord("a, a, a, a, b,b,b,c, c, c", new String[]{"a"}));
       // System.out.println(reverseStr("hyzqyljrnigxvdtneasepfahmtyhlohwxmkqcdfehybknvdmfrfvtbsovjbdhevlfxpda
        // ovjgunjqlimjkfnqcqnajmebeddqsgl", 39));
    //    System.out.println(checkValidString("((()))()(())(*()()())**(())()()()()((*()*))((*()*)"));
        
      //  System.out.println(countBinarySubstrings("0111011001000111111010101111111111101111101011111111100011000101100110110111110110100111110010001011101000100110110111101111111101101110101111101111010110011100110010100111111111011111101101000011111101111111110110000111110111111010011111010001101110110001111101111001011001011101001111111111011111101101110101111100110110101111111110010000110011111111011111101111010011111100010110001010111110100011110011110010100011111110010101110110110000001000111000100101100011111111101110011001100110011111011011110011101101011111101100010011011000110111100110111101111111111011011111110000110110010111101101100101101101110111011111111110100111100001100001100100011101110101111000010111110011110100110110111010111011010101000010001001110011111110101111010111100101110110011001111011101101111111100101001100010000011110110011001101110011111110011111011110111101111101111111111011111110111011101110011001010111000110110011111101010111011010001101111100101011111101011110010111101101110111101110111010010110010001011010100100011110011110110111111111000011111111100110100101011110110101111111110111111101101110111111011111100011010001010101011111111001100111110100111110010011100110010011011111111011101011111001101001001111111110111111011001110100010110111101010101010111011011111001111101001001111111011111101110111111100111111110111011011011100001101111011110011011011100011111111001011011111110101001110111101111110011101101011101011101100001110101111001110011011101001110111111110111110111101100110100111010110111111111011101111111111101100111111011111101111111010111100010111100101111001111111100111001111011001111010001111100011111001111011011001010111000111101110011011111110000011010001110111101011110101011100010011001111111111101101111101010101101010001111111111110111101110101111111010111111111111111100101011001101001111100010101011110110111111101010001010010111111110000111101111110011111100111010101111101011101101110110001011111111110100110010110010101010010000111100101011100001111111011110111001100011010010110101001111011001101101110001100111111011000111110111110110011101100101011011100000110010111101011010100111111100111110010111111001101110111101111011011101001101000010110001101100101110101111110111110111111111111111111110111101110100011111011111101001010110001000011011100111011001011010011000001111001110110011110110010111010001110111101011010110111101011011011010111111110110011100010111100010110001111111111101100110101001111111100100110111111110010001111010111001111111001011001001111001010101110100101111010111000110011110101001011111111010011110111010111111011111111110101000110101101101001111011110010101011010110010010111111011111101010100110001011110111101110111110111011010111111101111111110011101011110111001110111111111101110001110110011010111111101110100011111101101000111111111011111111101111011111111111101111010001110101110111100111101111110101111000010110101110111011011110111011010101111110101100111111001001001111111111101111100111110111011011110111111110101010111111000011101001111111101101111110111101110100101110000101111001111101111111111011011100101111101110101101111011101111000111111110110101011001110111101111011101010111111111101100110110101111101101101101101011101100001100100101001101001111110011010111111101011101101011111101111110010001111011010111110100011100101111010110100111111111100100001111011011101101111101111011111011101011111101110001100100101011110111110111111010101001100010111010101011001111101011100111110011011000111111111100111101111000101110110011011111001110110011111001111110111101101001100111111111010000110110011111111111001100110110111110000111111111000110011011111111101110010101110110011001011111010011101110101011100101100110110011011100110101111111110110011111011110111101100110011110111101110000110111111011111010001001011110010111110101110011100011101111010010011010111111000111101110011111100111111100111111011111010011010011011101101011100111111101110001101111101111010111100101111111100001111111111010111101110111110111111010110011100010011110000111101101111011000110110010011011000111110010000100111111111011101101011001000111011011111001101001011110101111101110011111111111001111100111101111101101111101111111111111000110100110110010011111111011101011111001111101011011111111110111101111101111110101010111101101111110111110110101110010111000100101110110111111100000010111110111111111010011011001011011111100111101101111111100000100110011111010010011111010100011100110110101110110111010101101001111100111111111111001100111011110101101101011110111111100111101111111010110010011011011101111110000001111111100101111100110110111001111011111111001111110100000111100110101010011111100110001001111111111101110010010001111101111110111110100100011110001011011111011111111101011111111111111110101111111100110011001110111010111011111011111110111110001111011010001010110101111010101110111101010011101111000111111011000111111110110001001110100000000100101010111011001111100101111110100001110111001110101011110101110001011110010001111110110110110100001001001010111101100111000110111001101111110111011111100111111001111111010101110001011101110111111101110011101101011111100100001111111010111011000111111100011110011001100100111001011001011011011011011110000011011101010101111101101101100111101000101011101011111111001010110011111110100110011001111011111001010000011101111111100010111001110111100111101011011001111110011101101100011011011110111100101111110111111111100101001100111111010001010110011101001111100011010011100111101111011101110111111011000101111000011101110110101101011011011111111111111011001010010110110110001011111001010101001111111010101011101100111100111100101111100101011111010110011111100010001110111110001111100111110010111101001110111110111001110110000110101001011111111111110111111111100111111111011110001110111110111110010001111000010110110111011100110111010100011011110000110101001001111001110000111111101101011111100111101110111111110011110111100001110101111011111011111110110110101000100111100010100100110011101010101111011011100111011000001110101100011101101011110111110111101111111100100010110101010101011111111011111101110010101111001111101101110111111010000111111100111110111011110111011111101100010010101011111111001111011110111011000111101000110111110110100101111111111011001011111111011001001111011011101111001111110000110001011010011100001111101101111010111110111111000011011101101111111001111110110001111111011101101011010001110111001101100110110011101111010111111101110001111011111111101010011010110110110110111001101001001001110111111100001111011011001110100000111001111011111110100110111101100011111100010111011111110110110111101100101011101111110110111111001100100111110111000011111101111100111101110111111110111000111010011100010110110111111011010110001011010111000101110111101011110111110111001011101111101110101010101111001110101011001111011111111110111011111000101110111111110011110111110101111111111111101110111100111011110111001110011111111101100010011011111101111011000010001101011001111010101011101111111111011100111111001011111111111110011110111110111111011111010110010101101111111011001010101111110111110111011100110011010111001111011111101111111010011110111101111001110011111001111010101111100011111010111110111111111111111101100110100110111010011111110110100110110011110010011111111100101111110111111001111000101101111111101000000011001001111101111011111011111001011111111111111111101111001011011010001011110110011101111111111001011111110110101010110011010101111101011110011001011111111110111111111111010111101100111111111110000100111101101111110111011110101010111011111101101011011111110011100111101100100001100100100101101100101111110111001111110101111101011110111111111100010111110000111111111111101011100101101101100011001111011101010100011101111110100011011011011011111111111110111101011101110001101111101010001010101101110101001010111101000110010101111011111101011011010110011111111111010001001100011111001111101001000111010101000111110010111111011111111111011111101111101101001110111011111001100110001101100111011010011111111001111110111010010110001010111101111010101100111101011111101111111101101101101110101001011100011101100111001111111111011011101111110111001100111101011010011111100101011011111101000001011101010011011110101100111101000001011111011001111111011101111011011111101111110110101101010110111111101111111111010111110110110111111011111100001101001101110110010011010001111101110011010011111110101111111111001110110010111100001110101111011011110100101101011010011111000111111100110111010011101111110111011101101111110101111110111010101111001011110010001110110111100111111001111110101000100101001011111111101110001011100110011011011011101010001101010111111111110110100011111110101100101011111101101011110101011111101011101101111111111111101101101011100110100111110011001101111101111111111000101010101110011110111011010101111111111011001011110100101110101011110111111011010101111111101001110110001110010111100100001111110110011011111111111111010001111101101000010111001111011111110111101011111011100111000100111111110100011100101110010010100011101011111001110100110001111110010111011111101111101100011011001110011011111101111110111101111110011100110100010101111111111111010110101111011010011101100011010011101011001111100111111101111111110011011111010110100111111101100110011011000111111100001111111111011101100101000001100101101000100001111011110000100100111111011110111111111000101010101101001100011100110110011011111011010110011110111111010101101000101110111101111110101111100110111111101011111011111101001110001000110100011100010111110101111101001110110011110110010111000110110011110111111111111101001011011110110111001100110110011111010101111111110111111101101101011101111010110101110110111011101111011110110010111100110111111111000101101100110111110111011101110111010111010101111111111010001011100111010101100110010000111110110100111001001101111100001111111111010010100111111110001010010100111001111101010110101101101111110110111000100111010110001101110110111101011" ));
    }
}
