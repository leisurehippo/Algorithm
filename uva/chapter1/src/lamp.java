import java.util.Scanner;

/**
 * Created by wj on 16/10/13.
 */
public class lamp {
    public static void main(String[] args) {
        int a = 100;//灯数
        int b = 100;//人数
        boolean[] tmp = new boolean[1000];
        for (int i = 1; i <= b; i++) {
            for (int j = 1; j <= a; j++) {
                if (j % i == 0){
                    tmp[j] = !tmp[j];
                    //tmp[j] += i%2==0?-1:1;
                }
            }
        }
        for (int i = 1; i <= a; i++) {
            if (tmp[i]){
                System.out.println(i);
            }
        }
    }
}
