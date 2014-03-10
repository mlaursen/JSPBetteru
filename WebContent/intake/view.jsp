<%@page contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:base>
  <jsp:attribute name="title">Daily Intake</jsp:attribute>
  <jsp:attribute name="content">
  <h3>This is the Daily Intake Page.  It is still under construction.</h3>
  <h4>This is a demo table</h4>
  <c:forEach items="${formulas}" var="formula">
  <t:intake_table formula="${formula}" />
  </c:forEach>
  </jsp:attribute>
</t:base>