import java.util.Scanner;
import java.util.regex.Pattern;

/**结束时怎么读取
 * Created by wj on 16/10/31.
 */
public class Decoding {
    public static char [][] code = new char[8][1<<8];
    public static Scanner input = new Scanner(System.in);
    public static String str = "";
    public static boolean readcode(){
        //Scanner input = new Scanner(System.in);
        String header =  input.nextLine();
        //这里
        if (header == "")
            return false;
        else{
            int i = 0;
            code[1][0] = header.charAt(i++);
            for (int j = 2; j < 8; j++) {
                for (int k = 0; k < (1 << j)-1; k++) {
                    if (i == header.length())
                        return true;
                    char c = header.charAt(i++);
                    code[j][k] = c;
                }
            }
            return true;
        }
    }
    public static int readint(int len, int index){
        return Integer.valueOf(str.substring(index,len+index),2);
    }
    public static void main(String[] args) {
        while(readcode()){
            Pattern p = Pattern.compile("[0-1]+");
            str = "";
            String s = "";
            //这里
            while(input.hasNext(p)){
                s=input.nextLine();
                str += s;
            }
            str += s;
            int len = 0;
            int index = 0;
            while((len=readint(3,index)) != 0){
                index += 3;
                int key = 0;
                while((key=readint(len,index)) != (1<<len) - 1){
                    index += len;
                    System.out.print(code[len][key]);
                }
                index += len;
            }
            System.out.print("\n");
        }
    }
}
