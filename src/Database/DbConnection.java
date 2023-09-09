package Database;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbConnection {
    public Connection conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/bibliotheque";
            String username = "root";
            String password = "Mohcine@2003";
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }
}