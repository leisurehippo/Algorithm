package Exercise;

import java.util.ArrayList;
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
    //store the distance from the start(0,0) with certain obstacle
    public static int [][][]dis;
    public static int []move_x = {0, 1, 0, -1};
    public static int []move_y = {1, 0, -1, 0};
    public static Queue<Grid> queue = new LinkedBlockingQueue<>();

    public static void init(){
        graph = new int[m][n];
        dis = new int[m][n][k+1];
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
        dis[0][0][0] = 1;
        Grid start = new Grid(0,0,0);
        queue.add(start);
        while (!queue.isEmpty()){
            Grid grid = queue.poll();
            for (int i = 0; i < 4; i++) {
                Grid next = move(grid,i,grid.obstacle);
                if (is_inside(next) && next.obstacle <= k && dis[next.x][next.y][next.obstacle] == 0){
                    dis[next.x][next.y][next.obstacle] = dis[grid.x][grid.y][grid.obstacle] + 1;
                    queue.add(next);
                }
            }
        }

    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        for (int i = 0; i < cases; i++) {
            //read input
            m = input.nextInt();
            n = input.nextInt();
            k = input.nextInt();
            init();
            //construct the graph
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < n; l++) {
                    int aij = input.nextInt();
                    graph[j][l] = aij;
                }
            }
            //if k is large, do not use bfs
            if (k > Math.max(n,m)){
                System.out.println(n + m - 2);
            }else{
                bfs();
                //find the min dis in dis[m][n][1~k]
                ArrayList<Integer> array = new ArrayList<>();
                for (int j = 0; j <= k; j++) {
                    int d = dis[m-1][n-1][j];
                    if (d != 0)
                        array.add(d);
                }
                if (array.size() == 0)
                    System.out.println("-1");
                else{
                    int min = array.get(0);
                    for (int j = 1; j < array.size(); j++) {
                        int d = array.get(j);
                        if (d < min)
                            min = d;
                    }
                    System.out.println(min - 1);
                }
            }

        }
    }
}
