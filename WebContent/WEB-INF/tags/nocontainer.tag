<%@ tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="title" fragment="true" %>
<%@ attribute name="refresh" fragment="true" %>
<%@ attribute name="additional_css" fragment="true" %>
<%@ attribute name="content" fragment="true" %>
<%@ attribute name="additional_js" fragment="true" %>
<%@ attribute name="bodyclass" required="false" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
  <head>
    <title><jsp:invoke fragment="title" /></title>
    <jsp:invoke fragment="refresh" />
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <jsp:invoke fragment="additional_css" />
  </head>
  <body<c:if test="${not empty bodyclass}"> class="${bodyclass}"</c:if>>
    <div class="navbar navbar-inverse">
      <div class="navbar-inner">
        <a class="brand" href="${pageContext.request.contextPath}/info.jsp">INFO</a>
        <ul class="nav">
          <li><a href="${pageContext.request.contextPath}/stats/index.jsp">Stats</a></li>
          <li><a href="${pageContext.request.contextPath}/goals/index.jsp">Goals</a></li>
          <li><a href="${pageContext.request.contextPath}/accounts/index.jsp">Settings</a></li>
          <li><a href="${pageContext.request.contextPath}/ingredients/index.jsp">Ingredients</a></li>
          <li><a href="${pageContext.request.contextPath}/meals/index.jsp">Meals</a></li>
        </ul>
        <ul class="nav pull-right">
          <li><a data-toggle="modal" href="${pageContext.request.contextPath}/ingredients/create.jsp">Add Ingredient</a></li>
          <li><a data-toggle="modal" href="${pageContext.request.contextPath}/meals/create.jsp">Add Meal</a></li>
          <li><a href="${pageContext.request.contextPath}<% if(session.getAttribute("userid") == null) { %>/index.jsp">Login<% } else { %>/accounts/logout.jsp">Logout<% } %></a></li>
        </ul>
      </div>
    </div>
    <jsp:invoke fragment="content" />
    <div class="navbar navbar-fixed-bottom">
      <div class="navbar-inner">
        <table class="navbar-bot">
          <tr>
            <td class="left">
            <span class="additional-info">Report any concerns to mlaursen</span>
            </td>
            <td class="center"><a class="brand">BETTERU</a></td>
            <td class="right"><span class="additional-info">Last Modified: somedate.jpg</span></td>
          </tr>
        </table>
      </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-2.0.3.js"></script>
    <script src="${pageContext.request.contextPath}/js/util.js"></script>
    <script src="${pageContext.request.contextPath}/js/form_validation.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
    <jsp:invoke fragment="additional_js"></jsp:invoke>
  </body>
</html>