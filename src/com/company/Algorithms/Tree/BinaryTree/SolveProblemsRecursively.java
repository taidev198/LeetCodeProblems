package com.company.Algorithms.Tree.BinaryTree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class SolveProblemsRecursively {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /**Top - Down Solution
     * 1. return specific value for null node
     * 2. update the answer if needed                      // anwer <-- params
     * 3. left_ans = top_down(root.left, left_params)      // left_params <-- root.val, params
     * 4. right_ans = top_down(root.right, right_params)   // right_params <-- root.val, params
     * 5. return the answer if needed                      // answer <-- left_ans, right_ans
     * */

    private static int answer;		// don't forget to initialize answer before call maximum_depth
    private static void maximum_depth(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            answer = Math.max(answer, depth);
        }
        maximum_depth(root.left, depth + 1);
        maximum_depth(root.right, depth + 1);
    }

    /**Bottom-Up Solution
     * 1. return specific value for null node
     * 2. left_ans = bottom_up(root.left)          // call function recursively for left child
     * 3. right_ans = bottom_up(root.right)        // call function recursively for right child
     * 4. return answers                           // answer <-- left_ans, right_ans, root.val*/

   private static int maximum_depth(TreeNode root) {
        if (root == null) {
            return 0;                                   // return 0 for null node
        }
        int left_depth = maximum_depth(root.left);
        int right_depth = maximum_depth(root.right);
        return Math.max(left_depth, right_depth) + 1;	// return depth of the subtree rooted at root
    }

    static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    static List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        pathSumHelper(root, sum, new ArrayList<>(), ans);
        return  ans;
    }

    static void pathSumHelper(TreeNode root, int sum, List<Integer> list, List<List<Integer>> ans){
        if(root != null){
            if (ans.size() > 0){
                List<Integer> tmp = ans.get(ans.size()-1);
                tmp.add(root.val);
                ans.add(tmp);
            }else {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(root.val);
                ans.add(tmp);
            }
            pathSumHelper(root.left, sum- root.val,list, ans);
            pathSumHelper(root.right, sum - root.val, list, ans);
            if(root.left == null && root.right == null && sum == root.val){
                List<Integer> tmp = ans.get(ans.size()-1);
                tmp.add(root.val);
                ans.add(tmp);
            }else {
                List<Integer> tmp = ans.get(ans.size()-1);
                if (tmp.size() > 0)
                tmp.remove(tmp.size()-1);
            }
        }
    }

    public static void main(String...args){

        TreeNode li = new TreeNode(5);
        li.left = new TreeNode(4);
        li.left.left = new TreeNode(11);
        li.left.left.right = new TreeNode(2);
        li.left.left.left = new TreeNode(7);

        li.right = new TreeNode(8);
        li.right.right = new TreeNode(4);
        li.right.left = new TreeNode(13);
        li.right.right.right = new TreeNode(1);
//        li.right.right.left = new TreeNode(5);
        System.out.println(pathSum(li, 22));
    }
}
