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

public final class MaterialAction extends Action {
  public ActionForward perform(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response)
    throws IOException, ServletException
  {
    String material = ((MaterialForm) form).getMATERIAL();

    ActionServlet actServ = getServlet();
    ServletContext servContext = actServ.getServletContext();
    InterfaceCaller infCaller = 
      (InterfaceCaller)servContext.getAttribute("ifaceCaller");
    Hashtable result = infCaller.getMaterialList(material);

    ActionErrors errors = new ActionErrors();	
    if (result.size() < 1) {
      errors.add(ActionErrors.GLOBAL_ERROR,
                 new ActionError("error.material.noreturn"));
    }
    if (!errors.empty()) {
      this.saveErrors(request, errors);
      return (new ActionForward(mapping.getInput()));
    }
    HttpSession session = request.getSession();
    session.setAttribute("MATERIALS", result);
    return (mapping.findForward("success"));
    }
}
