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


    public static void main(String...args){

        ExamRoom examRoom = new ExamRoom(10);
//        System.out.println(examRoom.seat());
////        System.out.println(examRoom.seat());
////        System.out.println(examRoom.seat());
////        System.out.println(examRoom.seat());
////        examRoom.leave(4);
////        System.out.println(examRoom.seat());
      //  System.out.println(maxDistToClosest(new int[]{0,0,0,0,0,0,0,0,0,1}));

        MyCalendar myCalendar = new MyCalendar();
        System.out.println(coinChange(new int[]{186,419,83,408}, 6249));

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
