<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.apress.ejsap.*" %>

<HTML>
  <HEAD>
    <TITLE>Look up materials</TITLE>
  </HEAD>
  <BODY>
  <% 
    String username = (String)session.getAttribute("username");
    if (username == null)
      out.println("You are not logged in.  Please try again.");
    else {
      InterfaceCaller ifCaller = 
           (InterfaceCaller)application.getAttribute("ifaceCaller");
      Hashtable materialsHash = 
           ifCaller.getMaterialList(request.getParameter("material"));
        Hashtable rowHash;
      if (materialsHash.isEmpty())
          out.println("<H2>Your search has returned zero results</H2>");
      else {
        out.println("Here are the list materials based on a search criteria of " +
        "<B>" + request.getParameter("material") + "</B><P>");      
        for (Enumeration e = materialsHash.elements(); e.hasMoreElements();) {
          rowHash = (Hashtable)e.nextElement();
          out.println("Material: " + 
                       (String)rowHash.get("MATERIAL") + "<BR>");
          out.println("Material description: " + 
                       (String)rowHash.get("MATL_DESC") + "<P>");        
        }
      }
    }
  %>
  </BODY>
</HTML>
