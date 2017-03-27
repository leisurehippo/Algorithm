import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class IdealPath {
    //store the path between two rooms, if there is a path between room i and j, then graph[i][j] = 1
    public static int [][]graph;
    //store the color between two rooms
    public static int [][]color;
    //store the shortest path to the end room for each room
    public static int []distance;
    //store the ideal color sequence for each room
    public static String []color_seq;
    //use for bfs
    public static Queue<Integer> queue = new LinkedBlockingQueue<Integer>();

    /**
     * Initialize parameters
     * @param n
     */
    public static void init(int n){
        graph = new int[n][n];
        color = new int[n][n];
        distance = new int[n];
        color_seq = new String[n];
        queue.clear();
    }

    /**
     * use bfs to find the ideal path
     * @param n
     */
    public static void bfs(int n){
        //init the end room
        distance[n-1] = 0;
        color_seq[n-1] = "";
        queue.add(n-1);
        //loop until the queue is empty
        while(!queue.isEmpty()){
            int node = queue.poll();
            for (int i = 0; i < n; i++) {
                //there is a path
                if (graph[node][i] == 1){
                    //the room is not visited or is visited by the last floor
                    if (distance[i] == 0 || distance[i] == distance[node] + 1) {
                        distance[i] = distance[node] + 1;  //add the distance
                        String str = color[i][node] + " " + color_seq[node];  //generate the color sequence
                        if (color_seq[i] == null)
                            color_seq[i] = str.trim();
                        //choose the ideal color sequence for the room
                        else
                            color_seq[i] = compare(color_seq[i], str) ? str.trim() : color_seq[i].trim();
                        queue.add(i);
                    }
                }
            }
        }
    }

    /**
     * return true if str2 < str1
     * @param str1
     * @param str2
     * @return
     */
    public static boolean compare(String str1, String str2){
        String []arr2 = str2.trim().split(" ");
        String []arr1 = str1.trim().split(" ");
        //assume the lengths of arr1 and arr2 are same
        for (int i = 0; i < arr1.length; i++) {
            if (arr2[i].compareTo(arr1[i]) < 0)
                return true;
        }
        return  false;
    }

    public static void main(String []args){
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            //input
            int n = input.nextInt();
            int m = input.nextInt();
            init(n);
            for (int i = 0; i < m; i++) {
                int node1 = input.nextInt() - 1;
                int node2 = input.nextInt() - 1;
                //ignore the path to itself
                if (node1 != node2) {
                    graph[node1][node2] = graph[node2][node1] = 1;
                    int c = input.nextInt();
                    //choose the smallest path if it has multi path
                    color[node1][node2] = color[node2][node1] = (color[node1][node2] == 0) ? c : Math.min(c, color[node1][node2]);
                }
            }
            //use bfs to find the ideal path
            bfs(n);
            //output
            //the ideal path is stored in the color_seq[0]
            String res = color_seq[0].trim();
            System.out.println((res.length() + 1) / 2);
            System.out.println(res);
        }
    }
}
