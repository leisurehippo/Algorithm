import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by wj on 16/10/22.
 */
public class Box {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            for (int i = 0; i < 6; i++) {
                int width = input.nextInt();
                int height = input.nextInt();
                String unique = ""+(width+height)+","+Math.abs(width-height);
                if (map.get(unique) == null)
                    map.put(unique,1);
                else
                    map.put(unique,map.get(unique)+1);
            }
            switch (map.size()){
                case 1:
                    System.out.println("POSSIBLE");
                    break;
                case 2:
                    if (map.containsValue(2)){
                        if()
                        System.out.println("POSSIBLE");
                    }

                    else
                        System.out.println("IMPOSSIBLE");
                    break;
                case 3:
                    Integer [] arr = new Integer[6];
                    Collection<Integer> collection = map.values();
                    collection.toArray(arr);
                    boolean flag = true;
                    for (int i = 0; i < 3; i++) {
                        if (arr[i] != 2){
                            flag = false;
                            break;
                        }
                    }
                    if (flag)
                        System.out.println("POSSIBLE");
                    else
                        System.out.println("IMPOSSIBLE");
                    break;
                default:
                    System.out.println("IMPOSSIBLE");
                    break;
            }
        }
    }
}
