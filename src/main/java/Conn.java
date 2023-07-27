import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    public Conn(){
        secConnection conn = new secConnection();
        try {
            // Class.forName("com.mysql.jdbc.Driver");
            c = conn.connect();
            s = c.createStatement();
            System.out.println("connection is done");      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
