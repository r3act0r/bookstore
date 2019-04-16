package edu.ua.culverhouse.mis.bookstore;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Book> myBooks = BookFile.GetAllBooks("11272757");
        for( Book book : myBooks ) {
            System.out.println(book);
        }
    }
}
