import java.util.Scanner;

/**
 * Created by wj on 16/11/2.
 */
public class Square {
    public static int [][] square;
    public static boolean judgeSquare(int x, int y, int n){
        for (int i = x; i < x+n; i++) {
            if (square[i][y] < 2 || square[i][y+n] < 2)
                return false;
        }
        for (int i = y; i < y+n; i++) {
            if (square[x][i] == 2 || square[x][i] == 0 ||
                    square[x+n][i] == 0 || square[x+n][i] == 2)
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = 0;
        while(input.hasNextLine()){
            int dot = Integer.valueOf(input.nextLine());
            int line = Integer.valueOf(input.nextLine());
            square = new int[dot+1][dot+1];
            //init square ,1 for horizontal line,2 for vertical line,3 for all
            for (int i = 0; i < line; i++) {
                String str = input.nextLine();
                String [] tmp = str.split(" ");
                char p = tmp[0].charAt(0);
                int x = Integer.valueOf(tmp[1]);
                int y = Integer.valueOf(tmp[2]);
                if (p == 'H')
                    square[x][y] += 1;
                else
                    square[y][x] += 2;
            }
            int [] size = new int[10];
            //scan all dots
            for (int i = 1; i < dot; i++) {
                for (int j = 1; j < dot; j++) {
                    int min = Math.min(dot-i,dot-j);
                    for (int k = 1; k <= min; k++) {
                        boolean flag = judgeSquare(i,j,k);
                        if (flag)
                            size[k] ++;
                    }
                }
            }
            count++;
            if (count!=1)
                System.out.println("\n**********************************\n");
            System.out.println("Problem #"+count+"\n");
            int num = 0;
            for (int i = 1; i < size.length; i++) {
                if (size[i] != 0){
                    System.out.println(size[i]+" square (s) of size "+i);
                    num++;
                }
            }
            if (num ==0)
                System.out.println("No completed squares can be found.");
            //System.out.print("\n");
        }
    }
}
