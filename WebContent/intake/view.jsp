<%@page contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:base>
  <jsp:attribute name="title">Daily Intake</jsp:attribute>
  <jsp:attribute name="content">
  <div class="row-fluid">
    <div class="span8 offset2">
      <t:successfield success="${success}" />
      <t:errorfield errors="${nonFieldError}" />
      </div>
  </div>
  <c:forEach items="${formulas}" var="formula"><h4><c:out value="${formula.getIntakeDateString()}"> /</c:out></h4>
  <table class="table table-striped table-bordered table-hover table-condensed">
  <tr>
    <th></th>
    <c:forEach items="${formula.meals}" var="meal"><th>${meal.name}</th></c:forEach>
    <th>Expected Total</th>
    <th>My Total</th>
    <th>Remaining Total</th>
  </tr>
  <tr>
  <th>Total Calories</th><c:forEach items="${formula.meals}" var="meal"><td><c:out value="${meal.totalCalories}" /></td></c:forEach>
  <td><c:out value="${formula.expectedCalories}" /></td>
  <td><c:out value="${formula.totalCalories}" /></td>
  <td><c:out value="${formula.remainingCalories}" /></td>
  </tr>
  <tr>
  <th>Total Fat</th>
  <c:forEach items="${formula.meals}" var="meal"><td><c:out value="${meal.totalFat}" /></td></c:forEach>
  <td><c:out value="${formula.expectedFat}" /></td>
  <td><c:out value="${formula.totalFat}" /></td>
  <td><c:out value="${formula.remainingFat}" /></td>
  </tr>
  <tr>
  <th>Total Carbohydrates</th>
  <c:forEach items="${formula.meals}" var="meal"><td><c:out value="${meal.totalCarbs}" /></td></c:forEach>
  <td><c:out value="${formula.expectedCarbs}" /></td>
  <td><c:out value="${formula.totalCarbs}" /></td>
  <td><c:out value="${formula.remainingCarbs}" /></td>
  </tr>
  <tr>
  <th>Total Protein</th>
  <c:forEach items="${formula.meals}" var="meal"><td><c:out value="${meal.totalProtein}" /></td></c:forEach>
  <td><c:out value="${formula.expectedProtein}" /></td>
  <td><c:out value="${formula.totalProtein}" /></td>
  <td><c:out value="${formula.remainingProtein}" /></td>
  </tr>
</table>
  </c:forEach>
  </jsp:attribute>
  <jsp:attribute name="additional_js">
  <script type="text/javascript">
  	$(function() {
  		window.setTimeout(function() {
  			$('.alert').alert('close');
  		}, 3000);
  	});
  </script>
  </jsp:attribute>
</t:base>