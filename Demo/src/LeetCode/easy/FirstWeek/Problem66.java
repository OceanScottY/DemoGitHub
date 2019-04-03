package LeetCode.easy.FirstWeek;

/**
 * @文件描述：  66. Plus One
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * @创建者：
 * @创建日期：2019/3/11
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem66 {

    public int[] plusOne(int[] digits) {
        int carrage = 1;
        for(int i=digits.length-1; i >=0 ; i--){
            digits[i] = digits[i] + carrage;
            if(digits[i] > 9){
                digits[i] -= 10;
                carrage = 1;
            }else{
                carrage = 0;
                break;
            }
        }
        if(carrage == 1){
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            for(int i=0; i<digits.length; i++){
                res[i+1] = digits[i];
            }
            return res;
        }else{
            return digits;
        }
    }
}
