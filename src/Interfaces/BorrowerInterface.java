package Interfaces;

import DTO.Borrower;

public interface BorrowerInterface {
    Borrower create(Borrower borrower);
    int getId(Borrower borrower);
}
