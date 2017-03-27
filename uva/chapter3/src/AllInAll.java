import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Created by wj on 16/10/22.
 */
public class AllInAll {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String in = new String();
        while(input.hasNextLine()){
            in = input.nextLine();
            StringTokenizer token = new StringTokenizer(in);
            String s = token.nextToken();
            String t = token.nextToken();
            int index = -1;
            boolean flag = false;
            if (s.length()>t.length()){
                flag = true;
            }
            for (int i = 0; i < s.length(); i++) {
                char tmp = s.charAt(i);
                index = t.indexOf(tmp,index+1);
                if (index == -1){
                    flag = true;
                    break;
                }
            }
            if (flag)
                System.out.println("No");
            else
                System.out.println("Yes");
        }

    }
}
