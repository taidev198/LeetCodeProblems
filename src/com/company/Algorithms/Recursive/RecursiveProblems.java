package com.company.Algorithms.Recursive;

public class RecursiveProblems {


    static int sumOfPositive(int[] arr, int size){
        if (size == 0)
            return 0;
        int sum = sumOfPositive(arr, size - 1);
        if (arr[size-1 ] >=0)
            sum += arr[size-1];

        return sum;
    }
    static int primePalindrome(int N) {
        if(N == 1 || N ==2)
        return 2;

        boolean[] notIsPrime = new boolean[2*N +1];
        int factor = 2;
        while (factor <= 2*Math.pow(10,8)){
            if (!notIsPrime[factor]){
                int j = factor;
                while (j <= 2*Math.pow(10,8)){
                    notIsPrime[j] = true;
                    j *= factor;
                }
            }
            if (factor >= N){
                char[] temp = Integer.toString(N).toCharArray();
                int left = 0;
                boolean isPanlidrome = true;
                while (left < temp.length/2 ){
                   if (temp[left] != temp[temp.length/2 - left-1]){
                       isPanlidrome = false;
                       break;
                   }
                   left++;
                }
                if (isPanlidrome)
                    return factor;
            }
            factor++;
        }
        return 0;
    }

    static boolean hasOddParity(String s, int len){
        if (len == 0)
            return false;
        if (s.charAt(len -1) == '1')
            return true;
        return hasOddParity(s, len -1);
    }



    public static void main(String...args){

       // System.out.println(hasOddParity("001", 3));
        System.out.println(primePalindrome(8));
    }
}
