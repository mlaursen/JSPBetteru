<%@page contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:base>
  <jsp:attribute name="title">Create Meal</jsp:attribute>
  <jsp:attribute name="content">
  <div class="row-fluid">
    <t:errorfield errors="${errors}" />
  </div>
  <div class="row-fluid">
    <div class="span8 offset2">
    ${form}
    </div>
  </div>
  </jsp:attribute>
  <jsp:attribute name="additional_js">
  </jsp:attribute>
</t:base>