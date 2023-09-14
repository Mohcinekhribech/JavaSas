package DTO;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title ;
    int ISBN;
    private String author;
    List<Borrowing> borrowings = new ArrayList<>();
    public static enum Status {
        disponible,
        indisponible,
        perdu
    }

    public String getTitle()
    {
        return this.title;
    }
    public String getAuthor()
    {
        return this.author;
    }
    public int getISBN()
    {
        return this.ISBN;
    }
    public void setTitle(String title)
    {
        this.title = title ;
    }
    public void setAuthor(String author)
    {
        this.author = author ;
    }
    public void setISBN(int ISBN)
    {
        this.ISBN = ISBN ;
    }
}
