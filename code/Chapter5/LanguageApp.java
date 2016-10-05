import java.util.ResourceBundle;
import java.util.Locale;
import java.util.Date;
public class LanguageApp {

  public static void main(String[] args){

   String langKey;
   Date currDate = new Date();
   if (args.length > 0)
    langKey = args[0];
   else
    langKey = "";

   Locale currLocale = new Locale(langKey);

   ResourceBundle myResources =
       ResourceBundle.getBundle("MyResources", currLocale);
   System.out.println("<<<***------------------------------***>>>");
   System.out.println(myResources.getString("main.welcome"));
   System.out.println("<<<***------------------------------***>>>" +
                                 System.getProperty("line.separator"));
   System.out.println(myResources.getString("main.articles") +
                                 System.getProperty("line.separator"));
   System.out.println(myResources.getString("main.projects") +
                                 currDate.toString() +
                                 System.getProperty("line.separator"));
   System.out.println(myResources.getString("main.contact") +
                                 " guru@opensourceguru.com" +
                                 System.getProperty("line.separator"));
  }
}
