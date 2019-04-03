package LeetCode.easy.ThirdWeek;

/**
 * @文件描述： 53. Maximum Subarray
 *
 *Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * @创建者：
 * @创建日期：2019/3/17
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem53 {

    public static int maxSubArray(int[] nums) {

        int[] sum = new int[nums.length];
        int max = nums[0];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = Math.max(nums[i], sum[i - 1] + nums[i]);
            max = Math.max(max, sum[i]);
        }
        return max;
    }

    public static void main(String[] args) {

    }
}
