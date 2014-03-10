<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ attribute name="time" required="true"%>
<%@ attribute name="url" required="true"%>
<%@ attribute name="message" required="true"%>

<t:base>
  <jsp:attribute name="refresh">
  <meta http-equiv="refresh" content="${time}; url=${url}">
  </jsp:attribute>
  <jsp:attribute name="content">
    <div class="alert alert-success">
      ${message}
    </div>
  </jsp:attribute>
</t:base>
