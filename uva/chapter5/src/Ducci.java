import java.util.*;

/**
 * Created by wj on 16/12/7.
 */
public class Ducci {
    /**
     * judge if the tuple reach a tuple of zeros
     * @param arrayList
     * @return
     */
    public static boolean judgeZero(ArrayList<Integer> arrayList){
        Set<Integer> set = new HashSet<>();
        //add all elements of arraylist into a hashset
        set.addAll(arrayList);
        //judge the size of the set
        return set.size() == 1;
    }

    /**
     * the next n-tuple in the sequence is formed by
     * taking the absolute differences of neighboring integers
     * @param arrayList
     * @return
     */
    public static ArrayList<Integer> changeArr(ArrayList<Integer> arrayList){
        int tmp = arrayList.get(0);
        for (int i = 0; i < arrayList.size()-1; i++) {
            int abs = Math.abs(arrayList.get(i)-arrayList.get(i+1));
            arrayList.set(i,abs);
        }
        int abs = Math.abs(arrayList.get(arrayList.size()-1)-tmp);
        arrayList.set(arrayList.size()-1,abs);
        return arrayList;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = Integer.valueOf(input.nextLine());
        for (int i = 0; i < num; i++) {
            //read the tuple from input
            ArrayList<Integer> arrayList = new ArrayList<>();
            int size = Integer.valueOf(input.nextLine());
            StringTokenizer token = new StringTokenizer(input.nextLine());
            for (int j = 0; j < size; j++) {
                arrayList.add(Integer.valueOf(token.nextToken()));
            }
            boolean flag = false;
            for (int j = 0; j < 1000; j++) {
                //judge if the tuple reach a tuple of zeros
                flag = judgeZero(arrayList);
                if (flag){
                    System.out.println("ZERO");
                    break;
                }else{
                    arrayList = changeArr(arrayList);
                }
            }
            //fall into a periodic loop
            if (!flag){
                System.out.println("LOOP");
            }
        }

    }
}
