package LeetCode.easy;

/**
 * Created by Scott on 2018/7/19
 */
public class Problem112 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
         if(root == null){
             return false;
         }else if(root.left == null && root.right == null && sum == root.val){
             return true;
         }else {
             return hasPathSum(root.left,sum - root.val) || hasPathSum(root.right,sum - root.val);
         }
    }
}
