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
public class Etudiant {
    public void insererAjouterSupp(char op,int id,String nom,String prenom ,String sex,String date,String tel,String adresse){
        Connection con=MaConnection.getConnection();
        PreparedStatement ps;
        //a pour ajouter
        if(op=='a'){
            try {
                ps=con.prepareStatement("INSERT INTO etudiant( nom, prenom, sex, Datenai, telephone, adresse) VALUES (?,?,?,?,?,?)");
                ps.setString(1, nom);
                ps.setString(2, prenom);
                ps.setString(3, sex);
                ps.setString(4, date);
                ps.setString(5, tel);
                ps.setString(6, adresse);
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Etudiant ajouté avec succes!");
                    
                }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
         }
        //m=mise a jour
        
         if(op=='m'){
            try {
                ps=con.prepareStatement("UPDATE `etudiant` SET `nom`=?,`prenom`=?,`sex`=?,`Datenai`=?,`telephone`=?,`adresse`=? WHERE `Id`=?");
                ps.setString(1, nom);
                ps.setString(2, prenom);
                ps.setString(3, sex);
                ps.setString(4, date);
                ps.setString(5, tel);
                ps.setString(6, adresse);
                ps.setInt(7, id);
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "les données d' étudiant ont été mises à jour avec succès!");
                    
                }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
         }
          if(op=='s')//s pour supprimer
          {
                int rep=JOptionPane.showConfirmDialog(null, "les scores de ce etudiant vont étre aussi supprimer !","supprimer etudiant!",JOptionPane.OK_CANCEL_OPTION);
            if(rep==0){
            try {
                ps=con.prepareStatement("DELETE FROM `etudiant` WHERE `Id`=?");
              
                ps.setInt(1, id);
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "l'etudiant a été supprimer avec succès!");
                    
                }
                ps=con.prepareStatement("delete from resultats where id_etudiant=?");
                ps.setInt(1, id);
                ps.executeUpdate();
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
        
         }
    
    }
    public void chargerEtudiants(JTable table,String  value) {
        Connection con=MaConnection.getConnection();
    PreparedStatement ps;
        try {
            ps=con.prepareStatement("select * from etudiant where concat(nom,prenom,telephone,adresse) like ?");
            ps.setString(1, "%"+value+"%");
            ResultSet rest=ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            Object row[];
            while(rest.next()){
                row=new Object[7];
                row[0]=rest.getInt(1);
                 row[1]=rest.getString(2);
                  row[2]=rest.getString(3); 
                  row[3]=rest.getString(4);
                   row[4]=rest.getString(5);
                    row[5]=rest.getString(6);
                     row[6]=rest.getString(7);
                     model.addRow(row);
                   
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
