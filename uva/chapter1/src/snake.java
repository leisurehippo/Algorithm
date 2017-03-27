/**
 * Created by wj on 16/10/13.
 */
public class snake {
    public static void main(String[] args) {
        final int max = 8;
        int n = 4;
        int[][] a = new int[max][max];
        int x = 0;
        int y = max-1;
        int i = 1;
        a[x][y] = 1;
        while(i < n*n){
            //down
            while(x+1 < n && a[x+1][y] == 0)
                a[++x][y] = ++i;
            //left
            while(y-1 >= max - n && a[x][y-1] == 0)
                a[x][--y] = ++i;
            //up
            while(x-1 >= 0 && a[x-1][y] == 0)
                a[--x][y] = ++i;
            //right
            while(y+1 < max && a[x][y+1] == 0)
                a[x][++y] = ++i;

        }


        for (int t = 0; t < max; t++) {
            for (int j = 0; j < max; j++) {
                System.out.print(a[t][j]);
            }
            System.out.print("\n");
        }
    }
}
