package LeetCode.easy.ThirdWeek;

import java.util.Arrays;

/**
 * @文件描述： 28. Implement strStr()
 * @创建者：
 * @创建日期：2019/3/17
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem28 {

    public static int strStr(String haystack, String needle) {
        if(needle.length() == 0)
            return 0;
        char[] strChar = haystack.toCharArray();
        int sourceLen = strChar.length;
        char[] targetChar = needle.toCharArray();
        int targetLen = targetChar.length;

        char first = targetChar[0];
        int max = sourceLen - targetLen;
        for(int i=0; i<= max; i++){
            //找到第一个相等的字符
            if(strChar[i] != first){
                while(i <= max && strChar[i] != first)
                    i++;
            }
            //匹配其他的字符
            if(i <= max){
                int j = i + 1;
                int end = j + targetLen - 1;
                for(int k = 1; j < end && targetChar[k] == strChar[j]; k++){
                    j++;
                }

                if(end == j){
                    return i;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
//        String str = "hello";
//        String target = "ll";
//        System.out.println(strStr(str,target));
//        System.out.println(str.indexOf(target));
//        System.out.println("".indexOf(""));
//        int[] a = new int[10];
//        Arrays.fill(a,9);
//        int[] newArr = Arrays.copyOf(a,a.length);





    }
}
