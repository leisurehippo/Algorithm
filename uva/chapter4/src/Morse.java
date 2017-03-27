import java.util.*;

/**
 * Created by wj on 16/11/9.
 */
public class Morse {
    public static HashMap<String,String> code = new HashMap<>();
    public static TreeMap<String,String> context = new TreeMap<>();

    /**
     * encode
     *
     * encode the str with Morse code
     *
     * @param str
     * @return
     */
    public static String encode(String str){
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            String tmp = str.substring(i,i+1);
            res += code.get(tmp);
        }
        return res;
    }

    /**
     * judgeError
     *
     * judge whether elements may be either
     * truncated from the end of a morse word
     * or added to the end of a morse word
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int judgeError(String str1, String str2){
        String a = str1;
        String b = str2;
        if (str1.length() > str2.length()){
            a = str2;
            b = str1;
        }
        if (!a.equals(b.substring(0,a.length())))
            return 200;
        else
            return b.length()-a.length();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String key = new String();
        //read the Morse code and store in the hashmap -- code
        while(!(key=input.next()).equals("*")){
            String value = input.next();
            code.put(key,value);
        }
        //read the context section and store in the hashmap -- context
        String str = new String();
        while(!(str=input.next()).equals("*")){
            context.put(str,encode(str));
        }
        //read the morse word and
        //find the appropriate matching word from context
        String morse = new String();
        while(!(morse=input.next()).equals("*")){
            int fit = 0;//the number of match
            int fit_length = 200;
            String fit_word = new String();//the first matched word
            String error_word = new String();
            //match all the word in the context
            for (Map.Entry<String, String> entry : context.entrySet()){
                String word = entry.getKey();
                String value = entry.getValue();
                //match perfectly
                if (morse.equals(value)){
                    fit++;
                    if (fit == 1){
                        fit_word = word;
                    }
                }else{
                    //no perfect match
                    if (fit < 1) {
                        //match the longest prefix of morse
                        //or has the fewest extra elements beyond those in morse
                        int tmp = judgeError(value, morse);
                        if (tmp < fit_length) {
                            fit_length = tmp;
                            error_word = word;
                        }
                    }
                }
            }

            if (fit > 1){
                System.out.println(fit_word+"!");
            }else if (fit == 1){
                System.out.println(fit_word);
            }else{
                System.out.println(error_word+"?");
            }
        }

    }
}
