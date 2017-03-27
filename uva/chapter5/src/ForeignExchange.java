import java.util.*;

/**
 * Created by wj on 16/12/7.
 */
public class ForeignExchange {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = new String();
        while (!(str = input.nextLine()).equals("0")){
            int num = Integer.valueOf(str);
            Map<String,Integer> map = new HashMap<>();
            for (int i = 0; i < num; i++) {
                StringTokenizer token = new StringTokenizer(input.nextLine());
                int original = Integer.valueOf(token.nextToken());
                int target = Integer.valueOf(token.nextToken());
                //set the key as the format "original->target"
                //and the value as the number of the key
                String key = original+"->"+target;
                if (map.containsKey(key)){
                    int count = map.get(key) + 1;
                    map.put(key,count);
                }else{
                    map.put(key,1);
                }
            }
            boolean flag = true;
            for (Map.Entry entry : map.entrySet()){
                String key = (String)entry.getKey();
                int value = (Integer)entry.getValue();
                String [] arr = key.split("->");
                //get the exchange rule as "target->original"
                String exchange = arr[1]+"->"+arr[0];
                if (map.containsKey(exchange)){
                    int tmp = map.get(exchange);
                    if (tmp != value){
                        //if the rule number do not as same as the target
                        flag = false;
                        break;
                    }
                }else{
                    //if do not contain the rule
                    flag = false;
                    break;
                }
            }
            if (flag)
                System.out.println("YES");
            else
                System.out.println("NO");

        }
    }
}
