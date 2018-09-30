package com.company.Algorithms.BinarySearch;

import java.util.Arrays;

public class BinarySearch {


    static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length == 0) return false;
        int left =0, right = matrix.length-1;

        while(left<=right){
            int mid = left + (right-left)/2;
            if(matrix[mid][0] <= target && matrix[mid][matrix[mid].length-1] >= target){
                for(int i=0;i<matrix[mid].length ;i++){
                    if(matrix[mid][i] == target)
                        return true;
                }
                break;
            }else if(matrix[mid][0] > target) right =mid-1;
            else left = mid+1;
        }
        return false;
    }

    static boolean searchMatrixII(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length == 0) return false;
        int left =0, right = matrix.length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(matrix[mid][0] <= target && matrix[mid][matrix[mid].length-1] >= target){
                for(int i=0;i<matrix[mid].length ;i++){
                    if(matrix[mid][i] == target)
                        return true;
                }
                break;
            }else if(matrix[mid][0] > target) right =mid-1;
            else left = mid+1;
        }
        left =0; right = matrix[0].length;

        return false;
    }

    static int search(int[] nums, int target) {
        int left = 0, right =nums.length-1;
        while(left <= right){
            int mid = left +(right-left)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < nums[mid +1] && nums[mid] > target)
                right = mid-1;
            else if(nums[mid] > nums[mid+1] && nums[mid] < target) left = mid+1;
            else left = mid+1;
        }
        return -1;
    }

    /**Given an array of integers nums sorted in ascending order,
     * find the starting and ending position of a given target value.
     * Input: nums = [5,7,7,8,8,10], target = 8
     Output: [3,4]*/
    static int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1,-1};
        int left=0,right =nums.length-1;
        while(left <=right){
            int mid = left +(right-left)/2;
            if(nums[mid] == target){
                int j=mid+1;
                int i = mid-1;
                while(j<nums.length && nums[j] == target)
                    j++;
                while(i>=0 && nums[i] == target)
                    i--;
                ans[0] = i+1;
                ans[1] = j-1;
                break;
            }else if(nums[mid] > target)
                right =mid-1;
            else left = mid+1;
        }
        return ans;
    }

    static  int getMoneyAmount(int n) {
        if(n == 1) return 0;
        int left = 1;
        int right = n;
        int money =0;
        while(left <right){
            int mid =left+ (right-left)/2;
            money += mid;
            if(mid <n)
                right = mid-1;
            else if(mid > n)
                left = mid+1;
            else return money;

        }
        return money;
    }

   static int mySqrt(int x) {
       if(x == 0)return 0;
       else if(x < 4) return 1;
       int left = 0;
       int right = x-1;
       int mid = 0;
       while(right - left >1){
           mid =left + (right -left)/2;
         //  if(mid * mid == x) return mid;
            if((mid *mid) <= x ) right = mid+1;
           else left = mid;
       }
       return mid;
    }

    public static void main(String...args){

   //  System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
       // System.out.println(search(new int[]{4,5,6,7,0,1,2},5));
        System.out.println(mySqrt(4));
    }
}
