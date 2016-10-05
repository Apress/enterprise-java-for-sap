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

public final class MaterialDetailAction extends Action {
  public ActionForward perform(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response)
    throws IOException, ServletException
  {
    String material = request.getParameter("MATERIAL");
    ActionServlet actServ = getServlet();
    ServletContext servContext = actServ.getServletContext();
    InterfaceCaller infCaller = 
      (InterfaceCaller)servContext.getAttribute("ifaceCaller");
    Hashtable result = infCaller.getMaterialDetail(material);
    ActionErrors errors = new ActionErrors();
    if (result.size() < 1) {
      errors.add(ActionErrors.GLOBAL_ERROR,
                 new ActionError("error.material.nodetail"));
    }
    if (!errors.empty()) {
      this.saveErrors(request, errors);
      return (new ActionForward(mapping.getInput()));
    }
//New code added to support external database
    MaterialDb materialDb = new MaterialDb();
    Hashtable dbHash = materialDb.getMaterial(material);
    if ((String) dbHash.get("ERROR") == null) {
      result.put("IMAGE", dbHash.get("IMAGE"));
    }
    else {
      result.put("IMAGE", "images/default.gif");
    }
    HttpSession session = request.getSession();
    session.setAttribute("MATERIALDETAIL", result);

    return (mapping.findForward("success"));
    }
}
