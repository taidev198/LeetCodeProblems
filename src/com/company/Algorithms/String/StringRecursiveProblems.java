package com.company.Algorithms.String;

public class StringRecursiveProblems {


    static String reverseString(String string){
        if (string.length() == 1)
            return string;
        return  reverseString(string.substring(1)) + string.charAt(0);

    }


//    static boolean winVietlot(int[] arr){
//        int len = arr.length;
//        int[] prefixSumSubArray = new int[len+1];
//        for (int i = 1; i <= len; i++) {
//            prefixSumSubArray[i] = arr[i-1] + prefixSumSubArray[i-1];
//        }
//
//        for (int i = 0; i <len ; i++) {
//            int prefixSum  = prefixSumSubArray[i+1] - prefixSumSubArray[i];
//
//            int j = i+1;
//
//            while (j <len && prefixSumSubArray[j+1] - prefixSumSubArray[i+1] == prefixSum)
//                j++;
//
//        }
//
//
//    }
//

    public static void main(String...args){

        System.out.println(reverseString("thanh tai nguyen"));

    }
}
