package DAO;

import DTO.Book;
import Database.DbConnection;
import Interfaces.BookInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookDAO implements BookInterface {

    Connection connection = new DbConnection().conn();
    @Override
    public boolean create(Book book)
    {
        try {
            PreparedStatement statement = this.connection.prepareStatement("INSERT INTO Book (ISBN,title,author,statut) values (?,?,?,?)");
            statement.setInt(1,book.getISBN());
            statement.setString(2,book.getTitle());
            statement.setString(3,book.getAuthor());
            statement.setString(4, String.valueOf(Book.Status.disponible));
            if(statement.execute())
            {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    @Override
    public boolean delete(Book book)
    {
        try {
            PreparedStatement statement = this.connection.prepareStatement("DELETE FROM Book WHERE ISBN = "+book.getISBN());
            if(statement.execute())
            {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    @Override
    public boolean update(Book book) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(" UPDATE book SET title = ? , author = ? , statut =? WHERE ISBN = ?");
            statement.setString(1,book.getTitle());
            statement.setString(2,book.getAuthor());
            statement.setString(3, String.valueOf(Book.Status.disponible));
            statement.setInt(4,book.getISBN());
            if(statement.execute())
            {
                return true;
            }
        }catch (SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }

    @Override

    public Book getOne(int ISBN)
    {
        Book b = new Book();
        try {
            PreparedStatement statement = this.connection.prepareStatement("select * from book WHERE ISBN = ?");
            statement.setInt(1, ISBN);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                b.setISBN(resultSet.getInt("ISBN"));
                b.setAuthor(resultSet.getString("author"));
                b.setTitle(resultSet.getString("title"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return b;
    }

    @Override
    public boolean lostBook(int ISBN) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(" UPDATE book SET statut =? WHERE ISBN = ?");
            statement.setString(1, String.valueOf(Book.Status.perdu));
            statement.setInt(2,ISBN);
            if (statement.execute()){
                return true;
            }
        }catch (SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public int countLostBooks() {
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT COUNT(*) as myCount FROM book WHERE statut = 'perdu'");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                return  resultSet.getInt("myCount");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public int countAvailableBooks() {
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT COUNT(*) as myCount FROM book WHERE statut = 'disponible'");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                return  resultSet.getInt("myCount");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public int countInvailableBooks() {
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT COUNT(*) as myCount FROM book WHERE statut = 'indisponible'");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                return  resultSet.getInt("myCount");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public List<Book> searchByTitle(String title)
    {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = this.connection.prepareStatement("select * from book where title Like ?" );
            statement.setString(1,"%"+title+"%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book b = new Book();
                b.setISBN(resultSet.getInt("ISBN"));
                b.setTitle(resultSet.getString("title"));
                b.setAuthor(resultSet.getString("author"));
                books.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
    @Override
    public ArrayList<Book> searchByAuthor(String author)
    {
        ArrayList<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = this.connection.prepareStatement("select * from book where author =?");
            statement.setString(1,author);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book b = new Book();
                b.setISBN(resultSet.getInt("ISBN"));
                b.setTitle(resultSet.getString("title"));
                b.setAuthor(resultSet.getString("author"));
                books.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
}