package com.company.Algorithms.BackTracking;

import com.company.Algorithms.Array.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BackTracking {


    public static void permute(int[] nums, int left, int right){
        if(left == right)
            System.out.println(Arrays.toString(nums));
        else {
            for (int i = left; i <=right; i++) {
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
                permute(nums, left+1, right);
                temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
            }
        }
    }


    static boolean res = false;
    static boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for(int i= 0; i< row; i++){
            for(int j=0;j< col; j++){
                if(board[i][j] == word.charAt(0))
                    existHelper(board, word, visited, new ArrayList<>(), i , j);
                //   res =  findPath(board, word, visited, new ArrayList<>(), i, j);
            }
        }
        return res;

    }

    static boolean findPath(char[][] board, String word, boolean[][] visited, List<Character> chosen, int r, int c){
        int index =0;
//        while (!chosen.isEmpty()){
//            if(chosen.size() == word.length() &&
//                    chosen.toString().replace("[", "").replace("]", "")
//                            .replace(",","").equals(word)){
//               return true;
//            }
//            if (isValid(board, word, visited, chosen, r, c+1, index+1)){
//                chosen.add(board[r][c+1]);
//            }else if (isValid(board, word, visited, chosen, r+1, c, index+1)){
//                chosen.add(board[r+1][c]);
//            }
//            else if (isValid(board, word, visited, chosen, r-1, c, index+1)){
//                chosen.add(board[r-1][c]);
//            }else if (isValid(board, word, visited, chosen, r+1, c, index+1)){
//                chosen.add(board[r][c-1]);
//            }else{
//                chosen.remove(chosen.size()-1);
//                visited[r][c] = false;
//            }
//        }

        int row = board.length;
        int col = board[0].length;
        int count = 0;
        while (index < word.length()){
            while (c < col && isValid(board, word, visited, chosen, r, c, index)){
                c++;
                index++;
                if (!isValid(board, word, visited, chosen, r, c, index)){
                    index--;
                    c--;
                    break;
                }

            }
            while (c >0 && isValid(board, word, visited, chosen, r, c, index)){
                c--;
                index++;
                if (!isValid(board, word, visited, chosen, r, c, index)){
                    index--;
                    c++;
                    break;
                }

            }
            while (r < row && isValid(board, word, visited, chosen, r, c, index)){
                r++;
                index++;
                if (!isValid(board, word, visited, chosen, r, c, index)){
                    index--;
                    r--;
                    break;
                }

            }
            while (r >0 && isValid(board, word, visited, chosen, r, c, index)){
                r--;
                index++;
                if (!isValid(board, word, visited, chosen, r, c, index)){
                    index--;
                    r++;
                    break;
                }

            }
            if (index == word.length())
                return true;
            index--;
        //    visited[r][c] = false;
        }

        return true;
    }

    static boolean isValid(char[][] board, String word, boolean[][] visited, List<Character> chosen, int r , int c, int index){

        if(r <0 || c < 0|| r >=  board.length || c >=board[0].length
                || visited[r][c] || chosen.size() > word.length() || word.charAt(index) != board[r][c]){
            if ( word.charAt(index) != board[r][c])
                visited[r][c] = true;
            return false;
        }
        visited[r][c] = true;
        return true;

    }

    static void existHelper(char[][] board, String word, boolean[][] visited,
                            List<Character> chosen, int r, int c){
        if(r <0 || c < 0|| r >=  board.length || c >=board[0].length
                || visited[r][c] || chosen.size() > word.length()){
            return ;
        }
        chosen.add(board[r][c]);
        visited[r][c] = true;
        if(chosen.size() == word.length() &&
                chosen.toString().replace("[", "").replace("]", "")
                        .replace(",","").replace(" ", "").equals(word)){
            res = true;
        }

        existHelper(board, word, visited, chosen, r , c+1);
        existHelper(board, word, visited, chosen, r +1, c);
        existHelper(board, word, visited, chosen, r - 1, c);
        existHelper(board, word, visited, chosen, r , c-1);
        chosen.remove(chosen.size() -1);
        visited[r][c] = false;
    }

    static List<List<Integer>> combine(int n, int k) {

        boolean[] visited = new boolean[n+1];
        List<List<Integer>> ans = new ArrayList<>();

        combineHelper(n, k, ans, new ArrayList<>(), visited);
        return ans;

    }

    static void combineHelper(int n, int k, List<List<Integer>> ans, List<Integer> chosen, boolean[] visited){
        if(chosen.size() == k){
            if(!ans.contains(chosen))
                ans.add(new ArrayList(chosen));
            return;
        }

        for(int i =1; i <= n ;i++){
            if(!visited[i]){
                visited[i] = true;
                if(chosen.size() ==0  || chosen.get(chosen.size()-1 ) < i){
                    chosen.add(i);
                }
                combineHelper(n, k, ans, chosen, visited);
                visited[i] = false;
                if(chosen.size() >0){
                    chosen.remove(chosen.size() -1);
                }
            }
        }
    }

    static int arraySumDelegate(int[] ints, int size){
        if (size == 0)
            return 0;
        int lastNumber = ints[size-1];
        int allButLastNumber = arraySumDelegate(ints, size -1);
        return lastNumber + allButLastNumber;
    }

    /**we can assume that a recursive call with a smaller value for size will
     * return  the correct result and compute the correct value for the overall
     * array from that. This will lead to a head-recursive solution.*/
    static int zeroCountRecursive(int[] ints, int size){

        if (size == 0) return 0;
        int count = zeroCountRecursive(ints, size -1);
        if (ints[size -1] == 0) count++;
        return count;
    }
    public static void main(String...args){
        //permute(new int[]{1,2,3,4}, 0, 3);
//        System.out.println(exist(new char[][]{{'A', 'B', 'C','E'}, {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}}, "ABCCED"));

        System.out.println(combine(5,4));

        class test{
            public test(String str){
                System.out.println(str);
            }
            public void show(){
                System.out.println("thanh tai nguyen");
            }
        }
        class test1 extends test{

            public test1(String str) {
                super(str);
                System.out.println(str);
            }
            public void show(){
                System.out.println("nguyen thanh tai");
            }

        }
        test t = new test("tai");
        test1 t2 = new test1("tai2");
        test t1 = t2;
        t.show();
        t1.show();
    }
}
