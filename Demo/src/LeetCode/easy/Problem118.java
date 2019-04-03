package LeetCode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Scott on 2018/7/19
 */
public class Problem118 {

    public static List<List<Integer>> generate(int numRows) {
        if(numRows == 0)
            return null;
        LinkedList<List<Integer>> result = new LinkedList<>();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        result.add(list);

        if(numRows == 1){
            return result;
        }

        for(int i=1; i<numRows; i++){
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for(int j=1; j<i; j++){

                temp.add(result.get(i-1).get(j-1)+result.get(i-1).get(j));
            }
            list.add(1);
            result.add(temp);
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = generate(2);
        System.out.println(list.toString());
    }

}
