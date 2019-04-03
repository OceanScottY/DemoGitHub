package LeetCode.Medium.ThirdDay;

import java.util.Collections;

/**
 * @文件描述： 6. ZigZag Conversion
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 * @创建者：
 * @创建日期：2019/3/26
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem6 {

    public static String convert(String s, int numRows) {

        if (s == null || s.length() == 0 || numRows <= 1) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        /*
         * a   e
         * b d f
         * c   g
         * 从a到d为一个zig，size: 一个zig的大小
         */
        int size = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            // 外层循环i表示第几行（从0开始）
            // 内层序号j表示第几个zig（从0开始）
            for (int j = i; j < s.length(); j += size) {
                res.append(s.charAt(j));
                // zig的第一行和最后一行中间的行
                // 每一个zig有两个元素在同一行，如：b和d在同一行
                // 同一行中的两个元素之间的距离是：size-2*i(size一个zig的大小，i当前是第几行，从0开始）
                // 同一个zig中的第一个元素在s中的位置是j，第二个元素在s中的位置是：j+size-2*i
                if (i > 0 && i < numRows - 1) {
                    int mid = j + size - 2 * i;
                    if (mid < s.length()) {
                        res.append(s.charAt(mid));
                    }
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {

    }

}
