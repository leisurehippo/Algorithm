import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by wj on 16/11/13.
 */
class Student{
    private int wake;
    private int sleep;
    private int loc;

    public void setWake(int wake) {
        this.wake = wake;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    public int getLoc() {
        return loc;
    }

    public int getSleep() {
        return sleep;
    }

    public int getWake() {
        return wake;
    }
}
class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = new String();
        while(!(str=input.nextLine()).equals("0")){
            int number = Integer.valueOf(str);
            Student [] students = new Student[number];
            int sleep = 0;
            int [] start = new int[10];
            for (int i = 0; i < number; i++) {
                StringTokenizer token = new StringTokenizer(input.nextLine());
                students[i].setWake(Integer.valueOf(token.nextToken()));
                students[i].setSleep(Integer.valueOf(token.nextToken()));
                students[i].setLoc(Integer.valueOf(token.nextToken()));
                start[i] = students[i].getLoc() - 1;
                if (students[i].getLoc() > students[i].getWake()){
                    sleep++;
                }
            }
            int time = 0;
            while(++time < 65536){
                
            }
        }
    }
}
