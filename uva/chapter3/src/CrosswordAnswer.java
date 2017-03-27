import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by wj on 16/10/21.
 */
public class CrosswordAnswer {
    public static int [][] num = new int[10][10];

    public static void output(String [] Str, int len, boolean flag){
        String[] Across = new String[100];
        String[] Down = new String[100];
        for (int i = 0; i < len; i++) {
            StringTokenizer t = new StringTokenizer(Str[i],"*");
            int index = 0;
            while(t.hasMoreTokens()){
                String tmp = t.nextToken();
                int no = Str[i].indexOf(tmp,index);
                index = no+tmp.length();
                if (flag)
                    Across[num[i][no]] = tmp;
                    //System.out.printf("%3d."+tmp+"\n",num[i][no]);
                else
                    Down[num[no][i]] = tmp;
                    //System.out.printf("%3d."+tmp+"\n",num[no][i]);
            }
        }
        if (flag){
            for (int i = 0; i < Across.length; i++) {
                if (Across[i] != null)
                    System.out.printf("%3d."+Across[i]+"\n",i);
            }
        }else{
            for (int i = 0; i < Down.length; i++) {
                if (Down[i] != null)
                    System.out.printf("%3d."+Down[i]+"\n",i);
            }
        }

    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String end = new String();
        int count = 1;  //puzzle count
        while(!(end = input.nextLine()).equals("0")){
            //get the number of rows and columns
            StringTokenizer toke = new StringTokenizer(end);
            int row = Integer.parseInt(toke.nextToken());
            int col = Integer.parseInt(toke.nextToken());
            int number = 1; //puzzle number
            String [] across = new String[row];
            String [] down = new String[col];
            for (int i = 0; i < down.length; i++) {
                down[i] = "";
            }
            //load puzzle
            for (int i = 0; i < row; i++) {
                String word = input.nextLine();
                across[i] = word;
                int index = 0;
                //calculator num[][]
                for (int j = 0; j < col; j++) {
                    if (word.charAt(j) != '*' &&
                            (i == 0 || j == 0 || across[i-1].charAt(j) == '*' || across[i].charAt(j-1) == '*')){
                        num[i][j] = number++;
                    }
                    down[j] += word.substring(index,index+1);
                    index++;
                }
            }
            if (count != 1)
                System.out.print("\n");
            System.out.println("puzzle #"+count+":");
            count++;
            System.out.println("Across");
            output(across,row,true);
            System.out.println("Down");
            output(down,col,false);

        }

    }
}
