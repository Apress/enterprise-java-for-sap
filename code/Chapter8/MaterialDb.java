package com.apress.ejsap;

import java.sql.*;
import java.util.Hashtable;

public class MaterialDb {
 static String dbDriver = "org.hsqldb.jdbcDriver";
 static String dbURL = "jdbc:hsqldb:hsql://localhost";
 static Connection conn = null;
 static Statement stat = null;
 public MaterialDb() {
  try {
   Class.forName(dbDriver);
   conn = DriverManager.getConnection(dbURL, "sa", "");
   stat = conn.createStatement();
  } catch (SQLException e) {
     System.out.println("SQL Exception");
     e.printStackTrace();
  }
    catch (ClassNotFoundException cEx) {
     System.out.println("Class not found exception");
     cEx.printStackTrace();
  }
 }
 public Hashtable getMaterial(String material) {
   Hashtable returnHash = new Hashtable();
   try {
    ResultSet rs = stat.executeQuery("SELECT *"
                   + " FROM SAPMATERIALS"
                   + " WHERE MATERIAL = '"
                   + material + "'");
    rs.next();
    returnHash.put("MATERIAL", rs.getString("MATERIAL"));
    returnHash.put("DESCRIPTION", rs.getString("DESCRIP"));
    returnHash.put("IMAGE", rs.getString("IMAGE"));
   } catch (SQLException e) {
      System.out.println(material + e.getMessage());
      returnHash.put("ERROR", e.getMessage());
   }
   finally
   {
     try { stat.close(); }
     catch (Exception e) { e.printStackTrace(); }
     try { conn.close(); }
     catch (Exception e) { e.printStackTrace(); }
   }
   return returnHash;
  }
}
