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
    Please enter your SAP Internet username and password:
    <P>
    <html:form action="saplogin.do" method="post" name="login" 
               type="com.apress.ejsap.LoginForm">
      <bean:message key="prompt.username"/>
      <BR>
      <html:text property="USERNAME" size="25"/>
      <BR>
      <bean:message key="prompt.password"/>
      <BR>
      <html:password property="PASSWORD" size="25"/>
      <P>
      <html:submit property="submit" value="Submit"/>
    </html:form>
  </BODY>
</html:html>
