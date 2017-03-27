import java.util.Scanner;

/**
 * 数数字
 * UVA1225
 * Created by wj on 16/10/18.
 */
public class DigitCounting {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String len = input.nextLine();
        for (int i = 0; i < Integer.valueOf(len); i++) {
            String str = input.nextLine();
            int [] res = new int[10];
            for (int j = 1; j <= Integer.valueOf(str); j++) {
                int num = j;
                do {
                    res[num%10]++;
                    num /= 10;
                }while(num != 0);
            }
            String result = res[0]+"";
            for (int j = 1; j < 10; j++) {
                result += " " + res[j];
            }
            System.out.println(result);
        }
    }
}
