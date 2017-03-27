import java.util.*;

/**runtime error
 * Created by wj on 16/12/4.
 */
public class DataBase {
    public static ArrayList<Map<String,Integer>> word = new ArrayList<>();
    public static ArrayList<Map<String,Integer>> relation = new ArrayList<>();
    public static int getId(String str, int index){
        if (word.size() < index+1){
            Map<String,Integer> tmp = new HashMap<>();
            tmp.put(str,0);
            word.add(index,tmp);
            return 0;
        }else{
            Map<String,Integer> map = word.get(index);
            if (map.containsKey(str)){
                return map.get(str);
            }else{
                int id = map.size();
                map.put(str,id);
                return id;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = new String();
        while(!(s = input.nextLine()).equals("")){
            StringTokenizer token = new StringTokenizer(s);
            int row = Integer.valueOf(token.nextToken());
            int column = Integer.valueOf(token.nextToken());
            word.clear();
            relation.clear();
            boolean flag = true;
            outer:
            for (int i = 0; i < row; i++) {
                String [] arr = input.nextLine().split(",");
                int [] id = new int[column];
                //encode each element in column
                for (int j = 0; j < column; j++) {
                    id[j] = getId(arr[j],j);
                }
                int count = 0;
                for (int j = 0; j < column; j++) {
                    for (int k = j+1; k < column; k++) {
                        String r = id[j]+","+id[k];
                        if (relation.size() < count+1){
                            Map<String,Integer> tmp = new HashMap<>();
                            tmp.put(r,i);
                            relation.add(count,tmp);
                        }else{
                            Map<String,Integer> rmap = relation.get(count);
                            if (rmap.containsKey(r)){
                                System.out.println("NO");
                                System.out.println((rmap.get(r)+1)+" "+(i+1));
                                System.out.println((j+1)+" "+(k+1));
                                flag = false;
                                break outer;
                            }else{
                                rmap.put(r,i);
                            }
                        }
                        count++;
                    }
                }
            }
            if (flag){
                System.out.println("YES");
            }
        }
    }
}
