import com.sap.mw.jco.*;

public class ServerTest{
 public static void main(String[] argv)
 {
    JCO.Client aConnection =
                      JCO.createClient("client", "username",
                                                   "password", "language",
                                                   "host", "system number");
   aConnection.connect();
   IRepository repository = new JCO.Repository("serverRep", aConnection);
   aConnection.disconnect();
   JCO.Server myServer = new SampleServer("sincgo","sapgw00","JCOSERVER",repository);
   myServer.start();
 }
}
