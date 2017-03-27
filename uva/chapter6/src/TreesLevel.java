import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wj on 17/2/21.
 */
class Node{
    public int data;
    public boolean valued;
    public Node lTree;
    public Node rTree;
    public int count;
}

public class TreesLevel {
    public static Node root;     //Binary Tree
    public static boolean flag;  //judge whether is completed

    /**
     * add node into the binary tree - root
     * @param data
     * @param step
     */
    public static void addNode(int data, String step){
        Node temp = root;
        for (int i = 0; i < step.length()-1; i++) {
            char s = step.charAt(i);
            if (s == 'L'){
                if (temp.lTree == null)
                    temp.lTree = new Node();
                temp = temp.lTree;
            }else if (s == 'R'){
                if (temp.rTree == null)
                    temp.rTree = new Node();
                temp = temp.rTree;
            }
        }
        if (temp.valued)
            flag = false;
        temp.data = data;
        temp.valued = true;
    }

    /**
     * use bfs to get level-order traversal of a tree
     * @return order
     */
    public static String bfs(){
        Queue<Node> queue = new LinkedBlockingQueue<>();
        String res = "";
        queue.add(root);
        while (!queue.isEmpty()){
            Node n = queue.poll();
            if (!n.valued) {
                flag = false;
                return res;
            }
            res += " " + n.data;
            if (n.lTree != null)
                queue.add(n.lTree);
            if (n.rTree != null)
                queue.add(n.rTree);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String str = new String();
            flag = true;
            root = new Node();
            while (!(str = input.next()).equals("()")){
                String []temp = str.split(",");
                int data = Integer.valueOf(temp[0].substring(1));
                String step = temp[1];  //include ")"
                addNode(data,step);
            }
            String res = bfs();
            if (flag)
                System.out.println(res.substring(1));
            else
                System.out.println("not complete");
        }
    }
}
