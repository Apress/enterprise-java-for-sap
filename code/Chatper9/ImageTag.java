package com.apress.ejsap;

import java.io.IOException;
import java.util.Hashtable;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class ImageTag extends TagSupport {
  public int doStartTag() throws JspTagException {
    return SKIP_BODY;
  }
  public int doEndTag() throws JspTagException {
    Hashtable tabHash = (Hashtable)
       pageContext.getSession().getAttribute("MATERIALDETAIL");
    try {
        if (tabHash.get("IMAGE") != null)
          pageContext.getOut().write((String)tabHash.get("IMAGE"));
        else
          pageContext.getOut().write("images/default.gif");
    } catch (IOException ex) {
      throw new JspTagException
            ("Unable to write ImageTag");
    }
  return EVAL_PAGE;
  }
}
