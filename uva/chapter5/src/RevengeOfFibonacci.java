import java.util.Scanner;

/**
 * Created by H-E-Z on 2017/3/14.
 */

/**
 * Node of the trie tree
 */
class Node{
    int id;  //the smallest index of the number which search from the root to this node
    Node []next;  //the child nodes, the index of which indicate the digit
    Node(){
        this.id = -1;
        this.next = new Node[10];
    }
}

/**
 * Use the trie tree to store the Fibonacci number
 *
 */
public class RevengeOfFibonacci {
    public static Node root = new Node();  //the trie tree
    public static int [][]F = new int[2][21000];  //store the first two fibonacci number by digit
    public static char []FF = new char[41];  //store the next fibonacci number, and use to build the trie tree

    /**
     * insert the fibonacci number into the trie tree
     * @param number
     * @param id
     * @param len
     */
    public static void insert(char []number, int id, int len){
        Node node = root;
        //the number is stored reversed
        for (int i = len - 1, count = 0; i >= 0 && count < 40; i--,count++) {
            char num = number[i];
            int a = (int)num - 48;  //convert char to int
            //new a node if is null
            if (node.next[a] == null)
                node.next[a] = new Node();
            node = node.next[a];
            //assigned the id only when it had not been assigned
            if (node.id == -1)
                node.id = id;
        }


    }

    /**
     * query the index of the fibonacci number
     * @param number
     * @param len
     * @return
     */
    public static int query(String number, int len){
        char []digit = number.toCharArray();
        Node node = root;
        for (int i = 0; i < len; i++) {
            int num = (int)digit[i] - 48;
            if (node.next[num] != null)
                node = node.next[num];
            else
                return -1;
        }
        return node.id;
    }


    public static void main(String[] args) {

        F[0][0] = F[1][0] = 1;
        FF[0] = '1';
        insert(FF,0,1);  //insert the first fibonacci number
        int start = 0, end = 1;  //stored the start and the end index of the number
        for (int i = 2; i < 100000; i++) {
            int first = i % 2 ,second = (i+1) % 2;  //store the result into the 'first' row of F
            int t = 0;  //the length of the fibonacci number
            int temp = end>40 ? (end-40) : start;
            for (int j = start; j < end; j++) {
                F[first][j] = F[first][j] + F[second][j];  //add two numbers by digit
                //carry
                if (F[first][j] > 9){
                    F[first][j] -= 10;
                    F[first][j+1] += 1;
                    if (j == end-1){
                        end++;
                    }
                }
                //store the first 40 digits
                if (j >= temp)
                    FF[t++] = (char) (F[first][j] + '0');
            }
            insert(FF,i,t);
            //when the length of the number is large than 50,
            //the back of the number can be ignored when calculator
            //only calculator the first 50 digits
            if (end - start > 50)
                start++;
        }

        Scanner input = new Scanner(System.in);
        int t = Integer.valueOf(input.nextLine());
        for (int i = 0; i < t; i++) {
            String number = input.nextLine();
            int index = query(number,number.length());
            System.out.println("Case #"+(i+1)+": "+index);
        }

    }
}