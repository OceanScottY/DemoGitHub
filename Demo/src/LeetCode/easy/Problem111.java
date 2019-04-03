package LeetCode.easy;

/**
 * Created by Scott on 2018/7/19
 */
public class Problem111 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //求树的最小深度
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        if(root!=null && root.left!=null && root.right!=null){
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            return 1 + Math.min(left,right);
        }else if(root!=null && root.left == null && root.right != null){
            return 1 + minDepth(root.right);
        }else if(root!=null && root.right == null && root.left != null){
            return 1 + minDepth(root.left);
        }else{
            return 1;
        }
    }
}
