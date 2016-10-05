<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/lib/struts.tld" prefix="struts" %>
<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html" %>

<html:html>
  <HEAD>
    <TITLE>Material Search</TITLE>
<html:base/>    
  </HEAD>
  <BODY>
    <html:errors/>
    <H1>Material Search</H1>
    <P>
    Enter a full or partial material number to search for:
    <P>
    <html:form action="materialsearch.do" method="post"  name="material"
          type="com.apress.ejsap.MaterialForm">
      <bean:message key="prompt.material"/>
      <html:text property="MATERIAL" size="25"/>
      <P>
      <html:submit property="submit" value="Submit"/>
    </html:form>
  </BODY>
</html:html>
