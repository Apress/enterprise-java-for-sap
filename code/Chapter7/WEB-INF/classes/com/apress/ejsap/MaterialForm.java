package com.apress.ejsap;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.*;

public final class MaterialForm extends ActionForm {
  private String material = null;

  public String getMATERIAL() {
    return (this.material);
  }
  public void setMATERIAL(String MATERIAL) {
    this.material = MATERIAL.toUpperCase().trim();
  }
  public void reset(ActionMapping mapping, 
                    HttpServletRequest request) {
    this.material = null;
  }
  public ActionErrors validate(ActionMapping mapping, 
                               HttpServletRequest request) {
    ActionErrors errors = new ActionErrors();
    if ((material == null) || (material.length() < 1)) {
        errors.add("material", new ActionError("error.material.required"));
    }
    return errors;
  }
}
