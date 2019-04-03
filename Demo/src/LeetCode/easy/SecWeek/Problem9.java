package LeetCode.easy.SecWeek;

import java.util.UUID;

/**
 * @文件描述： 9. Palindrome Number
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 * @创建者：
 * @创建日期：2019/3/11
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem9 {
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }else {
            String xStr = Integer.toString(x);
            int len = xStr.length();
            for(int i=0; i<= len/2; i++){
                if(xStr.charAt(i) != xStr.charAt(len-1-i)){
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(uuid.length()+ "  " + uuid);
    }

}
