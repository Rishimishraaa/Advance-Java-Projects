package DB_Info;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static {
        try {
            Class.forName(Conn.driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getCon() throws SQLException {
        return  DriverManager.getConnection(Conn.file,Conn.user,Conn.pass);
    }
}
