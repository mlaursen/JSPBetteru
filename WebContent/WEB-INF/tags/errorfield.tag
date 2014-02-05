<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="errors" required="true" %>
<c:if test="${not empty errors }">
  <div class="row-fluid">
    <div class="span8 offset2 alert alert-error alert-block">
      <button type="button" class="close" data-dismiss="alert">&times;</button>
      <strong>${errors}</strong>
    </div>
  </div>
</c:if>