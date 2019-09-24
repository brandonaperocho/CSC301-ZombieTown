package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Database {
  /*
   * http://www.phpmyadmin.co/
   * Server: sql9.freemysqlhosting.net
   * Name: sql9162830
   * Username: sql9162830
   * Password: Mfa9QVCjQs
   * Port number: 3306
   */
  
   public static Connection setupConn(){
     Connection con =null;
     
     try{ 
     Class.forName("com.mysql.jdbc.Driver");  
     con=DriverManager.getConnection("jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9162830?" +
         "user=sql9162830&password=Mfa9QVCjQs");
     }catch(Exception e){ System.out.println(e);}  
     return con;
   }
   
   public static void closeConn(Connection con) throws SQLException{
     con.close();
     con = null;
   }
   
}
