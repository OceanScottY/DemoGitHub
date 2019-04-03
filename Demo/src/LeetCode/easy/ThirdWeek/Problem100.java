package LeetCode.easy.ThirdWeek;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * @文件描述： 100. Same Tree
 * Given two binary trees, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 *
 * Example 1:
 *
 * Input:     1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * Output: true
 * Example 2:
 *
 * Input:     1         1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 *
 * Output: false
 *
 *
 * @创建者：
 * @创建日期：2019/3/21
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem100 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        //宽度优先搜索
        if(!check(p,q))
            return false;
        ArrayDeque<TreeNode> deqP = new ArrayDeque<>();
        ArrayDeque<TreeNode> deqQ = new ArrayDeque<>();

        deqP.add(p);
        deqQ.add(q);

        while(!deqP.isEmpty()){
            p = deqP.removeFirst();
            q = deqQ.removeFirst();

            if (!check(p, q)) return false;
            if (p != null) {
                // in Java nulls are not allowed in Deque
                if (!check(p.left, q.left)) return false;
                if (p.left != null) {
                    deqP.addLast(p.left);
                    deqQ.addLast(q.left);
                }
                if (!check(p.right, q.right)) return false;
                if (p.right != null) {
                    deqP.addLast(p.right);
                    deqQ.addLast(q.right);
                }
            }
        }
        return true;

    }

    public static boolean check(TreeNode p, TreeNode q){
        if(p == null && q == null)
            return true;
        if(p == null || q == null)
            return false;
        if(p.val != q.val)
            return false;
        return true;
    }

    public static void main(String[] args) {

    }


}
