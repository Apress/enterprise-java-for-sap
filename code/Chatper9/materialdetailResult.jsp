<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/lib/struts.tld" prefix="struts" %>
<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/lib/imagetag.tld" prefix="mytaglib" %>

<html:html>
  <HEAD>
    <TITLE>Material Detail Result</TITLE>
<html:base/>    
  </HEAD>
  <BODY>
<html:errors/> 
<H1>Material Detail</H1>
The following material record has been retrieved from SAP.
<P>
<B>Material: <%= request.getParameter("MATERIAL") %> </B>
<TABLE BORDER=1>
  <TR><TD><B>Field</B></TD><TD><B>Value</B></TD></TR>
<%
Hashtable resultHash = (Hashtable)session.getAttribute("MATERIALDETAIL");
%>
<!-- New code to display the material image -->
<TR><TD>MATERIAL IMAGE</TD>
<TD><IMG SRC="<%= mytaglib:image %>"></TD></TR>
<%
String field;
for (Enumeration e = resultHash.keys(); e.hasMoreElements();) {
  field = (String)e.nextElement();
%>
  <TR>
    <TD><%= field %></TD>
    <TD><%= (String)resultHash.get(field) %></TD>
  </TR>
<%
  }
%>
</TABLE>
</BODY>
</html:html>
