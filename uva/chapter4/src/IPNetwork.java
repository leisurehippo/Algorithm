import java.util.*;

/**
 * Created by wj on 16/11/7.
 */
public class IPNetwork {
    //record the minimum and maximum values for each part of the IP address
    public static int [] ip = new int[8];
    public static void clear(){
        for (int i = 0; i < 8; i++) {
            ip[i] = 0;
        }
    }
    public static void addIp(String [] address, int i){
        //first add
        if (i == 0){
            for (int j = 0; j < 4; j++) {
                ip[j*2] = Integer.valueOf(address[j]);
                ip[j*2+1] = Integer.valueOf(address[j]);
            }
        }else{
            for (int j = 0; j < 4; j++) {
                if (Integer.valueOf(address[j])<ip[j*2]){
                    ip[j*2] = Integer.valueOf(address[j]);
                }else if (Integer.valueOf(address[j])>ip[j*2+1]){
                    ip[j*2+1] = Integer.valueOf(address[j]);
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()){
            int number = Integer.valueOf(input.nextLine());
            clear();//init ip with 0
            //record IP address
            for (int i = 0; i < number; i++) {
                String [] address = input.nextLine().split("\\.");
                addIp(address,i);
            }
            //calculate the ip address and network mask
            String ip_min = "", ip_max = "";
            String ip_address_tmp = "", network_mask_tmp = "";
            int mask = 0;
            //find the different part of the ip address
            //record in binary form
            for (int i = 0; i < 4; i++) {
                if (ip[i*2] == ip[i*2+1]){
                    int tmp = Integer.valueOf(Integer.toBinaryString(ip[i*2]));
                    ip_address_tmp += String.format("%08d",tmp);
                    mask += 8;
                }else{
                    for (int j = i; j < 4; j++) {
                        int tmp_min = Integer.valueOf(Integer.toBinaryString(ip[j*2]));
                        int tmp_max = Integer.valueOf(Integer.toBinaryString(ip[j*2+1]));
                        ip_min += String.format("%08d",tmp_min);
                        ip_max += String.format("%08d",tmp_max);
                    }
                }
            }
            int index = 0;
            for (int i = 0; i < ip_max.length(); i++) {
                if (ip_min.charAt(i) == ip_max.charAt(i)){
                    ip_address_tmp += ip_min.charAt(i);
                }else{
                    index = i;
                    break;
                }
            }
            for (int i = 0; i < 32; i++) {
                if (i<index+mask)
                    network_mask_tmp += "1";
                else {
                    network_mask_tmp += "0";
                    ip_address_tmp += "0";
                }
            }
            String ip_address = "", network_mask = "";
            for (int i = 0; i < 4; i++) {
                int tmp_address = Integer.parseInt(ip_address_tmp.substring(i*8,i*8+8),2);
                ip_address += tmp_address+".";
                int tmp_mask = Integer.parseInt(network_mask_tmp.substring(i*8,i*8+8),2);
                network_mask += tmp_mask+".";
            }
            ip_address = ip_address.substring(0,ip_address.length()-1);
            network_mask = network_mask.substring(0,network_mask.length()-1);
            System.out.println(ip_address);
            System.out.println(network_mask);



        }
    }
}
