
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection connect() throws SQLException {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (url == null || url.isBlank()
                || user == null || user.isBlank()
                || password == null || password.isBlank()) {
            throw new SQLException(
                "Missing PostgreSQL environment variables: DB_URL, DB_USER or DB_PASSWORD"
            );
        }

        return DriverManager.getConnection(url, user, password);
    }
}
