package Interfaces;

import DTO.Book;
import java.util.List;

public interface BookInterface {
    boolean create(Book book);
    boolean delete(Book book);
    boolean update(Book book);
    Book getOne(int ISBN);
    boolean lostBook(int ISBN);
    int countLostBooks();
    int countAvailableBooks();
    int countInvailableBooks();
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String author);

}
