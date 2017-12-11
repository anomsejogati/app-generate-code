/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.generate.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Anom Sejogati
 */
public class KoneksiDB {
    
    public Connection getConnection() throws SQLException {
        Connection cn;
        try {
            String server = "jdbc:mysql://localhost/namadb";
            String drever = "com.mysql.jdbc.Driver";
            Class.forName (drever);
            cn = DriverManager.getConnection (server, "root","");
            return cn;
        }catch(SQLException | ClassNotFoundException se){
                System.out.println(se.toString());
                return null;
        }
    }
    
}
