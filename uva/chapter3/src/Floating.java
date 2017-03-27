import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by wj on 16/10/25.
 */
public class Floating {
    public static void main(String[] args) {
        int [][] num = new int[10][30];
        double [][] decimal = new double[10][30];
        for (int i = 0; i < 10; i++) {
            for (int j = 1; j <= 30; j++) {
                double a = 1-Math.pow(2,-i-1);
                double b = Math.pow(2,j) - 1;
                double sum = Math.log10(a) + b*Math.log10(2.0);
                num[i][j-1] = (int)Math.floor(sum);
                decimal[i][j-1] = Math.pow(10.0,sum - num[i][j-1]);
            }
        }
        Scanner input = new Scanner(System.in);
        String in = new String();
        while(!(in = input.nextLine()).equals("0e0")){
            StringTokenizer token = new StringTokenizer(in,"e");
            double a = Double.valueOf(token.nextToken());
            int b = Integer.valueOf(token.nextToken());
            int m = 0, e = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 1; j <= 30; j++) {
                    if (num[i][j-1] == b && Math.abs(a-decimal[i][j-1])<0.00001){
                        m = i;
                        e = j;
                        break;
                    }
                }
            }
            System.out.println(m+" "+e);
        }
    }
}
