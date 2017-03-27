import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wj on 17/3/17.
 */
public class OrderingTasks {
    //0:not visited, 1:visited, -1:visiting
    public static int []cond;
    public static Stack<Integer> stack = new Stack<>();
    public static int [][]graph;
    public static int n, m;
    public static boolean dfs(int u){
        cond[u] = -1;
        for (int i = 0; i < n; i++) {
            if (graph[u][i] == 1){
                if (cond[i] == -1)
                    return false;
                else if (cond[i] == 0 && !dfs(i))
                    return false;
            }
        }
        cond[u] = 1;
        stack.push(u);
        return true;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while ((n = input.nextInt()) != 0 && (m = input.nextInt())!= 0){
            //graph[a][b] = 1 means a < b
            graph = new int[n][n];
            stack.clear();
            cond = new int[100];
            for (int i = 0; i < m; i++)
                graph[input.nextInt() - 1][input.nextInt() - 1] = 1;

            //search every item
            for (int i = 0; i < n; i++) {
                if (cond[i] == 0){
                    dfs(i);
                }
            }

            String res = "";
            int count = 0;
            //pop the stack
            while(!stack.isEmpty()){
                if (count != 0)
                    res += " ";
                count ++;
                res += (stack.pop()+1);
            }
            System.out.println(res);

        }

    }
}
