package llvt_group.llvt_project.AllData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:sqlite:D:\\db_LVVT_project\\userAccounts.db"; // <-- укажи путь к .db
                connection = DriverManager.getConnection(url);
                connection.createStatement().execute("PRAGMA busy_timeout = 5000;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
