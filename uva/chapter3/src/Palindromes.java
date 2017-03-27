import java.util.Scanner;

/**
 * 判断一个字符串是否是回文串或镜像串
 * UVa401
 * Created by wj on 16/10/13.
 */
public class Palindromes {
    public final static String rev = "A   3  HIL JM O   2TUVWXY51SE Z  8 ";
    public final static String []msg = {
            " -- is not a palindrome.",
            " -- is a regular palindrome.",
            " -- is a mirrored string.",
            " -- is a mirrored palindrome."};

    /**
     * 判断一个字符串是否是镜像串
     * @param c
     * @return
     */
    public static char mirror(char c){
        if(Character.isLetter(c)){
            return rev.charAt(c-'A');
        }else{
            return rev.charAt(c-'1'+26);
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String in = input.nextLine();
            int palindrome = 1,mirrored = 1;
            char [] charArr = in.toCharArray();
            int len = in.length();
            for (int i = 0; i < (len+1)/2; i++) {
                if (charArr[len-1-i] != charArr[i])
                    palindrome = 0;
                if (charArr[len-1-i] != mirror(charArr[i]))
                    mirrored = 0;
            }
            System.out.println(in+msg[palindrome+mirrored*2]+"\n");
        }
    }
}
