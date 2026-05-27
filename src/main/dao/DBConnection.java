import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/data";
        String user = "root";
        String password = "infomanproject123";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful! Java is talking to MySQL.");

            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}