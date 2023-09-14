package DAO;

import DTO.Book;
import DTO.Borrower;
import Database.DbConnection;
import Interfaces.BorrowerInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowerDAO implements BorrowerInterface {
    Connection connection = new DbConnection().conn();
    @Override
    public int create(Borrower borrower) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("INSERT INTO Borrower (name,memberNum) values (?,?)");
            statement.setString(1,borrower.getName());
            statement.setInt(2, borrower.getMemberNum());
            statement.execute();
            return this.getId(borrower);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public int getId(Borrower borrower) {
        try {
            PreparedStatement statement = this.connection.prepareStatement("select * from borrower where name = ? And memberNum = ?");
            statement.setString(1,borrower.getName());
            statement.setInt(2,borrower.getMemberNum());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
