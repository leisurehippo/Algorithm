import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by wj on 16/12/7.
 */
public class CompoundWord {
    //use hashmap or set seem to has the same time
    public static Map<String,Integer> word = new LinkedHashMap<>();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String str = input.next();
            word.put(str,0);
        }
        for (Map.Entry entry : word.entrySet()){
            String tmp = (String)entry.getKey();
            //split the word into two word
            for (int j = 1; j < tmp.length(); j++) {
                String sub1 = tmp.substring(0,j);
                String sub2 = tmp.substring(j);
                if (word.containsKey(sub1) && word.containsKey(sub2)){
                    System.out.println(tmp);
                    break;
                }
            }
        }
    }
}
