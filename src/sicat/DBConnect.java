/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicat;

import static java.lang.Class.forName;
import static java.lang.System.out;
import java.sql.Connection;
import static java.sql.DriverManager.getConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author chris
 */
public class DBConnect {
        public static Connection setConnect() {
        String conString = "jdbc:mysql://localhost:3306/sicatering";
        Connection connect = null;
        try{
            forName("com.mysql.jdbc.Driver");
            connect = (Connection) getConnection(conString, "root", "");
            out.println("Connected");
        } catch (ClassNotFoundException | SQLException ex){
            getLogger(DBConnect.class.getName()).log(SEVERE, null, ex);
        }
        return connect;
    }
    public static int execute(String SQL) {
        int status = 0;
        Connection connect = setConnect();
        try {
            Statement st = (Statement) connect.createStatement();
            status = st.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    public static ResultSet executeQuery(String SQL) {
        ResultSet rs = null;
        Connection connect = setConnect();
        try {
            Statement st = (Statement) connect.createStatement();
            rs = st.executeQuery(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
