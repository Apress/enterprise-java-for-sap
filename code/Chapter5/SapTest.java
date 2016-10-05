  import java.util.Hashtable;
  import java.util.Enumeration;
  public class SapTest {

  public static void main(String[] args) {
  //Change this variable to narrow search criteria
    String materialSearch = "*";
    InterfaceCaller iCaller = new InterfaceCaller();
    Hashtable resultHash = iCaller.getMaterialList(materialSearch);
    Hashtable tempRow;
    for (Enumeration e = resultHash.elements(); e.hasMoreElements();) {
      tempRow = (Hashtable)e.nextElement();
      System.out.println("Material: " +
          (String)tempRow.get("MATERIAL") +
          System.getProperty("line.separator"));
      System.out.println("Material description: " +
          (String)tempRow.get("MATL_DESC") +
          System.getProperty("line.separator"));
    }
  }
}
