package Exercise;

import java.util.Scanner;

/**
 * Created by H-E-Z on 2017/3/26.
 */
class Tree{
    public char c;
    public Tree left_subtree, right_subtree;
}
public class TreeRecovery {
    /**
     * construct tree with preorder(root, left, right) and inorder(left, root, right)
     * @param preorder
     * @param inorder
     * @return
     */
    public static Tree build_tree(String preorder, String inorder){
        //the node is null
        if (preorder.length() == 0 && inorder.length() == 0)
            return null;
        //the node is the leave node
        if (preorder.length() == 1 && inorder.length() == 1){
            Tree node = new Tree();
            node.c = preorder.charAt(0);
            return node;
        }
        //get the root of the tree from the first charter of preorder
        char r = preorder.charAt(0);
        //the inorder of left and right tree from inorder (split by the root)
        int index = inorder.indexOf(r);
        String il = inorder.substring(0,index);
        String ir = inorder.substring(index+1);
        //get the perorder of left and right tree from preorder (use the length)
        int len = il.length();
        String pl = preorder.substring(1,len+1);
        String pr = preorder.substring(len+1);
        //create the node
        Tree tree = new Tree();
        tree.c = r;
        tree.left_subtree = build_tree(pl, il);
        tree.right_subtree = build_tree(pr,ir);

        return tree;
    }

    /**
     * get the tree's postorder(left, right, root)
     * @param tree
     * @return
     */
    public static String postorder(Tree tree){
        if (tree.left_subtree == null && tree.right_subtree == null)
            return String.valueOf(tree.c);
        String left = "";
        String right = "";
        //search from the left tree
        if (tree.left_subtree != null)
            left = postorder(tree.left_subtree);
        //search from the right tree
        if (tree.right_subtree != null)
            right = postorder(tree.right_subtree);

        return left + right + tree.c;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String preorder = input.next();
            String inorder = input.next();
            Tree root = build_tree(preorder, inorder);
            String r = postorder(root);
            System.out.println(r);
        }
    }
}
