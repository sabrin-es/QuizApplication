import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/quiz";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DBConnection() {}

    public static Connection getconnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
