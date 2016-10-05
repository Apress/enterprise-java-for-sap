import java.sql.*;

public class MaterialTableAction {
 static String dbDriver = "org.hsqldb.jdbcDriver";
 static String dbURL = "jdbc:hsqldb:hsql://localhost";
 static Connection conn = null;
 static Statement stat = null;

 public MaterialTableAction() {
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
  MaterialTableAction createTab = new MaterialTableAction();
  try {
   if (action.equals("create")) {
    stat.executeUpdate(
     "CREATE TABLE MATERIALS"
      + "(MATERIAL VARCHAR(30), "
      + "DESCRIP VARCHAR(256))");
   } else if (action.equals("delete")) {
    stat.executeUpdate("DROP TABLE MATERIALS");
   } else if (action.equals("add")) {
    stat.executeUpdate(
     "INSERT INTO MATERIALS VALUES('" + args[1] + "','" + args[2] + "')");
   } else if (action.equals("select")) {
    ResultSet rs = stat.executeQuery("SELECT * FROM MATERIALS");
    System.out.println("Select table results:");
    System.out.println("----------------------------------------");
    while (rs.next()) {
     System.out.println("Material:    " + rs.getString("MATERIAL"));
     System.out.println("Description: " + rs.getString("DESCRIP"));
     System.out.println("----------------------------------------");
    }
   }
  } catch (SQLException e) {
     e.printStackTrace();
  }
  finally
  {
   try { stat.close(); }
   catch (Exception e) { e.printStackTrace(); }
   try { conn.close(); }
   catch (Exception e) { e.printStackTrace(); }
  }
 }
}
