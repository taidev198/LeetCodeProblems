package com.company.Algorithms.Stack;

import java.util.*;

public class StackAndDFS {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    /**processing
     * boolean DFS(Node cur, Node target, Set<Node> visited) {
     *     return true if cur is target;
     *     for (next : each neighbor of cur) {
     *         if (next is not in visited) {
     *             add next to visted;
     *             return true if DFS(next, target, visited) == true;
     *         }
     *     }
     *     return false;
     * }*/
    static boolean DFS(TreeNode cur, TreeNode target, Set<TreeNode> visited){
        if (cur == target)
            return true;
        if (cur.left != null){
            if (!visited.contains(cur.left)){
                visited.add(cur.left);
                return DFS(cur.left, target, visited);
            }
        }
        if (cur.right != null){
            if (!visited.contains(cur.right)){
                visited.add(cur.right);
                return DFS(cur.right, target, visited);
            }
        }
        return false;
    }

    /**Given a binary tree, return the postorder traversal of its nodes' values.
     * Example:
     * Input: [1,null,2,3]
     * 1
     * \
     * 2
     * /
     * 3
     * Output: [3,2,1]
     * Follow up: Recursive solution is trivial, could you do it iteratively?*/
    static List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visited = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.peek();
            if ( cur!= null &&( !visited.contains(cur.left) || !visited.contains(cur.right))){
                if (!visited.contains(cur.left)){
                    stack.push(cur.left);
                }else {
                    stack.push(cur.right);
                }
            }else {
                if (cur == null){
                    visited.add(stack.pop());
                }
                else{
                    visited.add(stack.peek());
                    ans.add(stack.pop().val);
                }
            }
        }
        return ans;
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
        System.out.println(postOrderTraversal(li));
    }
}
