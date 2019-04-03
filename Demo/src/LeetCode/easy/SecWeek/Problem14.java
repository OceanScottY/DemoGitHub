package LeetCode.easy.SecWeek;

/**
 * @文件描述： 14. Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 * @创建者：
 * @创建日期：2019/3/11
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem14 {

    public static String longestCommonPrefix(String[] strs){


        return "";
    }

    /**
     * 找出最短的长度
     * @param strs
     * @return
     */
    public static int getMinLen(String[] strs){
        int min = strs[0].length();
        for(int i=0; i<strs.length; i++){
            if(min > strs[i].length()){
                min = strs[i].length();
            }
        }
        return min;
    }

    /**
     * 查找相同前缀的长度
     * @param str1
     * @param str2
     * @return
     */
    public static int getLenPrefix(String str1, String str2){
        int len = 0;
        if(str1.length() <= str2.length()){
            if(str2.contains(str1)){
                return str1.length();
            }
            len = str1.length();
        }else {
            if(str1.contains(str2)){
                return str2.length();
            }
            len = str2.length();
        }
        for(int i=0; i<len; i++){
            int endIndex = i+1;
            if(endIndex >= len){
                if(str1.equals(str2)){
                    return len;
                }
            }
            if(!str1.startsWith(str2.substring(0,endIndex))){
                return i;
            }
        }
        return 0;
    }



    public static void main(String[] args) {

//        getLenPrefix("a","aa");
        System.out.println("qwer".indexOf("qw"));
    }
}
