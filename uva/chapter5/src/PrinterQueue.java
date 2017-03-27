import java.util.*;

/**
 * Created by wj on 16/12/7.
 */
public class PrinterQueue {
    private int order;     //the order of the task
    private int priority;  //the priority of the task
    public PrinterQueue(int order, int priority){
        this.order = order;
        this.priority = priority;
    }
    public int getOrder(){
        return this.order;
    }
    public int getPriority(){
        return this.priority;
    }
    public static void main(String[] args) {

        //overload compare, bigger number means higher priority
        Comparator<PrinterQueue> OrderIsdn =  new Comparator<PrinterQueue>(){
            public int compare(PrinterQueue o1, PrinterQueue o2) {
                int numbera = o1.getPriority();
                int numberb = o2.getPriority();
                if(numbera > numberb){
                    return -1;
                } else{
                    return 1;
                }
            }
        };

        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        for (int i = 0; i < size; i++) {
            int num = input.nextInt();
            int job = input.nextInt();
            //key for the order, value for the priority
            Map<Integer,Integer> map = new LinkedHashMap<>();
            //priority queue
            Queue<PrinterQueue> priorityQueue =  new PriorityQueue<PrinterQueue>(100,OrderIsdn);
            //read the data and add it into the map and the queue
            for (int j = 0; j < num; j++) {
                int priority = input.nextInt();
                map.put(j,priority);
                PrinterQueue task = new PrinterQueue(j,priority);
                priorityQueue.add(task);
            }
            int time = 0;
            while(!map.isEmpty()) {
                //get the head of the map as current task
                Map.Entry<Integer,Integer> entry = map.entrySet().iterator().next();
                int currentKey = entry.getKey();
                int current = entry.getValue();

                //get the highest priority task
                PrinterQueue pmost = priorityQueue.peek();
                int prioritymost = pmost.getPriority();

                //if there has higher priority task, then put the current in the bottom of the queue(map)
                //and get the next task until there has not any higher priority task
                while (prioritymost > current) {
                    map.remove(currentKey);
                    map.put(currentKey, current);
                    Map.Entry<Integer,Integer> tmp = map.entrySet().iterator().next();
                    currentKey = tmp.getKey();
                    current = tmp.getValue();
                }
                priorityQueue.poll();
                map.remove(currentKey);
                time++;
                if (currentKey == job){
                    System.out.println(time);
                    break;
                }
            }
        }



    }
}
