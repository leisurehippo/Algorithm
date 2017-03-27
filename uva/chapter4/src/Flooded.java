import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by wj on 16/11/5.
 */
class Flooded {
    public static int m = 0;
    public static int n = 0;
    public static int water = 0;
    public static void findScale(int [] elevation, int num){
        int maxWater = 0;
        int count = 1;
        while(maxWater < water && count < elevation.length){
            maxWater += (elevation[count] - elevation[count-1]) * count * 100;
            count++;
        }
        System.out.println("Region "+num);
        num++;
        double waterElevation;
        double percent;
        if (maxWater < water) {
            percent = (double)(count) / (double)(m*n);
            waterElevation = (double) elevation[count - 1] + ((double) (water - maxWater) / (double) (m * n * 100));
        }else {
            percent = (double)(count-1) / (double)(m*n);
            waterElevation = (double) elevation[count - 1] - ((double) (maxWater - water) / (double) ((count - 1) * 100));
        }
        System.out.printf("Water level is %.2f meters.\n",waterElevation);
        System.out.printf("%.2f percent of the region is under water.\n\n",percent*100);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = new String();
        int num = 1;
        while(!(str=input.nextLine()).equals("0 0")){
            StringTokenizer token = new StringTokenizer(str);
            m = Integer.valueOf(token.nextToken());
            n = Integer.valueOf(token.nextToken());
            int [] elevation = new int [m*n];
            int k = 0;
            for (int i = 0; i < m; i++) {
                StringTokenizer t = new StringTokenizer(input.nextLine());
                for (int j = 0; j < n; j++)
                    elevation[k++] = Integer.valueOf(t.nextToken());
            }
            water = Integer.valueOf(input.nextLine());
            Arrays.sort(elevation);
            findScale(elevation, num);
        }
    }
}

