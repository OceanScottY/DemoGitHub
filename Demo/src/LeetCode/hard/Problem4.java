package LeetCode.hard;

/**
 * Created by Scott on 2018/7/24
 */
public class Problem4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /*if(nums1.length == 1 && nums2.length ==1){
            return (nums1[0] + nums2[0])/2;
        }
        double res;
        int l1_start = 0,l1_end = nums1.length - 1, l2_start = 0, l2_end = nums2.length - 1;
        while(l1_start <= l1_end && l2_start <= l2_end){
            if(nums1[l1_start] <= nums2[l2_start]){
                l1_start ++;
            }else {
                l2_start ++;
            }
            if(nums1[l1_end] >= nums2[l2_end]){
                l1_end --;
            }else {
                l2_end --;
            }
        }
        if(l1_start > l1_end){
            res = getMid(nums2,l2_start,l2_end);
        }else {
            res = getMid(nums1, l1_start, l1_end);
        }*/
        int l1 = nums1.length;
        int l2 = nums2.length;
        int mid = (l1+l2)/2;
        int[] s = new int[mid+1];
        int j=0,k=0;
        for(int i=0;i<mid+1;i++){
            if(j<l1&&(k>=l2||nums1[j]<nums2[k])){
                s[i]=nums1[j++];
            }else{
                s[i]=nums2[k++];
            }
        }
        if ((l1+l2)%2!=0){
            return (double)s[mid];
        }else{
            return (double)(s[mid-1]+s[mid])/2;
        }
    }

    public double getMid(int[] nums, int start, int end){
        if((end-start)%2 == 0){
            return (double)nums[(start+end)/2];
        }else {
            int index = (start+end)/2;
            return (double)(nums[index]+nums[index+1])/2;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,3};
        int[] nums2 = new int[]{2};
        System.out.println(new Problem4().findMedianSortedArrays(nums1, nums2));
    }

    //网上的答案：



}
