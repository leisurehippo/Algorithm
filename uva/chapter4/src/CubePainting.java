import java.util.Scanner;

/**
 * Created by wj on 16/11/5.
 */
public class CubePainting {
    public static char [] standard = new char[6];
    public static char [] tmp = new char[6];
    public static void horizonal(int n){
        char [] t = new char[6];
        System.arraycopy(tmp,0,t,0,6);
        int [][] arr = {{1,2,3,4,5,6},{2,6,3,4,1,5},{6,5,3,4,2,1},{5,1,3,4,6,2}};
        for (int i = 0; i < 6; i++) {
            t[i] = tmp[arr[n][i]-1];
        }
        System.arraycopy(t,0,tmp,0,6);
    }
    public static void vertical(int n){
        char [] t = new char[6];
        System.arraycopy(tmp,0,t,0,6);
        int [][] arr = {{1,2,3,4,5,6},{1,4,2,5,3,6},{1,5,4,3,2,6},{1,3,5,2,4,6}};
        for (int i = 0; i < 6; i++) {
            t[i] = tmp[arr[n][i]-1];
        }
        System.arraycopy(t,0,tmp,0,6);
    }
    public static void longitude(int n){
        char [] t = new char[6];
        System.arraycopy(tmp,0,t,0,6);
        int [][] arr = {{1,2,3,4,5,6},{3,2,6,1,5,4},{6,2,4,3,5,1},{4,2,1,6,5,3}};
        for (int i = 0; i < 6; i++) {
            t[i] = tmp[arr[n][i]-1];
        }
        System.arraycopy(t,0,tmp,0,6);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()){
            String str = input.nextLine();
            String test = str.substring(6);
            standard = str.substring(0,6).toCharArray();
            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 1; k < 4; k++) {
                        System.arraycopy(standard,0,tmp,0,6);
                        horizonal(i);
                        vertical(j);
                        longitude(k);
                        if (test.equals(String.valueOf(tmp)))
                            flag = true;
                    }
                }
            }
            if (flag)
                System.out.println("TRUE");
            else
                System.out.println("FALSE");
        }
    }
}
