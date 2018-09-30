package com.company.Algorithms.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeInterview1 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

   static void flatten(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();
        preOrderBST(root, ans);
        for (int i = 0; i < ans.size(); i++) {
            //root.left = null;
            //root.right = new TreeNode(ans.get(i));
           // root = root.right;
            System.out.println(ans.get(i));
        }
    }

    static void preOrderBST(TreeNode root, LinkedList<Integer> ans){
        if (root != null){
            ans.addLast(root.val);
            if (root.left != null)
            root.left.val = 1;
            preOrderBST(root.left, ans);
            preOrderBST(root.right, ans);
        }
    }

    static TreeNode convertBST(TreeNode root) {


        return root;
    }

   static int findSecondMinimumValue(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inOrderBST(root, ans);
        if(ans.size() < 2) return -1;
        else if(ans.size() == 2)
            return ans.get(0);
       for (int i = 0; i <ans.size(); i++) {
           System.out.println(ans.get(i));
       }
        return ans.get(ans.size()-2);
    }

    static void inOrderBST(TreeNode root, List<Integer> list){
        if(root!= null){
            inOrderBST(root.left, list);
            if(!list.contains(root.val))
                list.add(root.val);
            inOrderBST(root.right, list);
        }
    }
    static  void inOrderBST(TreeNode root, TreeNode pre){
        if(root != null){
            inOrderBST(root.left, root);
            if (root.right !=null)
            inOrderBST(root.right, root);
        }
    }

    static int[] findMode(TreeNode root) {
        if(root == null) return new int[0];
        if(root.left == null && root.right == null)
            return new int[]{root.val};


        return null;
    }


    /**https://algorithms.tutorialhorizon.com/find-the-maximum-depth-or-height-of-a-binary-tree/
     * find Height of tree
     * approach:
     * Get height of left subtree, say leftHeight
     * Get height of right subtree, say rightHeight
     * Take the Max(leftHeight, rightHeight) and add 1 for the root and return
     * call recursively*/
    public int treHeight(TreeNode root){
        return 1+ Math.max(treHeight(root.left), treHeight(root.right));
    }


    /**Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
     * Note: A leaf is a node with no children.
     * Example:
     * Given the below binary tree and sum = 22,
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \    / \
     * 7    2  5   1
     * Return:
     * [
     * [5,4,11,2],
     * [5,8,4,5]
     * ]*/
    static   List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        pathSumHelper(root, sum, new ArrayList<>(), ans);
        return  ans;
    }

    static void pathSumHelper(TreeNode root, int sum, List<Integer> list, List<List<Integer>> ans){
        if(root != null){
            list.add(root.val);
            pathSumHelper(root.left, sum- root.val,list, ans);
            pathSumHelper(root.right, sum - root.val, list, ans);
            if(root.left == null && root.right == null && sum - root.val ==0){//if node is leaf and node's is equal sum
                ans.add(new ArrayList(list));//add list to ans
            }
            list.remove(list.size()-1);//backtracking.
        }
    }

    public static void main(String...args){

        TreeNode li = new TreeNode(2);
        li.left = new TreeNode(2);
//        li.left.left = new TreeNode(4);
//        li.left.right = new TreeNode(3);
        //li.left.left.left = new TreeNode(7);

        li.right = new TreeNode(5);
        li.right.right = new TreeNode(5);
        li.right.left = new TreeNode(5);
//        li.right.right.right = new TreeNode(1);
//        li.right.right.left = new TreeNode(5);
       // flatten(li);
        System.out.println((findSecondMinimumValue(li)));
    }
}
