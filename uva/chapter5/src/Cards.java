import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wj on 16/12/7.
 */
public class Cards {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = new String();
        while(!(str = input.nextLine()).equals("0")){
            Queue<Integer> queue = new LinkedBlockingQueue<>();
            int size = Integer.valueOf(str);
            for (int i = 0; i < size; i++) {
                queue.add(i+1);
            }
            System.out.print("Discarded cards:");
            String res = "";
            while(queue.size() > 1){
                int discard = queue.poll();//the discarded card
                int peek = queue.poll();//the second card
                queue.add(peek);//put it in the bottom of the queue
                res += " "+discard+",";
            }
            if (res.length() > 1){
                res = res.substring(0,res.length()-1);
            }
            System.out.println(res);
            System.out.println("Remaining card: "+queue.peek());
        }
    }
}
