package com.apress.ejsap;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;

import com.apress.ejsap.*;

public final class LoginAction extends Action {
  public ActionForward perform(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response)
    throws IOException, ServletException
  {
    String username = ((LoginForm) form).getUSERNAME();
    String password = ((LoginForm) form).getPASSWORD();

    ActionServlet actServ = getServlet();
    ServletContext servContext = actServ.getServletContext();
    InterfaceCaller infCaller =
                    (InterfaceCaller)servContext.getAttribute("ifaceCaller");

    Hashtable result = infCaller.checkPassword(username, password);
    ActionErrors errors = new ActionErrors();
    if ("E".equals((String)result.get("RETURN.TYPE"))) {
      errors.add(ActionErrors.GLOBAL_ERROR,
                 new ActionError("error.authentication.fail"));
    }
    if (!errors.empty()) {
      this.saveErrors(request, errors);
      return (new ActionForward(mapping.getInput()));
    }
    HttpSession session = request.getSession();
    session.setAttribute("USERNAME", username);
    return (mapping.findForward("success"));
    }
}
