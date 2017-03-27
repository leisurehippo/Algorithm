import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wj on 17/3/15.
 */
class Intersection{
    int row, column;
    int dir;

    public Intersection(int row, int column, int dir) {
        this.row = row;
        this.column = column;
        this.dir = dir;
    }

    public String position(){
        return "(" + (this.row+1) + "," + (this.column+1) + ")";
    }
}
public class AbbottRevenge {
    public static String dirs = "NESW";
    public static String turns = "FLR";
    public static int []dr = {-1,0,1,0};
    public static int []dc = {0,1,0,-1};

    /**
     * get the next Intersection for a certain direction
     * @param intersection
     * @param turn
     * @return
     */
    public static Intersection walk(Intersection intersection, int turn){
        int dir = intersection.dir;
        //turn left
        if (turn == 1)
            dir = (dir + 3) % 4;
        //turn right
        if (turn == 2)
            dir = (dir + 1) % 4;
        Intersection res = new Intersection(intersection.row + dr[dir], intersection.column + dc[dir], dir);
        return res;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = new String();
        while(!(str = input.nextLine()).equals("END")){
            String name = str;  //the maze name
            //the certain direction is or not valid in the specified location and direction
            int [][][][]is_valid = new int[9][9][4][3];
            //the minimize length of path from the start node of a node
            int [][][]pathLength = new int[9][9][4];
            //the father node of the node in the BFS tree
            Intersection [][][]fatherNode = new Intersection[9][9][4];
            //intersection queue
            Queue<Intersection> intersection_queue = new LinkedBlockingQueue<>();

            //read the start and end node
            String inf = input.nextLine();
            char []temp = inf.toCharArray();
            int start_row = temp[0] - 49, start_column = temp[2] - 49;
            int start_dir = dirs.indexOf(temp[4]);
            int end_row = temp[6] - 49, end_column = temp[8] - 49;

            //initialization
            pathLength[start_row][start_column][start_dir] = 0;
            Intersection start = new Intersection(start_row,start_column,start_dir);
            Intersection first_step = walk(start,0);
            intersection_queue.add(first_step);
            pathLength[first_step.row][first_step.column][first_step.dir] = 1;
            fatherNode[first_step.row][first_step.column][first_step.dir] = start;

            //read the maze, update is_valid
            String maze = new String();
            while(!(maze = input.nextLine()).equals("0")){
                //read the intersection information
                StringTokenizer token = new StringTokenizer(maze);
                int row = Integer.valueOf(token.nextToken()) - 1;
                int column = Integer.valueOf(token.nextToken()) - 1;
                String s = new String();
                while(!(s=token.nextToken()).equals("*")){
                    char []m = s.toCharArray();
                    int d = dirs.indexOf(m[0]);
                    for (int i = 1; i < m.length; i++) {
                        int t = turns.indexOf(m[i]);
                        is_valid[row][column][d][t] = 1;
                    }
                }
            }

            boolean flag = false;
            //find the way out of the maze
            while(!intersection_queue.isEmpty()){
                Intersection node = intersection_queue.peek();
                intersection_queue.poll();
                //get out
                if (node.row == end_row && node.column == end_column){
                    System.out.println(name);
                    String res = "  ";
                    //store the path into a stack from the goal
                    Stack<Intersection> stack = new Stack<>();
                    stack.push(node);
                    while(true){
                        //if meet the 'first_step', then break
                        //why not the 'start'?
                        //because the 'start' can be reached sometimes, and the pathLength will no longer be 0
                        if (pathLength[node.row][node.column][node.dir] == 1)
                            break;
                        Intersection node_before = fatherNode[node.row][node.column][node.dir];
                        stack.push(node_before);
                        node = node_before;
                    }
                    stack.push(start);
                    int count = 0;
                    //format the output
                    while(!stack.isEmpty()){
                        Intersection intersection = stack.pop();
                        if (count != 0)
                            res += " ";
                        res += intersection.position();
                        count++;
                        if (count == 10 && (!stack.isEmpty())){
                            count = 0;
                            res += "\n  ";
                        }
                    }
                    System.out.println(res);
                    flag = true;
                    break;
                }
                //search the maze
                //attempt every turn
                for (int i = 0; i < 3; i++) {
                    Intersection next = walk(node,i);
                    if (is_valid[node.row][node.column][node.dir][i] == 1
                            && next.row < 9 && next.row >= 0
                            && next.column < 9 && next.column >= 0
                            && pathLength[next.row][next.column][next.dir] == 0){
                        pathLength[next.row][next.column][next.dir] = pathLength[node.row][node.column][node.dir] + 1;
                        fatherNode[next.row][next.column][next.dir] = node;
                        intersection_queue.add(next);
                    }

                }
            }
            if (!flag)
                System.out.println(name+"\n  No Solution Possible");

        }

    }
}

