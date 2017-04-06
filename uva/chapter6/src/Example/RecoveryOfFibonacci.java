package Example;

/**
 * Created by H-E-Z on 2017/3/14.
 */
class Node{
    int id;
    Example.Node[]next = new Example.Node[10];
    Node(){
        this.id = -1;
        for (int i = 0; i < 10; i++) {
            this.next[i] = null;
        }
    }
}

public class RecoveryOfFibonacci {
    public static Example.Node root = new Example.Node();
    public static int [][]F = new int[2][21000];
    public static char []FF = new char[40];

    public static void insert(char []number, int id, int len){
        Example.Node node = root;
        for (int i = 0; i < len; i++) {
            char num = number[i];
            int a = (int)num - 48;  //convert char to int
            if (node.next[a] == null)
                node.next[a] = new Example.Node();
            node = node.next[a];
        }
        if (node.id == -1)
            node.id = id;

    }
    public static void main(String[] args) {
        F[0][0] = F[1][0] = 1;
        FF[0] = '1';
        insert(FF,0,1);
        int start = 0, end = 1;
        for (int i = 2; i < 100000; i++) {
            int first = i % 2 ,second = (i+1) % 2;
            for (int j = start; j < end; j++) {
                F[first][j] = F[first][j] + F[second][j];
            }
        }


        int a=1;
//        Scanner input = new Scanner(System.in);
//        int t = Integer.valueOf(input.nextLine());
//        for (int i = 0; i < t; i++) {
//            String number = input.nextLine();
//
//
//        }

    }
}
