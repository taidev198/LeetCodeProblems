package com.company.Algorithms.Array;

import java.util.*;

public class ArrayInterview1 {


    static int threeSumClosest(int[] nums, int target) {
        int closestSumDiff = target ;
        if (nums.length <3) return  0;
        Arrays.sort(nums);
        int minDiff =-1;
        int n = nums.length; int i= n-1;
        while (i >=2){
            int left = 0, right = i-1;
            while (left< right){
                int tmp = nums[left] + nums[right] + nums[i];
                int diff = Math.abs(tmp - target);
                if (minDiff == -1){
                    minDiff = diff;
                    closestSumDiff = tmp;
                    right--;
                }
               else if (diff > minDiff)
                    left++;
                else {
                    if ( closestSumDiff >= target){
                        minDiff = diff;
                        closestSumDiff = tmp;
                    }
                    right--;
                }

            }
            i--;

        }
        return closestSumDiff;
    }

    /**Input:  [0,1,2,4,5,7]
     * Output: ["0->2","4->5","7"]
     * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.*/
    static List<String> summaryRanges(int[] nums) {
        boolean isRange = false;
        List<String> ans = new ArrayList<>();
        for(int i =0;i<nums.length;i++){
            int j=i+1;
            while(j<nums.length && nums[j] - nums[j-1] == 1)
                j++;
            if(j -i == 1){
                ans.add(Integer.toString(nums[i]));
            }else{
                ans.add(nums[i] +"->"+nums[j-1]);
            }
            i=j-1;

        }
        return ans;
    }


    /**Given an array of size n, find the majority element.
     * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
     * You may assume that the array is non-empty and the majority element always exist in the array.
     * Input: [2,2,1,1,1,2,2]
     * Output: 2
     */
    static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])){
                map.put(nums[i],1);
            }else {
               Integer tmp = map.get(nums[i]);
               map.replace(nums[i], tmp, tmp+1);
            }
        }

       List<Map.Entry<Integer, Integer>> sortedList = new ArrayList<>(sortByValue(map).entrySet());
        return sortedList.get(sortedList.size()-1).getKey();
    }

    static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        //  list.sort(Map.Entry.comparingByValue());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return  ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }});
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    static int triangleNumber(int[] nums) {
        int count =0;
        int n=nums.length;
        if(n <3) return 0;
        int left = 0,right =n-1;
        while(right - left >=2){
            int mid = left +1;
            right =n-1;
            while(mid<right){
                if((nums[mid] + nums[right] > nums[left])
                        &&(nums[mid] < nums[right] + nums[left])
                        && (nums[mid] + nums[left] > nums[right]))
                    count++;
                right--;

            }
            left++;
        }
        return count;
    }

    /**Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.
     * Given array nums = [-1, 0, 1, 2, -1, -4],
     * A solution set is:
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]*/
    static List<List<Integer>> threeSum(int[] A) {
        Set<List<Integer>> ans= new HashSet<>();
        int n = A.length;
        Arrays.sort(A);
        int i = n-1;
        while(i >=2){
            int left = 0,right = i-1;
            while(left < right){
                if(A[right] + A[left] >= -A[i]){
                    if(A[right] + A[left] == -A[i]){
                        List<Integer> res = new LinkedList<>();
                        res.add(A[left]);
                        res.add(A[right]);
                        res.add(A[i]);
                        ans.add(res);
                    } right--;
                }

                else left++;
            }
            i--;
        }
        return new LinkedList<>(ans);
    }

    static  int maximumSwap(int num) {
        char[] temp = Integer.toString(num).toCharArray();
        int n = temp.length;
        int max =-1;
        int maxIdx=-1;
        int i =0;
        while (i < n-1 && (temp[i] -'0') > (temp[i+1]-'0')){
            i++;
        }
        int left = i;
        if (left == n-1) return num;
        while (i< n){
            if (max == -1 || max <= temp[i]-'0' ){
                max = temp[i]-'0';
                maxIdx = i;
            }
            i++;
        }
        char tmp = temp[left];
        temp[left] = temp[maxIdx];
        temp[maxIdx] = tmp;
        return Integer.valueOf(new String(temp));
    }


    /**Given an integer array, find three numbers whose product is maximum and output the maximum product.
     * input:-4,-3,-2,-1,60
     * ouput;720*/
    static int maximumProduct(int[] nums) {
        int len = nums.length;
        if(len <3) return -1;
        Arrays.sort(nums);
        int max =-1;
        int left =0, right ;
        while(left < len -1){
            int mid = left+1;
            right =len-1;
            int product;
            while(mid <right){
               product = nums[left] *nums[mid]*nums[right];
                if (max == -1 || max < product){
                    max = product;
                }
                mid++;
            }
            left++;
        }
        return max;
    }

    static int minSubArrayLen(int s, int[] nums) {
        //prefixSum
        int len = nums.length;
        int[] preFixSum = new int[len+1];
        for(int i = 1;i<= len ;i++)
            preFixSum[i] = preFixSum[i-1] + nums[i-1];
        int minLen =-1;
        for(int i = 1;i<len; i++){
            for(int j =0; j<len-i;j +=i){
               // System.out.println(preFixSum[j+i+1] - preFixSum[j]);
                if(preFixSum[j+i+1] - preFixSum[j] == s) {
                    if (minLen == -1)
                        minLen = i;
                    else minLen = Math.min(minLen, i);
                }
            }
        }
        return minLen;
    }

    static int productExceptSelf(int[] nums, int m) {
        int n = nums.length;
        int[] preFixProduct = new int[n+1];
        preFixProduct[0] =1;
        for(int i=1;i <= n;i++)
            preFixProduct[i] = preFixProduct[i-1] * nums[i-1];
        int ans = 0;
        for(int i=0;i< n; i++){
            System.out.println(preFixProduct[i+1] / preFixProduct[i]);
            nums[i] = (preFixProduct[i+1] / preFixProduct[i]) %m;
            ans += nums[i];
        }
        return ans %m;
    }


    /**A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
     * Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
     * matrix = [
     * [1,2,3,4],
     * [5,1,2,3],
     * [9,5,1,2]
     * ]
     * Output: True
     * Explanation:
     * In the above grid, the diagonals are:
     * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
     * In each diagonal all elements are the same, so the answer is True.
     */
    static boolean isToeplitzMatrix(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;
        int i=0;

        int cur ;
        while (i < row){
            int r = i, c = 0;
            int pre = -1;
            while (r < row&& c < col){
                if (pre == -1){
                    cur = matrix[r][c];
                    pre = cur;
                }else {
                    if (pre != matrix[r][c])
                        return false;
                }
                r++;c++;
            }
            i++;
        }

        i = 1;
        while (i < col){
            int r = 0, c = i;
            int pre = -1;
            while (r < row && c < col){
                if (pre == -1)
                    pre = matrix[r][c];
                else if (pre != matrix[r][c])
                    return false;
                r++;c++;
            }
            i++;
        }
        return true;
    }

    static void solve(int[][] M){
        int row = M.length;
        int col = M[0].length;
        if(row == 1 && col == 1) return ;
        int[][] ans = new int[row][col];
        if (row > 1){
            for (int r = 0; r < row; r++) {
                if (col > 1){
                    for (int c = 0; c < col; c++) {
                        int count = countNeighbor(row, col, r, c);
                        if (count == 8) count++;
                        int cal = M[r][c];
                        if (r > 0 && c > 0 &&  r< row-1 && c < col-1){
                            cal += M[r][c+1] + M[r][c-1] + M[r+1][c+1] + M[r-1][c+1]
                                    +M[r-1][c] + M[r+1][c] +M[r-1][c-1] + M[r+1][c-1];
                        }else if (c ==0){
                            if (r == 0 ) cal += M[r][c+1] + M[r+1][c]+ M[r+1][c+1];
                            else if (r == row -1) cal += M[r-1][c] + M[r-1][c+1]+ M[r][c+1];
                            else cal += M[r-1][c] + M[r-1][c+1]+ M[r][c+1] +M[r+1][c ]+M[r+1][c+1];
                        }else if (c == col-1){
                            if (r == 0 ) cal += M[r][c-1] + M[r+1][c] + M[r+1][c-1];
                            else if (r== row-1) cal += M[r-1][c] + M[r][c-1] + M[r-1][c-1];
                            else  cal += M[r-1][c] + M[r][c-1] + M[r+1][c] +M[r-1][c-1]+ M[r+1][c-1];
                        }else {
                            if (r == 0)
                                cal += M[r][c-1] + M[r][c+1] + M[r+1][c-1] + M[r+1][c+1] + M[r+1][c];
                            else cal += M[r][c+1] +M[r][c-1] + M[r-1][c+1] + M[r-1][c-1] + M[r-1][c];
                        }
                        System.out.println("count :"+count +"  cal:" +cal + " row:" + r +" col :" +c);
                        ans[r][c] = (int) Math.floor(cal/ count);
                    }
                }else {
                    int count  ;
                    if (r == 0 || r == row -1) count = 2;
                    else count = 3;
                    int cal = M[r][0];
                    if (r ==0)
                        cal += M[r+1][0];
                    else if (r == row -1)
                        cal += M[r-1][0];
                    else {
                        cal += M[r+1][0];
                        cal += M[r-1][0];
                    }
                    ans[r][0] = (int) Math.floor(cal/ count);
                }

            }
        }else {

            for (int c = 0; c < col; c++) {
                int count  ;
                if (c == 0 || c == col -1) count = 2;
                else count = 3;
                int cal = M[0][c];
                if (c ==0)
                    cal += M[0][c+1];
                else if (c == col -1)
                    cal += M[0][c-1];
                else {
                    cal += M[0][c+1];
                    cal += M[0][c-1];
                }
                ans[0][c] = (int) Math.floor(cal/ count);
            }
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                System.out.print(ans[r][c] +" ");
            }
            System.out.println();
        }

    }

    static int calGrayNeighbor(int[][] M, int i, int j){
        if (i < 0 || j < 0 || i >= M.length || j >= M[0].length)
            return 0;
        if (M[i][j] == 0)
            return 0;
        int count = 1;
       return count+ calGrayNeighbor(M, i, j+1)+//right
        calGrayNeighbor(M, i, j-1)+//left
        calGrayNeighbor(M, i+1, j+1)+//right bottom
        calGrayNeighbor(M, i-1, j+1)+//right up
        calGrayNeighbor(M, i-1, j)+//up
        calGrayNeighbor(M, i+1, j)+//down
        calGrayNeighbor(M, i-1, j-1)+//left up
        calGrayNeighbor(M, i+1, j-1);//left bottom

    }

    static int countNeighbor(int row, int col, int i, int j){
        int total = 8;
        if (i == 0 || i == row -1)
            total -=2;
        if (j == 0 || j == col -1)
            total -= 2;

        return total;
    }

    public static void main(String...args){
        //System.out.println(threeSumClosest(new int[]{-1,2,1,-4}, 1));
       // System.out.println(triangleNumber(new int[]{2,2,3,4}));
           // System.out.println(majorityElement(new int[]{2,2,1,1,1,2,2}));
       // System.out.println(maximumProduct(new int[]{-4,-3,-2,-1,60}));
       // System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
      //  System.out.println(productExceptSelf(new int[]{1,2,3,4}, 12));

        solve(new int[][]{{2},{3}});

    }
}
