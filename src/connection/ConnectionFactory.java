/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author claud
 */
public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://10.67.32.3:3306/bdcadastro";
    private static final String USER = "webserver";
    private static final String PASS = "database";
    
    
    
    public static Connection getConnection(){
        
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);               
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão ConnectionFactory getConnection: ",ex);
        }    
    }
    
    public static void closeConnection(Connection con){
        
        try {
            if(con != null){  
                con.close();
            } 
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    

    public static void closeConnection(Connection con, PreparedStatement stmt){
        
        try {
            
            if(stmt != null){
                stmt.close();
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        closeConnection(con);
        }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){ 


             closeConnection(con, stmt);

            try {

                if(rs != null){
                    rs.close();
                }

            } catch (SQLException ex) {
                    Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
}
