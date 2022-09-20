package connection;

import java.sql.Connection;
import java.sql.DriverManager;
        
public class DBConnection {
    Connection connection;
    static String bd = "hospedaje";
    static String port = "3306";
    static String login = "root";
    static String password = "admin";
}
