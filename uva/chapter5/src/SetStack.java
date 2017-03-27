import java.util.*;

/**uva12096
 * 超时
 * 跑debug里第一条明显慢
 * Created by wj on 16/11/26.
 */
public class SetStack {
    public static Map<Set,Integer> map = new HashMap<>();
    public static Vector<Set> vector = new Vector<>();
    public static int getId(Set set){
        if(map.containsKey(set)){
            return map.get(set);
        }else {
            vector.add(set);
            int id = vector.size() - 1;
            map.put(set,id);
            return id;
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = Integer.valueOf(input.nextLine());
        for (int i = 0; i < num; i++) {
            Stack<Integer> stack = new Stack<>();
            map.clear();
            vector.clear();
            int size = Integer.valueOf(input.nextLine());
            int [] time = new int[5];
            int [] count = new int[5];
            for (int j = 0; j < size; j++) {
                String op = input.nextLine();
                switch (op){
                    case "PUSH":
                        long sp = System.currentTimeMillis();
                        Set emptySet = new HashSet<>();
                        stack.push(getId(emptySet));
                        long ep = System.currentTimeMillis();
                        time[0] += (ep-sp);
                        count[0] += 1;
                        System.out.println("PUS"+(float)time[0] / (float)count[0]);
                        break;
                    case "DUP":
                        long sd = System.currentTimeMillis();
                        int tmp = stack.pop();
                        stack.push(tmp);
                        stack.push(tmp);
                        long ed = System.currentTimeMillis();
                        time[1] += (ed-sd);
                        count[1] += 1;
                        System.out.println("DUP"+(float)time[1] / (float)count[1]);
                        break;
                    case "UNION":
                        long su = System.currentTimeMillis();
                        Set union = new HashSet<>();
                        union.addAll(vector.elementAt(stack.pop()));
                        union.addAll(vector.elementAt(stack.pop()));
                        stack.push(getId(union));
                        long eu = System.currentTimeMillis();
                        time[2] += (eu-su);
                        count[2] += 1;
                        System.out.println("UNI"+(float)time[2] / (float)count[2]);
                        break;
                    case "INTERSECT":
                        long si = System.currentTimeMillis();
                        Set intersect = new HashSet<>();
                        intersect.addAll(vector.elementAt(stack.pop()));
                        intersect.retainAll(vector.elementAt(stack.pop()));
                        stack.push(getId(intersect));
                        long ei = System.currentTimeMillis();
                        time[3] += (ei-si);
                        count[3] += 1;
                        System.out.println("INT"+(float)time[3] / (float)count[3]);
                        break;
                    case "ADD":
                        long sa = System.currentTimeMillis();
                        Set first = vector.elementAt(stack.pop());
                        Set second = vector.elementAt(stack.pop());
                        Set n = new HashSet<>();
                        n.addAll(second);
                        n.add(first);
                        stack.push(getId(n));
                        long ea = System.currentTimeMillis();
                        time[4] += (ea-sa);
                        count[4] += 1;
                        System.out.println("ADD"+(float)time[4] / (float)count[4]);
                        break;
                }
                System.out.println(vector.elementAt(stack.peek()).size());
            }
            System.out.println("***");
        }
    }
}
