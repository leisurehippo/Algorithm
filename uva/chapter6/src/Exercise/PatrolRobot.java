package Exercise;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wj on 17/4/5.
 */
class Grid{
    public int x, y;
    public int obstacle;

    public Grid(int x, int y, int obstacle) {
        this.x = x;
        this.y = y;
        this.obstacle = obstacle;
    }

    public Grid(int x, int y) {
        this.x = x;
        this.y = y;
        this.obstacle = 0;
    }
}
public class PatrolRobot {
    public static int m, n, k;
    public static int [][]graph;
    public static int [][]dis;
    public static int [][]obstacle;
    public static boolean [][]flag;
    public static int []move_x = {0, 1, 0, -1};
    public static int []move_y = {1, 0, -1, 0};
    public static Queue<Grid> queue = new LinkedBlockingQueue<>();

    public static void init(int m ,int n){
        graph = new int[m][n];
        dis = new int[m][n];
        obstacle = new int[m][n];
        flag = new boolean[m][n];
        queue.clear();
    }

    public static Grid move(Grid grid, int i, int obstacle){
        int next_x = grid.x + move_x[i];
        int next_y = grid.y + move_y[i];
        Grid res = new Grid(next_x, next_y);

        if (is_inside(res) && graph[next_x][next_y] == 1)
            res.obstacle = obstacle + 1;

        return res;
    }

    public static boolean is_inside(Grid grid){
        return grid.x >= 0 && grid.x < m && grid.y >= 0 && grid.y < n;
    }

    public static void bfs(){
        dis[0][0] = 1;
        obstacle[0][0] = 0;
        Grid start = new Grid(0,0,0);
        queue.add(start);
        while (!queue.isEmpty()){
            Grid grid = queue.poll();
            for (int i = 0; i < 4; i++) {
                Grid next = move(grid,i,grid.obstacle);
                //TODO: select those grids which have less obstacle
                if (is_inside(next) && next.obstacle <= k && (dis[next.x][next.y] == 0 || dis[next.x][next.y] == dis[grid.x][grid.y] + 1)){

                    if (!flag[next.x][next.y])
                        obstacle[next.x][next.y] = next.obstacle;
                    else{
                        if (next.obstacle >= obstacle[next.x][next.y])
                            next.obstacle = obstacle[next.x][next.y];
                        else
                            obstacle[next.x][next.y] = next.obstacle;
                    }

                    dis[next.x][next.y] = dis[grid.x][grid.y] + 1;
                    queue.add(next);
                }
            }
        }

    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        for (int i = 0; i < cases; i++) {

            m = input.nextInt();
            n = input.nextInt();
            k = input.nextInt();
            init(m, n);
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < n; l++) {
                    int aij = input.nextInt();
                    graph[j][l] = aij;
                }
            }
//            if (k > Math.max(n,m)){
//                System.out.println(n + m - 2);
//            }eles{
//                bfs();
//                System.out.println(dis[m-1][n-1] - 1);
//            }
            if (i == 298){
                bfs();
                for (int j = 0; j < m; j++) {
                    for (int l = 0; l < n; l++) {
                        System.out.print(dis[j][l]+" ");
                    }
                    System.out.println();
                }
                System.out.println();
                for (int j = 0; j < m; j++) {
                    for (int l = 0; l < n; l++) {
                        System.out.print(obstacle[j][l]+" ");
                    }
                    System.out.println();
                }
            }


        }
    }
}
