import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by H-E-Z on 2017/3/26.
 */
class Square{
    public int x, y;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Square(String string){
        char a = string.charAt(0);
        char b = string.charAt(1);
        this.x = a - 'a';
        this.y = b - '1';
    }
}
public class KnightMoves {
    public static int [][]graph;
    public static Queue<Square> queue;
    public static int []x = {-2,-1,1,2,2,1,-1,-2};
    public static int []y = {1,2,2,1,-1,-2,-2,-1};

    public static Square move(Square square, int m){
        Square res = new Square(square.x+x[m], square.y+y[m]);
        return res;
    }

    public static boolean inside(Square square){
        if (square.x >= 0 && square.x < 8 && square.y >= 0 && square.y < 8)
            return true;
        else
            return false;
    }

    public static void bfs(Square start, Square end){
        graph[start.x][start.y] = 0;
        queue.add(start);
        while (!queue.isEmpty()){
            Square square = queue.poll();
            if (square.x == end.x && square.y == end.y)
                break;
            for (int i = 0; i < 8; i++) {
                Square next = move(square,i);
                if (inside(next) && graph[next.x][next.y] == 0){
                    graph[next.x][next.y] = graph[square.x][square.y] + 1;
                    queue.add(next);
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            String start = input.next();
            String end = input.next();
            queue = new LinkedBlockingQueue<>();
            graph = new int[8][8];
            Square s = new Square(start);
            Square e = new Square(end);
            bfs(s, e);
            System.out.println("To get from " + start + " to " + end + " takes " + graph[e.x][e.y] + " knight moves.");
        }

    }
}
