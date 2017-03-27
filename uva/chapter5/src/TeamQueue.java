import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wj on 16/12/3.
 */
public class TeamQueue {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = new String();
        int count = 0;
        while(!(str = input.nextLine()).equals("0")){
            count++;
            //which team do elements belong to
            //key to the element`s id, value to the team
            Map<Integer,Integer> map = new HashMap<>();
            for (int i = 0; i < Integer.valueOf(str); i++) {
                StringTokenizer token = new StringTokenizer(input.nextLine());
                int num = Integer.valueOf(token.nextToken());
                for (int j = 0; j < num; j++) {
                    int id = Integer.valueOf(token.nextToken());
                    map.put(id,i);
                }
            }

            System.out.println("Scenario #"+count);
            //for each team, record the element order
            Map<Integer,Queue> team = new HashMap<>();
            //the main queue, records the team order
            Queue<Integer> queue = new LinkedBlockingQueue<>();
            String command = new String();
            while(!(command = input.nextLine()).equals("STOP")){
                if (command.equals("DEQUEUE")){
                    int fteam = queue.peek();
                    Queue tmp = team.get(fteam);
                    int res = (int)tmp.poll();
                    if (tmp.isEmpty()){
                        queue.remove();
                    }
                    System.out.println(res);
                }else{
                    String [] tmp = command.split(" ");
                    int id = Integer.valueOf(tmp[1]);//the element id
                    int teamId = map.get(id);//the team id of the element
                    //the team is already in the queue
                    if (queue.contains(teamId)){
                        //add the element into the team
                        Queue newQueue = team.get(teamId);
                        newQueue.add(id);
                        team.replace(teamId,newQueue);

                    }else{
                        //add the tram into the queue
                        queue.add(teamId);
                        //create this team and add into the vector
                        Queue<Integer> newTeam = new LinkedBlockingQueue<>();
                        newTeam.add(id);
                        team.put(teamId,newTeam);
                    }
                }
            }
            System.out.print("\n");
        }
    }
}
