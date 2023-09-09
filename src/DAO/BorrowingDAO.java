package DAO;

import DTO.Book;
import DTO.Borrower;
import DTO.Borrowing;
import Database.DbConnection;
import Interfaces.BorrowingInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDAO implements BorrowingInterface {
    Connection connection = new DbConnection().conn();
    @Override
    public Borrower create(Borrowing borrowing) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("INSERT INTO Borrowing (bookId,borrowerId,startDate) values (?,?,?)");
            statement.setInt(1,borrowing.getBook().getISBN());
            statement.setInt(2, borrowing.getBorrower().getId());
            statement.setDate(3, (Date) borrowing.getStartDate());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean returnBook(int ISBN ,Date returnDate) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(" UPDATE borrowing SET returnDate =? WHERE bookId = ?");
            statement.setDate(1, returnDate);
            statement.setInt(2, ISBN);
            statement.execute();
            return true;
        }catch (SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public List<Borrowing> showBorrowedBooks() {
        List<Borrowing> borrowings = new ArrayList<>();
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM Book JOIN Borrowing ON Book.ISBN = Borrowing.bookId JOIN Borrower ON Borrowing.borrowerId= Borrower.id where statut = 'disponible'" );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Borrowing b = new Borrowing();
                b.getBook().setISBN(resultSet.getInt("ISBN"));
                b.getBook().setTitle(resultSet.getString("title"));
                b.getBook().setAuthor(resultSet.getString("author"));
                b.setStartDate(resultSet.getDate("startDate"));
                b.getBorrower().setName(resultSet.getString("name"));
                b.getBorrower().setMemberNum(resultSet.getInt("memberNum"));
                borrowings.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return borrowings;
    }
}