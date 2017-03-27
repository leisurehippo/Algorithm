import java.util.Scanner;

/**
 * Puzzle
 * uva227
 * Created by wj on 16/10/20.
 */
public class Puzzle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String end = new String();
        char [][] puzzle = new char[5][5];
        int count = 1;
        while(!(end = input.nextLine()).equals("Z")){
            //save in the array
            int line = 0;
            int x = 0, y = 0;//the empty position
            do {
                for (int i = 0; i < end.length(); i++) {
                    if (end.charAt(i) == ' '){
                        x = line;
                        y = i;
                    }
                    puzzle[line][i] = end.charAt(i);
                }
                if (end.length() == 4) {
                    x = line;
                    y = 4;
                    puzzle[line][4] = ' ';
                }
                line++;
                end = input.nextLine();
            }while(line<5);
            //save moves in move
            String move = end;
            while(end.charAt(end.length()-1) != '0'){
                end = input.nextLine();
                move += end;
            }
            boolean flag = false;
            for (int i = 0; i < move.length(); i++) {
                char m = move.charAt(i);
                switch (m) {
                    case 'A':
                        if (x-1 < 0){
                            flag = true;
                            break;
                        }else{
                            puzzle[x][y] = puzzle[x-1][y];
                            x = x-1;
                        }
                        break;
                    case 'B':
                        if (x+1 > 4){
                            flag = true;
                            break;
                        }else{
                            puzzle[x][y] = puzzle[x+1][y];
                            x = x+1;
                        }
                        break;
                    case 'R':
                        if (y+1 > 4){
                            flag = true;
                            break;
                        }else{
                            puzzle[x][y] = puzzle[x][y+1];
                            y = y+1;
                        }
                        break;
                    case 'L':
                        if (y-1 < 0){
                            flag = true;
                            break;
                        }else {
                            puzzle[x][y] = puzzle[x][y-1];
                            y = y-1;
                        }
                        break;
                    default:
                        break;
                }
                if (flag)
                    break;
            }
            puzzle[x][y] = ' ';
            if (count != 1)
                System.out.print("\n");
            System.out.println("Puzzle #"+count+":");
            count++;
            if (flag)
                System.out.println("This puzzle has no final configuration.");
            else{
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        System.out.print(puzzle[i][j]+" ");
                    }
                    System.out.println(puzzle[i][4]);
                }
            }

        }

    }
}
