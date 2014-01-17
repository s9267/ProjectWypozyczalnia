package dbController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection con = null;
    private static String user = "root";
    private static String password = "";

    public static Connection getCon() {
        if (con == null) {
            try {
                String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wypozyczalnia?characterEncoding=UTF-8", user, password);
            } catch (SQLException ex) {
                System.out.println("SQLException - błąd z utworzeniem połączenia"); 
            } catch (InstantiationException ex) {
                System.out.println("InstantiationException - błąd z utworzeniem połączenia"); 
            } catch (IllegalAccessException ex) {
            	System.out.println("IllegalAccessException - błąd z utworzeniem połączenia");
            } catch (ClassNotFoundException ex) {
            	System.out.println("ClassNotFoundException - błąd z utworzeniem połączenia");
            }
        }
        return con;
    }

    public static void killConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException - błąd z zakonczeniem połączenia"); 
            }
        }
    }
}
