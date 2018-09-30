package com.company.Algorithms.Array;

import java.util.*;

public class ArrayInterview {

    static int pivotIndex(int[] nums) {
        int[] p = prefixSum(nums);
        int n = nums.length;
        int i=0;
        while(i<n){
            int leftSum = p[i] - p[0];
            int rightSum = p[n] - p[i+1];
            if(leftSum == rightSum) return i;
            i++;
        }
        return -1;
    }
    static int[] prefixSum(int[] arr){
        int n= arr.length;
        int[] p = new int[n+1];
        p[0] =arr[0];
        for(int i=1;i <= n; i++)
        p[i] = p[i-1] +arr[i-1];

        return p;
    }


    /**Input: nums = [3, 6, 1, 0]
     *Output: 1
     *Explanation: 6 is the largest integer, and for every other number in the array x,
     *6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
     *Input: nums = [1, 2, 3, 4]
     *Output: -1
     *Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.*/
    static int dominantIndex(int[] nums) {
        if (nums.length == 1) return 0;
        int maxIdx = -1;
        int max = nums[0];
        for(int i = 0;i<nums.length;i++){
            boolean ok = true;
            if(nums[i] == 0) continue;
            for(int j=0;j<nums.length;j++){
                if( nums[j] ==0)
                    continue;
                if(  j != i &&  (nums[i] / nums[j]) < 2 ){
                    ok = false;
                }
            }if((ok && nums[i] >= max )){
                max = nums[i];
                maxIdx =i;
            }
        } return maxIdx;
    }

    /**The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2.
     * If it does not exist, output -1 for this number.
     * Input: nums1 = [2,4], nums2 = [1,2,3,4].
     * Output: [3,-1]
     * Explanation:
     * For number 2 in the first array, the next greater number for it in the second array is 3.
     * For number 4 in the first array, there is no next greater number for it in the second array, so output -1.*/
    static  int[] nextGreaterElement(int[] nums1, int[] nums2) {
        for(int i=0;i<nums1.length;i++){
            int greaterNumber = -1;
            for(int j=0;j<nums2.length;j++){
                if(nums2[j] == nums1[i]){
                    while(j<nums2.length){
                        if(nums2[j] > nums1[i]){
                            greaterNumber = nums2[j];
                            break;
                        }
                        j++;
                    }
                }
            }

            nums1[i] = greaterNumber;
        }
        return nums1;
    }

    /**An array is monotonic if it is either monotone increasing or monotone decreasing.
     * An array A is monotone increasing if for all i <= j, A[i] <= A[j].
     * An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
     * Input: [1,2,2,3]
     * Output: true
     * Input: [1,3,2]
     * Output: false*/
    static boolean isMonotonic(int[] A) {
        int n= A.length;
        if(n == 1) return true;
        int diff =  A[1] -A[0];
        for(int i=2;i< n;i++){
            int tmp =  A[i] -A[i-1];
            if(diff * tmp < 0) return false;
            if(diff ==0 && tmp != 0)
                diff =tmp;
        }
        return true;
    }

    static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            int tmp = nums[nums[i]-1];
            nums[nums[i]-1] = nums[i];
            nums[i] =tmp;
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i] ==0)
                res.add(i+1);
        }
        return res;
    }


    /**A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that
     *  each row, column, and both diagonals all have the same sum.
     * Input: [[4,3,8,4],
     [9,5,1,9],
     [2,7,6,2]]
     Output: 1
     Explanation:
     The following subgrid is a 3 x 3 magic square:
     438
     951
     276
     */
    static int numMagicSquaresInside(int[][] grid) {
        int count =0;
        int row = grid.length;
        int col = grid[0].length;
        for(int i=0;i<=row-3;i++){
            for(int j=0; j<= col-3;j++){
                int pre = 0;
                boolean isOk = true;
                for(int k = i;k<i+3;k++){
                    int sum =0;
                    for(int l = j; l<j+3;l++){
                        if (grid[k][l] >=10){
                            isOk = false;
                            break;
                        }
                       else sum += grid[k][l];
                    }
                    if(pre ==0)
                        pre = sum;
                    else if(pre != sum){
                        isOk = false;
                        break;
                    }
                }
                for(int k = j;k<j+3;k++){
                    int sum =0;
                    for(int l = i; l<i+3;l++)
                        sum += grid[l][k];
                    if(pre ==0)
                        pre = sum;
                    else if(pre != sum){
                        isOk = false;
                        break;
                    }
                }
               int sum =0;
                for(int k = i, l = j;k<i+3&&l<l+3;k++,l++)
                sum += grid[k][l];
                if(pre != sum) continue;
                sum =0;
                for(int k = i+2,l =j;k>=i&&l<j+3;k--,l++)
                    sum += grid[k][l];
                if(pre != sum) continue;
                if (isOk)
                count ++;
            }
        }
        return count;
    }


    static int questEfficiencyItem(int[] h, int[] points, int timeForQuests) {
        int max = 0;
        int start = 0;
        int sum =0;
        int timeTemp = timeForQuests;
        for(int end = 0;end <points.length;end++){
            sum += points[end];
            timeTemp -= h[end];
            if(timeTemp < 0){
                timeTemp = timeForQuests;
                sum =0;
                end = start;
                start++;
            }
            if(sum > max){
                max = sum;
            }
        }

        return max;
    }


    /**Input: 5
     * Output:
     * [
     * [1],
     * [1,1],
     * [1,2,1],
     * [1,3,3,1],
     * [1,4,6,4,1]
     * ]*/
    static  List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> preLists = new ArrayList<>();
        for(int i = 1;i<= numRows; i++){
            List<Integer> rowLists = new ArrayList<>();
            rowLists.add(1);
            if(i>1){
                if (i>2){
                    for(int j =1;j<preLists.size();j++){
                        rowLists.add(preLists.get(j) + preLists.get(j-1));
                    }
                }
                rowLists.add(1);
            }
            preLists = rowLists;
            ans.add(rowLists);
        }
        return ans;
    }

    /**Input: 3
     Output: [1,3,3,1]*/

    static List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        if (rowIndex == 0) return ans;
        ans.add(1);
        if (rowIndex == 1) return ans;
        int[] temp = new int[rowIndex+1];
        temp[0] = 1;
        temp[1] = 1;
        for (int i = 2; i <= rowIndex; i++) {
            for (int j = 1; j < ans.size(); j++) {
                ans.set(j, temp[j-1] + temp[j]);
            }
            for (int j = 0; j < ans.size(); j++) {
                temp[j] = ans.get(j);
            }
            ans.add(1);
            temp[ans.size()-1] = 1;
        }
        return ans;
    }

    static  int[][] flipAndInvertImage(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for(int j=0;j< A[i].length/2;j++){
                A[i][j] += A[i][ A[i].length - j-1];
                A[i][ A[i].length - j-1]  = A[i][j] -A[i][ A[i].length - j-1];
                A[i][j] -= A[i][ A[i].length - j-1];
            }

            for (int j = 0; j < A[i].length; j++) {
                if(A[i][j] == 0)
                    A[i][j] = 1;
                else A[i][j] = 0;
            }
        }
        return A;
    }


    static int maxProduct(int[] nums) {
        int n= nums.length;
        if(n==1) return nums[0];
        int max = nums[0] ;
        for (int end = 1; end <= nums.length; end++) {
            int product=1;
            for (int i = 0; i < end; i++)
                product *= nums[i];
            int window_product = product;
            for (int i = end; i < n; i++) {
                if (nums[i-end] !=0){
                    window_product = (window_product*nums[i]) / nums[i-end];

                }else window_product *= nums[i];
                product = Math.max(product, window_product);
            }
            max = Math.max(product, max);
        }
        return max;
    }

    static  void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if (k == 0) return;
        int d = 0;
        while (d< k) {
            int temp = nums[n-k+d];
            for (int i = n-k+d; i >d ; i--) {
                nums[i] = nums[i-1];
            }
            nums[d] = temp;
            d++;
        }
        for (int i = 0; i < n; i++) {
            System.out.println(nums[i]);
        }
    }

    static int removeDuplicates(int[] nums) {
        int left =0, right=left;
        int n= nums.length;
        boolean hasMoreTwo = false;
        while (right < n ){
            if (nums[left] == nums[right]){
                if (!hasMoreTwo)
                right++;
                else  nums[++left] = nums[right++];
            }
            else {
                //more two elements
                if(right -left >2){
                    if (hasMoreTwo){
                       // left++;
                        nums[++left] = nums[right++];
                        //hasMoreTwo = false;
                    }else {
                        nums[left+2] = nums[right++];
                        left += 2;
                        hasMoreTwo = true;
                    }
                    //right
                }
                //has one element
                else if (right-left == 1){
                    left ++;
                }
                //has two elements
                else left+=2;
            }
        }
        for (int i = 0; i <= left; i++) {
            System.out.println(nums[i]);
        }
        return left+1;

    }

    /**Input:
    * nums =
    * [[1,2],
    * [3,4]]
    * r = 1, c = 4
    * Output:
    * [[1,2,3,4]]
    * nums =
    * [[1,2],
    * [3,4]]
    * r = 2, c = 4
    * Output:
    * [[1,2],
    * [3,4]]
    * Explanation:
    * There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
     */
    static  int[][] matrixReshape(int[][] nums, int r, int c) {
        int row = nums.length;
        int col = nums[0].length;
        if(row * col < r * c) return nums;
        row =0; col =0;
        int[][] ans = new int[r][c];
        for(int i= 0;i< r;i++){
            for(int j= 0;j< c;j++){
                ans[i][j] = nums[row][col++];
                if(col == nums[0].length){
                    col =0;
                    row++;
                }
            }
        }
        return ans;
    }

    static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int numOs = 0;
        int maxNumOs = 0;
        for(int i =0;i< flowerbed.length; i++){
            if(flowerbed[i] ==0){
                int j = i+1;
                numOs++;
                while(j <flowerbed.length && flowerbed[j] == 0){
                    numOs++;
                    j++;
                }

                if(numOs > maxNumOs)
                    maxNumOs = numOs;
                i=j-1;
            }
        }
        return maxNumOs > 2*n;

    }

    static double findMaxAverage(int[] nums, int k) {
        if(nums.length == 1) return nums[0]/k;
        int n = nums.length;
        double sum;
        double maxAverageSubArr = -1;
        int[] prefixSum = new int[nums.length+1];
        for(int i=1; i<= n;i++)
            prefixSum[i] = prefixSum[i-1]+nums[i-1];
        for(int end =0;end <= nums.length-k; end++){
            sum = prefixSum[end +k] - prefixSum[end] ;
            if(sum > maxAverageSubArr|| maxAverageSubArr == -1)
                maxAverageSubArr = sum;
        }
        return maxAverageSubArr/k;
    }

    static  void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=0;
        if(nums2.length ==0 ) return;
        while(i<m){
            int minValue = nums1[i];
            int minIdx =-1;
            for(int j =0;j<n;j++){
                if( nums2[j] < minValue||minIdx == -1 ){
                    minIdx = j;
                    minValue = nums2[j];
                }
            }
            if( nums1[i] > nums2[minIdx]){
                nums1[i] += nums2[minIdx];
                nums2[minIdx] = nums1[i] - nums2[minIdx];
                nums1[i] -= nums2[minIdx];
            }
            i++;
        }
        if(m>=n){
            for (int j = 0; j < n; j++) {
                nums1[j+i] = nums2[j];
            }
        }else{


            for(int j = i;j<n+m; j++){
                int minValue = nums1[i];
                int minIdx = -1;
                for(int k= 0;k <n; k++){
                    if (nums2[k] != -1){
                        if((nums2[k] < minValue && nums2[k] >= nums1[j])||minIdx == -1 ){
                            minIdx = k;
                            minValue = nums2[k];
                        }
                    }
                }
                nums1[j] = nums2[minIdx];
                nums2[minIdx] = -1;

            }
        }
        for (int j = 0; j < m+n; j++) {
            System.out.println(nums1[j]);
        }

    }

    static int maxProfit(int[] prices) {
        int bestProfit =0;
        int n = prices.length;
        for (int end = 0; end < n; end++) {
         int right = n-1;
         while (end < right){
             bestProfit = Math.max(prices[right] - prices[end], bestProfit);
             right--;
         }
        }
        return bestProfit;
    }

    static List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> ans=  new ArrayList<>();
        int n = nums.length;
        if( n== 1){
            ans.add(nums[0]);
            return ans;
        }

        for(int i=0; i< nums.length; i++){
            if (!map.containsKey(nums[i]))
                map.put(nums[i], 1);
            else {
                Integer tmp = map.get(nums[i]);
                if (tmp + 1 > n/3 && !ans.contains(nums[i]))
                    ans.add(nums[i]);
                map.replace(nums[i], tmp, tmp+1);
            }
        }
        Collections.sort(ans);
        return  ans;
    }

    static int findLengthOfLCIS(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        int maxLen= 1;
        int left =0,right;
        int start ;
        while(left < len){
            right = left+1;
            start = left;
            while(right < len && nums[right] > nums[start]){
                start = right;
                right++;
            }
            maxLen  = Math.max(maxLen, right - left);
            left = right;
        }
        return maxLen;
    }

    public static void main(String...args){
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
       // System.out.println(questEfficiencyItem(new int[]{1,4,2}, new int[]{2,3,2}, 4));
       // rotate(new int[]{1,2,3,4,5,6,7}, 1);
        //merge(new int[]{0,1,2,8,0,0},4, new int[]{0,2},2);
        System.out.println(maxProduct(new int[]{0,2}));
    }
}
