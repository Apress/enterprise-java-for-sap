import com.sap.mw.jco.*;

public class SampleServer extends JCO.Server {
  public SampleServer(String gwhost, String gwserv, String progid, 
                                      IRepository repository)
  {
   super(gwhost,gwserv,progid,repository);
   System.out.println("JCO Server has been started");
  }
  protected void handleRequest(JCO.Function function)
  {
   JCO.ParameterList paramsIn  = function.getImportParameterList();
   JCO.ParameterList paramsOut = function.getExportParameterList();
   JCO.ParameterList tables = function.getTableParameterList();
   System.out.println("Calling service: " + function.getName());

   if (function.getName().equals("Z_RFC_JCO_INTERFACE")) {
    String ident = paramsIn.getString("IDENT");
    JCO.Table idTable = tables.getTable("TAB_IDTEXT");
    idTable.appendRows(2);
    if (ident.equals("BEYE")) {
     idTable.setRow(0);
     idTable.setValue("BEYE", "IDENT");  
     idTable.setValue("Blind eye", "SHORT_TEXT");
     idTable.setValue("A blind eye sees only truth", "LONG_TEXT");
    
     idTable.setRow(1);
     idTable.setValue("BEYE", "IDENT");    
     idTable.setValue("Blinded eyes", "SHORT_TEXT");
     idTable.setValue("Your eyes may be blinded, but you are truly free", 
                                 "LONG_TEXT");    
    }
    else if (ident.equals("MIGHT")) {
     idTable.setRow(0);
     idTable.setValue("MIGHT", "IDENT");  
     idTable.setValue("Always mighty", "SHORT_TEXT");
     idTable.setValue("Always the mighty are first to fall", "LONG_TEXT");
    
     idTable.setRow(1);
     idTable.setValue("MIGHT", "IDENT");    
     idTable.setValue("Might and maybe", "SHORT_TEXT");
     idTable.setValue("Might and maybe are the musings of the mumbler", 
                                 "LONG_TEXT");    
    }      
    paramsOut.setValue(ident,"EX_IDENT");     
    tables.setValue(idTable, "TAB_IDTEXT");
   }
  }
}
