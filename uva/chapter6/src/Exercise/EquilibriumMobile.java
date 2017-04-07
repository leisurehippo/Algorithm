package Exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by H-E-Z on 2017/4/7.
 */

/**
 * Regard each node as the standard of the whole mobile,
 * and calculator the weight of the whole mobile
 * For it is the binary tree, the whole weight is equal to weight*(2^height) = weight << height
 * The height of the tree is the count of single '[' before the certain number
 * Because the result is so large, I store the result as MAP<KEY->(weight,height),VALUE->count>
 */
public class EquilibriumMobile {
    //KEY : "weight,height"  VALUE : count
    public static Map<String,Integer> map = new HashMap<>();

    /**
     * return the key of map according to the weight and the height
     * To get the simplest format, divide weight with 2
     * @param str  The certain number
     * @param len  The count of single '[' (Height)
     * @return
     */
    public static String divtwo(String str, int len){
        int num = Integer.valueOf(str);
        int count = 0;
        while(num % 2 == 0){
            num = num / 2;
            count++;
        }
        String res = num + "," + (count+len);
        return res;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        for (int i = 0; i < cases; i++) {
            map.clear();
            String expr = input.next();
            Stack<Character> stack = new Stack<Character>();
            for (int j = 0; j < expr.length(); j++) {
                char c = expr.charAt(j);
                if (c == '[')
                    stack.push(c);
                else if (c == ']')
                    stack.pop();
                else if (c != ','){
                    //find the number
                    int k = j;
                    for ( ; k < expr.length(); k++) {
                        if (expr.charAt(k) == ',' || expr.charAt(k) == ']')
                            break;
                    }
                    String res = divtwo(expr.substring(j,k),stack.size());
                    if (map.containsKey(res))
                        map.put(res,map.get(res)+1);
                    else
                        map.put(res,1);
                    j = k - 1;
                }
            }
            //find max count
            int max = 0;
            int sum = 0;
            for (Map.Entry<String, Integer> entry: map.entrySet()) {
                int value = entry.getValue();
                sum += value;
                if (value > max)
                    max = value;
            }

            System.out.println(sum - max);
        }
    }
}
