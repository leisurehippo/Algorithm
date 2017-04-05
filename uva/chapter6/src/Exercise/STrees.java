package Exercise;

import java.util.Scanner;

/**
 * Created by H-E-Z on 2017/3/26.
 */
public class STrees {
    public static char []arr;

    /**
     * order each query into right order(variable order)
     * @param str
     * @param variable
     * @return
     */
    public static String order(String str, String variable){
        String []var = variable.split(" ");
        for (int i = 0; i < var.length; i++) {
            int num = Integer.valueOf(var[i].substring(1)) - 1;
            arr[i] = str.charAt(num);
        }
        return String.valueOf(arr);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = new String();
        int count = 1;
        while(!(str = input.nextLine()).equals("0")){
            System.out.println("S-Tree #" + count + ":");
            count++;
            int n = Integer.valueOf(str);
            arr = new char[n];
            String variable = input.nextLine();
            String leave = input.nextLine();
            int m = Integer.valueOf(input.nextLine());
            String res = "";
            for (int i = 0; i < m; i++) {
                String query = input.nextLine();
                int index = Integer.valueOf(order(query,variable),2);
                res += leave.charAt(index);
            }
            System.out.println(res+"\n");
        }
    }
}
