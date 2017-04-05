package Example;

/**
 * Created by wj on 17/3/23.
 */
public class TopologicalSort {
    public static int []visit;
    public static int n, t;
    public static int [][]Graph;
    public static int []topo;
    public static boolean dfs(int u){
        visit[u] = -1;
        for (int i = 0; i < n; i++) {
            if (Graph[u][i] == 1){
                if (visit[i] < 0)
                    return false;
                else if (visit[i] == 0 && !dfs(i))
                    return false;
            }
        }
        visit[u] = 1;
        topo[--t] = u;
        return true;
    }
    public static boolean topo(int n){
        for (int i = 0; i < n; i++) {
            if (visit[i] == 0){
                if (!dfs(i))
                    return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {

    }
}
