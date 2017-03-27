import java.util.*;

/**
 * Created by wj on 16/12/8.
 */
class Book {
    private String title;
    private String author;

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }
    public String getTitle(){
        return this.title;
    }
    public String getAuthor(){
        return  this.author;
    }

}


public class Borrower{
    //all books
    public static List<Book> books = new ArrayList<>();
    //returned books
    public static List<Book> returned = new ArrayList<>();
    //key:title  value:author
    public static Map<String,String> map = new HashMap<>();

    //when a book is borrowed, remove it from the booklist
    public static void removeBook(String title){
        Iterator iter = books.iterator();
        while(iter.hasNext()){
            Book book = (Book)iter.next();
            if (book.getTitle().equals(title)){
                books.remove(book);
                break;
            }
        }
    }

    public static void main(String[] args) {

        Comparator<Book> OrderIsdn =  new Comparator<Book>(){
            public int compare(Book o1, Book o2) {
                String author1 = o1.getAuthor();
                String author2 = o2.getAuthor();
                String title1 = o1.getTitle();
                String title2 = o2.getTitle();
                if (author1.equals(author2)){
                    return title1.compareTo(title2);
                }else{
                    return author1.compareTo(author2);
                }
            }
        };

        Scanner input = new Scanner(System.in);
        String str = new String();
        //get all books
        while (!(str = input.nextLine()).equals("END")) {
            String [] tmp = str.split(" by ");//title by author
            Book book = new Book(tmp[0],tmp[1]);
            books.add(book);
            map.put(tmp[0],tmp[1]);
        }
        Collections.sort(books,OrderIsdn);

        while (!(str = input.nextLine()).equals("END")) {
            returned.clear();
            while (!str.equals("SHELVE")) {
                String command = str.substring(0,6);
                String title = str.substring(7);
                Book book = new Book(title,map.get(title));
                if (command.equals("BORROW")){
                    removeBook(title);
                }else{
                    returned.add(book);
                }
                str = input.nextLine();
            }
            Collections.sort(returned,OrderIsdn);
            //return each book
            Iterator iter = returned.iterator();
            while(iter.hasNext()){
                Book returnedBook = (Book)iter.next();
                books.add(returnedBook);
                Collections.sort(books,OrderIsdn);
                //get index
                int index = books.indexOf(returnedBook);
                if (index == 0){
                    System.out.println("Put "+returnedBook.getTitle()+" first");
                }else{
                    System.out.println("Put "+returnedBook.getTitle()+" after "+books.get(index-1).getTitle());
                }
            }
            System.out.println("END");
        }

    }
}
