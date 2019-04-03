package LeetCode.easy.SecWeek;

/**
 * @文件描述： 35. Search Insert Position
 *Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * @创建者：
 * @创建日期：2019/3/15
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem35 {

    public static int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid = (low + high) / 2;
        while (low <= high) {
            System.out.println("low=" + low + "; mid=" + mid + "; high=" + high);
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target)
                low = mid + 1;
            else high = mid - 1;
            mid = (low + high) / 2;
        }
        return low;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,7,9};
        System.out.println(searchInsert(nums,4));
    }
}
