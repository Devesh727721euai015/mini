package mini;

//DatabaseConnection.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
 private static final String DB_URL = "jdbc:mysql://localhost:3306/course_registration_db";
 private static final String DB_USER = "root";
 private static final String DB_PASSWORD = "Devesh@261474";

 public static Connection getConnection() throws SQLException {
     return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
 }
}
