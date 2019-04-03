package LeetCode.easy.FirstWeek;

import java.util.HashMap;
import java.util.Map;

/**
 * @文件描述： 13 Roman to Integer
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * @创建者：
 * @创建日期：2019/3/11
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem13 {

    public int romanToInt(String s) {
        Map<Character,Integer> romanMap = new HashMap();
        romanMap.put('M',1000);
        romanMap.put('D',500);
        romanMap.put('C',100);
        romanMap.put('L',50);
        romanMap.put('X',10);
        romanMap.put('V',5);
        romanMap.put('I',1);

        int pre =romanMap.get(s.charAt(s.length()-1));
        int current = 0;
        int result = pre;
        for(int i=s.length()-2; i>= 0; i--){
            current = romanMap.get(s.charAt(i));
            if(current >= pre){
                result += current;
            }else{
                result -= current;
            }
            pre = current;
        }
        return result;
    }
}
