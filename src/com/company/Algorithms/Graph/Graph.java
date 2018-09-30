package com.company.Algorithms.Graph;

public class Graph {

    /**
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.Input:
     * 1 1 0 0 0
     * 1 1 0 0 0
     * 0 0 1 0 0
     * 0 0 0 1 1
     * Output: 3*/
    static int numIslands(char[][] grid) {
        if(grid == null) return 0;
        int isLandCount =0;
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[r].length; c++){
                if(grid[r][c] == '1'){
                    isLandCount++;
                    changIsLandToWater(grid, r ,c);
                }
            }
        }
        return isLandCount;
    }

    static void changIsLandToWater(char[][] grid, int i, int j){
        if(i < 0 || j < 0|| i>= grid.length || j >= grid[0].length||grid[i][j] == '0')
            return ;
        grid[i][j] ='0';
        changIsLandToWater(grid, i+1,j);//up
        changIsLandToWater(grid, i,j-1);//left
        changIsLandToWater(grid, i,j+1);//right
        changIsLandToWater(grid, i-1,j);//down
    }

    static void solve(char[][] board) {

        int row = board.length;
        int col = board[0].length;
        for (int r = 1; r < row-1; r++) {
            for (int c = 1; c < col-1; c++) {
                flipOToX(board, r, c);
            }
        }
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                System.out.print(board[r][c] +" ");
            }
            System.out.println();
        }
    }

    static void flipOToX(char[][] board, int i, int j){
        if (i<1 || j<1 || i>= board.length-1 || j >= board[0].length|| board[i][j] == 'X')
            return;
        board[i][j] ='X';
        flipOToX(board, i+1,j);
        flipOToX(board, i-1,j);
        flipOToX(board, i,j+1);
        flipOToX(board, i,j-1);
    }

    //   Logical Thought
    // Take nums = [1, 1, 1] for example, part of potential search tree is as below:

        //        start
    //     /         \
    //     +1(11)     ...
    //   /        \
    //  +1+1(1)    +1-1(1)
    //    /  \        /   \
    // +1+1+1 +1+1-1 +1-1+1 +1-1-1
    // Essence
    // reject procedure curIndex == nums.length
    // accept procedure curIndex == nums.length && S == 0
    // Code
    private static int cntWays = 0;
    static int findTargetSumWays(int[] nums, int S) {
        findTargetSumWaysHelper(nums, S, 0);
        return cntWays;
    }
    static void findTargetSumWaysHelper(int[] nums, int S, int currIdx){
        if (currIdx == nums.length){
            if (S == 0)
                cntWays++;
            return;
        }
            findTargetSumWaysHelper(nums,  S - nums[currIdx], currIdx+1);
            findTargetSumWaysHelper(nums,  S + nums[currIdx], currIdx+1);
    }

    static int findTargetSumWays1(int[] nums, int S) {
        int totalSum = 0;
        for (int num : nums) {  //calculate the totalSum keeping all the elements in the array positive
            totalSum += num;
        }
        if (totalSum < S || -totalSum > S) { //If the target sum S is not reachable by the range
            return 0;
        }
        int[] dp = new int[2 * totalSum + 1];
        //dp[i] -> the number of ways to have sum = i - totalSum
        dp[totalSum] = 1;
        //We start from no elements in the array, so there is one way to have sum = 0 that there is no element
        for (int i = 0; i < nums.length; i++) { //Start from deciding to add the first element as positive or negative
            int[] next = new int[2 * totalSum + 1];
            for (int j = 0; j < 2 * totalSum + 1; j++) {
                if (dp[j] != 0) {  //if current sum j - totalSum is already reached by the previous searched numbers
                    next[j + nums[i]] += dp[j]; //plus the num of ways to have sum = j - totalSum to the num of ways to have sum = j + nums[i] - totalSum
                    next[j - nums[i]] += dp[j];
                }//The previous reached range is  -totalSum < [-currSum, currSum ] < totalSum.
                //Since currSum + nums[i] no larger than totalSum, -currSum - nums[i] no smaller than -totalSum, so it won't exceed the boundary
            }
            dp = next;
        }
        return dp[S + totalSum]; //return the num of ways to have sum = S
    }

    public static void main(String...args){

        System.out.println(findTargetSumWays(new int[]{1,1,1,1,1}, 3));
//       solve(new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','O','O','X'},{'X','O','X','X'}});
    }
}
