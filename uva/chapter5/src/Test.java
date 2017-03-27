import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by wj on 16/11/14.
 */

public class Test{
    public static List<Integer> requestList = new ArrayList<>();
    public static Map<Integer,List<Integer>> timeMap = new HashMap<>();
    public static void main(String[] args) {

        List<Integer> tmp = new ArrayList<>();
        tmp.add(3);
        tmp.add(5);
        timeMap.put(0,tmp);

        requestList.addAll(tmp);


    }
}
