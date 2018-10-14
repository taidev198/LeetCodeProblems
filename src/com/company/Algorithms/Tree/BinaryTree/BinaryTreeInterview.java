package com.company.Algorithms.Tree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInterview {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    static int maximumDepthOfBinaryTree(TreeNode root){

    return maximumDepthOfBinaryTreeHelper(root, 0 ,0);
    }

    static int maximumDepthOfBinaryTreeHelper(TreeNode root, int ans, int depth){
        if (root != null){
            if (root.left == null && root.right == null)
                ans = Math.max(ans, depth);
            maximumDepthOfBinaryTreeHelper(root.left, ans, depth+1);
            maximumDepthOfBinaryTreeHelper(root.right, ans, depth+1);
            return ans;
        }
        return ans;
    }

    static int maximumDepthOfBinaryTreeBottomUpSolution(TreeNode root){
        if (root == null)
            return 0;
        return 1 + Math.max( maximumDepthOfBinaryTreeBottomUpSolution(root.left),
                maximumDepthOfBinaryTreeBottomUpSolution(root.right));

    }

    static List<Integer> pathSumInBinaryTree(TreeNode root, int s){
        List<Integer> ans = new ArrayList<>();
        pathSumInBinaryTreeHelper(root, s, ans);

        return ans;
    }

    static void pathSumInBinaryTreeHelper(TreeNode root, int s, List<Integer> ans){
        if (root != null){
            if (root.left == null && root.right == null){
                if (s - root.val == 0)
                    ans.add(root.val);
                  else  ans.remove(ans.size()-1);
            }

            if (s - root.val > 0) ans.add(root.val);

            pathSumInBinaryTreeHelper(root.left, s - root.val, ans);
            pathSumInBinaryTreeHelper(root.right, s - root.val, ans);
        }
    }


    static boolean hasPathSum(TreeNode root, int sum){
        if(root != null){
            if (root.left == null && root.right == null){
                if (sum - root.val == 0)
                    return true;
            }
            hasPathSum(root.left, sum - root.val);
            hasPathSum(root.right, sum - root.val);
        }
        return false;
    }

    static TreeNode sortedArrayToBST(int[] nums) {

       return sortedArrayToBSTHelper(nums, 0, nums.length -1);
    }

    static TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right){
        if (left > right) return  null;
            int mid = (right + left)/2;
            TreeNode newNode = new TreeNode(nums[mid]);
            newNode.left = sortedArrayToBSTHelper(nums, left, mid -1);
            newNode.right = sortedArrayToBSTHelper(nums, mid+1, right);
            return newNode;
    }

    static TreeNode buildTree(int[] preorder, int[] inorder) {
        int idxRoot = 0;
        TreeNode root = new TreeNode(preorder[idxRoot]);
        //find root's position in inorder, then left of root is left subtree and
        //right of root is right subtree
        int j =0;
        int lenPreOrder = preorder.length;
        int lenInOrder = inorder.length;
        int left =0;
        TreeNode leftTree = root;
        TreeNode rightTree = root;
        while(j < lenInOrder){
            if (inorder[j] == preorder[idxRoot] && idxRoot < lenPreOrder -1){
                idxRoot++;
                int k = j-1;
                int len = j-left;
                while (k >=left && inorder[k] == preorder[idxRoot]){
                    leftTree.left = new TreeNode(inorder[k--]);
                    leftTree = leftTree.left;
                    idxRoot++;
                }
                left = len+1;
                if (idxRoot < lenPreOrder){
                    rightTree.right = new TreeNode(preorder[idxRoot]);
                    leftTree = rightTree.right;
                    rightTree = rightTree.right;
                }
                else break;
            }else j++;
        }
        return root;
    }


    static TreeNode buildTree1(int[] preorder, int[] inorder){

        return buildTree1Helper(preorder, inorder, 0, preorder.length-1, 0);
    }

    static TreeNode buildTree1Helper(int[] preorder, int[] inorder, int left , int right, int idxRoot){
        if (idxRoot >= preorder.length) return null;
        //find root's position
        int i;
        for (i = idxRoot; i <= right; i++) {
            if (preorder[idxRoot] == inorder[i])
                break;
        }
        TreeNode root = new TreeNode(preorder[idxRoot]);
        root.left = buildTree1Helper(preorder, inorder, left, i-1, idxRoot+ 1);
        root.right = buildTree1Helper(preorder, inorder, i+1, right, idxRoot+1);
        return root;
    }

    static boolean isBinarySearchTree(TreeNode root){
        return validate(root, null, null);
    }

     static  boolean validate(TreeNode node, Integer min, Integer max) {
            if (node == null) {
                return true;
            }

            if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
                return false;
            }

            return validate(node.left, min, node.val) && validate(node.right, node.val, max);
        }
    public static void main(String...args){
        TreeNode li = new TreeNode(1);
        li.left = new TreeNode(-1);
      //  li.left.right = new TreeNode(3);
//        li.left.right = new TreeNode(3);
        //li.left.left.left = new TreeNode(7);

//        li.right = new TreeNode(5);
//        li.right.right = new TreeNode(3);
//        li.right.left = new TreeNode(3);
//        li.right.right.right = new TreeNode(1);
//        li.right.right.left = new TreeNode(5);
        TreeNode res = buildTree1(new int[]{1,2,3}, new int[]{2,3,1});
        System.out.println(isBinarySearchTree(li));

       
    }
}
