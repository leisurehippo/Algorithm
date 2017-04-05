package Exercise;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by H-E-Z on 2017/3/26.
 */
public class ParenthesesBalance {
    public static Stack<Character> stack = new Stack<>();
    public static boolean judge(String str){
        int num = str.length();
        if (num == 0)
            return true;
        if (num % 2 != 0)
            return false;
        stack.clear();
        boolean flag = true;
        for (int j = 0; j < num; j++) {
            char c = str.charAt(j);
            if (c == '(' || c == '[')
                stack.push(c);
            else{
                char oppo = c == ')' ? '(' : '[';
                if (!stack.isEmpty()){
                    char s = stack.pop();
                    if (s != oppo)
                        return false;
                }else
                    return false;
            }
        }
        if (!stack.isEmpty())
            return false;
        return  true;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = Integer.valueOf(input.nextLine());
        for (int i = 0; i < num; i++) {
            String str = input.nextLine();
            boolean flag = judge(str);
            if (flag)
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}
