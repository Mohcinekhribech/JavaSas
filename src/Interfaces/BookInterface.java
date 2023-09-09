package Interfaces;

import DTO.Book;
import java.util.List;

public interface BookInterface {
    Book create(Book book);
    Book delete(Book book);
    Book update(Book book);
    Book getOne(int ISBN);
    int countLostBooks();
    int countAvailableBooks();
    int countInvailableBooks();
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String author);

}
