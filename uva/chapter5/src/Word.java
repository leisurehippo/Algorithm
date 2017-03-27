import java.util.*;

/**
 * Created by wj on 16/12/7.
 */
public class Word {
    public static Set<String> word = new HashSet<>();
    public static ArrayList<String> arrayList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String str = input.next();
            arrayList.add(str);
            word.add(str);
        }
        for (int i = 0; i < arrayList.size(); i++) {
            String tmp = arrayList.get(i);
            for (int j = 1; j < tmp.length(); j++) {
                String sub1 = tmp.substring(0,j);
                String sub2 = tmp.substring(j);
                if (word.contains(sub1) && word.contains(sub2)){
                    System.out.println(tmp);
                    break;
                }
            }
        }

    }
}
