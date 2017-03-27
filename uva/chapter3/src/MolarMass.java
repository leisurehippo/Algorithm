import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 计算分子量
 * UVA1586未通过
 * Created by wj on 16/10/20.
 */
public class MolarMass {
    public static void main(String[] args) {
        //Standard Atomic Weight
        HashMap<Character, Double> weight = new HashMap<Character, Double>();
        weight.put('C', 12.01);
        weight.put('H', 1.008);
        weight.put('O', 16.00);
        weight.put('N', 14.01);
        //get input length
        Scanner input = new Scanner(System.in);
        String len = input.nextLine();
        for (int i = 0; i < Integer.valueOf(len); i++) {
            //get formula
            String formula = input.nextLine();
            double mass = 0.0;//molar mass
            for (int j = 0; j < formula.length(); j++) {
                char atomic = formula.charAt(j);
                //judge whether is atomic
                if (Character.isLetter(atomic)){
                    //the number of atomic
                    int index = formula.length();
                    for (int k = j+1; k < formula.length(); k++) {
                        if (Character.isLetter(formula.charAt(k))){
                            index = k;
                            break;
                        }
                    }
                    int num = 1;
                    if (j != formula.length() - 1 && index - j > 1){
                        num = Integer.valueOf(formula.substring(j+1,index));
                    }

//                    int num = (index==j+1)?1:Integer.valueOf(
//                            (index==formula.length())?
//                                    formula.substring(j+1):formula.substring(j+1,index));
                    mass += weight.get(atomic) * num;
                }
            }
            DecimalFormat res = new DecimalFormat("#.000");
            System.out.println(res.format(mass));
        }
    }
}
