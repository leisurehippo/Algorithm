import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wj on 16/10/18.
 */
public class PeriodicStrings {
    /**
     * 判断str是否只由per组成
     * @param per
     * @param str
     * @return
     */
    public static boolean judge(String per, String str){
        if (str.length() % per.length() != 0)
            return false;
        else{
            String tmp = "";
            for (int i = 0; i < str.length() / per.length(); i++)
                tmp += per;

            if (tmp.equals(str))
                return true;
            else
                return false;
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String len = input.nextLine();
        for (int i = 0; i < Integer.valueOf(len); i++){
            String tmp = input.nextLine();
            String str = input.nextLine();
//            int length = 1;
//            boolean flag = false;
//            while(!str.substring(length).equals("")){
//                if (judge(str.substring(0,length),str.substring(length))){
//                    flag = true;
//                    break;
//                }
//                else
//                    length++;
//            }
            char end = str.charAt(str.length()-1);
            Pattern p = Pattern.compile("["+end+"]");
            Matcher m = p.matcher(str);
            int res = 0;
            while(m.find()){
                int index = m.start();
                String sub = str.substring(0,index+1);
                if (judge(sub,str)){
                    res = index + 1;
                    break;
                }
            }
            System.out.println(res);
            if (i != Integer.valueOf(len)-1)
                System.out.print("\n");
        }
    }
}
