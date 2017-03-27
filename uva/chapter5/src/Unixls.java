import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by wj on 16/12/4.
 */
public class Unixls {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()){
            int num = Integer.valueOf(input.nextLine());
            int longest = 0;
            String [] arr = new String[num];
            for (int i = 0; i < num; i++) {
                String str = input.nextLine();
                if (str.length() > longest){
                    longest = str.length();
                }
                arr[i] = str;
            }
            Arrays.sort(arr);
            int column = (60 - longest) / (longest+2) + 1;
            int row = (int)Math.ceil((float)num / column);
            String title = "";
            for (int i = 0; i < 60; i++) {
                title += "-";
            }
            System.out.println(title);
            for (int i = 0; i < row; i++) {
                String output = "";
                for (int j = 0; j < column - 1; j++) {
                    int index = i + row*j;
                    int len = longest + 2;
                    if (index < num){
                        String tmp = String.format("%-"+len+"s",arr[index]);
                        output += tmp;
                    }
                }
                int lindex = i+row*(column-1);
                if (lindex < num){
                    output += String.format("%-"+longest+"s",arr[lindex]);
                }
                System.out.println(output);
            }

        }
    }
}
