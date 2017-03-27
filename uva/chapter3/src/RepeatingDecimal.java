import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by wj on 16/10/21.
 */
public class RepeatingDecimal {
    /**
     * judge whether the number is
     * pure circulating decimal(-1)
     * or mixed recurring decimal(the length of the non-repeating part)
     * or non-repeating decimal(0)
     * @param num
     * @return
     */
    public static int prime(int num){
        //pure circulating decimal
        if (num%2 != 0 && num%5 != 0)
            return 0;
        else{
            int prime2 = 0, prime5 = 0;
            while(num != 1){
                if (num%2 == 0){
                    prime2++;
                    num /= 2;
                }
                else if (num%5 == 0){
                    prime5++;
                    num /= 5;
                }
                else
                    return Math.max(prime2,prime5);//mixed recurring decimal
            }
            return -1;//non-repeating decimal
        }
    }
    public static int divisor(int m,int n) {
        if (m % n == 0) {
            return n;
        } else {
            return divisor(n,m % n);
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String in = new String();
        while(input.hasNextLine()){
            in = input.nextLine();
            StringTokenizer token = new StringTokenizer(in);
            int numerator = Integer.valueOf(token.nextToken());
            int denominator = Integer.valueOf(token.nextToken());
            if (numerator == 0){
                System.out.println(numerator+"/"+denominator+" = 0.(0)");
                System.out.println("   1 = number of digits in repeating cycle\n");
            }else{
                int simple = denominator / divisor(denominator,numerator);
                int prime = prime(simple);
                if (prime == -1 || simple == 1){
                    double res = (double)numerator/(double)denominator;
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(50); // 设置最大小数位,由于最大3000,5位小数就够了
                    String result = df.format(res);
                    String dot = (numerator%denominator==0)? "." : "";
                    System.out.println(numerator+"/"+denominator+" = "+result+dot+"(0)");
                    System.out.println("   1 = number of digits in repeating cycle\n");
                }else{
                    int number = numerator / denominator;
                    int tmp_numerator = numerator - number*denominator;
                    int remainder  = 0;
                    int divisor = tmp_numerator*10;
                    int quotient = 0;
                    int standard = tmp_numerator;
                    String cycle = "";
                    String noncycle = "";

                    //non-repeating part
                    for (int i = 0; i < prime; i++) {
                        quotient = divisor / denominator;
                        noncycle += quotient;
                        standard = divisor % denominator;
                        divisor = standard * 10;
                    }
                    //repeating part
                    while (remainder != standard){
                        quotient = divisor / denominator;
                        cycle += quotient;
                        remainder = divisor % denominator;
                        divisor = remainder * 10;
                    }
                    if (cycle.length()>50)
                        System.out.println(numerator+"/"+denominator+" = "+number+"."+noncycle+"("+cycle.substring(0,50)+"...)");
                    else
                        System.out.println(numerator+"/"+denominator+" = "+number+"."+noncycle+"("+cycle+")");
                    System.out.println("   "+cycle.length()+" = number of digits in repeating cycle\n");

                }
            }
        }
    }
}
