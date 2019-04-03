package LeetCode.easy.SecWeek;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @文件描述： 20. Valid Parentheses
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Input: "()[]{}"
 * Output: true
 *
 * Input: "([)]"
 * Output: false
 * @创建者：
 * @创建日期：2019/3/11
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem20 {

    public static boolean isValid(String s){
        if(s.length()%2 != 0){
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('{','}');
        map.put('[',']');
        map.put('(',')');
        Stack<Character> temp = new Stack<>();
        temp.push(s.charAt(0));
        for(int i=1; i<s.length(); i++){
            if(!temp.isEmpty()){
                Character ch = map.get(temp.peek());
                if(ch != null && ch == s.charAt(i)){
                    temp.pop();
                }else {
                    temp.push(s.charAt(i));
                }
            }else {
                temp.push(s.charAt(i));
            }

        }
        if(temp.empty()){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isValid("{}{}{]{}{}"));

    }

}
