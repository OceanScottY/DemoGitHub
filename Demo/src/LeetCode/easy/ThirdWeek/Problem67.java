package LeetCode.easy.ThirdWeek;

/**
 * @文件描述： 67. Add Binary
 *
 * Given two binary strings, return their sum (also a binary string).
 *
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * @创建者：
 * @创建日期：2019/3/20
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Problem67 {

    public static String addBinary(String a, String b) {
        if (a.length()>b.length())
            return addBinary(b,a);
        char[] chara=a.toCharArray();
        char[] charb=b.toCharArray();
        int lena=chara.length;
        int lenb=charb.length;
        StringBuilder sb=new StringBuilder();
        int carry=0;
//add the same length numbers
        for (int i=0;i<lena;i++){
            int inta=chara[lena-1-i]-'0';
            int intb=charb[lenb-1-i]-'0';
            sb.append(inta^intb^carry);
            carry=(inta&intb)|((inta^intb)&carry);
        }
//add the longer length numbers
        for (int i=lenb-lena-1;i>=0;i--){
            int intb=charb[i]-'0';
            sb.append(intb^carry);
            carry=intb&carry;
        }
//judge the first bit
        if(carry>0)
            sb.append(carry);
        return sb.reverse().toString();
    }


    public static void main(String[] args) {
        System.out.println((int)('1'-48));
    }
}
