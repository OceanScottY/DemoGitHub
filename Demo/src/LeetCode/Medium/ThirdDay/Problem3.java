package LeetCode.Medium.ThirdDay;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @文件描述： 3. Longest Substring Without Repeating Characters
 *
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * @创建者：
 * @创建日期：2019/3/21
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem3 {


    public static int lengthOfLongestSubstring(String s) {
        int[] temp = new int[256];
        Arrays.fill(temp,-1);
        int left = -1;
        int res = 0;
        for(int i=0; i < s.length(); i++){
            left = Math.max(left, temp[s.charAt(i)]);
            temp[s.charAt(i)] = i;
            res = Math.max(res, i - left);
        }
        return res;

    }
    public int lengthOfLongestSubstring2(String s) {
        int[] m = new int[256];
        Arrays.fill(m, -1);
        int res = 0, left = -1;
        for (int i = 0; i < s.length(); ++i) {
            left = Math.max(left, m[s.charAt(i)]);
            m[s.charAt(i)] = i;
            res = Math.max(res, i - left);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(" "));
        HashMap<Integer,String> map = new HashMap();
        map.put(1,"2");
        ConcurrentHashMap<Integer,String> test = new ConcurrentHashMap<>();
        test.put(1,"2");
    }
}
