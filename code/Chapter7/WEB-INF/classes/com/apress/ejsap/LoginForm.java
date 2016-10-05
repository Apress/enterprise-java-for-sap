package com.apress.ejsap;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.*;

public final class LoginForm extends ActionForm {
  private String password = null;
  private String username = null;

  public String getPASSWORD() {
    return (this.password.toUpperCase().trim());
  }
  public void setPASSWORD(String PASSWORD) {
    this.password = PASSWORD;
  }
  public String getUSERNAME() {
    return (this.username);
  }
  public void setUSERNAME(String USERNAME) {
    this.username = USERNAME;
  }
  public void reset(ActionMapping mapping, 
                    HttpServletRequest request) {
    this.password = null;
    this.username = null;
  }
  public ActionErrors validate(ActionMapping mapping, 
                               HttpServletRequest request) {
    ActionErrors errors = new ActionErrors();
    if ((username == null) || (username.length() < 1)) {
        errors.add("username", new ActionError("error.username.required"));
    }
    if ((password == null) || (password.length() < 1)) {
        errors.add("password", new ActionError("error.password.required"));
    }
    return errors;
  }
}
