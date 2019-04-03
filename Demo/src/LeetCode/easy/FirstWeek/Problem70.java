package LeetCode.easy.FirstWeek;

/**
 * @文件描述： 70. Climbing Stairs
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * @创建者：
 * @创建日期：2019/3/11
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem70 {

    public int climbStairs(int n) {
        int[] nums = new int[n];
        for(int i=0; i< n; i++){
            if(i == 0)
                nums[i] = 1;
            else if(i == 1)
                nums[i] = 2;
            else
                nums[i] = nums[i-1] + nums[i-2];
        }
        return nums[n-1];

    }
}
