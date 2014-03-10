<%@ tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="title" fragment="true"%>
<%@ attribute name="refresh" fragment="true"%>
<%@ attribute name="additional_css" fragment="true"%>
<%@ attribute name="content" fragment="true"%>
<%@ attribute name="additional_js" fragment="true"%>
<%@ attribute name="container" required="false"%>
<%@ attribute name="modals" required="false"%>
<%@ attribute name="bodyclass" required="false"%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<title><jsp:invoke fragment="title" /></title><jsp:invoke
  fragment="refresh" />
<t:csslink css="main.css" />
<t:csslink css="bootstrap/bootstrap.min.css" />
<t:csslink css="bootstrap/bootstrap-responsive.css" />
<jsp:invoke fragment="additional_css" />
</head>
<body <c:if test="${not empty bodyclass}"> class="${bodyclass}"</c:if>>
  <div class="navbar navbar-inverse">
    <div class="navbar-inner">
      <a class="brand"
        href="${pageContext.request.contextPath}/info.jsp">INFO</a>
      <ul class="nav">
        <li><t:href link="stats/index.jsp" linktext="Statistics" /></li>
        <li><t:href link="intake/index.jsp" linktext="Daily Intake" /></li>
        <li><t:href link="accounts/index.jsp"
            linktext="Account Settings" /></li>
        <li><t:href link="ingredients/index.jsp"
            linktext="View Ingredients" /></li>
        <li><t:href link="meals/index.jsp" linktext="View Meals" /></li>
      </ul>
      <ul class="nav pull-right">
        <li><t:href link="ingredients/create.jsp"
            linktext="Add an Ingredient" /></li>
        <li><t:href link="meals/create.jsp" linktext="Add a Meal" /></li>
        <li>
          <% if(session.getAttribute("userid") == null) { %><t:href
            link="index.jsp" linktext="Login" />
          <%} else {%><t:href link="accounts/logout.jsp" linktext="Logout" />
          <% } %>
        </li>
      </ul>
    </div>
  </div>
  <div class="container${container}">
    <jsp:invoke fragment="content" />
    ${modals}
  </div>
  <div class="navbar navbar-fixed-bottom">
    <div class="navbar-inner">
      <table class="navbar-bot">
        <tr>
          <td class="left"><span class="additional-info">Report
              any concerns to mlaursen</span></td>
          <td class="center"><a class="brand">BETTERU</a></td>
          <td class="right"><span class="additional-info">Last
              Modified: somedate.jpg</span></td>
        </tr>
      </table>
    </div>
  </div>
  <t:jslink js="jquery/jquery-2.0.3.js" />
  <t:jslink js="util.js" />
  <t:jslink js="bootstrap/bootstrap.js" />
  <jsp:invoke fragment="additional_js" />
</body>
</html>