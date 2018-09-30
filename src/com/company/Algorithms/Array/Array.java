package com.company.Algorithms.Array;

import org.omg.PortableInterceptor.INACTIVE;

import java.math.BigInteger;
import java.util.*;

public class Array {

    static void rotate(int[] nums, int k) {
        int n= nums.length;
        k = k % n;
    }

    static void spiralOrder(int[][] matrix) {
       // if(matrix.length ==0) return new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        List<Integer> ans = new ArrayList<>();
        int start = 0;
        while (start < col/2 && start < row /2){
            if (col > 1 && start < row)
            for (int i = start; i < col-start; i++)
                ans.add(matrix[start][i]);
            if (row >1 && col>=1 )
            for (int i = start +1; i < row-start; i++)
                ans.add(matrix[i][col-1-start]);
            if (col >1 && row >1)
            for (int i = col-2-start; i >=start; i--)
                ans.add(matrix[row-1-start][i]);
            if (row >=col-2-start && col >1)
            for (int i =col-2-start; i >start; i--)
                ans.add(matrix[i][start]);
            start++;
        }
         for (int i = 0; i < ans.size(); i++) {
             System.out.println(ans.get(i));
         }
       // return  ans;
    }

    static int arrayConversion(int[] inputArray) {
            int k= 1;
            int len = inputArray.length;
            while(len !=1){
                int newSize =0;
                if(k %2 ==0){
                    for(int i=0;i<len;i+=2){
                        inputArray[newSize++] = inputArray[i+1] *inputArray[i];
                    }
                }else{
                    for(int i=0;i<len;i+=2){
                        inputArray[newSize++] = inputArray[i+1] + inputArray[i];
                    }
                }
                len = newSize;
                k++;
            }
            return inputArray[0];
    }



    static boolean isPowerOfThree(int n) {

        double value = Math.log10(n) / Math.log10(3);
        System.out.println(value);
        return Math.ceil(value) - value == 0.0;
    }

    /**[0,1,0,3,12] =>> output:[1,3,12,0,0]*/
    static void moveZeroes(int[] nums) {
        int k=0;
        for(int i =0; i< nums.length;i++){
            boolean ok = false;
            if(nums[i] !=0){
                continue;
            }
            int j=i;
            while(j<nums.length){
                if(nums[j] == 0)j++;
                else{
                    ok = true;
                    break;
                }
            }
            if(ok){
                nums[i] += nums[j];
                nums[j] = nums[i] - nums[j];
                nums[i] -= nums[j];
            }else break;
        }
    }

    static int superDigit(String n, int k) {
        String temp =n;
        for(int i=0;i<k-1;i++)
            n += temp;
        BigInteger big = new BigInteger(n);
        long value = calSumOfDigits(big.longValue());
        while(Long.toString(value).length() !=1)
            value = calSumOfDigits(value);
        return Math.toIntExact(value);
    }
    static long calSumOfDigits(long n){
        if(n<10) return n;
        return n%10 +calSumOfDigits(n/10) ;
    }

    /**Returns a list of prime factors for a natural number.*/
    static void trialDivision(int n){
        int[] ans = new int[n];
        int j = 0;
        int factor = 2;//The first possible factor.
        while (n>1){//while n still has remaining factors.
            if (n%factor ==0){
                ans[j++] = factor;
                n /= factor;
            }else factor++;
        }
        for (int i = 0; i < j; i++) {
            System.out.println(ans[i]);
        }
    }

    static void trialDivision1(int n){
        int[] ans = new int[n/2];
        int j=0;
        while (n %2==0){
            ans[j++] = 2;
            n/=2;
        }
        int factor =3;
        while (factor * factor <= n){
        if (n % factor ==0){
            ans[j++] = factor;
            n /=factor;
        }else n +=2;

        }
        if (n != 1)ans[j++]  = n;
        for (int i = 0; i < j; i++) {
            System.out.println(ans[i]);
        }
    }
    /**Using the simple 6k+-i*/
    static boolean isPrime(int n){
        if (n<=3) return true;
        else if (n % 2 ==0 || n % 3==0) return false;
        int i = 5;
        while (i*i <=n){
            if (n % i==0 ||n% (i +2) ==0) return false;
            i+=6;
        }
        return true;
    }

    /**11:2,3,5,7*/
    static void findAllPrimeSmallThanN(int n){
        List<Integer> list = new ArrayList<>();
        boolean[] isPrime = new boolean[n+3];
        Arrays.fill(isPrime, true);
        for (int i = 2; i < n; i++) {
            if (isPrime[i]){
                int j =i+i;
                while (j< n){
                    isPrime[j] = false;
                    j+=i;
                }
                list.add(i);
            }
        }
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }

    static int primesSum2(int n) {
        int res =0;
        boolean[] isNotPrime = new boolean[n+1];
        for (int i = 2; i <= n; i++) {
            if (!isNotPrime[i]){
                int j =i+i;
                while (j<= n){
                    isNotPrime[j] = true;
                    j+=i;
                }
                res +=i;
                res %= 1000000007;
            }
        }
        return res;
    }

    static void sortByLength(String[] inputArray) {
        for(int i =0;i<inputArray.length-1;i++){
            int j=i;
           int minIdx = i;
            while(j<inputArray.length){
                if(inputArray[minIdx].length() <= inputArray[j].length())
                    j++;
                else minIdx = j;
            }

            String tmp = inputArray[i];
            inputArray[i] = inputArray[minIdx];
            inputArray[minIdx] = tmp;

        }
        for (int i = 0; i < inputArray.length; i++) {
            System.out.println(inputArray[i]);
        }
    }

    static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=0;
        if(nums2.length ==0 ) return;
        while(i<m){
            int minValue = nums1[i];
            int minIdx =i;
            for(int j =0;j<n;j++){
                if( nums2[j] < minValue){
                    minIdx = j;
                    minValue = nums2[j];
                }
            }
            if(minIdx < n && nums2[minIdx] != nums1[i]){
                nums1[i] += nums2[minIdx];
                nums2[minIdx] = nums1[i] - minValue;
                nums1[i] -= nums2[minIdx];
            }
            i++;
        }
        for (int j = 0; j < n; j++) {
            nums1[j+i] = nums2[j];
        }

        for (int j = 0; j < m+n; j++) {
            System.out.println(nums1[j]);
        }

    }

    static void arrayPreviousLess(int[] items) {
        for(int i = 0;i< items.length;i++){
            boolean hasSmall= false;
            for(int j = i-1;j>=0;j--){
                if(items[j] < items[i]){
                    items[i] = items[j];
                    hasSmall = true;
                    break;
                }
            }
            if(!hasSmall) items[i] = -1;
        }
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }

    }

    static boolean pairOfShoes(int[][] shoes) {
        int count =0;
            for(int i=0;i<shoes.length;i++){
                if(shoes[i][0] == 0)
                    count++;
            }
            return count == shoes.length/2;
    }

    static int singleNumber(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for(int i =0;i<nums.length;i++){
            if (!hashSet.add(nums[i]))
                hashSet.remove(nums[i]);
        }
        return Integer.valueOf(hashSet.toString().substring(1,2));
    }
    static int firstUniqChar(String s) {
        char[] tmp = s.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < tmp.length; i++) {
            if (!hashMap.containsKey(tmp[i])){
                hashMap.put(tmp[i], i);
            }else hashMap.remove(tmp[i]);
        }
    System.out.println(hashMap.toString());
      //  return hashMap.isEmpty()? -1: Integer.valueOf(hashMap.toString().substring(1,2));
        return -1;
    }

    /**https://leetcode.com/problems/kth-largest-element-in-an-array/description/
     * Find the kth largest element in an unsorted array.
     *  Note that it is the kth largest element in the sorted order, not the kth distinct element
     * Input: [3,2,1,5,6,4] and k = 2
     Output: 5*/
    static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length -k];
    }

    /**Input: [1,1,0,1,1,1]
     Output: 3
     Explanation: The first two digits or the last three digits are consecutive 1s.
     The maximum number of consecutive 1s is 3.*/

    static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] == 1){
                int j=i;
                while(j <nums.length && nums[j]==1)
                    j++;
                int dis = j-i;
                if(dis > max)
                    max = dis;
                i=j;
            }
        }
        return max;
    }

    /**Input: 22
     Output: 2
     Explanation:
     22 in binary is 0b10110.
     In the binary representation of 22, there are three ones, and two consecutive pairs of 1's.
     The first consecutive pair of 1's have distance 2.
     The second consecutive pair of 1's have distance 1.
     The answer is the largest of these two distances, which is 2.*/
    static int binaryGap(int N) {

        if(N == 1 || N ==0) return 0;
        String tmp = binaryNumber(N);
        System.out.println(tmp);
        int left =0, right ;
        int max = 0;
        while (left < tmp.length()){
            right =left+1;
            if (tmp.charAt(left) == '0')
                left++;
            else {
                while (right < tmp.length() && tmp.charAt(right) == '0')
                    right++;
                if (right == tmp.length() &&   tmp.charAt(right-1) =='0')
                    break;
                if(right-left>max )
                    max = right-left;
                left = right;
            }
        }
        return max;
    }


    /**Input: "abbxxxxzzy"
     Output: [[3,6]]
     Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.*/
    static  List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> ans = new ArrayList<>();
        int left =0,right;
        int n=S.length();
        while(left<n){
            right = left;
            List<Integer> temp = new ArrayList<>();
            while(right<n && S.charAt(right) == S.charAt(left))
                right++;
            if(right-left <3 && right == n) break;
            if(right-left>=3){
                temp.add(left);
                temp.add(right-1);
                ans.add(temp);
            }
            left=right;
        }
        return ans;
    }

    static String binaryNumber(int n){
        StringBuilder res = new StringBuilder();
        while (n != 0){
            res.append(n%2);
            n/=2;
        }
        return res.reverse().toString();
    }


    /**Given nums = [0,0,1,1,1,2,2,3,3,4],
     Your function should return length = 5,
     with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.*/
    static int removeDuplicates(int[] nums) {
        int left =0, right=left;
        int n= nums.length;
        while (right < n){
            if (nums[left] == nums[right]) right++;
            else nums[++left] = nums[right++];
        }
        return left+1;
    }

    static  int removeMoreDuplicates(int[] nums) {
        int left =0, right=left;
        int n= nums.length;
        while (right < n ){
            if (nums[left] == nums[right]) right++;
            else {
                if(right -left >2 ){
                    nums[left+2] = nums[right];
                    left +=2;
                }
                else if (right-left == 1)
                    left ++;
                else{
                    left+=2;
                }
                right++;
            }
        }
        return left+1;
    }

    /**find out whether there are two distinct indices i and j in the array such that
     * nums[i] = nums[j] and the absolute difference between i and j is at most k.
     * Input: nums = [1,2,3,1], k = 3
     Output: true*/
    static boolean containsNearbyDuplicate(int[] nums, int k) {
        int left =0,right=0;
        int n = nums.length;
        while(left <n){
            right =left+1;
            while(right <n ){
                if( nums[right] == nums[left]){
                    if(right -left <= k) return true;
                }
                right++;
            }
            left++;

        }
        return false;
    }

    /**https://leetcode.com/problems/contains-duplicate-iii/description/*/
    static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int left =0,right=0;
        int n = nums.length;
        while(left <n){
            right =left+1;
            int value = Math.abs(nums[right] - nums[left]);
            System.out.println(value<=Integer.MIN_VALUE );
            while(right <n ){
                if(Math.abs(nums[right] - nums[left]) > Integer.MIN_VALUE &&
                        Math.abs(nums[right] - nums[left]) <= Integer.MAX_VALUE &&
                        Math.abs(nums[right] - nums[left]) <=t){
                    if(right -left <= k) return true;
                }
                right++;
            }
            left++;

        }
        return false;
    }

    static int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1,-1};
        int left=0,right =nums.length-1;
        while(right - left >=2){
            int mid = left +(right-left)/2;
            if(nums[mid] == target){
                ans[0] = mid;
                int j=mid+1;
                while(j<nums.length  &&nums[j] == target)
                    j++;
                ans[1] = j-1;
                break;
            }else if(nums[mid] > target)
                right =mid-1;
            else left = mid+1;
        }
        return ans;
    }

    static int majorityElement(int[] nums) {

        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        for (int i = 0; i < nums.length; i++) {
            if (!hashtable.containsValue(nums[i]))
                hashtable.put(nums[i], 1);
            else {
                HashSet<Integer> hashSet = new HashSet<>();
                Map<Integer, Integer> map = new Hashtable<>();
                }
        }
        return 0;
    }

    static boolean checkDialog(int[][] arr){
        int j = 0, n = arr.length, m = arr[0].length;
        HashSet<Integer> hashSet = new HashSet<>();
        while (j < n){
            for (int i = j; i < m-j; i++) {
                hashSet.add(arr[i][i]);
            }
            if (hashSet.size() == m - 1-j)
                return false;
            for (int i = n-1-j; i >0; i--) {
                hashSet.add(arr[i][i]);
            }
            if (hashSet.size() == n - 1-j)
                return false;
            j++;
        }
        return true;
    }

    static int[] fairCandySwap(int[] A, int[] B) {
        int sumA=0, sumB =0;
        for(int i=0;i<A.length;i++)
            sumA += A[i];

        for(int i=0;i<B.length;i++)
            sumB += B[i];
        int mid = (sumA + sumB)/2;

        for(int i=0;i<A.length;i++){
            for(int j =0; j< B.length; j++){
                if( sumA- A[i] + B[j] == mid)
                    return new int[]{A[i], B[j]};
            }
        }
        return new int[]{A[0], B[0]};
    }

    public static void main(String...args){
       // int[] ans = searchRange(new int[]{5,7,7,8,8,10}, 8);
       // System.out.println(ans[0] +" "+ans[1]);
       //System.out.println(containsNearbyAlmostDuplicate(new int[]{-1, 2147483647}, 1, 2147483647));
      //  System.out.println(checkDialog(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
        spiralOrder(new int[][]{{1,2,3,4,5,6,7,8,9,10},{11,12,13,14,15,16,17,18,19,20}});
    }
   static  class Shape{
        public Shape(){
            draw();
        }

        public void draw(){
            System.out.println("function of base class");
        }

   }
    static class Point extends Shape{
        private int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public void  draw(){
            System.out.println("function of derived class");
        }
    }
}
