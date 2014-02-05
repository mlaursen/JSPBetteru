<%@page isErrorPage="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base bodyclass="page404">
  <jsp:attribute name="title">Page 404</jsp:attribute>
  <jsp:attribute name="content">
  <div class="span10">
    <h1 class="not_found">Uh oh, Something Broke!</h1>
    <h3>We're Sorry</h3>
    <p>What you are looking for doesn't seem to exist.
    Check out any of the links on the navigation bar or go to the <a href="${pageContext.request.contextPath}">index</a> page.
    </p>
  </div>
  <div class="thanks">Thanks to Writer <a href="http://techfar.com/author/gmornob/">gnornob</a> for the free 404 page.</div>
  </jsp:attribute>
</t:base>