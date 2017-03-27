import java.io.*;
import java.math.BigInteger;

public class fibonacci {

    public static void main(String[] args) {

        FileWriter fw;
        try {
            fw = new FileWriter("fibonacci1.txt");
            BigInteger a = new BigInteger("1");
            BigInteger b = new BigInteger("1");
            for (int i = 2; i < 227; i++) {
                BigInteger c = a.add(b);
                String str = c.toString();
                fw.write(i+" "+str+"\n");
                a = b;
                b = c;
            }

            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}