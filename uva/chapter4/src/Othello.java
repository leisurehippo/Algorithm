import java.util.Scanner;

/**
 * Created by wj on 16/11/4.
 */
public class Othello {
    public static char [][] board = new char[8][8];
    public static boolean [][] judge = new boolean[8][8];//judge the available position
    public static int white = 0, black = 0;//the number of white or black piece
    public static boolean turn = false;//true for white,false for black
    //read from the input and init the board and the number of pieces
    public static void initBoard(Scanner input){
        for (int i = 0; i < 8; i++) {
            String str = input.nextLine();
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == 'W')
                    white++;
                else if (str.charAt(j) == 'B')
                    black++;
                board[i][j] = str.charAt(j);
            }
        }
    }
    //if can form the bracket then set judge
    public static void bracket(int i, int j, int di, int dj){
        int x = i+di , y = j+dj;
        boolean flag = false;
        while(x>=0 && x<8 && y>=0 && y<8 && board[x][y] == (turn?'B':'W')){
            x += di;
            y += dj;
            flag = true;
        }
        if (x>=0 && x<8 && y>=0 && y<8 && board[x][y] == '-' && flag)
            judge[x][y] = true;
    }
    //reset the judge array
    public static void clearJudge(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                judge[i][j] = false;
            }
        }
    }
    //set the available position with true
    public static void setJudge(){
        //find all the pieces this turn
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == (turn?'W':'B')){
                    //search all 8 direction
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            bracket(i,j,k,l);
                        }
                    }
                }
            }
        }
    }
    public static void setBoard(int i, int j, int di, int dj){
        int x = i+di , y = j+dj;
        boolean flag = false;
        while(x>=0 && x<8 && y>=0 && y<8 && board[x][y] == (turn?'B':'W')){
            x += di;
            y += dj;
            flag = true;
        }
        if (x>=0 && x<8 && y>=0 && y<8 && board[x][y] == (turn?'W':'B') && flag){
            for (x = x-di,y = y-dj; x!=i || y!=j ; x-=di,y-=dj) {
                board[x][y] = (turn?'W':'B');
                if (turn){
                    white++;
                    black--;
                }else{
                    white--;
                    black++;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = Integer.valueOf(input.nextLine());
        for (int i = 0; i < number; i++) {
            white = 0;
            black = 0;
            initBoard(input);
            turn = input.nextLine().equals("W");
            String command = new String();
            if (i != 0)
                System.out.print("\n");
            while(!(command=input.nextLine()).equals("Q")){
                switch (command.charAt(0)){
                    case 'L':
                        clearJudge();
                        setJudge();
                        String tmp = "";
                        for (int j = 0; j < 8; j++) {
                            for (int k = 0; k < 8; k++) {
                                if (judge[j][k])
                                    tmp += "("+(j+1)+","+(k+1)+") ";
                            }
                        }
                        if (tmp.equals("")) {
                            System.out.println("No legal move.");
                            turn = !turn;
                        }else
                            System.out.println(tmp.trim());
                        break;
                    case 'M':
                        int x = Character.getNumericValue(command.charAt(1)) - 1;
                        int y = Character.getNumericValue(command.charAt(2)) - 1;
                        if (turn)
                            white++;
                        else
                            black++;
                        board[x][y] = (turn?'W':'B');
                        for (int j = -1; j < 2; j++) {
                            for (int k = -1; k < 2; k++) {
                                setBoard(x,y,j,k);
                            }
                        }
                        turn = !turn;
                        System.out.printf("Black - %2d White - %2d\n",black,white);
                        break;
                    default:
                        break;
                }
            }
            if (command.charAt(0) == 'Q'){
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 8; k++) {
                        System.out.print(board[j][k]);
                    }
                    System.out.print("\n");
                }
            }


        }

    }
}
