package Exercise;

import java.util.*;

/**
 * Created by wj on 17/4/10.
 */
class Region{
    int id;
    Region []children;

    public Region() {
        this.id = 0;
        this.children = new Region[4];
    }
}
public class SpatialStructures {
    public static int [][]graph;
    public static Region root;
    public static ArrayList<Integer> arrayList = new ArrayList<>();


    /**
     * jusge the subquadrant is consist entirely of black pixels(1)
     * or entirely of white pixels (-1) or neither(0)
     * @param i
     * @param j
     * @param len
     * @return
     */
    public static int judge(int i, int j, int len){
        int black_count = 0, white_count = 0;
        int sum = len * len;
        for (int k = i; k < i + len; k++) {
            for (int l = j; l < j + len; l++) {
                if (graph[k][l] == 1)
                    black_count++;
                else
                    white_count++;
            }
        }

        if (black_count == sum)
            return black_count;
        else if (white_count == sum)
            return -white_count;
        else
            return 0;
    }
    public static void quadtree(int i, int j, int len, Region region){
        for (int k = 0; k < 4; k++) {
            int i_temp = (k < 2) ? i : i + len / 2;
            int j_temp = (k % 2 == 0) ? j : j + len / 2;
            int len_temp = len / 2;
            int pixels = judge(i_temp, j_temp, len_temp);
            region.children[k] = new Region();
            int l = String.valueOf(region.id).length();
            if (region.id == 0)
                region.children[k].id = k + 1;
            else
                region.children[k].id = (k + 1) * (int)Math.pow(10,l) + region.id;
            if (pixels > 0){
                int base5 = region.children[k].id;
                int base10 = Integer.parseInt(String.valueOf(base5),5);
                arrayList.add(base10);
            } else if (pixels == 0){
                quadtree(i_temp, j_temp, len_temp, region.children[k]);
            }
        }

    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;
        int cases = 1;
        while ((n = input.nextInt()) != 0){
            if (cases != 1)
                System.out.println();
            System.out.println("Image " + cases++);
            if (n > 0){
                graph = new int[n][n];
                root = new Region();
                arrayList.clear();
                int black_count = 0;

                //read the graph, and record the number of black pixel
                for (int i = 0; i < n; i++) {
                    String str = input.next();
                    for (int j = 0; j < n; j++) {
                        if (str.charAt(j) == '1'){
                            black_count++;
                            graph[i][j] = 1;
                        }
                    }
                }
                if (black_count == 0){
                    System.out.println("Total number of black nodes = 0");
                }else if (black_count == n * n) {
                    System.out.println("0");
                    System.out.println("Total number of black nodes = 1");
                }else {
                    quadtree(0,0,n,root);
                    Collections.sort(arrayList);  //sort the arrayList
                    //print the black nodes
                    int count = 0;
                    for (int i = 0; i < arrayList.size(); i++, count++) {
                        if (i != 0 && count != 12)
                            System.out.print(" ");
                        if (count == 12){
                            count = 0;
                            System.out.println();
                        }
                        System.out.print(arrayList.get(i));
                    }
                    System.out.println();
                    System.out.println("Total number of black nodes = "+arrayList.size());
                }

            }else{
                graph = new int[-n][-n];
                int number;
                boolean flag = false;
                while((number = input.nextInt()) != -1){
                    if (number == 0){
                        flag = true;
                        for (int i = 0; i < -n; i++) {
                            for (int j = 0; j < -n; j++) {
                                System.out.print("*");
                            }
                            System.out.println();
                        }

                    }else{
                        String base5 = Integer.toString(number,5);
                        //set 1 to the graph according to the black node
                        int i = 0, j = 0, len = -n;
                        for (int k = base5.length() - 1; k >= 0; k--) {
                            int num = base5.charAt(k) - '0';
                            i = (num < 3) ? i : i + len / 2;
                            j = (num % 2 == 1) ? j : j + len / 2;
                            len /= 2;
                        }
                        for (int k = i; k < i + len; k++) {
                            for (int l = j; l < j + len; l++) {
                                graph[k][l] = 1;
                            }
                        }

                    }

                }
                //print the graph
                if (!flag){
                    for (int i = 0; i < -n; i++) {
                        for (int j = 0; j < -n; j++) {
                            if (graph[i][j] == 0)
                                System.out.print(".");
                            else
                                System.out.print("*");
                        }
                        System.out.println();
                    }
                }

            }


        }
    }
}
