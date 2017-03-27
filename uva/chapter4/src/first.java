import java.util.Scanner;

/**
 * Created by weimengxi on 16/11/6.
 */
public class first {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int num =s.nextInt();
        int count = 1;
        while(num>0){
            num--;
            String str = s.next();
            int a = s.nextInt();
            int b = s.nextInt();

            int k =getBlueTimes( str.length(), str, b)-getBlueTimes(str.length(),str,a);
            if(a%str.length()>=1){
                if(str.charAt(a%str.length()-1)=='B'){
                    k++;
                }
            }else{
                if(str.charAt(str.length()-1)=='B'){
                    k++;
                }
            }

            System.out.println("Case #"+count+": "+k);
            count++;
        }
    }
    public static int getBlueTimes(int len,String str,int pos){
        int times = pos/len;
        int rest = pos%len;
        int count1=0,count2=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='B'){
                count1++;
            }
        }
        for(int i=0;i<rest;i++){
            if(str.charAt(i)=='B'){
                count2++;
            }
        }
        return count1*times+count2;
    }
}
