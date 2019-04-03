package LeetCode.easy;

import java.util.ArrayList;
import java.util.List;

public class Problem108 {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            this.val = x;
        }
    }

    public static void main(String[] args) {
        int[] arr = {-10,-3,0,5,9};

    }
    public TreeNode sortArrayToBST(int[] nums){
        return dfs(nums, 0, nums.length - 1);
    }
    public TreeNode dfs(int[] nums, int left, int right){
        if(left > right)
            return null;
        int mid = (left + right)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, left, mid - 1);
        root.right = dfs(nums, mid + 1, right);
        return root;
    }
}
