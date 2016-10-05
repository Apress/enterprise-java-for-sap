<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.apress.ejsap.*" %>

<HTML>
  <HEAD>
    <TITLE>User Login</TITLE>
  </HEAD>
  <BODY>
  <% 
    String username = request.getParameter("username");
    String password = (String)request.getParameter("password");
    Hashtable userCheck = InterfaceCaller.checkPassword(username, password);
    if (!userCheck.get("RETURN.TYPE").equals("E")) {
/* 
   If the user is authenticated in SAP, we set an attribute in the session 
   called 'username' which is equal to the ID they used to log in
*/
    session.setAttribute("username", username);
  %>  
    <H1>Welcome, <%= request.getParameter("username") %></H1>
    You have logged in at: <%= new Date().toString() %>
    <P>
<!-- This URL takes us to the materials lookup page -->
    <A HREF="materialList.jsp">Look up materials</A>
  <%
    }
    else {
      out.println("<H1> Password incorrect, please try again.</H1>");
    }
  %>
  </BODY>
</HTML>
