<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ attribute name="formula" required="true"%>
<h4>${formula.intakeDate}</h4>
<table
  class="table table-striped table-bordered table-hover table-condensed">
  <tr>
    <th></th>
    <c:forEach items="${formula.meals}" var="meal">
      <th>${meal.name}</th>
    </c:forEach>
    <th>Meal 01</th>
    <th>Meal 02</th>
    <th>Meal 03</th>
    <th>Meal 04</th>
    <th>Meal 05</th>
    <th>Expected Total</th>
    <th>My Total</th>
    <th>Remaining Total</th>
  </tr>
</table>