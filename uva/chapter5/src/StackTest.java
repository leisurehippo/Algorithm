import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;



public class StackTest{

    static Stack<HashSet<Set>> stack = new Stack<HashSet<Set>>();
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int num = Integer.valueOf(input.nextLine());
        for (int i = 0; i < num; i++) {
            int size = Integer.valueOf(input.nextLine());
            for (int j = 0; j < size; j++) {
                String line = input.nextLine().toLowerCase();
                if("push".equals(line)){
                    stack.push(new HashSet<Set>());
                }else if("dup".equals(line)){
                    HashSet<Set> temp = stack.pop();
                    stack.push(temp);
                    stack.push((HashSet<Set>) temp.clone());
                }else if("union".equals(line)){
                    HashSet<Set> temp1 = stack.pop();
                    HashSet<Set> temp2 = stack.pop();
                    temp1.addAll(temp2);
                    stack.push(temp1);
                }else if("intersect".equals(line)){
                    HashSet<Set> temp1 = stack.pop();
                    HashSet<Set> temp2 = stack.pop();
                    temp1.retainAll(temp2);
                    stack.push(temp1);
                }else if("add".equals(line)){
                    HashSet<Set> temp1 = stack.pop();
                    HashSet<Set> temp2 = stack.pop();
                    temp2.add(temp1);
                    stack.push(temp2);
                }
                System.out.println(stack.peek().size());
            }
            System.out.println("***");
        }



    }
}
