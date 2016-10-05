import com.sap.mw.jco.*;
public class MaterialList {
  public static final String SAP_CLIENT ="100";
  public static final String USER_ID ="username";
  public static final String PASSWORD = "password";
  public static final String LANGUAGE = "E";
  public static final String HOST_NAME = "hostname";
  public static final String SYSTEM_NUMBER = "00";
  private static JCO.Client aConnection;
  private static IRepository aRepository;

static {
  createConnection();
  retrieveRepository();
}

private static void createConnection() {
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

private static void retrieveRepository() {
  try {
    aRepository = new JCO.Repository("SAPRep", aConnection);
    }
    catch (Exception ex) {
      System.out.println("Failed to retrieve SAP repository");
    }
  }
  
  public static void main(String[] args) {

  System.out.println("This class looks up materials.");
  try {
    String matSearch;
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
