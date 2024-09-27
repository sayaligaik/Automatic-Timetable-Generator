package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database implements Provider {

    public static Connection connection=null;

    public static Connection getConnction() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(DRIVER_NAME);
                connection = DriverManager.
                        getConnection(HOST_NAME
                        + DATABASE_NAME, SERVER_USER_NAME, SERVER_PASSWORD);

            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public static boolean closeConnection(Connection connection) {
        boolean isValid = false;
        try {
            connection.close();
            if (connection.isClosed()) {
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public static void main(String[] args) {
        Connection con = Database.getConnction();
        if (con != null) {
            System.out.println("Connection :: " + con);
        } else {
            System.out.println("Connection object null");
        }
    }

}
