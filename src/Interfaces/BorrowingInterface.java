package Interfaces;

import DTO.Borrower;
import DTO.Borrowing;

import java.sql.Date;
import java.util.List;

public interface BorrowingInterface {
    Borrower create(Borrowing borrowing);
    boolean returnBook(int ISBN, Date returnDate);
    List<Borrowing> showBorrowedBooks();
}
