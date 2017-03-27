import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by wj on 16/12/5.
 */
class Building {
    int id;
    double x,y,width,depth,height;
//    public boolean cmp(){
//
//    }
}
public class Urban {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = new String();
        while(!(str = input.nextLine()).equals("0")){
            int num = Integer.valueOf(str);
            Building [] builds = new Building[num];
            for (int i = 0; i < num; i++) {
                StringTokenizer token = new StringTokenizer(input.nextLine());
                builds[i].id = i+1;
                builds[i].x = Double.valueOf(token.nextToken());
                builds[i].y = Double.valueOf(token.nextToken());
                builds[i].width = Double.valueOf(token.nextToken());
                builds[i].depth = Double.valueOf(token.nextToken());
                builds[i].height = Double.valueOf(token.nextToken());
            }
        }
    }
}
