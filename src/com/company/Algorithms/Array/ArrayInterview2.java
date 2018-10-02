package com.company.Algorithms.Array;

import java.util.*;

public class ArrayInterview2 {

    /**Given an array A of non-negative integers, return an array consisting of all the even elements of A,
     * followed by all the odd elements of A.
     You may return any answer array that satisfies this condition.*/
    static int[] sortArrayByParity(int[] A) {
        int len = A.length;
        if(len <=1) return A;
        for(int i=0;i< len;i++){
            if(A[i] % 2== 0)
                continue;
            for(int j =i+1;j<len;j++){
                if(A[j] % 2==1)
                    continue;
                A[i] += A[j];
                A[j] = A[i] - A[j];
                A[i] -= A[j];
                break;
            }
        }
        return A;
    }

    static int sumSubarrayMins(int[] A) {
        long startTime = System.currentTimeMillis();
        int ans =0;
        int len =A.length;
        if (len ==1) return A[0];
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            ans = ans % 1000000007 + A[i] %1000000007;
        }
        for (int i = 2; i <=len; i++) {//length of subarray
             List<Integer> list = new ArrayList<>();
             int left = 0;
            for ( ; left < i; left++) {
               list.add(A[left]);
            }
            ans = ans % 1000000007 + Collections.min(list) % 1000000007;
            for (int j = i; j < len; j++) {
                list.remove(0);
                list.add(A[j]);
                ans = (ans % 1000000007) + (Collections.min(list) %1000000007);
            }
        }
        System.out.println("Time:" + (System.currentTimeMillis() - startTime));
        return ans;
    }

    static int sumSubarrayMins1(int[] A) {
        long startTime = System.currentTimeMillis();
        int ans = 0;
        int len = A.length;
        if (len == 1) return A[0];
        List<Integer> listSubArray = new ArrayList<>();
        List<Integer> cloneList =new ArrayList<>();
        List<Integer> cloneList1 =new ArrayList<>();
        for (int i = 0; i < len; i++) {
            listSubArray.add(A[i]);
            cloneList.add(A[i]);
            cloneList1.add(A[i]);
            ans = ans % 1000000007 + A[i] % 1000000007;
        }

        ans = (ans % 1000000007) + (Collections.min(listSubArray) %1000000007);
        for (int i = 0; i < len-2; i++) {//length of subarray
            listSubArray.remove(0);
            ans = (ans % 1000000007) + (Collections.min(listSubArray) %1000000007);
        }

        for (int i = 0; i < len/2; i++) {
            cloneList1.remove(0);
            cloneList1.remove(cloneList1.size()-1);
            if (cloneList1.size() >1)
            ans = (ans % 1000000007) + (Collections.min(cloneList1) %1000000007);
            else break;
        }

        for (int i = 0; i < len-2; i++) {//length of subarray
            cloneList.remove(cloneList.size() -1);
            ans = (ans % 1000000007) + (Collections.min(cloneList) %1000000007);
        }
        System.out.println("Time:" + (System.currentTimeMillis() - startTime));
        return ans;
    }

    /**Input: [1,0,0,0,1,0,1]
     Output: 2
     Explanation:
     If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
     If Alex sits in any other open seat, the closest person has distance 1.
     Thus, the maximum distance to the closest person is 2.*/
    static  int maxDistToClosest(int[] seats) {
        int maxDistance = 1;
        int len = seats.length;
        if (len == 1)
            return 0;
        for (int i = 0; i < len; i++) {
            while (i < len && seats[i] == 1)
                i++;
            int j = i;
            while (j< len && seats[j] == 0)
                j++;
            if (j - i != 1){
                if (i == 0 )
                    maxDistance = Math.max(maxDistance, (j-i) );
                else if (j == len)
                    maxDistance = Math.max(maxDistance, (j-i) );
                else maxDistance = Math.max(maxDistance, (j-i +1)/2 );
            }
            i=j-1;
        }
        return maxDistance;
    }
    static boolean checkPossibility(int[] nums) {
        int n = nums.length;
        if(n == 1 || n == 2) return true;
        int i =0;
        int len = nums.length;
        while (i < len -1 && nums[i] <= nums[i+1])
            i++;
        int pre;
        if (i == 0){
            pre = 0;
            i++;
        }else {
            pre = nums[i-1];
            i+=2;
        }
        while (i < len -1 && nums[i] <= nums[i+1] && nums[i] >= pre)
            i++;
        return i == len-1 || i == len;
    }

    static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int len = coins.length;
        int count =0;
        for(int i= len-1; i>=0; i--){
            if(amount /coins[i] >0){
                count += amount / coins[i];
                amount = amount % coins[i];
                if(count == 0)
                    break;
            }
        }
        if(amount > 0)
            return -1;
        return count;
    }

    static boolean increasingTriplet(int[] nums) {

        int len = nums.length;
        if(len <3) return false;
        for(int i =0; i< len -2;i++){
            int j = i+1;
            while(j < len && nums[j] <= nums[i])
                j++;
            if(j == len|| j == len -1)
                continue;
            int k = j+1;
            while(k < len && nums[k] <= nums[j] && nums[k] <=nums[i])
                k++;
            if(k == len)
                continue;
           else if ( k < len && !(nums[k] > nums[j] && nums[k] >nums[i]) )
                continue;
            return true;
        }
        return false;
    }

    /**EX:5,3,5,3
     * :3,1 len 2
     * 4,4 len 1*/
    static int zigzag(int[] a) {
        int len = a.length;
        if(len == 2 ){
            if( a[0] == a[1]) return 1;
            return 2;
        }
        int lenSequence = 1;
        for(int i=0;i< len-2; i++){
            boolean isGreater = true;
            if(a[i] >= a[i+1])
                isGreater = false;
            int j = i+1;
            while(j <len -1 ){
                isGreater = !isGreater;
                if(isGreater && a[j] < a[j+1] && a[j] <a[j-1])
                    j++;
                else if(!isGreater && a[j] > a[j+1] && a[j] >a[j-1])
                    j++;
                else {
                    break;
                }
            }
            if( j - i +1 > lenSequence){
                lenSequence = j - i+1;
            }
        }
        return lenSequence;

    }

    /**A periodic sequence s is defined as follows:
     * s[0], a, b and m are all given positive integers;
     * s[i] for i > 0 is equal to (a * s[i - 1] + b) mod m.
     * Find the period of s, i.e. the smallest integer T such that for each i > k (for some integer k): s[i] = s[i + T].
     * Example
     * For s0 = 11, a = 2, b = 6, and m = 12, the output should be
     * periodicSequence(s0, a, b, m) = 2.
     * The sequence would look like this: 11, 4, 2, 10, 2, 10, 2, 10, 2, 10....*/
    static int periodicSequence(int s0, int a, int b, int m) {

        List<Integer> list = new ArrayList<>();
        list.add(s0);
        int index =-1;
        for(int i=1;index == -1 ;i++){
            int value = (list.get(i-1) *a +b) %m;
            if(list.indexOf(value) != -1 || index != list.indexOf(value))
                index = list.size() - list.indexOf(value);
            list.add(value);
        }
        return index;
    }


    public static void main(String...args){

       // ExamRoom examRoom = new ExamRoom(10);
//        System.out.println(examRoom.seat());
////        System.out.println(examRoom.seat());
////        System.out.println(examRoom.seat());
////        System.out.println(examRoom.seat());
////        examRoom.leave(4);
////        System.out.println(examRoom.seat());
      //  System.out.println(maxDistToClosest(new int[]{0,0,0,0,0,0,0,0,0,1}));

        System.out.println(periodicSequence(11,2,6,12));

   }

    static class MyCalendar {
        TreeMap<Integer, Integer> calendar = new TreeMap<>();

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            if (calendar.isEmpty()){
                calendar.put(end, start);
                return true;
            }
            List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(calendar.entrySet());
            int left =0;
            int right = entryList.size()-1;
            while (left <= right){
                int mid = left + (right - left) /2;
                Map.Entry<Integer, Integer> tmp = entryList.get(mid);
                if (tmp.getKey() == end || tmp.getValue() == start
                        || (tmp.getValue() < start && tmp.getKey() >start) ||
                        (tmp.getValue() < end && tmp.getKey() >end)
                        || (tmp.getKey() > end && tmp.getValue() > start))
                    return false;
                else if (tmp.getKey() > end)
                    right = mid -1;
                else left = mid+1;
            }
            calendar.put(end, start);
            return true;
        }
    }


    static class ExamRoom {
       int[] roomSeats;
       boolean isSat = false;
       public ExamRoom(int N) {
           roomSeats = new int[N];
       }

       public int seat() {
           if(!isSat){
               isSat = true;
               roomSeats[0]= 1;
               return 0;
           }

           int maxDistance = maxDistToClosest(roomSeats);
           roomSeats[maxDistance]= 1;
           return maxDistance;
       }

       public int maxDistToClosest(int[] seats) {
           int maxDistance = 1;
           int len = seats.length;
           if (len == 1)
               return 0;
           for (int i = 0; i < len; i++) {
               while (i < len && seats[i] == 1)
                   i++;
               int j = i;
               while (j< len && seats[j] == 0)
                   j++;
               if (j - i != 1){
                   if (i == 0 )
                       maxDistance = Math.max(maxDistance, (j-i) );
                   else if (j == len)
                       maxDistance = Math.max(maxDistance, (j-i) );
                   else maxDistance = Math.max(maxDistance, (j-i +1)/2 );
               }
               i=j-1;
           }
           return maxDistance;
       }

       public void leave(int p) {
           roomSeats[p] = 0;
       }
   }
}
