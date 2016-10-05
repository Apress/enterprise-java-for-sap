<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/lib/struts.tld" prefix="struts" %>
<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html" %>

<html:html>
  <HEAD>
    <TITLE>SAP Login</TITLE>
<html:base/>    
  </HEAD>
  <BODY>
<html:errors/>  
  <H1>SAP Login</H1>
  <P>
    <H3>Welcome, <%= session.getAttribute("USERNAME") %>.</H3>
    You have been successfully authenticated in SAP.
    <P>
<A HREF="/sap/materialsearch.jsp">Material Search</A>
  </BODY>
</html:html>
