import java.util.Scanner;

/**
 * Created by wj on 16/10/13.
 */
public class hanxin {
    public static void main(String[] args) {

        for (int i = 123; i < 328; i++) {
            int[] tmp = new int[10];
            int flag = 0;
            int b = i*2;
            int c = i*3;
            tmp[i/100] = tmp[i/10%10] = tmp[i%10] = 1;
            tmp[b/100] = tmp[b/10%10] = tmp[b%10] = 1;
            tmp[c/100] = tmp[c/10%10] = tmp[c%10] = 1;
            for (int j = 1; j < 10; j++) {
                if (tmp[j] == 1){
                    flag ++;
                }
            }
            if (flag == 9){
                System.out.println(i+" "+b+" "+c);
            }
        }
    }
}

