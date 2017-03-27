import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by wj on 16/12/6.
 */
public class Alignment {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //record the max length of each word in order
        ArrayList<Integer> len = new ArrayList<>();
        //record the input line
        ArrayList<String> code = new ArrayList<>();
        while (input.hasNextLine()){
            String str = input.nextLine();
            code.add(str);
            StringTokenizer token = new StringTokenizer(str);
            int i = 0;
            while (token.hasMoreTokens()) {
                int length = token.nextToken().length();
                if (i > len.size() - 1) {
                    len.add(length);
                }else{
                    if (length > len.get(i)){
                        len.set(i,length);
                    }
                }
                i++;
            }
        }
        for (int i = 0; i < code.size(); i++) {
            StringTokenizer token = new StringTokenizer(code.get(i));
            String output = "";
            int count = 0;
            while (token.hasMoreTokens()) {
                String word = String.format("%-"+len.get(count)+"s",token.nextToken());
                output += word + " ";
                count++;
            }
            output = output.trim();
            System.out.println(output);
        }
    }
}
