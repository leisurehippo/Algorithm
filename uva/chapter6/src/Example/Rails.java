package Example;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by wj on 17/2/17.
 */
public class Rails {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String num = new String();
        Stack<Integer> stack = new Stack<>();
        while (!(num=input.nextLine()).equals("0")){
            String str = new String();

            while (!(str=input.nextLine()).equals("0")){
                StringTokenizer token = new StringTokenizer(str);
                int n = 1;
                boolean flag = true;
                stack.clear();
                while (token.hasMoreTokens()){
                    int train = Integer.valueOf(token.nextToken());

                    if (train == n){
                        n++;
                    }else if (train > n){
                        for (int i = n; i < train; i++) {
                            stack.push(i);
                        }
                        n = train+1;
                    }else {
                        int s = stack.pop();
                        if (train != s){
                            flag = false;
                            break;
                        }

                    }
                }

                if (flag)
                    System.out.println("Yes");
                else
                    System.out.println("No");

            }
            System.out.print("\n");
        }
    }
}
