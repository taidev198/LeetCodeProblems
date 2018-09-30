package com.company.Algorithms.Sorting;

import java.util.*;
import java.util.stream.Collectors;

public class Sorting {



    static void bucketSort(int[] arr, int key){

    }

    /**https://www.interviewcake.com/concept/java/counting-sort*/
    /**Input: [2,0,2,1,1,0]
     Output: [0,0,1,1,2,2]*/
   static void sortColors(int[] nums) {
        int[] numCounts = new int[3];
        for(int i=0;i< nums.length;i++)
            numCounts[nums[i]]++;
        int currentSortedIndex =0;
        for(int i=0;i<numCounts.length;i++){
            int count = numCounts[i];
            for(int j =0;j < count ;j++)
                nums[currentSortedIndex++] = i;
        }

    }

    static String sortByDigits(int[] arr){
//        List<Integer> list = Arrays.stream(arr)
//                .boxed().sorted((o1, o2) -> {
//                    String num1 = Integer.toString(o1);
//                    String num2 = Integer.toString(o2);
//                    if ((num1.charAt(0) - '0') == (num2.charAt(0) - '0')) {
//                        if (o1 % 10 == 0 && o2 % 10 == 0)
//                            return o1 > o2 ? -1:1;
//                        else if (o1 % 10 == 0) {
//                            if (num1.length() == num2.length())
//                               return o1 > o2 ? -1:1;
//                            return 1;
//                        } else if (o2 % 10 == 0) {
//                            if (num1.length() == num2.length())
//                                return o1 > o2 ? -1:1;
//                            return -1;
//                        }
//                        return o1 > o2 ? -1:1;
//                    }else if ((num1.charAt(0) -'0') > (num2.charAt(0) -'0'))
//                        return -1;
//                    return 1;
//                }).collect(Collectors.toList());
        List<Integer> list = Arrays.stream(arr)
                .boxed().sorted(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return -o1.compareTo(o2);
                    }
                }).collect(Collectors.toList());
        return list.toString().replace("[","")
                .replace("]","").replace(", ","");

    }

    public static void main(String...args){
        System.out.println((sortByDigits(new int[]{121,12})));
    }
}

