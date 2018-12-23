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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SEIF
 */
public class Cours {
     public void insererAjouterSupp(char op,Integer id,String libelle,Integer nbh ){
        Connection con=MaConnection.getConnection();
        PreparedStatement ps;
        //a pour ajouter
        if(op=='a'){
            try {
                ps=con.prepareStatement("INSERT INTO cours( libelle, nbheurs) VALUES (?,?)");
                ps.setString(1, libelle);
                ps.setInt(2, nbh);
              
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Cour  ajouté avec succes!");
                    
                }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
         }
        //m=mise a jour
        
        if(op=='m'){
            try {
                ps=con.prepareStatement("UPDATE `cours` SET `libelle`=?,`nbheurs`=? WHERE `Id`=?");
                ps.setString(1, libelle);
                ps.setInt(2, nbh);
                
                ps.setInt(3, id);
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "les données du cours ont été mises à jour avec succès!");
                    
                }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
         }
        
          if(op=='s')//s pour supprimer
          {
               int rep=JOptionPane.showConfirmDialog(null, "les scores de ce cours vont étre aussi supprimer !","supprimer cours!",JOptionPane.OK_CANCEL_OPTION);
            if(rep==0){
            try {
                ps=con.prepareStatement("DELETE FROM `cours` WHERE `Id`=?");
              
                ps.setInt(1, id);
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "le cours a été supprimer avec succès!");
                    
                }
                ps=con.prepareStatement("DELETE FROM `resultats` WHERE `id_cours`=?");
                ps.setInt(1, id);
                ps.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
        
         }
    
    }
    public void chargerCours(JTable table,String  value) {
        Connection con=MaConnection.getConnection();
    PreparedStatement ps;
        try {
            ps=con.prepareStatement("select * from cours where concat(libelle,nbheurs) like ?");
            ps.setString(1, "%"+value+"%");
            ResultSet rest=ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            Object row[];
            while(rest.next()){
                row=new Object[3];
                row[0]=rest.getInt(1);
                 row[1]=rest.getString(2);
                  row[2]=rest.getString(3); 
                 
                     model.addRow(row);
                   
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public boolean checkCoursExiste(String cours){
        boolean test = false;
         Connection con=MaConnection.getConnection();
    PreparedStatement ps;
        try {
            ps=con.prepareStatement("select * from cours where libelle like ?");
            ps.setString(1, "%"+cours+"%");
            ResultSet rest=ps.executeQuery();
          if(rest.next()){
                
                test=true;
                   
                  
            }else test= false;
        } catch (SQLException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
        
        return test;
    }
    
    
    public int getCoursId(String libelle){
        int id=0;
         Connection con=MaConnection.getConnection();
    PreparedStatement ps;
        try {
            ps=con.prepareStatement("select * from cours where libelle like ?");
            ps.setString(1, "%"+libelle+"%");
            ResultSet rest=ps.executeQuery();
          if(rest.next()){
                
                id=rest.getInt("Id");
                   
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return id;
    }
    
    public void chargerCoursCombo(JComboBox combo) {
        Connection con=MaConnection.getConnection();
    PreparedStatement ps;
        try {
            ps=con.prepareStatement("select * from cours ");
           
            ResultSet rest=ps.executeQuery();
            
            while(rest.next()){
                combo.addItem(rest.getString("libelle"));
                   
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(Etudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
