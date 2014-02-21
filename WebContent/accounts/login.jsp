<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base modals="${modals}">
  <jsp:attribute name="title">Account Login</jsp:attribute>
  <jsp:attribute name="content">
  <div class="row-fluid">
  	<div class="span12"></div>
    <div class="span12"></div>
    <div class="span12"></div>
    <div class="span12"></div>
  </div>
  <t:successfield success="${success}" />
  <t:errorfield errors="${errors}" />
  <div class="row-fluid">
    <div class="span8 offset2">
    <span class="betteru-banner"></span>
      <div class="hero-unit">
      ${form}
      </div>
    </div>
  </div>
  </jsp:attribute>
</t:base>