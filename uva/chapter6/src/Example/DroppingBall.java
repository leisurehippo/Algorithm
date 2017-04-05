package Example;

import java.util.Scanner;

/**
 * Created by wj on 17/2/21.
 */
public class DroppingBall {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        for (int i = 0; i < num; i++) {
            int depth = input.nextInt();
            int ball = input.nextInt();
            int number = 1;
            for (int j = 0; j < depth - 1; j++) {
                if (ball % 2 == 0){
                    number = number*2 + 1;
                    ball = ball / 2;
                }else{
                    number = number * 2;
                    ball = (ball + 1) / 2;
                }
            }
            System.out.println(number);
        }
    }
}
