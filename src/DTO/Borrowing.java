package DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Borrowing {
    private Date startDate;
    private Date returnDate;
    private Book book = new Book();
    private Borrower borrower = new Borrower();
    public Date getStartDate() {
        return startDate;
    }
    public Borrower getBorrower() {
        return borrower;
    }
    public Book getBook() {
        return book;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setEndDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}