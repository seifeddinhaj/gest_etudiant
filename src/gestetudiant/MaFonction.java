/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestetudiant;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SEIF
 */
public class MaFonction {
    public int getNombreDonnees(String nom){
        int total=0;
        
        Connection con=MaConnection.getConnection();
        try {
            Statement ps=con.createStatement();
           
           
            ResultSet rs=ps.executeQuery("select count(*) as 'tot' from "+nom);
            while(rs.next()){
           total=rs.getInt("tot");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaFonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return total;
    }
    
}
