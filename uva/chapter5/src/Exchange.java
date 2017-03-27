import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by wj on 16/12/16.
 */
class Message {
    private int id;
    private int size;
    private int price;
    private int isactive;
    private boolean buy;

    public Message(int id, int size, int price, int isactive, boolean buy) {
        this.id = id;
        this.size = size;
        this.price = price;
        this.isactive = isactive;
        this.buy = buy;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public int getIsactive() {
        return isactive;
    }

    public boolean isBuy() {
        return buy;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
public class Exchange{

    public static int recompare(Message o1, Message o2, boolean flag){
        if (o1.getIsactive() == 1 && o2.getIsactive() == 1){
            if (o1.getPrice() == o2.getPrice()){
                return o1.getId() - o2.getId();
            }else{
                int res = o1.getPrice() - o2.getPrice();
                return flag ? -res : res;
            }
        }else{
            if (o1.getIsactive() == 0){
                return 1;
            }else{
                return -1;
            }
        }
    }

    public static void main(String[] args) {

        //higher price has the higher priority
        Comparator<Message> OrderBuy =  new Comparator<Message>(){
            public int compare(Message o1, Message o2) {
                return recompare(o1, o2, true);
            }
        };
        //lower price has the higher priority
        Comparator<Message> OrderSell =  new Comparator<Message>(){
            public int compare(Message o1, Message o2) {
                return recompare(o1, o2, false);
            }
        };


        //the message to buy
        Queue<Message> buy = new PriorityBlockingQueue<>(10000,OrderBuy);
        //the message to sell
        Queue<Message> sell = new PriorityBlockingQueue<>(10000,OrderSell);
        //key: id  value: message
        //use to modify the canceled message
        Map<Integer,Message> map = new HashMap<>();
        //record the size of buy order
        Map<Integer,Integer> buyMap = new HashMap<>();
        //record the sell order
        Map<Integer,Integer> sellMap = new HashMap<>();

        Scanner input = new Scanner(System.in);
        int count = 0;
        String str = new String();
        while(!(str=input.nextLine()).equals("")){
            if (count != 0)
                System.out.print("\n");
            count++;

            buy.clear();
            sell.clear();
            map.clear();
            buyMap.clear();
            sellMap.clear();

            int num = Integer.valueOf(str);
            //each test case
            for (int i = 0; i < num; i++) {
                StringTokenizer token = new StringTokenizer(input.nextLine());
                while(token.hasMoreTokens()){
                    String command = token.nextToken();
                    int bid_size = 0, bid_price = 0, ask_size = 0, ask_price = 99999;

                    if (command.equals("CANCEL")){
                        int cancelId = Integer.valueOf(token.nextToken());//canceled message`s id
                        Message cancelMessage = map.get(cancelId);//canceled message
                        //canceled message is active before
                        if (cancelMessage.getIsactive() != 0){
                            cancelMessage.setIsactive(0);//set it into not active

                            if (cancelMessage.isBuy()) {
                                //it is buy order
                                buy.remove(cancelMessage);//remove it from buy queue
                                //change the size of buyMap
                                int cancel_price = cancelMessage.getPrice();
                                int cancel_size = cancelMessage.getSize();
                                buyMap.put(cancel_price,buyMap.get(cancel_price)-cancel_size);

                            } else {
                                //it is sell order
                                sell.remove(cancelMessage);//remove it from sell queue
                                //change the size of sellMap
                                int cancel_price = cancelMessage.getPrice();
                                int cancel_size = cancelMessage.getSize();
                                sellMap.put(cancel_price,sellMap.get(cancel_price)-cancel_size);

                            }
                        }
                        //calculator bid_price and ask_price
                        if (!buy.isEmpty()){
                            bid_price = buy.peek().getPrice();
                        }
                        if (!sell.isEmpty()){
                            ask_price = sell.peek().getPrice();
                        }

                    }else{
                        //get the size and price
                        int size = Integer.valueOf(token.nextToken());
                        int price = Integer.valueOf(token.nextToken());

                        if (command.equals("BUY")){
                            //buy order
                            Message message = new Message(i,size,price,1,true);
                            buy.add(message);//add into the buy queue
                            //update buyMap
                            if (buyMap.containsKey(price)){
                                int old_size = buyMap.get(price);
                                buyMap.put(price,size+old_size);
                            }else{
                                buyMap.put(price,size);
                            }
                            map.put(i+1,message);//put into the map
                        }else{
                            //sell order
                            Message message = new Message(i,size,price,1,false);
                            sell.add(message);//add into the sell order
                            //update sellMap
                            if (sellMap.containsKey(price)){
                                int old_size = sellMap.get(price);
                                sellMap.put(price,size+old_size);
                            }else{
                                sellMap.put(price,size);
                            }
                            map.put(i+1,message);//put into the map
                        }


                        boolean buy_active = false;
                        Message bestBuy = buy.poll();
                        Message bestSell = sell.poll();
                        //calculator current bid_price and ask_price
                        if (!(null == bestBuy) && bestBuy.getIsactive() != 0) {
                            bid_size = bestBuy.getSize();
                            bid_price = bestBuy.getPrice();
                            buy_active = true;
                        }
                        if (!(null == bestSell) && bestSell.getIsactive() != 0){
                            ask_size = bestSell.getSize();
                            ask_price = bestSell.getPrice();
                        }

                        if (!buy_active)
                            sell.add(bestSell);
                        while(buy_active){
                            //trade is generated
                            if (bid_price >= ask_price){
                                //trade size and price
                                int trade_size = Math.min(bid_size,ask_size);
                                int trade_price = command.equals("BUY")?ask_price:bid_price;
                                System.out.println("TRADE "+trade_size+" "+trade_price);

                                //update the sell queue
                                if (ask_size > trade_size) {
                                    bestSell.setSize(ask_size - trade_size);
                                    sell.add(bestSell);
                                }
                                else {
                                    bestSell.setIsactive(0);
                                }
                                sellMap.put(ask_price,sellMap.get(ask_price)-trade_size);//update sellMap

                                //update the buy queue
                                if (bid_size > trade_size) {
                                    bestBuy.setSize(bid_size - trade_size);
                                    buy.add(bestBuy);
                                }
                                else {
                                    bestBuy.setIsactive(0);
                                }
                                buyMap.put(bid_price,buyMap.get(bid_price)-trade_size);//update buyMap

                            }else{
                                buy.add(bestBuy);
                                if (!(null == bestSell))
                                    sell.add(bestSell);
                                break;
                            }

                            //the trade continues
                            bestBuy = buy.poll();
                            bestSell = sell.poll();
                            //calculator current bid_price and ask_price
                            if (!(null == bestBuy)) {
                                bid_size = bestBuy.getSize();
                                bid_price = bestBuy.getPrice();
                                buy_active = true;
                            }else{
                                buy_active = false;
                                sell.add(bestSell);
                            }
                            if (!(null == bestSell)){
                                ask_size = bestSell.getSize();
                                ask_price = bestSell.getPrice();
                            }else{
                                ask_price = 99999;
                                ask_size = 0;
                            }


                        }
                        bid_size = 0;
                        bid_price = 0;
                        ask_size = 0;
                        ask_price = 99999;
                        if (!buy.isEmpty() && buy.peek().getIsactive() != 0)
                            bid_price = buy.peek().getPrice();
                        if (!sell.isEmpty() && sell.peek().getIsactive() != 0)
                            ask_price = sell.peek().getPrice();
                    }

                    //calculator the bid_size and ask_size
                    if (buyMap.containsKey(bid_price))
                        bid_size = buyMap.get(bid_price);
                    if (sellMap.containsKey(ask_price))
                        ask_size = sellMap.get(ask_price);

                    System.out.println("QUOTE "+bid_size+" "+bid_price+" - "+ask_size+" "+ask_price);

                }
            }
            String t = input.nextLine();
        }
    }
}
