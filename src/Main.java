
import DAO.BookDAO;
import DAO.BorrowerDAO;
import DAO.BorrowingDAO;
import DTO.Book;
import DTO.Borrower;
import DTO.Borrowing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static Scanner s = new Scanner(System.in);
    static Book book = new Book();
    static BookDAO bookDAO = new BookDAO();
    static BorrowerDAO borrowerDAO = new BorrowerDAO();
    static BorrowingDAO borrowingDAO = new BorrowingDAO();
    public static void main(String[] args) {
        int ch;
        do {
            ch = menu();
            treatment(ch);
        }while(ch>=1 && ch<=8);
    }
    public static int menu()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("**********************************************************************");
        System.out.println("*                                                                    *");
        System.out.println("*  1 . Ajouter un Livre           2 . Supprimer un Livre             *");
        System.out.println("*  3 . Chercher des Livre         4 . Modifier  un Livre             *");
        System.out.println("*  5 . Emprunter un Livre         6 . Retourner un Livre             *");
        System.out.println("*  7 . Statistiques des Livre     8 . Afficher les livres emprunté   *");
        System.out.println("*                                                                    *");
        System.out.println("**********************************************************************");
        return s.nextInt();
    }
    public static void treatment(int ch)
    {

        switch (ch) {
            case 1 -> {
                System.out.print("ISBN : ");
                book.setISBN(s.nextInt());
                System.out.print("Titre : ");
                book.setTitle(s.next());
                System.out.print("Auteur : ");
                book.setAuthor(s.next());
                bookDAO.create(book);
            }
            case 2 -> {
                System.out.println("ISBN : ");
                book.setISBN(s.nextInt());
                bookDAO.delete(book);
            }
            case 3 -> {
                System.out.println("1 . chercher par Livre");
                System.out.println("2 . chercher par auteur");
                int ch1 = s.nextInt();
                switch (ch1) {
                    case 1:
                        System.out.print("donne le titre : ");
                        List<Book> books= bookDAO.searchByTitle(s.next());
                        for (Book l : books){
                            System.out.print("isbn : "+l.getISBN() +" titre :" +l.getTitle()+" auteur :" +l.getAuthor()+"\n");
                        }
                        break;
                    case 2:
                        System.out.print("donne l'auteur : ");
                        List<Book> bookss= bookDAO.searchByAuthor(s.next());
                        for (Book l : bookss){
                            System.out.print("isbn : "+l.getISBN() +" titre :" +l.getTitle()+" auteur :" +l.getAuthor()+"\n");
                        }
                        break;
                }
            }
            case 4 ->{
                System.out.print(" - Entrer ISBN du Liver : ");
                Book books= bookDAO.getOne(s.nextInt());
                System.out.println("information de livre precedant :");
                    System.out.print(" - isbn : "+books.getISBN() +" - titre :" +books.getTitle()+" - auteur :" +books.getAuthor()+"\n");
                    System.out.print("Titre : ");
                    book.setTitle(s.next());
                    System.out.print("Auteur : ");
                    book.setAuthor(s.next());
                    bookDAO.update(book);
            }
            case 5 ->{
                Borrower borrower = new Borrower();
                Borrowing borrowing =new Borrowing();
                System.out.println("Entrer ISBN du Livre : ");
                borrowing.getBook().setISBN(s.nextInt());
                System.out.println("Entrer les information de l'emprunteur : ");
                System.out.println("- Nom :");
                borrower.setName(s.next());
                System.out.println("- le nombre de membre : ");
                borrower.setMemberNum(s.nextInt());
                int borrowerId = borrowerDAO.getId(borrower);
                if(borrowerId == 0){
                    borrowerDAO.create(borrower);
                }
                java.sql.Date sqlDateFromLocalDate = java.sql.Date.valueOf(java.time.LocalDate.now());
                borrowing.setStartDate(sqlDateFromLocalDate);
                borrowing.getBorrower().setId(borrowerId);
                borrowingDAO.create(borrowing);
            }
            case 6 -> {
                java.sql.Date sqlDateFromLocalDate = java.sql.Date.valueOf(java.time.LocalDate.now());
                System.out.print("- Entrer ISBN du Livre : ");
                if(borrowingDAO.returnBook(s.nextInt(),sqlDateFromLocalDate)){
                    System.out.println("Done .");
                }
            }
            case 7 -> {
                System.out.println("Les livre perdu : " + bookDAO.countLostBooks());
                System.out.println("Les livre emprunté : " + bookDAO.countInvailableBooks());
                System.out.println("Les livre disponible : " + bookDAO.countAvailableBooks());
            }
            case 8 -> {
                List<Borrowing> borrowings= borrowingDAO.showBorrowedBooks();
                for (Borrowing b : borrowings){
                    System.out.println("*****************************");
                    System.out.println("-isbn : "+b.getBook().getISBN() +" -titre :" +b.getBook().getTitle()+" -auteur :" +b.getBook().getAuthor());
                    System.out.println("-name : "+b.getBorrower().getName() + " -nombre du membre :"+b.getBorrower().getMemberNum()+" -date d'emprunt :"+b.getStartDate());
                }
            }
        }
    }
}