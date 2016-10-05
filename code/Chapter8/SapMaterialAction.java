import java.sql.*;
import java.util.Hashtable;
import java.util.Enumeration;

public class SapMaterialAction {
 static String dbDriver = "org.hsqldb.jdbcDriver";
 static String dbURL = "jdbc:hsqldb:hsql://localhost";
 static Connection conn = null;
 static Statement stat = null;
 public SapMaterialAction() {
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
 public static void main(String[] args) {
  String action = args[0];
  SapMaterialAction createTab = new SapMaterialAction();
  try {
   if (action.equals("create")) {
    InterfaceCaller ifCaller = new InterfaceCaller();
     stat.executeUpdate("CREATE TABLE SAPMATERIALS"
           + "(MATERIAL VARCHAR(30), "
           + "DESCRIP VARCHAR(256), "
           + "IMAGE VARCHAR(256))");
     Hashtable returnHash = ifCaller.getMaterialList(args[1]);
     Hashtable rowHash;
     for (Enumeration e = returnHash.elements(); e.hasMoreElements();) {
      rowHash = (Hashtable)e.nextElement();
      stat.executeUpdate(
       "INSERT INTO SAPMATERIALS VALUES('"
            + (String)rowHash.get("MATERIAL")
            + "','" + (String)rowHash.get("MATL_DESC")
            + "','images//default.gif')");
     }
   } else if (action.equals("delete"))
    stat.executeUpdate("DROP TABLE SAPMATERIALS");
   else if (action.equals("add"))
    stat.executeUpdate("INSERT INTO MATERIALS VALUES('"
                        + args[1] + "','"
                        + args[2] + "','"
                        + args[3] + "')");
   else if (action.equals("select")) {
    ResultSet rs = stat.executeQuery("SELECT * FROM SAPMATERIALS");
    System.out.println("Select table results:");
    System.out.println("----------------------------------------");
    while (rs.next()) {
     System.out.println("Material:    " + rs.getString("MATERIAL"));
     System.out.println("Description: " + rs.getString("DESCRIP"));
     System.out.println("Image:       " + rs.getString("IMAGE"));
     System.out.println("----------------------------------------");
    }
   }
   else if (action.equals("update")) {
    stat.executeUpdate("UPDATE SAPMATERIALS SET IMAGE = '"
              + args[2] + "' WHERE MATERIAL = '"
              + args[1] + "'");
   }
  } catch (SQLException e) { e.printStackTrace(); }
  finally
  {
    try { stat.close(); }
    catch (Exception e) { e.printStackTrace(); }
    try { conn.close(); }
    catch (Exception e) { e.printStackTrace(); }
  }
 }
}
