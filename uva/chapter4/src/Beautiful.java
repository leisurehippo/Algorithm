import java.util.Scanner;

/**
 * Created by wj on 16/11/6.
 */
public class Beautiful {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = Integer.valueOf(input.nextLine());
        for (int i = 0; i < count; i++) {
            long number = Long.valueOf(input.nextLine()) - 1;
            int t = (int)Math.floor(java.lang.StrictMath.pow(number,1.0/6));
            boolean flag = false;
            for (int j = 2; j < t; j++) {
                int n = (int)Math.floor(Math.log(number) / Math.log(j));
                if ((1 - Math.pow(j,n)) % (1-j) == 0 && (long)Math.floor((Math.pow(j,n) - 1) / (j-1) * j) == number){
                    System.out.println("Case #"+(i+1)+": "+j);
                    flag = true;
                    break;
                }
            }
            if (!flag){
                for (int j = 6; j > 1; j--) {
                    long a = (long)Math.floor(java.lang.StrictMath.pow(number,1.0/j));
                    if ((1 - Math.pow(a,j)) % (1-a) == 0 && (long)Math.floor((Math.pow(a,j) - 1) / (a-1) * a) == number){
                        System.out.println("Case #"+(i+1)+": "+a);
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    System.out.println("Case #"+(i+1)+": "+number);
                }
            }

        }
    }
}
