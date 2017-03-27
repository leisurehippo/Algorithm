import java.util.Scanner;

/**
 * 求解一个环状序列的最小字典序
 * UVA1584
 * Created by wj on 16/10/18.
 */
public class CircularSequence {

    /**
     * 比较两个字符串大小,若str1<str2的字典序返回true
     * @param str1
     * @param str2
     * @return
     */
    public static boolean compare(String str1, String str2){

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(i))
                continue;
            if (str1.charAt(i) < str2.charAt(i))
                return true;
            else
                return false;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String len = input.nextLine();
        //String len = "1";
        for (int i = 0; i < Integer.valueOf(len); i++) {
            String str = input.nextLine();
            //String str = "GAGTT";
            String res = str;
            for (int j = 0; j < str.length(); j++) {
                String tmp = str.substring(j).concat(str.substring(0,j));
                if (compare(tmp,res))
                    res = tmp;
            }
            System.out.println(res);
        }

    }
}
