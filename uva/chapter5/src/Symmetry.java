import java.util.*;

/**
 * Created by wj on 16/12/7.
 */
public class Symmetry {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = Integer.valueOf(input.nextLine());
        for (int i = 0; i < size; i++) {
            int num = Integer.valueOf(input.nextLine());
            //record the minimum and the maximum value of x coordinate
            int min = 10000;
            int max = -10000;
            Set<String> set = new HashSet<>();
            for (int j = 0; j < num; j++) {
                StringTokenizer token = new StringTokenizer(input.nextLine());
                int x = Integer.valueOf(token.nextToken());
                int y = Integer.valueOf(token.nextToken());
                if (x < min){
                    min = x;
                }
                if (x > max){
                    max = x;
                }
                //add the coordinate of each point into the set as "x,y"
                String point = x+","+y;
                set.add(point);
            }
            //the axis of the symmetry is the average of max and min
            double axis = (max+min) / 2.0;
            Iterator iter = set.iterator();
            boolean flag = true;
            while(iter.hasNext()){
                String [] point = ((String)iter.next()).split(",");
                //calculate the coordinate of the point according to the symmetrical axis
                int symmetryx = Integer.valueOf(point[0]) + (int)(2*(axis-Integer.valueOf(point[0])));
                String symmetry = symmetryx + "," + point[1];
                if (!set.contains(symmetry)){
                    System.out.println("NO");
                    flag = false;
                    break;
                }
            }
            if (flag){
                System.out.println("YES");
            }
        }
    }
}
