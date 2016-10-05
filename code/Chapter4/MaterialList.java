import com.sap.mw.jco.*;
public class MaterialList {
  public static void main(String[] args) {

  final String SAP_CLIENT ="100";
  final String USER_ID ="username";
  final String PASSWORD = "password";
  final String LANGUAGE = "E";
  final String HOST_NAME = "hostname";
  final String SYSTEM_NUMBER = "00";
  String matSearch;
  JCO.Client aConnection;
  IRepository aRepository;

  System.out.println("This class looks up materials.");
  try {
    aConnection = JCO.createClient(SAP_CLIENT,
                                   USER_ID,
                                   PASSWORD,
                                   LANGUAGE,
                                   HOST_NAME,
                                   SYSTEM_NUMBER);
    aConnection.connect();
    aRepository = new JCO.Repository("SAPRep", aConnection);

    IFunctionTemplate functionTemplate = aRepository.getFunctionTemplate("BAPI_MATERIAL_GETLIST");

    JCO.Function function = new JCO.Function(functionTemplate);
    JCO.ParameterList tabParams = function.getTableParameterList();
    JCO.Table materials = tabParams.getTable("MATNRSELECTION");

    if (args.length == 0)
        matSearch = "*";
    else
        matSearch = args[0];

    materials.appendRow();
    materials.setRow(0);
    materials.setValue("I", "SIGN");
    materials.setValue("CP", "OPTION");
    materials.setValue(matSearch, "MATNR_LOW");
    aConnection.execute(function);

    JCO.ParameterList resultParams = function.getExportParameterList();
    JCO.Table materialList = function.getTableParameterList().getTable("MATNRLIST");

    if (materialList.getNumRows() > 0) {
        do {
                 System.out.println("<<<**--------Basic Material Record--------**>>>");
            for (JCO.FieldIterator fI = materialList.fields();
                 fI.hasMoreElements();)
              {
                JCO.Field tabField = fI.nextField();
                System.out.println(tabField.getName() +
                                   (":\t") +
                                   tabField.getString());
                           }
               System.out.println("\n");
            }
            while(materialList.nextRow() == true);
      }
    else {
          System.out.println("Sorry, couldn't find any materials");
      }
    }
    catch (Exception ex) {
        System.out.println("Call to SAP has failed.");
    }
    }
}
