import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by wj on 16/12/4.
 */
public class UglyNumber {
    public static int [] coeff = {2,3,5};
    public static void main(String[] args) {
        Queue<Long> queue = new PriorityBlockingQueue<>();
        Set<Long> set = new HashSet<>();
        int i = 0;
        long num = 1;
        queue.add(num);
        set.add(num);
        while (true){
            i++;
            if (i == 1500){
                System.out.println("The 1500'th ugly number is "+queue.peek()+".");
                break;
            }
            long x = queue.poll();
            for (int j = 0; j < 3; j++) {
                long y = x*coeff[j];
                if (!set.contains(y)){
                    set.add(y);
                    queue.add(y);
                }
            }
        }

    }
}
