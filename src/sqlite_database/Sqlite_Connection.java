/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite_database;

import java.io.File;
import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author lucky
 */
public class Sqlite_Connection {
public static Connection conn=null;    
public static String sqlite_server="jdbc:sqlite:";
public static String resetPath="";

public static boolean isDatabaseExists(String  dbFilePath ) 
{


File dbFile=new File(dbFilePath);
return dbFile.exists();

}
public static Connection getConnection() throws SQLException{
sqlite_server="jdbc:sqlite:";
String getFilePath=new File("").getAbsolutePath();
String fileAbsolatePath=getFilePath.concat("dictionary.db");

if(isDatabaseExists(fileAbsolatePath)){
    try {
        Class.forName("org.sqlite.JDBC");
        conn=DriverManager.getConnection(sqlite_server+fileAbsolatePath);
        System.out.print("veritabanı oluştu");
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Sqlite_Connection.class.getName()).log(Level.SEVERE, null, ex);
    }
}
else {
createNewDatabase("database","dictionary");
    try {
        Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Sqlite_Connection.class.getName()).log(Level.SEVERE, null, ex);
    }
conn=DriverManager.getConnection(sqlite_server+fileAbsolatePath);
}
    return conn;

}

    public static void createNewDatabase(String fileSubFolder, String fileName) {
      
      String getFilePath=new File("").getAbsolutePath();
      String fileAbsolutePath="";
      if(fileSubFolder.isEmpty()){
      fileAbsolutePath=getFilePath.concat("\\home\\lucky\\NetBeansProjects\\sqlite_database\\"+fileName+".db");
      resetPath=fileAbsolutePath;
      
      }
      else {
          fileAbsolutePath=getFilePath.concat("\\home\\lucky\\NetBeansProjects\\sqlite_database\\"+fileSubFolder+"\\"+fileName+".db");
          
            resetPath=fileAbsolutePath;

      
      }
      
    try {
        Connection conn=DriverManager.getConnection(sqlite_server+fileAbsolutePath);
    } catch (SQLException ex) {
        Logger.getLogger(Sqlite_Connection.class.getName()).log(Level.SEVERE, null, ex);
    }
    if (conn!=null)
    {
          try {
              DatabaseMetaData meta=conn.getMetaData();
              Statement stmt=conn.createStatement();
             String sql="CREATE TABLE Kelimeler(id INT PRIMARY KEY NOT NULL,"
             +"kelime TEXT NOT NULL,"
             +"turu TEXT NOT NULL,"
             +"tanimi not null)"; 
              
              
              stmt.executeQuery(sql);
          } catch (SQLException ex) {
              Logger.getLogger(Sqlite_Connection.class.getName()).log(Level.SEVERE, null, ex);
          }
    
    }    }

}
