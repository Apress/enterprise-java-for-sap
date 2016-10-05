<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/lib/struts.tld" prefix="struts" %>
<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html" %>

<html:html>
  <HEAD>
    <TITLE>Material Search Results</TITLE>
<html:base/>    
  </HEAD>
  <BODY>
<html:errors/> 
  <H1>Material Search Results</H1>
  Select a material from the list for more information: 
 <TABLE BORDER=1>
  <TR><TD><B>Material</B></TD><TD><B>Description</B></TD></TR>
<%
  Hashtable resultHash = (Hashtable)session.getAttribute("MATERIALS");
  Hashtable tempRow;
  for (Enumeration e = resultHash.elements(); e.hasMoreElements();) {
    tempRow = (Hashtable)e.nextElement();
%>
  <TR>
    <TD><A HREF="/sap/materialdetail.do?MATERIAL=<%= 
                 (String)tempRow.get("MATERIAL") %>">
    <%= (String)tempRow.get("MATERIAL") %></A>
    </TD>
    <TD><%= (String)tempRow.get("MATL_DESC") %></TD>
  </TR>
<P>
<%
  }
%>
</TABLE>
</BODY>
</html:html>
