import java.util.*;

/**
 * Created by wj on 16/12/5.
 */
public class MTA {
    public static Set<String> mtas = new HashSet<>();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //read the MTA description and add into the mtas for each user
        String str = new String();
        while (!(str = input.nextLine()).equals("*")){
            StringTokenizer token = new StringTokenizer(str);
            token.nextToken();
            String mta = token.nextToken();
            int num = Integer.valueOf(token.nextToken());
            for (int i = 0; i < num; i++) {
                String user = token.nextToken()+"@"+mta;
                mtas.add(user);
            }
        }
        while (!(str = input.nextLine()).equals("*")){
            ArrayList<String> sending = new ArrayList<>();
            Map<String,Integer> map = new HashMap<>();
            //get the sender
            StringTokenizer token = new StringTokenizer(str);
            String sender = token.nextToken();
            String [] tmp = sender.split("@");
            String senderMta = tmp[1];
            //get the recipient identifiers
            while (token.hasMoreTokens()){
                String recipient = token.nextToken();
                String [] t = recipient.split("@");
                String mtaname = t[1];
                if (map.containsKey(mtaname)){
                    if (!sending.contains(recipient)){
                        int index = map.get(mtaname);
                        map.replace(mtaname,index+1);
                        sending.add(index,recipient);
                    }
                }else{
                    int index = sending.size();
                    sending.add(index,recipient);
                    map.put(mtaname,index+1);
                }
            }
            //get message
            input.nextLine();
            String message = "";
            while (!(str = input.nextLine()).equals("*")){
                message += "     "+str + "\n";
            }
            //print
            int index = 0;
            while (index < sending.size()){
                boolean flag = false;
                String [] t = sending.get(index).split("@");
                String target = t[1];
                System.out.println("Connection between "+senderMta+" and "+target);
                System.out.println("     HELO "+senderMta);
                System.out.println("     250");
                System.out.println("     MAIL FROM:<"+sender+">");
                System.out.println("     250");

                while (index < sending.size()){
                    String [] tmparr = sending.get(index).split("@");
                    String tmptarget = tmparr[1];
                    if (tmptarget.equals(target)){
                        System.out.println("     RCPT TO:<"+sending.get(index)+">");
                        if (mtas.contains(sending.get(index))){
                            flag = true;
                            System.out.println("     250");
                        }else{
                            System.out.println("     550");
                        }
                        index++;
                    }else{
                        break;
                    }
                }
                if (flag){
                    System.out.println("     DATA\n     354\n"+message+"     .\n     250");
                }
                System.out.println("     QUIT");
                System.out.println("     221");
            }




        }

    }
}
