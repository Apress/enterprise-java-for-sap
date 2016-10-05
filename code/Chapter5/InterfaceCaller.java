import com.sap.mw.jco.*;
import java.util.Hashtable;
import java.util.ResourceBundle;
public class InterfaceCaller {

  ResourceBundle sapProperties = ResourceBundle.getBundle("sapdata");
  public final String SAP_CLIENT =
      sapProperties.getString("jco.client.client");
  public final String USER_ID =
sapProperties.getString("jco.client.user");
  public final String PASSWORD =
      sapProperties.getString("jco.client.passwd");
  public final String LANGUAGE =
sapProperties.getString("jco.client.langu");
  public final String HOST_NAME =
sapProperties.getString("jco.client.ashost");
  public final String SYSTEM_NUMBER =
sapProperties.getString("jco.client.sysnr");

  private JCO.Client aConnection;
  private IRepository aRepository;

  public InterfaceCaller() {
    createConnection();
    retrieveRepository();
}

  public Hashtable getMaterialList(String searchParam) {
    JCO.Function function = this.getFunction("BAPI_MATERIAL_GETLIST");
    JCO.ParameterList tabParams = function.getTableParameterList();
    JCO.Table materials = tabParams.getTable("MATNRSELECTION");

    materials.appendRow();
    materials.setRow(0);
    materials.setValue("I", "SIGN");
    materials.setValue("CP", "OPTION");
    materials.setValue(searchParam, "MATNR_LOW");
    aConnection.execute(function);

    JCO.ParameterList resultParams = function.getExportParameterList();
    JCO.Table materialList =
        function.getTableParameterList().getTable("MATNRLIST");

    Hashtable returnHash = new Hashtable();
    Hashtable rowHash = new Hashtable();
    int i = 0;
    if (materialList.getNumRows() > 0) {
        do {
            for (JCO.FieldIterator fI = materialList.fields();
                 fI.hasMoreElements();)
              {
                JCO.Field tabField = fI.nextField();
                rowHash.put(tabField.getName(),tabField.getString());
           }
               returnHash.put("line" + i, rowHash);
               rowHash = new Hashtable();
               i++;
            }
            while(materialList.nextRow() == true);
    }
    else {
          System.out.println("Sorry, couldn't find any materials");
    }
    return returnHash;
  }

  public Hashtable checkPassword(String username, String password) {

    JCO.Function function = getFunction("BAPI_CUSTOMER_CHECKPASSWORD");
    JCO.ParameterList listParams = function.getImportParameterList();
    listParams.setValue(username, "CUSTOMERNO");
    listParams.setValue(password, "PASSWORD");

    aConnection.execute(function);

    JCO.ParameterList resultParams = function.getExportParameterList();
    Hashtable returnHash = new Hashtable();
    returnHash.put("RETURN.TYPE",extractField("RETURN","TYPE",resultParams));
    returnHash.put("RETURN.CODE",extractField("RETURN","CODE",resultParams));
    returnHash.put("RETURN.MESSAGE",
                    extractField("RETURN","MESSAGE",resultParams));
    return returnHash;
    }

  public String extractField(String structure,String field,
                                    JCO.ParameterList parameterList)
  {
      return ((JCO.Structure)parameterList.getValue(structure)).getString(field);
  }

  public JCO.Function getFunction(String name) {
    try {
         return aRepository.getFunctionTemplate(name.toUpperCase()).getFunction();
    }
    catch (Exception ex) {}
      return null;
    }

private void createConnection() {
  try {
    aConnection = JCO.createClient(SAP_CLIENT,
                                   USER_ID,
                                   PASSWORD,
                                   LANGUAGE,
                                   HOST_NAME,
                                   SYSTEM_NUMBER);
    aConnection.connect();
    }
    catch (Exception ex) {
      System.out.println("Failed to connect to SAP system");
    }
  }

private void retrieveRepository() {
  try {
    aRepository = new JCO.Repository("SAPRep", aConnection);
    }
    catch (Exception ex) {
      System.out.println("Failed to retrieve SAP repository");
    }
  }
}
