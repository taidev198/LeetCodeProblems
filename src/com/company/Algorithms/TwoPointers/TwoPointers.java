package com.company.Algorithms.TwoPointers;

import java.util.*;
import java.util.stream.Collectors;

public class TwoPointers {

    static boolean isPalindrome(String s) {
        if (s.length() ==0 || s.length() == 1) return true;
        int left =0;
        int right = s.length()-1;
        while (left <right){
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

        /**Input: "leetcode"
         Output: "leotcede"*/
    static  String reverseVowels(String s) {
        int left = 0;
        int right = s.length()-1;
        char[] tmp = s.toCharArray();
        while(left<right){
            if(Character.toString(tmp[left]).matches("[aeiouAEIOU]*") && Character.toString(tmp[right]).matches("[aeiouAEIOU]*")){
                char temp = tmp[left];
                tmp[left] = tmp[right];
                tmp[right] = temp;
                left++;
                right--;
            }
            else if(!Character.toString(tmp[left]).matches("[aeiouAEIOU]*")) left++;
            else right--;

        }
        return new String(tmp);
    }


   static int countSegments(String s) {
        if(s.length() ==0) return 0;
        String[] list = s.split(" ");
        int ans = list.length;
        for(int i=0; i< list.length;i++){
            if(list[i].length() == 0) ans--;
        }
        return ans;
    }


    static int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        if(len < 3)
            return 0;
        Arrays.sort(nums);

        int closest = Integer.MAX_VALUE-1;
        for(int start = 0; start < len-2;start++){
            int left = start+1, right = len-1;
            while(left < right) {
                int value = nums[left] + nums[start] + nums[right];
                if (Math.abs(value - target) < Math.abs(closest - target)){
                    closest = value;
                }
                if(value == target)
                    return value;
                else if(value > target) {
                    right--;
                }else left++;
            }
        }
        return closest;
    }

    static String minSubstringWithAllChars(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        if(sLength == 0 && tLength == 0)
            return "";
        return "";
    }

    /**Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
     * B.length >= 3
     * There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
     * (Note that B could be any subarray of A, including the entire array A.)
     * Given an array A of integers, return the length of the longest mountain.
     * Return 0 if there is no mountain.
     * Example 1:
     * Input: [2,1,4,7,3,2,5]
     * Output: 5
     * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.*/
    static int longestMountain(int[] A) {
        int n =A.length;
        if(n <3) return 0;
        int start =0,end =0;
        int max = 0;
        int i=1;
        while (i < n){
            if (A[i] > A[i-1]){
                start = i-1;
                int j =i+1;
                while (j<n && A[j] >A[j-1])
                    j++;
                while (j<n && A[j] <A[j-1])
                    j++;
                end = j;
                max = Math.max(end-start, max);
            }else start++;

            i++;
        }

        return max;
    }

    static int findPairs(int[] nums, int k) {
        int len = nums.length;
        if (k < 0)
            return 0;
        int count =0;
        Set<Integer> map = new HashSet<>();
        for (int i = 0; i < len; i++) {
            map.add(nums[i]);
        }
        for (int i = 0; i < len; i++) {
            int complement = Math.abs(k + nums[i]);
            if (map.contains(complement)){
                count++;
                map.remove(complement);
            }
        }
        return count;
    }

    /**Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
     *  n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
     * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
     * Note: You may not slant the container and n is at least 2.
     * Input: [1,8,6,2,5,4,8,3,7]
     * Output: 49*/
    static  int maxArea(int[] height) {
        int max = 0;
        int n= height.length;
        int left =0, right = n-1;
        while(left < right){
            max = Math.max(max, Math.min(height[left], height[right] )* (right-left));//compare max with current area
            if(height[left] < height[right])//if height of left less than height of right, then move forward
                left++;
            else
                right--;//else move backward
        }
        return max;
    }


    static int maxProduct(int[] nums) {
        int len = nums.length;
        if(len == 1)
            return nums[0];
        int maxPro =0;

        Arrays.sort(nums);


        return maxPro;
    }

    static List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        if(len <4) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i <len-3 ; i++) {
            int left = i+1;
            while (left < len -2){
                int right = len-1,mid = left +1;
                while (mid < right){
                    int value = nums[i] + nums[left] + nums[mid] + nums[right];
                    if (value == target){
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[left]);
                        tmp.add(nums[mid]);
                        tmp.add(nums[right]);
                        ans.add(tmp);
                        mid++;
                        right--;
                    }else if (value > target)
                        right--;
                    else mid++;
                }
                left++;
            }

        }

        return ans;
    }

    /**Solution 2: using divide and conquer*/
    static public List<List<Integer>> fourSumI(int[] nums, int target) {
       int len = nums.length;
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0, len);
    }
    static ArrayList<List<Integer>> kSum(int[] nums, int target, int k, int index, int len) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if(index >= len) {
            return res;
        }
        if(k == 2) {
            int i = index, j = len - 1;
            while(i < j) {
                //find a pair
                if(target - nums[i] == nums[j]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(target-nums[i]);
                    res.add(temp);
                    //skip duplication
                    while(i<j && nums[i]==nums[i+1]) i++;
                    while(i<j && nums[j-1]==nums[j]) j--;
                    i++;
                    j--;
                    //move left bound
                } else if (target - nums[i] > nums[j]) {
                    i++;
                    //move right bound
                } else {
                    j--;
                }
            }
        } else{
            for (int i = index; i < len - k + 1; i++) {
                //use current number to reduce ksum into k-1sum
                ArrayList<List<Integer>> temp = kSum(nums, target - nums[i], k-1, i+1, len);
                if(temp != null){
                    //add previous results
                    for (List<Integer> t : temp) {
                        t.add(0, nums[i]);
                    }
                    res.addAll(temp);
                }
                while (i < len-1 && nums[i] == nums[i+1]) {
                    //skip duplicated numbers
                    i++;
                }
            }
        }
        return res;
    }

    /**Given an array of n positive integers and a positive integer s,
     * find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
     Example:
     Input: s = 7, nums = [2,3,1,2,4,3]
     Output: 2
     Explanation: the subarray [4,3] has the minimal length under the problem constraint.*/
    static int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if(len == 0)
            return 0;
        int minLen = 0;
        int sum=0;
        int start = 0;
        for(int i=0;i <len;i ++ ){
            sum += nums[i];
            if(sum >= s){
                if(minLen == 0 || i - start + 1 < minLen){
                    minLen = i-start+1;
                }
                i = start;
                start++;
                sum =0;
            }

        }
        return minLen;
    }

    static int removeDuplicatesII(int[] nums) {
        int len = nums.length;
        if (len <= 1)
            return len;

        int left = 0, right = left+1, tail=0;
        while (right < len){
            if (nums[left] == nums[right]) right++;
            else{
                if (right -left == 1){
                    nums[tail++] = nums[left];
                }

                else if (right-  left == 2){
                    nums[tail++] = nums[left];
                    nums[tail++] = nums[left];
                    left = right;
                }else {
                    nums[tail++] = nums[left];
                    nums[tail++] = nums[left];
                    left = right;
                }
            }

        }
        if (right -left == 1){
            nums[tail++] = nums[left];
        }

        else if (right-  left == 2){
            nums[tail++] = nums[left];
            nums[tail++] = nums[left];
        }else {
            nums[tail++] = nums[left];
            nums[tail++] = nums[left];
        }
        for (int i = 0; i < tail; i++) {
            System.out.print(nums[i] +" ");
        }
        System.out.println();
        return tail;

    }

    /**In a row of trees, the i-th tree produces fruit with type tree[i].
     * You start at any tree of your choice, then repeatedly perform the following steps:
     * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
     * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
     * Note that you do not have any choice after the initial choice of starting tree:
     * you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
     * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
     * What is the total amount of fruit you can collect with this procedure?
     * Example 1:
     * Input: [1,2,1]
     * Output: 3
     * Explanation: We can collect [1,2,1].*/
    static int totalFruit(int[] tree) {
        /**Solution using Slide window:*/
        int len = tree.length;
        int start =0;
        int count =0;
        int max = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i< len; i++){
            if(list.size() == 0 || !list.contains(tree[i])){
                count++;//count the number of different fruit types
                if(count == 3){//if has three types so then reset to initial
                    i = start;//i start at start's position
                    start++;//increase start by one
                    max = Math.max(max, list.size());//set max
                    list.clear();//clear list
                    count=1;//set count to 1 because current fruit is different
                }
            }
            list.add(tree[i]);//add fruit into list
        }
        return max = Math.max(max, list.size());//compare to get max to return the final result
    }
    public static void main(String...args){

       System.out.println(removeDuplicatesII(new int[]{1,1,1,2,2,3}));

     //   System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
