/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techtonic;

/**
 *
 * @author Livinvg
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 
 *
 * @author Encode
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rollex
 */
public class Connect {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    //private static final String PASSWORD = "transport";
   // private static final String DATABASE = "jdbc:mysql://localhost:3306/transport_manager";
    private static final String DATABASE = "jdbc:mysql://localhost:3306/techtonic";
    public static Connection setConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
             //JOptionPane.showMessageDialog(null, ex.toString());
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}