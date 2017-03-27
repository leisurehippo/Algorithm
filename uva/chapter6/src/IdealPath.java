import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class IdealPath {
    public static int [][]graph;
    public static int [][]color;
    public static int []distance;
    public static String []color_seq;
    public static Map<Integer,ArrayList<Integer>> map = new HashMap<>();
    public static Queue<Integer> queue = new LinkedBlockingQueue<Integer>();
    public static void init(int n){
        graph = new int[n][n];
        color = new int[n][n];
        distance = new int[n];
        color_seq = new String[n];
        map.clear();
        queue.clear();
    }

    public static void bfs(int n){
        distance[n-1] = 0;
        color_seq[n-1] = "";
        queue.add(n-1);
        while(!queue.isEmpty()){
            int node = queue.poll();
            for (int i = 0; i < n-1; i++) {
                if (graph[node][i] == 1){
                    if (distance[i] == 0 || distance[i] == distance[node] + 1) {
                        distance[i] = distance[node] + 1;
                        String str = color[i][node] + " " + color_seq[node];
                        if (color_seq[i] == null)
                            color_seq[i] = str.trim();
                        else
                            color_seq[i] = compare(color_seq[i], str) ? str.trim() : color_seq[i].trim();
                        if (!queue.contains(i))
                            queue.add(i);
//                        ArrayList<Integer> arrayList = new ArrayList<>();
//                        if (map.containsKey(distance[i]))
//                            arrayList = map.get(distance[i]);
//                        arrayList.add(i);
//                        map.put(distance[i],arrayList);
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
//        int n = Math.min(arr1.length,arr2.length);
        for (int i = 0; i < arr1.length; i++) {
            if (arr2[i].compareTo(arr1[i]) < 0)
                return true;
        }
        return  false;
    }

    public static String path(){
        int dis = distance[0];
        int node = 0;
        String res = "";
        while(dis != 0){
            dis --;
            ArrayList<Integer> arrayList = map.get(dis);
            String str = color_seq[arrayList.get(0)];
            int next = arrayList.get(0);
            for (int i = 1; i < arrayList.size(); i++) {
                String temp = color_seq[arrayList.get(i)];
                if (compare(str,temp)){
                    str = temp;
                    next = arrayList.get(i);
                }
            }
            res += " " + color[node][next];
            node = next;
        }
        return res.substring(1);
    }

    public static void main(String []args){
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            int n = input.nextInt();
            int m = input.nextInt();
            init(n);
            for (int i = 0; i < m; i++) {
                int node1 = input.nextInt() - 1;
                int node2 = input.nextInt() - 1;
                if (node1 != node2){
                    graph[node1][node2] = graph[node2][node1] = 1;
                    int c = input.nextInt();
                    color[node1][node2] = color[node2][node1] = (color[node1][node2] == 0) ? c : Math.min(c,color[node1][node2]);
                }
            }
            bfs(n);
//            String res = path();
            String res = color_seq[0].trim();
            System.out.println((res.length() + 1) / 2);
            System.out.println(res);
        }
    }
}

