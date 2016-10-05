package com.apress.ejsap;
import com.sap.mw.jco.*;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Properties;
import java.io.*;
public class InterfaceCaller {
  private JCO.Client aConnection;
  private IRepository aRepository;
  private final String JCO_POOL = "myPool";
  public InterfaceCaller() {
// New method to create connection pool
    createConnectionPool();
    retrieveRepository();
  }
  public Hashtable getMaterialDetail(String material) {
    JCO.Function function = getFunction("BAPI_MATERIAL_GET_DETAIL");
    JCO.ParameterList listParams = function.getImportParameterList();
    listParams.setValue(material, "MATERIAL");
    aConnection = JCO.getClient(JCO_POOL);
    aConnection.execute(function);
    JCO.releaseClient(aConnection);
    JCO.ParameterList resultParams = function.getExportParameterList();
    JCO.Structure fieldList = resultParams.getStructure("MATERIAL_GENERAL_DATA");
    Hashtable returnHash = new Hashtable();
    if (fieldList.getFieldCount() > 0) {
      for (JCO.FieldIterator fI = fieldList.fields(); fI.hasMoreElements();)
      {
        JCO.Field tabField = fI.nextField();
        returnHash.put(tabField.getName(), tabField.getString());
      }
    }
    return returnHash;
  }
  public Hashtable getMaterialList(String searchParam) {
    JCO.Function function = getFunction("BAPI_MATERIAL_GETLIST");
    JCO.ParameterList tabParams = function.getTableParameterList();
    JCO.Table materials = tabParams.getTable("MATNRSELECTION");
    materials.appendRow();
    materials.setRow(0);
    materials.setValue("I", "SIGN");
    materials.setValue("CP", "OPTION");
    materials.setValue(searchParam, "MATNR_LOW");
    aConnection = JCO.getClient(JCO_POOL);
    aConnection.execute(function);
    JCO.releaseClient(aConnection);
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
     aConnection = JCO.getClient(JCO_POOL);
    aConnection.execute(function);
    JCO.releaseClient(aConnection);
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
  private void createConnectionPool() {
    Properties sapData = new Properties();
    try {
      FileInputStream in = new FileInputStream("sapdata.properties");
      sapData.load(in);
      in.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    JCO.Pool connPool = JCO.getClientPoolManager().getPool(JCO_POOL);
    if (connPool == null) {
      JCO.addClientPool(JCO_POOL,
                                      5,
                                      sapData);
    }
  }
  private void retrieveRepository() {
    try {
      aConnection = JCO.getClient(JCO_POOL);
      aRepository = new JCO.Repository("SAPRep", aConnection);
     }
    catch (Exception ex) {
      System.out.println("Failed to retrieve SAP repository");
    }
    JCO.releaseClient(aConnection);
  }
}
