<%@ tag language="java" pageEncoding="ISO-8859-1"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ attribute name="info" required="true"%><c:if test="${not empty info}">  <div class="row-fluid">
    <div class="span8 offset2 alert alert-info alert-block">
      <button type="button" class="close" data-dismiss="alert">&times;</button>
      <strong>${info}</strong>
    </div>
  </div></c:if>