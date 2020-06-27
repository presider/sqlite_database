/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite_database;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author lucky
 */
public class Operations {

public static void insertData(int id,String kelime,String turu,String tanimi){
    try {
        String SqlQ="INSERT INTO Kelimeler(id,kelime,turu,tanimi)VALUES(?, ?, ?, ?)";
        
        Connection conn=Sqlite_Connection.getConnection();
        PreparedStatement pst;
        pst=conn.prepareStatement(SqlQ);
        pst.setInt(1,id);
        pst.setString(2,kelime);
        pst.setString(3,turu);
        pst.setString(4,tanimi);
        pst.executeUpdate();
        System.out.print("insert into table...");
        
    } catch (SQLException ex) {
        Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
    }

} 

public static  DefaultTableModel customModel(String query,JTable table){
DefaultTableModel model=(DefaultTableModel) table.getModel();
try{

        Connection conn=Sqlite_Connection.getConnection();
        Statement sta=conn.createStatement();
        ResultSet resultset=sta.executeQuery(query);

if(resultset.isBeforeFirst()) {
while(resultset.next()){
  
Object dataObject[]={    
resultset.getString("kelime"),
resultset.getString("turu"),
resultset.getString("tanimi")
};

model.addRow(dataObject);
}
System.out.print("model created...");
}
}
catch (SQLException ex) {
        Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
    }
    return model;

 
}}