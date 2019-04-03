package LeetCode.easy.ThirdWeek;

import java.util.Arrays;

/**
 * @文件描述： 88. Merge Sorted Array
 *
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 *
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * Example:
 *
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 *
 * @创建者：
 * @创建日期：2019/3/21
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem88 {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int num1Index = m - 1;
        int nums2Index = n - 1;
        int index = m + n - 1;
        for (; index > 0 && nums2Index >= 0 && num1Index >= 0; index--){
            if(nums1[num1Index] >= nums2[nums2Index]){
                nums1[index] = nums1[num1Index];
                num1Index--;
            }else {
                nums1[index] = nums2[nums2Index];
                nums2Index--;
            }
        }
        if(nums2Index == -1){
            for(int i=num1Index; i>=0; i--,index--){
                nums1[index] = nums1[num1Index];
                num1Index--;
            }
        }
        if (num1Index == -1){
            for(int i=nums2Index; i>=0; i--, index--){
                nums1[index] = nums2[nums2Index];
                nums2Index--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {4,5,6,0,0,0};
        int[] nums2 = {1,2,3};
        merge(nums1,3,nums2,3);
        System.out.println(Arrays.toString(nums1));
    }
}
