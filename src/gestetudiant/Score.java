/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestetudiant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SEIF
 */
public class Score {
    public void chargerScores(JTable table) {
        Connection con=MaConnection.getConnection();
    PreparedStatement ps;
        try {
            ps=con.prepareStatement("select * from resultats ");
            
            ResultSet rest=ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            Object row[];
            while(rest.next()){
                row=new Object[4];
               
                  row[2]=rest.getFloat(1);
                  row[3]=rest.getString(2);
                   row[0]=rest.getInt(3);
                 row[1]=rest.getInt(4);
                  
                 
                     model.addRow(row);
                   
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
     public void TousLessScores(JTable table) {
        Connection con=MaConnection.getConnection();
    PreparedStatement ps;
        try {
            ps=con.prepareStatement("SELECT e.id,e.nom,e.prenom,c.libelle,note FROM resultats,etudiant e,cours c where id_etudiant=e.id and id_cours=c.id");
            
            ResultSet rest=ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            Object row[];
            while(rest.next()){
                row=new Object[5];
                row[0]=rest.getInt("id");
                 row[1]=rest.getString("nom");
                  row[2]=rest.getString("prenom");
                  row[3]=rest.getString("libelle");
                     row[3]=rest.getFloat("note");
                  
                  
                  
                 
                     model.addRow(row);
                   
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
     public void insererAjouterSupp(char op,Integer id_etd,Integer id_cours,Float note ,String description ){
        Connection con=MaConnection.getConnection();
        PreparedStatement ps;
        //a pour ajouter
        if(op=='a'){
            try {
                ps=con.prepareStatement("INSERT INTO resultats(id_etudiant, id_cours, note, description) VALUES (?,?,?,?)");
                ps.setInt(1, id_etd);
                ps.setInt(2, id_cours);
                   ps.setFloat(3, note);
                      ps.setString(4, description);
              
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Score  ajouté avec succes!");
                    
                }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
         }
        //m=mise a jour
        
        if(op=='m'){
            try {
                ps=con.prepareStatement("UPDATE resultats SET note=?,description=? WHERE Id_etudiant=? and id_cours=?");
                ps.setFloat(1, note);
                ps.setString(2, description);
                
                ps.setInt(3, id_etd);
                ps.setInt(4, id_cours);
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "les données du score ont été mises à jour avec succès!");
                    
                }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
         }
        
          if(op=='s')//s pour supprimer
        {
            try {
                ps=con.prepareStatement("DELETE FROM resultats WHERE Id_etudiant=? and id_cours=?");
              
                ps.setInt(1, id_etd);
                 ps.setInt(2, id_cours);
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "le score a été supprimer avec succès!");
                    
                }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
         }
    
    }
    
}
