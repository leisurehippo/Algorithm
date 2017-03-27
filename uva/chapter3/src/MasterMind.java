import java.util.Scanner;

/**
 * 猜数字游戏的提示
 * UVA340未通过
 * Created by wj on 16/10/17.
 */
class MasterMind {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String len = new String();
        int count = 1;
        while(!(len = input.nextLine()).equals("0")){

            String end = "0";
            for (int i = 0; i < Integer.valueOf(len) - 1; i++) {
                end += " 0";
            }
            String strCorrect = input.nextLine();
            String [] correct = strCorrect.split(" ");
            String stranswer = new String();
            System.out.print("Game "+count+++":\n");
            while(!(stranswer = input.nextLine()).equals(end)){
                int count1 = 0, count2 = 0;
                int [] correctnum = new int[9];
                int [] answernum  = new int[9];
                String [] answer = stranswer.split(" ");
                for (int i = 0; i < Integer.valueOf(len); i++) {
                    if (answer[i].equals(correct[i]))
                        count1++;
                    else{
                        correctnum[Integer.valueOf(correct[i])-1] ++;
                        answernum[Integer.valueOf(answer[i])-1] ++;
                    }
                }
                for (int i = 0; i < 9; i++) {
                    count2 += answernum[i]>correctnum[i]?correctnum[i]:answernum[i];
                }
                System.out.print("    ("+count1+","+count2+")\n");
            }

        }
        //System.out.print("\n");
    }
}
