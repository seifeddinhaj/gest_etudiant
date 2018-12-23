/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestetudiant;


import java.sql.DriverManager;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SEIF
 */
public class MaConnection {
     public static Connection getConnection(){
        Connection con=null;
        
         try {
              Class.forName("com.mysql.jdbc.Driver");
                con= DriverManager.getConnection("jdbc:mysql://localhost/stdmgdb","root","");
                
         
         } catch (Exception ex) {
             System.out.println(ex.getMessage());
         }
        
        
        
        return con;
        
        
    }
}
