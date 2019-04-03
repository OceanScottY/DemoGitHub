package JavaDemoTest.regexDemo;

/**
 * @文件描述： 正则表达式
 * @创建者：
 * @创建日期： 2019/3/5
 * @版权声明：
 * @缩进/编码： tabstop=4 utf-8
 */
public class RegexDemo {


    /**
     * 要求：
     *  1、5~15位数字
     *  2、0不能开头
     *  3、都是数字
     *
     */
    public static void verifyQQ(String qq){
        boolean flag = true;
        if(qq.length() >= 5 && qq.length() <= 15){
            if(!qq.startsWith("0")){
                char[] arr = qq.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char ch = arr[i];
                    if(!(ch >= '0' && ch <= '9')){
                        System.out.println("不符合，不是QQ");
                        return;
                    }
                }
            }else {
                System.out.println("以0开头，不是QQ");
            }
        }else {
            System.out.println("长度不符合，不是QQ");
        }
        System.out.println("是QQ");
    }

    public static void testRegex(){
        String regex = "[abc]";     //[]代表单个字符
        String regex1 = "[^abc]";   //
        String regex2 = "[a-zA-Z]"; //a到z或者A到Z
        String regex3 = "[a-d[m-p]]";   //a到d或者m到p
        String regex4 = "[a-z&&[def]]"; //d,e,f(交集)
        String regex5 = "[a-z&&[^bc]]"; //a到z，除了b,c  减去
        String regex6 = "[a-z&&[^m-p]]";    //a到z，除了m到p
        System.out.println("10".matches(regex1));
        System.out.println("d".matches(regex1));

    }

    /**
     * .    任意字符
     * \d   数字：[0-9]
     * \D   非数字：[^0-9]
     * \s   空白字符：
     * \S   非空白字符
     * \w   单词字符
     * \W   非单词字符
     */
    public static void deom1(){
        String regex1 = "\\d";
        String regex2 = "\\D";
        String regex3 = "\\s";
        String regex4 = "\\S";
    }


    public static void main(String[] args) {
//        verifyQQ("532164710");
        testRegex();
    }


}
