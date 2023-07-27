import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class secConnection {
    private final String user = "root";
    private final String password = "password";
    private final String address = "jdbc:mysql://localhost:3306/travel_and_toursim";

    public Connection connect() throws SQLException{
        return DriverManager.getConnection(address, user, password);
    }
}
