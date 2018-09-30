package com.company.Algorithms;

import java.util.*;

public class EasyProblems {

     public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    static  public boolean isPalindrome(int x) {
        if(x<0) return false;
        int left = x;
        int right = x;
        int pow=10, n =(int)Math.log10(x)+1;
        int i=0;
        while (i<n/2){
            if(left/(int)Math.pow(10,n-1-i) != right %10)
                return false;
            right /=10;
            left %=(int)Math.pow(10, n-i -1);
            i++;
        }
        return true;
    }

    static public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        char[] tmp = s.toCharArray();
        String[] pattern = new String[] {"[]","{}","()"};
        for (int i = 0; i <tmp.length ; i++) {
            if (tmp[i] == '{' || tmp[i] == '[' || tmp[i] == '(')
                stack.push(tmp[i]);
            if ((tmp[i] == '}' || tmp[i] == ']' || tmp[i] == ')') ){
                    if(stack.isEmpty())  return  false;
                    switch (tmp[i]){
                        case '}':if (stack.pop() != pattern[1].charAt(0)) return false;
                        break;
                        case ')':if (stack.pop() != pattern[2].charAt(0)) return false;
                            break;
                        case ']':if (stack.pop() != pattern[0].charAt(0)) return false;
                            break;
                    }
            }
        }
        return stack.isEmpty();
    }

    static  int removeElement(int[] nums, int val) {
        int num=0;
        for (int i = 0; i <nums.length ; i++) {
            if (nums[i] == val){
                num++;
                for (int j = i; j < nums.length-1; j++) {
                    nums[j] = nums[j+1];
                }
                --i;
                if(nums[i+1] != val)
                    break;
            }
        }
        System.out.println(num);
       // nums = Arrays.copyOfRange(nums,0, nums.length - val);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
        return nums.length;
    }

    static int removeDuplicates(int[] nums) {


        return nums.length;
    }

    static int[][] flipAndInvertImage(int[][] A) {

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
               if(A[i][j] == 0)
                   A[i][j] = 1;
               else A[i][j] = 0;
            }
        }
        return A;
    }

    static int[] plusOne(int[] digits) {
        boolean isIncreament=true;
        for(int i= digits.length-1; i>=0;i --){
            if(isIncreament){
                if(digits[i]+1 < 10)
                isIncreament =false;
                digits[i] = (digits[i] +1)%10;

            }
        }
        if (digits[0] == 0){
          int[] res = new int[digits.length+1];
          Arrays.fill(res, 0);
          res[0] = 1;
          return res;
        }
        return digits;
    }

    static  public boolean findTarget(TreeNode root, int k) {
        Queue<TreeNode> q= new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.left != null)
                    q.add(cur.left);
                if (cur.right != null)
                    q.add(cur.right);
            }
            int i=0;
            while (i< q.size()){
                if (((LinkedList<TreeNode>) q).indexOf(k- ((LinkedList<TreeNode>) q).get(i).val) != i
                        && ((LinkedList<TreeNode>) q).indexOf(k- ((LinkedList<TreeNode>) q).get(i).val) != -1)
                    return true;
                i++;
            }
        }
    return false;
    }

    static int findDuplicate(int[] nums) {
        boolean[] isValid = new boolean[nums.length+1];

        for(int i=0;i<nums.length;i++){
            if(isValid[nums[i]]) return nums[i];
            else isValid[nums[i]] = true;
        }
        return -1;
    }
    static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=0,j=0;
        while(i<(m+n)){
            if(nums1[i] <= nums2[j] && nums1[i] !=0) i++;
            else if(nums1[i] > nums2[j]){
                nums1[i] += nums2[j];
                nums2[j] = nums1[i] - nums2[j];
                nums1[i] -= nums2[j];
                i++;
            }else if(nums1[i] ==0) {
                nums1[i] = nums2[j];
                j++;
                i++;
            }
        }
        for (int k = 0; k < m+n; k++) {
            System.out.println(nums1[k]);
        }
    }

    static boolean checkEqualFrequency(int[] inputArray) {
        Arrays.sort(inputArray);
        int cur =-1;
        int fre =0;
        for(int i=1;i<inputArray.length;i++){
            if(inputArray[i] == inputArray[i-1])
                fre++;
            else{
                if(cur == -1 && i<inputArray.length-1)
                    cur = fre;
                else if(cur != fre ||cur ==-1) return false;
                fre = 0;

            }
        }

        return true;
    }

    static int maxSubArray(int[] nums) {
        int max = nums[0];

        int maxStart =0;
        int maxEnd = 0;
        int currentStart =0;
        int sum =0;
        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];

            if (sum > max){
                maxEnd = end;
                maxStart = currentStart;
                max = sum;
            }
            if (sum < 0){
                sum = 0;
                currentStart = end+1;
            }
        }
         return max;
    }

    static void setZeroes(int[][] matrix) {
        boolean[] unUsedCol = new boolean[matrix[0].length];
        boolean[] unUsedRow = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] ==0){
                    unUsedCol[j] = true;
                    unUsedRow[i] = true;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(unUsedCol[j] || unUsedRow[i]){
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
               System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    static int[][] spiralNumbers(int n) {
        int gt = 1;
        int h =0;
        int[][] result = new int[n][n];
        while(h <= n/2){
            int row = h, col = n -1 -h;
            for(int i = row; i <= col; i++)
                result[row][i] = gt++;
            for(int i = row+1; i <= col; i++)
                result[i][col] = gt++;
            for(int i = col -1; i >= row; i--)
                result[col][i] = gt++;
            for(int i = col -1; i > row; i--)
                result[i][row] = gt++;
            h++;

        }
        return result;
    }

    static List<Integer> spiralOrder(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        List<Integer> ans = new ArrayList<>();
        int start = 0;
        while (start <= col/2){
            for (int i = start; i < col-start; i++)
                ans.add(matrix[start][i]);
            if (row == 1) break;
            for (int i = start +1; i < row-start; i++)
                ans.add(matrix[i][col-1-start]);
            for (int i = col-1-start; i >start; i--)
                ans.add(matrix[row-1-start][i]);
            for (int i =row-1-start; i >start; i--)
                ans.add(matrix[i][start]);

            start++;
        }
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
        return  ans;
    }

    static boolean searchMatrix(int[][] matrix, int target) {
        int left = 0, right = matrix.length-1;
        while (left <=right){
            int mid = (left + right) /2;
            if (target <= matrix[mid][0] && target >= matrix[mid][matrix[mid].length-1]){

            }
        }
         return false;
    }

    static boolean leafSimilar(TreeNode root1, TreeNode root2) {
         List<Integer> list1 = new ArrayList<>();
         List<Integer> list2 = new ArrayList<>();
          leafOfTree(list1, root1);
          leafOfTree(list2, root2);
         return list1.equals(list2);
    }

    static void leafOfTree(List<Integer> list, TreeNode root){
         if (root.left == null && root.right == null){
             list.add(root.val);
         }else {
             if (root.left != null)
             leafOfTree(list, root.left);
             if (root.right != null)
             leafOfTree(list, root.right);
         }
    }

    public static void main(String...args){
        TreeNode li = new TreeNode(3);
        li.left = new TreeNode(5);
//        li.left.left = new TreeNode(6);
//        li.left.right = new TreeNode(2);
//        li.left.right.right = new TreeNode(4);
//        li.left.right.left  = new TreeNode(7);
//
//        li.right = new TreeNode(1);
//        li.right.right = new TreeNode(8);
//        li.right.left = new TreeNode(9);
       System.out.println(leafSimilar(li, li));
    }
}
