import java.util.*;

/**
 * Created by wj on 16/12/12.
 */
class Web {
    private int webDocu;
    private int webLine;

    public Web(int webDocu, int webLine) {
        this.webDocu = webDocu;
        this.webLine = webLine;
    }

    public int getWebDocu() {
        return this.webDocu;
    }

    public int getWebLine() {
        return this.webLine;
    }

}

public class WebSearch{

    /**
     * key:   word
     * value: List<Web>
     */
    public static Map<String,List<Web>> map = new HashMap<>();
    /**
     * key:   word
     * value: index of document
     */
    public static Map<String,Set<Integer>> docMap = new HashMap<>();
    //record each documents
    public static String []documents = new String[100];

    public static void findLines(Set<Integer> document, List<Web> arr){

        int n = 0;
        //the current printed document, ues to judge whether to print "----------"
        int current = arr.get(n).getWebDocu();
        while(!document.contains(current)){
            n++;
            current = arr.get(n).getWebDocu();
        }
        for (int i = n; i < arr.size(); i++) {
            int docu = arr.get(i).getWebDocu();
            int line = arr.get(i).getWebLine();
            if (document.contains(docu)) {
                if (docu != current){
                    System.out.println("----------");
                }
                String doc = documents[docu];
                String [] lines = doc.split("\n");
                System.out.println(lines[line-1]);
                current = docu;
            }
        }

    }
    public static void main(String[] args) {

        Comparator<Web> OrderIsdn =  new Comparator<Web>(){
            public int compare(Web o1, Web o2) {
                int webDocu1 = o1.getWebDocu();
                int webDocu2 = o2.getWebDocu();
                int webLine1 = o1.getWebLine();
                int webLine2 = o2.getWebLine();
                if (webDocu1 == webDocu2){
                    return webLine1 - webLine2;
                }else{
                    return webDocu1 - webDocu2;
                }
            }
        };

        Scanner input = new Scanner(System.in);
        //documents
        int num = Integer.valueOf(input.nextLine());
        //<editor-fold desc="load documents">
        for (int i = 0; i < num; i++) {
            String str = new String();
            int line = 0;
            String doc = "";
            while (!(str = input.nextLine()).equals("**********")){
                line++;
                doc += str+"\n";

                Web web = new Web(i,line);
                Set<String> dup = new HashSet<>();
                StringTokenizer token = new StringTokenizer(str,",|.|-| ");
                //<editor-fold desc="Description">
                while(token.hasMoreTokens()){

                    String word = token.nextToken().toLowerCase();
                    if (!dup.contains(word)){
                        if (map.containsKey(word)){
                            List<Web> list = map.get(word);
                            list.add(web);
                        }else{
                            List<Web> list = new ArrayList<>();
                            list.add(web);
                            map.put(word,list);
                        }
                        if (docMap.containsKey(word)){
                            Set<Integer> set = docMap.get(word);
                            set.add(i);
                        }else{
                            Set<Integer> set = new HashSet<>();
                            set.add(i);
                            docMap.put(word,set);
                        }
                        dup.add(word);
                    }
                }
                //</editor-fold>
            }
            documents[i] = doc;
        }
        //</editor-fold>

        //queries
        //record the result set of docMap
        Set<Integer> result = new HashSet<>();
        //the complete set of documents
        Set<Integer> documentsIndex = new HashSet<>();
        for (int i = 0; i < num; i++) {
            documentsIndex.add(i);
        }
        int qnum = Integer.valueOf(input.nextLine());
        for (int i = 0; i < qnum; i++) {
            String query = input.nextLine();
            StringTokenizer token = new StringTokenizer(query);
            while (token.hasMoreTokens()) {
                String word = token.nextToken();
                if (word.equals("NOT")){
                    //"NOT word"
                    word = token.nextToken();
                    result.clear();
                    //if the word is in the docMap, then calculator the difference set
                    if (docMap.containsKey(word)){
                        Set<Integer> include = docMap.get(word);
                        result.addAll(documentsIndex);
                        result.removeAll(include);
                    }else{
                        //else calculator the complete set
                        result.addAll(documentsIndex);
                    }

                    //print the result
                    if (result.isEmpty()) {
                        System.out.println("Sorry, I found nothing.");
                    }else{
                        //let the result in order and record into the arraylist
                        ArrayList<Integer> a = new ArrayList<>();
                        Iterator iter = result.iterator();
                        while(iter.hasNext()){
                            a.add((int)iter.next());
                        }
                        Collections.sort(a);

                        for (int j = 0; j < a.size(); j++) {
                            int index = a.get(j);
                            if (j != 0)
                                System.out.println("----------");

                            System.out.print(documents[index]);
                        }
                    }

                }else{

                    if (token.hasMoreTokens()){
                        String op = token.nextToken();
                        String anotherWord = token.nextToken();

                        result.clear();
                        if (op.equals("AND")){
                            //"word1 AND word2"
                            if (docMap.containsKey(word) && docMap.containsKey(anotherWord)){
                                result.addAll(docMap.get(word));
                                result.retainAll(docMap.get(anotherWord));
                            }
                        }else{
                            //"word1 OR word2"
                            if (docMap.containsKey(word))
                                result.addAll(docMap.get(word));

                            if (docMap.containsKey(anotherWord))
                                result.addAll(docMap.get(anotherWord));

                        }

                        if (result.isEmpty()){
                            System.out.println("Sorry, I found nothing.");
                        }else{

                            List<Web> s = new ArrayList<>();
                            if (map.containsKey(word)){
                                List<Web> list1 = map.get(word);
                                for (Web web : list1){
                                    if (!s.contains(web)){
                                        s.add(web);
                                    }
                                }
                            }
                            if (map.containsKey(anotherWord)){
                                List<Web> list2 = map.get(anotherWord);
                                for (Web web : list2){
                                    if (!s.contains(web)){
                                        s.add(web);
                                    }
                                }
                            }

                            Collections.sort(s,OrderIsdn);
                            findLines(result,s);
                        }

                    }else{
                        if (docMap.containsKey(word)){
                            Set<Integer> set = docMap.get(word);
                            List<Web> s = map.get(word);
                            Collections.sort(s,OrderIsdn);
                            findLines(set,s);
                        }else{
                            System.out.println("Sorry, I found nothing.");
                        }
                    }
                }
                System.out.println("==========");
            }
        }

    }
}
