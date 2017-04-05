package Example;

import java.util.*;

/**
 * Created by wj on 17/2/17.
 */
class Matrix{
    String name;
    int row;
    int column;

    public Matrix(String name, int row, int column) {
        this.name = name;
        this.row = row;
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

}

public class MatrixChainMultiplication {
    public static Map<String,Matrix> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = Integer.valueOf(input.nextLine());
        //read the matrices and put them into map
        for (int i = 0; i < num; i++) {
            StringTokenizer token = new StringTokenizer(input.nextLine());
            String name = token.nextToken();
            int row = Integer.valueOf(token.nextToken());
            int column = Integer.valueOf(token.nextToken());
            Matrix matrix = new Matrix(name,row,column);
            map.put(name,matrix);
        }
        Stack<Matrix> stack = new Stack<>();
        //read the expressions
        while (input.hasNextLine()){
            String expression = input.nextLine();
            boolean flag = true;//the error flag
            stack.clear();
            int count = 0;//the number of elementary multiplications
            for (int i = 0; i < expression.length(); i++) {
                String str = expression.substring(i,i+1);
                if (str.equals("("))
                    continue;
                if (str.equals(")")){
                    Matrix b = stack.pop();
                    Matrix a = stack.pop();
                    if (a.getColumn() != b.getRow()){
                        flag = false;
                        break;
                    }else{
                        count += a.getColumn()*a.getRow()*b.getColumn();
                        String newName = a.getName()+b.getName();
                        int row = a.getRow();
                        int column = b.getColumn();
                        Matrix newm = new Matrix(newName,row,column);
                        map.put(newName,newm);
                        stack.push(newm);
                    }
                }else{
                    Matrix m = map.get(str);
                    stack.push(m);
                }
            }
            if (flag)
                System.out.println(count);
            else
                System.out.println("error");
        }
    }
}
