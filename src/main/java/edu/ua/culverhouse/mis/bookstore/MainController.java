package edu.ua.culverhouse.mis.bookstore;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
         
        List<Book> myBooks = BookFile.GetAllBooks("11272757");
        for( Book book : myBooks ) {
            System.out.println(book + "\n");
        }

        /* Book test = myBooks.get(2);
        test.setTitle("The Telltale Heart");
        BookFile.SaveBook(test, "11272757", "edit"); */
    }
}
