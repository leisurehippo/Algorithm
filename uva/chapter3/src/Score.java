import java.util.Scanner;

/**
 * Created by wj on 16/10/18.
 */
public class Score {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String len = input.nextLine();
        for (int i = 0; i < Integer.valueOf(len); i++) {
            String str = input.nextLine();
            char [] tmp = str.toCharArray();
            int score = 0;
            int count = 0;
            for (int j = 0; j < str.length(); j++) {
                if (tmp[j] == 'O'){
                    count++;
                }else{
                    count = 0;
                }
                score += count;
            }
            System.out.println(score);
        }

    }
}
