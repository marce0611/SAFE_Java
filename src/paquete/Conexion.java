/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author Lucy
 */
public class Conexion {

    public Connection conectarBD(String bd) {
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbUri = "jdbc:mysql://localhost/"+bd;
        //Cambiar usuario y contraseña si es necesario.
        String dbUser = "admin";
        String dbPass = "";
        Connection conn=null;
        try {
            try {
                Class.forName(dbDriver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn = DriverManager.getConnection(dbUri,dbUser, dbPass);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    
}
