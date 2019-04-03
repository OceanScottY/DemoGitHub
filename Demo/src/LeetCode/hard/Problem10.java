package LeetCode.hard;

/**
 * Created by Scott on 2018/8/7
 */
public class Problem10 {


    /**
     * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
     *
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     *
     */
    public static boolean isMatch(String s, String p) {
        if(p.length() == 0){
            return s.length() == 0;
        }
        if(p.length() == 1){
            return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && s.length()==1;
        }

        if(p.charAt(1) != '*'){
            if(s.length() == 0){
                return false;
            }else {
                return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
            }
        }else {
            while(s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')){
                if(isMatch(s,p.substring(2))){
                    return true;
                }
                s = s.substring(1);
            }
            return isMatch(s,p.substring(2));
        }

    }

    public static void main(String[] args) {

    }
}
