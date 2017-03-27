import java.util.Scanner;

/**
 * Created by wj on 16/10/22.
 */
public class DNAConsensusString {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String len = input.nextLine();
        for (int i = 0; i < Integer.valueOf(len); i++){
            int row = input.nextInt();
            int col = input.nextInt();

            String dna = input.nextLine();
            int [][] nucleotides = new int[col][20];
            for (int j = 0; j < row; j++) {
                dna = input.nextLine();
                for (int k = 0; k < col; k++)
                    nucleotides[k][dna.charAt(k)-'A']++;
            }
            int count = 0;
            for (int j = 0; j < col; j++) {
                int max = 0, index = 0, sum = 0;
                for (int k = 0; k < 20; k++) {
                    sum += nucleotides[j][k];
                    if (nucleotides[j][k] > max){
                        max = nucleotides[j][k];
                        index = k;
                    }
                }
                sum -= max;
                count += sum;
                char tmp = (char)(index+'A');
                System.out.print(tmp);
            }
            System.out.println("\n"+count);

        }
    }
}
