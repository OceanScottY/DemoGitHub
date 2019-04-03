package LeetCode.easy.ThirdWeek;

/**
 * @文件描述： 38. Count and Say
 *The count-and-say sequence is the sequence of integers with the first five terms as following:
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 *
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 *
 * Note: Each term of the sequence of integers will be represented as a string.
 *
 *
 * @创建者：
 * @创建日期：2019/3/17
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem38  {

    public static String countAndSay(int n) {
        String pre = "1";
        for(int i=1; i < n; i++){
            pre = sayStr(pre);
        }
        return pre;
    }
    public static String sayStr(String str){
        if(str.length() == 0)
            return "";
        char[] strChar = str.toCharArray();
        int count = 1;
        char pre = strChar[0];
        StringBuilder sb = new StringBuilder();
        for(int i=1; i < strChar.length; i++){
            if(strChar[i] == pre){
                count++;
            }else {
                sb.append(Integer.toString(count) + pre);
                pre = strChar[i];
                count = 1;
            }
        }
        sb.append(Integer.toString(count) + pre);
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }
}
