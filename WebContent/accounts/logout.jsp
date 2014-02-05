<%@page contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:redirect url="${pageContext.request.contextPath}/index.jsp" time="3"
  message="You have successfully logged out.  Redirecting to the login page in 3 seconds." />

<%
	session.invalidate();
%>