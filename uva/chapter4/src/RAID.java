import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by wj on 16/11/11.
 */
public class RAID {
    public static String [][] data = new String[100][6];
    public static int disk = 0;//[2,6]
    public static int block_size = 0;//[1,64]
    public static int data_num = 0;//[1,100]
    public static int parity = 0;//0 for even parity,1 for odd parity

    /**
     * determine whether the data is valid using parity information
     * if two blocks become unavailable or the parity is wrong return false
     * else return true
     * @return
     */
    public static boolean judgeValid(){
        for (int i = 0; i < data_num; i++) {
            for (int j = 0; j < block_size; j++) {
                int unavailable = 0;
                int res = 0;
                int index = 0;
                for (int k = 0; k < disk; k++) {
                    if (unavailable > 1)
                        return false;
                    if (data[i][k].charAt(j) == 'x'){
                        unavailable++;
                        index = k;
                    }else{
                        res ^= data[i][k].charAt(j) - '0';
                    }
                }
                if ((unavailable == 0 && res != parity) || unavailable >1)
                    return false;
                if (unavailable == 1){
                    String s = data[i][index].substring(0,j) + String.valueOf(res^parity) + data[i][index].substring(j+1);
                    data[i][index] = s;
                }
            }
        }
        return true;
    }

    /**
     * recover the data
     * @return
     */
    public static String recoverData(){
        int parity_block = 0;
        String data_tmp = "";
        String res = "";
        //read the original data in data_tmp
        for (int i = 0; i < data_num; i++) {
            parity_block = parity_block%disk + 1;
            for (int j = 0; j < disk; j++) {
                if (j+1 != parity_block){
                    data_tmp += data[i][j];
                }
            }
        }
        //add extra ‘0’ bits at the end of the recovered data
        //so the number of bits is always a multiple of 4.
        while(data_tmp.length()%4 != 0){
            data_tmp += "0";
        }
        //Converts the binary data to hexadecimal
        for (int i = 0; i < data_tmp.length() / 4; i++) {
            String s = data_tmp.substring(i*4,(i+1)*4);
            res += Integer.toHexString(Integer.parseInt(s, 2));
        }
        return res.toUpperCase();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = new String();
        int count = 1;
        while(!(str=input.nextLine()).equals("0")){
            StringTokenizer token = new StringTokenizer(str);
            disk = Integer.valueOf(token.nextToken());//[2,6]
            block_size = Integer.valueOf(token.nextToken());//[1,64]
            data_num = Integer.valueOf(token.nextToken());//[1,100]
            parity = input.nextLine().equals("E")?0:1;//0 for even parity,1 for odd parity
            //record data into the data array, column for each disk
            for (int i = 0; i < disk; i++) {
                String disk_data = input.nextLine();
                for (int j = 0; j < data_num; j++) {
                    data[j][i] = disk_data.substring(j*block_size,(j+1)*block_size);
                }
            }
            boolean flag = judgeValid();
            if (flag){
                String recovered_data = recoverData();
                System.out.println("Disk set "+count+" is valid, contents are: "+recovered_data);
            }else{
                System.out.println("Disk set "+count+" is invalid.");
            }
            count++;
        }

    }
}
