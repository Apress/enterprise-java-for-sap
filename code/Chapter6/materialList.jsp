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
   %>
   <H1>SAP Material List Lookup</H1>
   <FORM ACTION="materialResult.jsp" METHOD="post">
   <INPUT TYPE="text" NAME="material">
   <P>
   <INPUT TYPE="submit" VALUE="Get Materials">
   </FORM>
  <%      
    }
  %>
  </BODY>
</HTML>
