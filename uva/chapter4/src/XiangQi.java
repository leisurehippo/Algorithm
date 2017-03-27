import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by wj on 16/11/1.
 */
public class XiangQi {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = new String();
        while(!(str=input.nextLine()).equals("0 0 0")){
            //read the number of red pieces and the position of black general
            StringTokenizer token = new StringTokenizer(str);
            int num = Integer.valueOf(token.nextToken());
            int x = Integer.valueOf(token.nextToken());
            int y = Integer.valueOf(token.nextToken());
            //init the chess and judge,0 means available
            char [][] chess = new char[11][10];
            char [][] judge = new char[11][10];
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 10; j++) {
                    chess[i][j] = '0';
                    judge[i][j] = '0';
                }
            }
            //chess[x][y] = 'B';
            //read red pieces into chess
            String [] piece_pos = new String[num];
            for (int i = 0; i < num; i++) {
                String s = input.nextLine();
                piece_pos[i] = s;
                StringTokenizer piece = new StringTokenizer(s);
                char p = piece.nextToken().charAt(0);
                int rx = Integer.valueOf(piece.nextToken());
                int ry = Integer.valueOf(piece.nextToken());
                chess[rx][ry] = p;
            }
            String t = input.nextLine();
            //mark unavailable position with '1'
            for (int i = 0; i < num; i++) {
                String [] tmp = piece_pos[i].split(" ");
                int cx = Integer.valueOf(tmp[1]);
                int cy = Integer.valueOf(tmp[2]);
                switch (tmp[0]){
                    case "G":
                        for (int j = cx - 1; j > 0; j--) {
                            if (!Character.isLetter(chess[j][cy]))
                                judge[j][cy] = '1';
                            else{
                                judge[j][cy] = '1';
                                break;
                            }
                        }
                        break;
                    case "R":
                        //down
                        for (int j = cx+1; j < 11; j++) {
                            if (!Character.isLetter(chess[j][cy]))
                                judge[j][cy] = '1';
                            else{
                                judge[j][cy] = '1';
                                break;
                            }
                        }
                        //up
                        for (int j = cx-1; j > 0; j--) {
                            if (!Character.isLetter(chess[j][cy]))
                                judge[j][cy] = '1';
                            else{
                                judge[j][cy] = '1';
                                break;
                            }
                        }
                        //right
                        for (int j = cy+1; j < 10; j++) {
                            if (!Character.isLetter(chess[cx][j]))
                                judge[cx][j] = '1';
                            else{
                                judge[cx][j] = '1';
                                break;
                            }
                        }
                        //left
                        for (int j = cy-1; j > 0; j--) {
                            if (!Character.isLetter(chess[cx][j]))
                                judge[cx][j] = '1';
                            else{
                                judge[cx][j] = '1';
                                break;
                            }
                        }
                        break;
                    case "H":
                        if (cx<9 && !Character.isLetter(chess[cx+1][cy])){
                            if (cy<9)
                                judge[cx+2][cy+1] = '1';
                            if (cy>1)
                                judge[cx+2][cy-1] = '1';
                        }
                        if (cx>2 && !Character.isLetter(chess[cx-1][cy])){
                            if (cy<9)
                                judge[cx-2][cy+1] = '1';
                            if (cy>1)
                                judge[cx-2][cy-1] = '1';
                        }
                        if (cy<8 && !Character.isLetter(chess[cx][cy+1])){
                            if (cx>1)
                                judge[cx-1][cy+2] = '1';
                            if (cx<10)
                                judge[cx+1][cy+2] = '1';
                        }
                        if (cy>2 && !Character.isLetter(chess[cx][cy-1])){
                            if (cx>1)
                                judge[cx-1][cy-2] = '1';
                            if (cx<10)
                                judge[cx+1][cy-2] = '1';
                        }
                        break;
                    case "C":
                        //down
                        outerd:
                        for (int j = cx+1; j < 11; j++) {
                            if (!Character.isLetter(chess[j][cy]))
                                continue;
                            else{
                                for (int k = j+1; k < 11; k++) {
                                    if (!Character.isLetter(chess[k][cy]))
                                        judge[k][cy] = '1';
                                    else{
                                        judge[k][cy] = '1';
                                        break outerd;
                                    }
                                }
                            }
                        }
                        //up
                        outeru:
                        for (int j = cx-1; j > 0; j--) {
                            if (!Character.isLetter(chess[j][cy]))
                                continue;
                            else{
                                for (int k = j-1; k > 0; k--) {
                                    if (!Character.isLetter(chess[k][cy]))
                                        judge[k][cy] = '1';
                                    else{
                                        judge[k][cy] = '1';
                                        break outeru;
                                    }
                                }
                            }
                        }
                        //right
                        outerr:
                        for (int j = cy+1; j < 10; j++) {
                            if (!Character.isLetter(chess[cx][j]))
                                continue;
                            else{
                                for (int k = j+1; k < 10; k++) {
                                    if (!Character.isLetter(chess[cx][k]))
                                        judge[cx][k] = '1';
                                    else{
                                        judge[cx][k] = '1';
                                        break outerr;
                                    }
                                }
                            }
                        }
                        //left
                        outerl:
                        for (int j = cy-1; j > 0; j--) {
                            if (!Character.isLetter(chess[cx][j]))
                                continue;
                            else{
                                for (int k = j-1; k > 0; k--) {
                                    if (!Character.isLetter(chess[cx][k]))
                                        judge[cx][k] = '1';
                                    else{
                                        judge[cx][k] = '1';
                                        break outerl;
                                    }
                                }
                            }
                        }
                        break;
                }
            }
//            chess[x][y] = 'B';
//            for (int i = 0; i < 11; i++) {
//                for (int j = 0; j < 10; j++) {
//                    System.out.print(chess[i][j]+" ");
//                }
//                System.out.print("\n");
//            }
            boolean flag = false;
            if (x<3 && judge[x+1][y] != '1')
                flag = true;
            if (x>1 && judge[x-1][y] != '1')
                flag = true;
            if (y>4 && judge[x][y-1] != '1')
                flag = true;
            if (y<6 && judge[x][y+1] != '1')
                flag = true;
            if (flag)
                System.out.println("NO");
            else
                System.out.println("YES");
        }
    }
}
