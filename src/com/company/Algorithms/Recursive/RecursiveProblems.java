package com.company.Algorithms.Recursive;

import java.util.ArrayList;
import java.util.List;

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

    /***/
    static void printAllPathFromTopLeftToRightBottomIn2nd(int[][] arr){
        int row = arr.length;
        int col = arr[0].length;
        boolean[][] visited = new boolean[row][col];
        List<Integer> chosen = new ArrayList<>();
        printAllPathFromTopLeftToRightBottomIn2ndHelper(arr, chosen, visited, 0, 0, row, col, new int[]{row-1, col-1});
    }

    static void printAllPathFromTopLeftToRightBottomIn2ndHelper(int[][] arr,List<Integer> chosen,
                                                          boolean[][] visited, int i, int j, int r, int c,
                                                          int[] target){
        if (i >= r || i <0 || j >= c || j <0 || visited[i][j])
            return;
        chosen.add(arr[i][j]);
        visited[i][j] = true;
        if (i == target[0] && j == target[1])
            System.out.println(chosen);
        printAllPathFromTopLeftToRightBottomIn2ndHelper(arr, chosen, visited, i, j+1, r, c, target);
        printAllPathFromTopLeftToRightBottomIn2ndHelper(arr, chosen, visited, i+1, j, r, c, target);
        chosen.remove(chosen.size()-1);
        visited[i][j] =false;

    }
    /**solution 2:without using backtracking*/
    static void printAll(int[][] arrA ,int currentRow, int currentColumn, String path) {
        if (currentRow == arrA.length - 1) {
            for (int i = currentColumn; i < arrA[0].length; i++) {
                path += "-" + arrA[currentRow][i];
            }
            System.out.println(path);
            return;
        }
        if (currentColumn == arrA[0].length - 1) {
            for (int i = currentRow; i <= arrA.length - 1; i++) {
                path += "-" + arrA[i][currentColumn];
            }
            System.out.println(path);
            return;
        }
        path = path + "-" + arrA[currentRow][currentColumn];
        printAll(arrA,currentRow + 1, currentColumn, path);
        printAll(arrA, currentRow, currentColumn + 1, path);
        //	printAll(currentRow + 1, currentColumn + 1, path);
    }

    public static void main(String...args){

       // System.out.println(hasOddParity("001", 3));
        //System.out.println(primePalindrome(8));
        printAllPathFromTopLeftToRightBottomIn2nd(new int[][]{{1,2,3},{5,6,7}, {9,10,11}});
    }
}
