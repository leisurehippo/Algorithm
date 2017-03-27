import java.math.BigInteger;
import java.util.*;

/**
 * Created by wj on 16/12/14.
 */
public class Dictionary {
    public static Map<String,BigInteger> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = Integer.valueOf(input.nextLine());
        for (int i = 0; i < num; i++) {
            map.clear();
            String oldDic = input.nextLine();
            String newDic = input.nextLine();

            List<String> addWord = new ArrayList<>();
            List<String> removeWord = new ArrayList<>();
            List<String> changeWord = new ArrayList<>();

            if (oldDic.length() > 2){
                String []old = oldDic.substring(1,oldDic.length()-1).split(",");
                for (int j = 0; j < old.length; j++) {
                    String []arr = old[j].split(":");
                    String key = arr[0];
                    BigInteger value = new BigInteger(arr[1]);
                    map.put(key,value);
                    removeWord.add(key);
                }
            }

            boolean flag = false;
            if (newDic.length() > 2){
                String []newArr = newDic.substring(1,newDic.length()-1).split(",");
                Set<String> newKey = new HashSet<>();
                for (int j = 0; j < newArr.length; j++) {
                    String []arr = newArr[j].split(":");
                    String key = arr[0];
                    newKey.add(key);
                    if (map.containsKey(key)){
                        BigInteger oldValue = map.get(key);
                        BigInteger newValue = new BigInteger(arr[1]);
                        if (oldValue.compareTo(newValue) != 0){
                            changeWord.add(key);
                            flag = true;
                        }
                    }else{
                        flag = true;
                        addWord.add(key);
                        newKey.remove(key);
                    }
                }
                removeWord.removeAll(newKey);

            }
            if (removeWord.size() != 0){
                flag = true;
            }

            if (flag){
                if (addWord.size() != 0){
                    Collections.sort(addWord);
                    System.out.print("+");
                    for (int j = 0; j < addWord.size(); j++) {
                        if (j != 0)
                            System.out.print(",");
                        System.out.print(addWord.get(j));
                    }
                    System.out.print("\n");
                }
                if (removeWord.size() != 0){
                    Collections.sort(removeWord);
                    System.out.print("-");
                    for (int j = 0; j < removeWord.size(); j++) {
                        if (j != 0)
                            System.out.print(",");
                        System.out.print(removeWord.get(j));
                    }
                    System.out.print("\n");
                }
                if (changeWord.size() != 0){
                    Collections.sort(changeWord);
                    System.out.print("*");
                    for (int j = 0; j < changeWord.size(); j++) {
                        if (j != 0)
                            System.out.print(",");
                        System.out.print(changeWord.get(j));
                    }
                    System.out.print("\n");
                }

            }else{
                System.out.println("No changes");
            }
            System.out.print("\n");

        }
    }
}
