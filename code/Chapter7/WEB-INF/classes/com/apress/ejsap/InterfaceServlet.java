import com.apress.ejsap.*;
import java.io.*;
//These are the servlet libraries
import javax.servlet.*;
import javax.servlet.http.*;

public class InterfaceServlet
             extends HttpServlet{
    public void init(ServletConfig servConfig) throws ServletException{
      ServletContext servContext = servConfig.getServletContext();
      InterfaceCaller infCaller = new InterfaceCaller();
      servContext.setAttribute("ifaceCaller", infCaller);
    }
}
