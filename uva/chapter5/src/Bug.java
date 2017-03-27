import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by wj on 16/12/12.
 */
public class Bug {

    /**
     * key:   arrayName
     * value: size
     */
    public static Map<String,Integer> map = new HashMap<>();
    /**
     * key:   arrayName
     * value: a map(key:index  value:value) of assigned element
     */
    public static Map<String,Map<Integer,Integer>> assigned = new HashMap<>();

    /**
     * judge whether a string is a integer
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * judge whether a expression is valid
     * @param str
     * @return
     */
    public static int []judge(String str){
        int []result = {1,0};
        //is a number
        if (isNumeric(str)){
            result[0] = 0;
            result[1] = Integer.valueOf(str);
        }else{
            int index = str.indexOf("[");
            String arrayName = str.substring(0,index);
            String expression = str.substring(index+1,str.length()-1);
            int []judgeRes = judge(expression);
            //the expression is valid and the index is valid
            if (judgeRes[0] == 0 && judgeRes[1] < map.get(arrayName) && judgeRes[1] >= 0){
                Map<Integer,Integer> m = assigned.get(arrayName);
                //the assignment is valid
                if (!m.isEmpty() && m.containsKey(judgeRes[1])){
                    result[0] = 0;
                    result[1] = m.get(judgeRes[1]);
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = new String();
        while (!(str = input.nextLine()).equals(".")) {
            map.clear();
            assigned.clear();
            int line = 0;
            boolean flag = true;
            while (!str.equals(".")){
                if (flag){
                    String [] tmp = str.split("=");
                    //declaration
                    if (tmp.length == 1){
                        String []s = str.split("\\[");
                        int size = Integer.valueOf(s[1].substring(0,s[1].length()-1));
                        map.put(s[0],size);
                        Map<Integer,Integer> emptyMap = new HashMap<>();
                        assigned.put(s[0],emptyMap);
                    }else{
                        String assignment = tmp[0];
                        String expression = tmp[1];
                        int []res = judge(expression);
                        if (res[0] != 0){
                            flag = false;
                        }else{
                            int number = res[1];
                            int index = assignment.indexOf("[");
                            String arrayName = assignment.substring(0,index);
                            String exp = assignment.substring(index+1,assignment.length()-1);
                            res = judge(exp);
                            if (res[0] != 0){
                                flag = false;
                            }else{
                                if (res[1] >= map.get(arrayName) || res[1] < 0){
                                    flag = false;
                                }else{
                                    Map<Integer,Integer> m = assigned.get(arrayName);
                                    m.put(res[1],number);
                                }
                            }
                        }

                    }
                    line++;
                }


                str = input.nextLine();
            }

            if (flag){
                System.out.println("0");
            }else{
                System.out.println(line);
            }
        }
    }
}
