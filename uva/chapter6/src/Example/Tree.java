package Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by wj on 17/2/21.
 */

public class Tree {
    public static Example.Node root;
    public static ArrayList<Example.Node> arrayList = new ArrayList<>();

    /**
     * build tree recursively(according to the inorder traversal and postorder traversal),
     * and calculate the sum of weight from the root
     * @param r
     * @param inOrder
     * @param postOrder
     */
    public static void buildTree(Example.Node r, List<Integer> inOrder, List<Integer> postOrder){
        Example.Node temp = r;
        Example.Node tempL = r;
        Example.Node tempR = r;

        int data = postOrder.get(postOrder.size()-1);//the last element of the postorder traversal is the root
        int index = inOrder.indexOf(data);//the index of the root in the inorder traversal
        //find the inorder and postorder traversal of the left tree and the right tree of the root
        List<Integer> tempInL = inOrder.subList(0,index);
        List<Integer> tempPostL = postOrder.subList(0,tempInL.size());
        List<Integer> tempInR = inOrder.subList(index+1,inOrder.size());
        List<Integer> tempPostR = postOrder.subList(tempPostL.size(),postOrder.size()-1);

        temp.data = data;    //assign the data of the root
        temp.count += data;  //add data to the count
        int count = temp.count;
        //if the node has no left tree and no right tree,
        //then return and add the node into the list
        if (tempInL.size() == 0 && tempInR.size() == 0){
            arrayList.add(temp);
            return;
        }

        //if the node has left tree
        if (tempInL.size() != 0 && tempPostL.size() != 0){
            tempL.lTree = new Example.Node();  //create a left tree
            tempL = tempL.lTree;       //set tempL to its left tree
            tempL.count += count;      //add count into its left tree's count
            buildTree(tempL,tempInL,tempPostL);
        }
        //if the node has right tree
        if (tempInR.size() != 0 && tempPostR.size() != 0){
            tempR.rTree = new Example.Node();  //create a right tree
            tempR = tempR.rTree;       //set tempRto its right tree
            tempR.count += count;      //add count into its right tree's count
            buildTree(tempR,tempInR,tempPostR);
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()){
            //convert the input into list
            StringTokenizer tokenIn = new StringTokenizer(input.nextLine());
            StringTokenizer tokenPost = new StringTokenizer(input.nextLine());
            List<Integer> listIn = new ArrayList<>();
            List<Integer> listPost = new ArrayList<>();

            while (tokenIn.hasMoreTokens())
                listIn.add(Integer.valueOf(tokenIn.nextToken()));

            while (tokenPost.hasMoreTokens())
                listPost.add(Integer.valueOf(tokenPost.nextToken()));

            //build the tree recursively
            root = new Example.Node();
            arrayList.clear();
            buildTree(root, listIn, listPost);

            //traverse the leave and find the minimum sum of weight
            int weight = arrayList.get(0).count;
            int index = arrayList.get(0).data;
            for (int i = 0; i < arrayList.size(); i++) {
                Example.Node leave = arrayList.get(i);
                if (leave.count < weight){
                    weight = leave.count;
                    index = leave.data;
                }
            }
            System.out.println(index);

        }
    }
}
