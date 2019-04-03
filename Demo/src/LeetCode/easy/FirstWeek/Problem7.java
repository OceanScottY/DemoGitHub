package LeetCode.easy.FirstWeek;

/**
 * @文件描述： 7 Reverse Integer
 * Given a 32-bit signed integer, reverse digits of an integer.
 * @创建者：
 * @创建日期：2019/3/11
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem7 {

    public int reverse(int x) {
        int maxint = 2147483647;
        int minint = -2147483648;
        long param = x;
        if(x < 0)
            param = -x;
        long result = 0;
        while(param != 0){
            result = result * 10 + param % 10;
            param = param / 10;
        }
        if(x < minint || x > maxint || result < minint || result > maxint){
            return 0;
        }
        if(x < 0){
            return -(int)result;
        }else{
            return (int)result;
        }
    }
}
