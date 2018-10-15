package com.company.Algorithms.Tree;
import java.util.*;

public class TreeInterview {
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    /** 3
     / \
     9  20
     /  \
     15   7
     return its level order traversal as:
     [
     [3],
     [9,20],
     [15,7]
     ]*/
    static  List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<=size/2;i++){
                TreeNode cur = q.poll();
                list.add(cur.val);
                if(cur.left != null){
                    q.add(cur.left);
                }
                if(cur.right != null){
                    q.add(cur.right);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    static List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
         pathSumHelper(root, sum, 0, new ArrayList<>(), ans);
         return  ans;
    }

    static void pathSumHelper(TreeNode root, int sum, int sum1, List<Integer> list, List<List<Integer>> ans){
        if(root.left != null || root.right != null){
            if (sum - sum1 >= root.val)
            list.add(root.val);
            if (root.left != null)
            pathSumHelper(root.left, sum, sum1+root.val,list, ans);
            if (root.right != null)
            pathSumHelper(root.right, sum, sum1+root.val, list, ans);
            //list.remove(list.size()-1);
        }
        if(root.left == null && root.right == null ){
            if (sum == sum1 +root.val) {
                list.add(root.val);
                ans.add(list);
                list.remove(list.size() -1);
            }
        }
    }

    /**Input:
     1
     /   \
     2     3
     \
     5
     Output: ["1->2->5", "1->3"]*/
    static List<String> binaryTreePaths(TreeNode root) {
        List<String> ans  =new ArrayList<>();
        binaryTreePathsHelper(root, "", ans);
        return ans ;
    }
    static void binaryTreePathsHelper(TreeNode root, String res, List<String> ans){
        if (root.left != null || root.right != null){
            res += (root.val) +"->";
            if (root.left !=null)
            binaryTreePathsHelper(root.left, res, ans);
            if (root.right != null)
            binaryTreePathsHelper(root.right, res, ans);
        }
      if (root.left == null && root.right == null){
            res += (root.val) ;
            ans.add(res);
        }
    }

    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        List<Integer> chosen = new ArrayList<>();
        maxDepthHelper(root, 1, chosen);
        return Collections.max(chosen);
    }

    public void maxDepthHelper(TreeNode root, int s, List<Integer> chosen){
        if(root != null) {
            maxDepthHelper(root.right, s+1, chosen);
            maxDepthHelper(root.left, s +1, chosen);
            if (root.left == null && root.right == null) {
                chosen.add(s);
            }
        }
    }
    /**in-processing*/
    static boolean isValidBST(TreeNode root) {

        return inOrderTraversal(root, false);
    }

    static boolean inOrderTraversal(TreeNode root, boolean isLeft){
        if (root.left != null || root.right != null){
            if (isLeft && root.left != null){
                return root.left.val < root.val;
            }else if (!isLeft && root.right != null)
                return root.right.val > root.val;
             inOrderTraversal(root.left, true);
             inOrderTraversal(root.right, false);
        }
        return true;
    }

    /**done
     * Assume a BST is defined as follows:
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     *   5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * Output: false
     * Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
     * is 5 but its right child's value is 4.*/

    static boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        List<Integer> ans = new ArrayList<>();
        List<Boolean> isValid = new ArrayList<>();
        isValid.add(true);
        inOrderBST1(root, ans, isValid);
        return isValid.get(0);
    }

    static void inOrderBST1(TreeNode root, List<Integer> list, List<Boolean> isValid){
        if(root!= null){
            inOrderBST1(root.left, list, isValid);
            if(list.size() >0 && root.val <= list.get(list.size()-1)) isValid.set(0, false);
            list.add(root.val);
            inOrderBST1(root.right, list, isValid);
        }
        //  return true;
    }

    static boolean checkBST(TreeNode root, TreeNode left, TreeNode right){
        if (left == null && right != null) return root.val < right.val;
        else  if (right == null && left != null) return  left.val < root.val;
        return (left != null ? left.val : 0) < root.val && (right != null ? right.val : 0) > root.val && checkBST(root, root.left, root.right);
    }
    static boolean traversal(TreeNode root, List<Integer> list){
        if (root != null){
            traversal(root.left, list);
            if (list.size() == 0)
                list.add(root.val);
            else {
                if (list.get(list.size()-1) > root.val )
                    return false;
                list.add(root.val);
            }
            traversal(root.right, list);
        }
        return true;
    }


    /**Input: [1,null,2,3]
    * 1
    * \
    * 2
    * /
    * 3
    * Output: [1,2,3]*/
    static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.peek();//get top element of stack
            if (cur != null){//check if it is whether null
                ans.add(cur.val);//add value to ans
                stack.push(cur.left);//push left node to stack
            }else{
                     stack.pop();//pop null node off stack
                    if (stack.isEmpty())//check stack is whether empty
                        break;//stack is empty so completed.
                        stack.push(stack.pop().right);//pop top element off stack and push
            }                                         // right node of it to stack.
        }
        return ans;
    }

    /**Input: [1,null,2,3]
     * 1
     * \
     * 2
     * /
     * 3
     * Output: [1,3,2]*/
    static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.peek();
            if (cur != null){
                stack.push(cur.left);
            }else {
                stack.pop();
                if (stack.isEmpty())
                    break;
                    cur = stack.pop();
                    ans.add(cur.val);
                    stack.push(cur.right);
            }
        }
        return ans;
    }

    static List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.peek();
            if (cur != null){
                stack.push(cur.left);
            }else {
                stack.pop();
                if (!stack.isEmpty()){
                    stack.push(stack.pop().right);
                    if ( stack.peek() == null){
                        stack.pop(); ans.add(stack.peek().val);
                    }

                }
            }
        }
        return ans;
    }

    /**Example 1:
     * Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]
     * 5
     * / \
     * 3    6
     * / \    \
     * 2   4    8
     * /        / \
     * 1        7   9
     * Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
     * 1
     * \
     * 2
     * \
     * 3
     * \
     * 4
     * \
     * 5
     * \
     * 6
     * \
     * 7
     * \
     * 8
     * \
     * 9  */
    static TreeNode increasingBST(TreeNode root) {
        if (root== null) return null;
        List<TreeNode> ans = new ArrayList<>();
        inorderTraversal(root, ans);
        TreeNode res = new TreeNode(ans.get(0).val);
        TreeNode tmp = res;
        for(int i=1;i < ans.size(); i++){
            TreeNode cur  =ans.get(i);
            tmp.right = cur;
            tmp = tmp.right;
        }
        return res;
    }

    static void inorderTraversal(TreeNode root,  List<TreeNode> ans){
        if (root != null) {
            inorderTraversal(root.left, ans);
            ans.add(new TreeNode(root.val));
            inorderTraversal(root.right, ans);
        }
    }

    static TreeNode searchBST(TreeNode root, int val) {
        if(root == null || root.val == val) return root;
        if(val > root.val)
            return  searchBST(root.right, val);
        return  searchBST(root.left, val);
    }

    /**Given the below binary tree and sum = 22,
     5
     / \
     4   8
     /   / \
     11  13  4
     /  \      \
     7    2      1
     return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.*/
    static boolean hasPathSum(TreeNode root, int sum) {
        List<Boolean> chosen = new ArrayList<>();
        hasPathWithGivenSumHelper(root, sum, chosen);
        return chosen.indexOf(true) != -1;
    }

    static void hasPathWithGivenSumHelper(TreeNode root, int s, List<Boolean> chosen){
        if(root != null) {
            hasPathWithGivenSumHelper(root.right, s - root.val, chosen);
            hasPathWithGivenSumHelper(root.left, s - root.val, chosen);
            if (root.left == null && root.right == null) {
                if (s - root.val == 0)
                    chosen.add(true);
            }
        }
    }


    /**Input: [1,2,3]
     * 1
     * / \
     * 2   3
     * Output: 25
     * Explanation:
     * The root-to-leaf path 1->2 represents the number 12.
     * The root-to-leaf path 1->3 represents the number 13.
     * Therefore, sum = 12 + 13 = 25.*/
    static int sumNumbers(TreeNode root) {

        List<Integer> ans = new ArrayList<>();
        sumNumberHelper(root, ans, 0);
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
        return 0;
    }

    static void sumNumberHelper(TreeNode root, List<Integer> ans, int num){
        if (root != null){
            num = num * 10 + root.val;
            sumNumberHelper(root.left, ans, num);
            sumNumberHelper(root.right, ans, num);
            if (root.left == null && root.right == null){
                ans.add(num);
            }
        }
    }

    /**Given the tree:
    * 4
    * / \
    * 2   7
    * / \
    * 1   3
    * And the value to insert: 5
    * You can return this binary search tree:
    * 4
    * /   \
    * 2     7
    * / \   /
    * 1   3 5*/
    static  TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode tmp = root;
        if (root == null) return root;
        boolean isLeft = false;
        TreeNode cur = root;
        while (tmp != null ){
            if (tmp.val > val){
                isLeft = true;
                cur = tmp;
                tmp = tmp.left;
            }
            else{
                isLeft = false;
                cur = tmp;
                tmp = tmp.right;
            }
        }
        if (isLeft) cur.left = new TreeNode(val);
        else cur.right = new TreeNode(val);
        return root;
    }

    /**
     *  3
     * / \
     * 9  20
     * /  \
     * 15   7
     * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.*/
    static List<Integer> sumLeafTree(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        preOrderTraversal(root, ans, false);
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
        return  ans;
    }

    static void preOrderTraversal(TreeNode root, List<Integer> ans, boolean isLeft){
        if (root != null){
            preOrderTraversal(root.left, ans, true);
            preOrderTraversal(root.right, ans, false);
            if (isLeft && root.left == null && root.right == null)
                ans.add(root.val);
        }
    }

    static boolean preOrderTraversalTreeIV(TreeNode root, int k){
        boolean isFound = false;
        if (root != null){
             isFound = searchBST1(root, k - root.val);

            preOrderTraversalTreeIV(root.left, k);
            preOrderTraversalTreeIV(root.right, k);
        }
        return isFound;
    }

    static boolean searchBST1(TreeNode root, int val) {
        if (root == null ) return false;
        else if( root.val == val) return true;
        if(val > root.val)
            return  searchBST1(root.right, val);
        return  searchBST1(root.left, val);
    }

    /**Input:
     * *  1         1
     * / \       / \
     * 2   3     2   3
     * [1,2,3],   [1,2,3]
     * Output: true
     * Input:
     * 1         1
     * /           \
     * 2             2
     * [1,2],     [1,null,2]
     * Output: false
     */
    static  boolean isSameTree(TreeNode p, TreeNode q) {
        if( p == null || q == null)
        return q ==null && p == null;
        return p.val == q.val  && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    /**
     * *3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its bottom-up level order traversal as:
     * [
     * [15,7],
     * [9,20],
     * [3]
     * ]
     * DFS*/
    static  public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        levelMaker(wrapList, root, 0);
        return wrapList;
    }

     static void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
        if(root == null) return;
        if(level >= list.size()) {
            list.add(0, new LinkedList<Integer>());
        }
        levelMaker(list, root.left, level+1);
        levelMaker(list, root.right, level+1);
        list.get(list.size()-level-1).add(root.val);
    }


    boolean isValidBST1(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            TreeNode cur = q.poll();
            if (cur.left != null){
                if (cur.val < cur.left.val) return false;
                q.add(cur.left);
            }
            if (cur.right != null){
                if (cur.val > cur.right.val) return false;
                q.add(cur.right);
            }
        }
        return true;
    }

    static int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int width =1;
        boolean isEnd = false;
        while(!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if(cur != null){
                    ((LinkedList<TreeNode>) q).add(0, cur.left);
                    q.add(cur.left);
                    q.add(cur.right);
                    if (cur.left == null && cur.right == null)
                    isEnd = true;
                }
            }
            if (!isEnd)
            width = Math.max(width, q.size());
        }
        return width;
    }

    /**Given binary tree [3,9,20,null,null,15,7],
     3
     / \
     9  20
     /  \
     15   7
     return its zigzag level order traversal as:
     [
     [3],
     [20,9],
     [15,7]
     ]*/
    static  List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root== null) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        boolean isZigZag = false;
        while (!stack.isEmpty()){
            List<Integer> temp = new ArrayList<>();
            Stack<TreeNode> tempSTack = new Stack<>();
            while (!stack.isEmpty()){
                TreeNode cur = stack.pop();
                temp.add(cur.val);
                if (!isZigZag){
                    if (cur.left != null){
                        tempSTack.push(cur.left);
                    }
                    if (cur.right != null){
                        tempSTack.push(cur.right);
                    }
                }else {
                    if (cur.right != null){
                        tempSTack.push(cur.right);
                    }
                    if (cur.left != null){
                        tempSTack.push(cur.left);
                    }
                }
            }
            stack.addAll(tempSTack);
            ans.add(temp);
            isZigZag = !isZigZag;
        }

        return ans;
    }

    static String findProfession(int level, int pos) {
        if(level == 0 ) return "Engineer";
        Queue<String> q = new LinkedList<>();
        q.add("Engineer");
        while(level != 0){
            int size = q.size();
            int i = 0;
            while (i< size){
                String curr = q.poll();
                if (curr.equals("Engineer")){
                    q.add("Engineer");
                    q.add("Doctor");
                }else {
                    q.add("Doctor");
                    q.add("Engineer");
                }
                i++;
            }
            level--;
        }

        return ((LinkedList<String>) q).get(pos-1);
    }
    public static void postOrder(TreeNode root){
        if (root!= null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.val);
        }

    }
    public static void main(String...args){
        TreeNode li = new TreeNode(1);
        li.left = new TreeNode(3);
        li.left.left = new TreeNode(7);
        li.left.right = new TreeNode(6);
        //li.left.left.left = new TreeNode(7);

        li.right = new TreeNode(2);
        li.right.right = new TreeNode(4);
        li.right.left = new TreeNode(5);
        li.right.right.right = new TreeNode(8);
        li.right.left.right = new TreeNode(9);
//      List<List<Integer>> res = pathSum(li, 22);
//        for (int i = 0; i < res.size(); i++) {
//            System.out.println(res.get(i));
//        }
        System.out.println(preOrderTraversal(li));
        preOrderTraversal(li);
    }
}
