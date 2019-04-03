package LeetCode.easy;


/**
 * 递归调用的缺点：
 *   1、递归是函数调用自身，函数调用有时间和空间的消耗：每一次函数调用，都需要在内存栈中分配空间以保存参数、返回地址以及临时变量，
 *      而往栈中压入数据和弹出数据都需要时间。
 *   2、递归中很多计算都是重复的，由于其本质是把一个问题分解成两个或者多个小问题，多个小问题存在相互重叠的部分，则存在重复计算，
 *      如fibonacci斐波那契数列的递归实现
 *   3、调用栈可能会溢出，每一次函数调用会在内存栈中分配空间，而每个进程的栈的容量是有限的，当调用的层次太多的时候，就是超出栈的
 *      容量，从而导致栈溢出。
 *优点：
 *    实现较为容易，代码简洁、可读性强
 */


/**
 * Created by Scott on 2018/7/19
 */
public class Problem110 {
    /**
     * 平衡二叉树：
     *              1、如果一个树是null
     *              2、每一个node的左右子树的最大深度相差不超过1
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //判断一个树是否是平衡二叉树
    public static boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        int diff = getDeep(root.left) - getDeep(root.right);
        if(diff > 1 || diff < -1)
            return false;
        else
            return isBalanced(root.left) && isBalanced(root.right);
    }
    //获取树的最大深度
    public static int getDeep(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = getDeep(root.left);
        int right = getDeep(root.right);
        return 1 + Math.max(left, right);
    }

}
