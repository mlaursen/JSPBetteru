<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="success" required="true" %>
<c:if test="${not empty success}">
  <div class="row-fluid">
    <div class="span8 offset2 alert alert-success alert-block">
      <button type="button" class="close" data-dismiss="alert">&times;</button>
      <strong>${success}</strong>
    </div>
  </div>
</c:if>