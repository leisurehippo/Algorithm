package Example; /**
 * Created by wj on 17/2/20.
 */

import java.util.Scanner;

/**
 * Time Limit Exceeded
 * 怀疑是输入读取有问题,测试debug时一直不结束,结果是对的
 */
public class BeijuText {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = new String();
        while(!(str = input.nextLine()).equals("")){
//            String str = input.nextLine();
            int []next = new int[100000];
            next[0] = 0;
            int cur = 0, last = 0;
            for (int i = 1; i <= str.length(); i++) {
                char temp = str.charAt(i-1);
                if (temp == '['){
                    cur = 0;
                }else if (temp == ']'){
                    cur = last;
                }else{
                    next[i] = next[cur];
                    next[cur] = i;
                    if (cur == last)
                        last = i;
                    cur = i;
                }
            }
            for (int i = next[0]; i != 0; i = next[i]) {
                System.out.print(str.charAt(i-1));
            }
            System.out.print("\n");
        }
    }
}
