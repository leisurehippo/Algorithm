package Exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PetriNetSimulation {
    //the token of each place
    public static int []token;
    //KEY->transition_id    VALUE->key:input  place id  value:count
    public static Map<Integer,Map<Integer,Integer>> enter = new HashMap<>();
    //KEY->transition_id    VALUE->key:output place id  value:count
    public static Map<Integer,Map<Integer,Integer>> exit = new HashMap<>();

    public static void setMap(int put, int i, boolean is_enter){
        Map<Integer,Map<Integer,Integer>> map_op = is_enter ? enter : exit;
        Map<Integer,Integer> mapop_value = new HashMap<>();
        if (map_op.containsKey(i))
            mapop_value = map_op.get(i);

        if (mapop_value.containsKey(put))
            mapop_value.put(put,mapop_value.get(put)+1);
        else
            mapop_value.put(put,1);
        map_op.put(i,mapop_value);
    }

    public static boolean judge_enable(Map<Integer,Integer> map, int op){
        boolean flag = true;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (op != 0)
                token[key] += op*value;
            else{
                if (value > token[key]){
                    flag = false;
                    break;
                }
            }

        }
        return flag;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int np;
        int cases = 1;
        while((np = input.nextInt()) != 0){
            //store the number of token of each place
            token = new int[np];
            for (int i = 0; i < np; i++)
                token[i] = input.nextInt();

            enter.clear();
            exit.clear();
            int nt = input.nextInt();
            //set the input and output for each transition
            for (int i = 0; i < nt; i++) {
                int put;
                while ((put = input.nextInt()) != 0)
                    setMap(Math.abs(put) - 1, i, put < 0);
            }

            int step = input.nextInt();
            int count = 0;
            boolean is_dead = false;
            System.out.print("Case " + cases++ + ": ");
            while(step != 0){
                //traversal each transition
                boolean enable = false;
                for (int i = 0; i < nt; i++) {
                    Map<Integer,Integer> enter_token = enter.get(i);
                    Map<Integer,Integer> exit_token = exit.get(i);
                    boolean flag = judge_enable(enter_token, 0);
                    if (flag){
                        enable = true;
                        step--;
                        count++;
                        judge_enable(enter_token, -1);
                        judge_enable(exit_token, 1);
                        break;
                    }
                }

                if (!enable){
                    is_dead = true;
                    System.out.print("dead after " + count + " transitions\n");
                    System.out.print("Places with tokens: ");
                    String result = "";
                    for (int i = 0; i < np; i++) {
                        if (token[i] != 0)
                            result += (i+1) + " (" + token[i] + ") ";
                    }
                    System.out.println(result.substring(0,result.length()-1)+"\n");
                    break;
                }
            }
            if (!is_dead){
                System.out.println("still live after " + count + " transitions");
                System.out.print("Places with tokens: ");
                String result = "";
                for (int i = 0; i < np; i++) {
                    if (token[i] != 0)
                        result += (i+1) + " (" + token[i] + ") ";
                }
                System.out.println(result.substring(0,result.length()-1)+"\n");
            }
        }
    }
}
