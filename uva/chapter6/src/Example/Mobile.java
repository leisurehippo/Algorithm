package Example;

import java.util.Scanner;

/**
 * Created by wj on 17/2/23.
 */
class Balance{
    public int W1, D1, W2, D2;
    public Balance left, right;

}
public class Mobile {
    public static Scanner input = new Scanner(System.in);
    public static boolean flag;
    public static int judge(Balance balance){
//        Balance balance = root;

        balance.W1 = input.nextInt();
        balance.D1 = input.nextInt();
        balance.W2 = input.nextInt();
        balance.D2 = input.nextInt();

        if (balance.W1 == 0){
            balance.left = new Balance();
            balance.W1 = judge(balance.left);
        }
        if (balance.W2 == 0){
            balance.right = new Balance();
            balance.W2 = judge(balance.right);
        }
        if (balance.W1 != 0 && balance.W2 != 0){
            if (balance.W1 * balance.D1 != balance.W2 * balance.D2)
                flag = false;
        }
        return balance.W1+balance.W2;

    }
    public static void main(String[] args) {
        int num = input.nextInt();
        for (int i = 0; i < num; i++) {
            Balance root = new Balance();
            flag = true;
            judge(root);
            if (flag)
                System.out.println("YES\n");
            else
                System.out.println("NO\n");
        }
    }
}
