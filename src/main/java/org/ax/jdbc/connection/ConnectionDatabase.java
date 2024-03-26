package org.ax.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    private static String url = "jdbc:mysql://localhost:3306/name_db_pacs?serverTimezone=America/Santiago";
    private static String user = "root";
    private static String pass = "root";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null){
            connection = DriverManager.getConnection(url, user, pass);
        }
        return connection;
    }
}
