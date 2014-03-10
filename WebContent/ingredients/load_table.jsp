<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4>Ingredients</h4>
<table
  class="table table-striped table-bordered table-hover table-condensed">
  <tr>
    <th class="ingredient-table-name">Name</th>
    <th class="ingredient-table-brand">Brand</th>
    <th class="ingredient-table-category">Category</th>
    <th class="ingredient-table-serving">Serving Size (default)</th>
    <th class="ingredient-table-serving">Serving Size (alternate)</th>
    <th class="ingredient-table-macro">Calories (kCal)</th>
    <th class="ingredient-table-macro">Fat (g)</th>
    <th class="ingredient-table-macro">Carbs (g)</th>
    <th class="ingredient-table-macro">Protein (g)</th>
    <c:forEach items="${ingredients}" var="i">
      <tr>
        <td>${i.name}</td>
        <td>${i.brand}</td>
        <td>${i.category}</td>
        <td>${i.defaultServing}</td>
        <td>${i.alternateServing}</td>
        <td>${i.calories}</td>
        <td>${i.fat}</td>
        <td>${i.carbs}</td>
        <td>${i.protein}</td>
      </tr>
    </c:forEach>
</table>