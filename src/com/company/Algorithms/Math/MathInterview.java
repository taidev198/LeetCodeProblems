package com.company.Algorithms.Math;

import java.util.ArrayList;
import java.util.List;

public class MathInterview {

    static int trailingZeroes(int n) {
//        // Initialize result
//        int count = 0;
//
//        // Keep dividing n by powers
//        // of 5 and update count
//        for (int i = 5; n / i >= 1; i *= 5)
//            count = count+(n / i);
//
//        return count;
         /*
        The only tricky thing about this problem is to determine how many 5s are there. since if you reach five,
         you will innevitably reach 4, and thus you have a ton. Plus there are tons of even number there than five, so no worries,
          you will find one to match 5 for any amount of 5 you find in the number n*/
        int ret=0;
        while(n>0){
            ret+=n/5;
            n/=5; //this loop makes sure n/ 5,25,125,625... since each is a power of 5 and have to be counted again until the power
        }
        return ret;
    }

    /**12 + 92 = 82
     82 + 22 = 68
     62 + 82 = 100
     12 + 02 + 02 = 1
     true because end with 1*/
    static boolean isHappy(int n) {

        List<Integer> list = new ArrayList<>();
        list.add(n);
        n = sumOfProductDigits(Integer.toString(n));
        while(n != 1 && !list.contains(n)){
            list.add(n);
            n = sumOfProductDigits(Integer.toString(n));
        }
        return n ==1;
    }
   static int sumOfProductDigits(String num){
        char[] tmp = num.toCharArray();
        int res = 0;
        for(int i=0;i<tmp.length;i++)
            res += (tmp[i] -'0')*(tmp[i]-'0');
        return res;

    }

    /**Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.*/
    static boolean isUgly(int num) {
        if(num == 1) return true;
        int[] factor = new int[]{2,3,5};
        if(num <=0 || num == Integer.MIN_VALUE)
            return false;
        int i = 2;
        while (num !=0 && i>=0){
            if (num % factor[i] == 0){
                num /= factor[i];
            }
            else i--;
        }
        return num ==1;
    }

    /**Given a non-negative integer num, repeatedly add all its digits until
     *  the result has only one digit.
     *  Input: 38
     * Output: 2
     * Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
     *              Since 2 has only one digit, return it.*/
    static int addDigits(int num) {
        if(num ==0) return 0;
        return num %9==0?9: num%9;
    }

    /**Input: 28
     Output: True
     Explanation: 28 = 1 + 2 + 4 + 7 + 14*/
    static boolean checkPerfectNumber(int num) {
        int sum =0;
        for(int i=1;i<= num/2;i++){
            if(num % i==0)
                sum +=i;
        }
        return sum == num;
    }

    public static void main(String...args){

        System.out.println(checkPerfectNumber(99999995));
    }
}
