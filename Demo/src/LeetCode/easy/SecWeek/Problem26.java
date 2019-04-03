package LeetCode.easy.SecWeek;

/**
 * @文件描述： 26 Remove Duplicates from Sorted Array
 *
 *Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * @创建者： Scott
 * @创建日期： 2019/3/11
 * @版权声明：
 * @缩进/编码： tabstop=4 utf-8
 */
public class Problem26 {

    public int removeDuplicates(int[] nums) {
        if(nums.length == 0)
            return 0;
        int index = 0;
        for(int i=1; i<nums.length; i++){
            if(nums[index] != nums[i]){
                index++;
                nums[index] = nums[i];
            }
        }
        return index+1;
    }

    public static void main(String[] args) {

    }
}
