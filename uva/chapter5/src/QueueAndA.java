import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by wj on 16/12/17.
 */
class Staff{

    private int id;
    private int last_time;
    private int finish_time;
    private int priority;

    public Staff(int id, int last_time, int finish_time, int priority) {
        this.id = id;
        this.last_time = last_time;
        this.finish_time = finish_time;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }
}
class Request{
    private int id;
    private int number;
    private int firstTime;
    private int serviceTime;
    private int interval;

    public Request(int id, int number, int firstTime, int serviceTime, int interval) {
        this.id = id;
        this.number = number;
        this.firstTime = firstTime;
        this.serviceTime = serviceTime;
        this.interval = interval;
    }
}
public class QueueAndA {

    public static Map<Integer,List<Integer>> timeMap = new HashMap<>();
    public static List<Integer> requestList = new ArrayList<>();
    public static Map<Integer,Staff> staffMap = new HashMap<>();
    public static Map<Integer,List<Integer>> taskMap = new HashMap<>();

    public static void main(String[] args) {

        Comparator<Staff> OrderIsdn =  new Comparator<Staff>(){
            public int compare(Staff o1, Staff o2) {
                int p1 = o1.getPriority();
                int p2 = o2.getPriority();
                int id1 = o1.getId();
                int id2 = o2.getId();

                if (p1 == p2){
                    return id1 - id2;
                }else{
                    return p1 - p2;
                }
            }
        };

        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        while(num != 0){
            timeMap.clear();
            requestList.clear();
            staffMap.clear();

            for (int i = 0; i < num; i++) {
                int id = input.nextInt();
                int number = input.nextInt();
                int firstTime = input.nextInt();
                int serviceTime = input.nextInt();
                int interval = input.nextInt();
                for (int j = 0; j < number; j++) {
                    int time = firstTime + interval*j;
                    if (timeMap.containsKey(time)){
                        List<Integer> tmp = timeMap.get(time);
                        tmp.add(id);
                    }else{
                        List<Integer> list = new ArrayList<>();
                        list.add(id);
                        timeMap.put(time,list);
                    }
                }
                Request request = new Request(id,number,firstTime,serviceTime,interval);
            }
            num = input.nextInt();
            for (int i = 0; i < num; i++) {
                int id = input.nextInt();
                for (int j = 0; j < input.nextInt(); j++) {
                    int task = input.nextInt();
                    if (taskMap.containsKey(task)){

                    }else{
                        Queue<Staff> queue = new PriorityBlockingQueue<>(5,OrderIsdn);
//                        queue.add();
                    }
                }

            }

            int time = 0;
            while(true){
                if (timeMap.containsKey(time)){
                    List<Integer> tmp = timeMap.get(time);
                    requestList.addAll(tmp);
                }


                time++;
            }
        }
    }
}
