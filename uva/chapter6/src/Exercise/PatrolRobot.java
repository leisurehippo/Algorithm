package Exercise;

import java.util.Scanner;

/**
 * Created by wj on 17/4/5.
 */
class Grid{
    public int x, y;
    public int obstacle;
}
public class PatrolRobot {
    public static int [][]graph;
    public static int [][]dis;
    public static int [][]father;
    public static int [][]obstacle;

    public static void init(int m ,int n){
        graph = new int[m][n];
        dis = new int[m][n];
//        father =
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        for (int i = 0; i < cases; i++) {
            int m = input.nextInt();
            int n = input.nextInt();
            int k = input.nextInt();
            for (int j = 0; j < m; j++) {
                int aij = input.nextInt();

            }
        }
    }
}
