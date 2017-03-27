import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wj on 16/10/23.
 */
public class KickDown {
    /**
     * generate Regular Expressions of str
     * 2 to match 1, and 1 can match 1 or 2
     * @param str
     * @return
     */
    public static String pattern(String str){
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            if (tmp == '2')
                res += "1";
            else
                res += "[1-2]";
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()){
            String master = input.nextLine();
            String driven = input.nextLine();
            if (driven.length() < master.length()){
                //swap driven and master
                String tmp = master;
                master = driven;
                driven = tmp;
            }
            Pattern pattern = Pattern.compile(pattern(master));
            Matcher matcher = pattern.matcher(driven);
            if (matcher.find()){
                System.out.println(driven.length());
            }else{
                int index = master.length();
                for (int i = 1; i < master.length(); i++) {
                    //move the master to the left or the right a byte length
                    //and judge whether it can match to the driven
                    String right = master.substring(0,master.length()-i);
                    pattern = Pattern.compile(pattern(right)+"$");
                    Matcher matcher1 = pattern.matcher(driven);
                    String left = master.substring(i);
                    pattern = Pattern.compile("^"+pattern(left));
                    Matcher matcher2 = pattern.matcher(driven);
                    if (matcher1.find() || matcher2.find()){
                        index = i;
                        break;
                    }
                }
                int res = index + driven.length();
                System.out.println(res);
            }


        }

    }
}
