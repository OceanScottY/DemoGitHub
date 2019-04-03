package LeetCode.easy.FirstWeek;

/**
 * @文件描述： 69. Sqrt(x)
 *
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 *
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 * @创建者：
 * @创建日期：2019/3/11
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem69 {

    public int mySqrt(int x) {
        int left =0;
        int right = x/2+1;
        long mid = 0;
        while(left <= right){
            mid = (left + right)/2;

            if(Math.pow(mid,2) == x){
                return (int)mid;
            }else if(Math.pow(mid,2) > x){
                right = (int)mid-1;
            }else{
                left = (int)mid+1;
            }
        }
        return right;
    }
}
