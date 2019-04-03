package LeetCode.easy.SecWeek;

/**
 * @文件描述： 58. Length of Last Word
 *
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 *
 * If the last word does not exist, return 0.
 *
 * Note: A word is defined as a character sequence consists of non-space characters only.
 *
 * @创建者：
 * @创建日期：2019/3/15
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem58 {


    public static int lengthOfLastWord(String s) {
        int count = 0;
        for(int i=s.length()-1; i>=0; i--){
            if(s.charAt(i) == ' '){
                break;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("a"));
    }

}
