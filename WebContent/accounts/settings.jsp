<%@page contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:base>
  <jsp:attribute name="title">Edit Account</jsp:attribute>
  <jsp:attribute name="additional_css">
  <link
      href="${pageContext.request.contextPath}/css/bootstrap/overcast/jquery-ui-1.10.3.custom.css"
      rel="stylesheet" />
  </jsp:attribute>
  <jsp:attribute name="content">
  <div class="row-fluid">
    <div class="span8 offset2">
      <t:successfield success="${success}" />
      <t:errorfield errors="${nonFieldError}" />
      <h3>Welcome!</h3>
      <h4>Current Settings:</h4>
      <div class="span12"></div>
      ${form}
    </div>
  </div>
  </jsp:attribute>
  <jsp:attribute name="additional_js">
  <script
      src="${pageContext.request.contextPath}/js/jquery/jquery-ui-1.10.3.custom.js"></script>
  <script type="text/javascript">
  	$(function() {
  		$('#id_birthday').datepicker();
  		window.setTimeout(function() {
  			$('.alert').alert('close');
  		}, 3000);
  	});
  </script>
  </jsp:attribute>
</t:base>