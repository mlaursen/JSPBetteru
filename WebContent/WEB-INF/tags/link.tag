<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="link" required="true" %>
<%@ attribute name="linktext" required="true" %>
<a href="${pageContext.request.contextPath}/${link}">${linktext}</a>