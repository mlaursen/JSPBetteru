<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <h4>Ingredients</h4>
      <table class="table table-striped table-bordered table-hover table-condensed">
        <tr>
          <th>Name</th>
          <th>Brand</th>
          <th>Category</th>
          <th>Serving Size (default)</th>
          <th>Serving Size (alternate)</th>
          <th>Calories (kCal)</th>
          <th>Fat (g)</th>
          <th>Carbohydrates (g)</th>
          <th>Protein (g)</th>
        <c:forEach items="${ingredients}" var="i"><tr>
          <td>${i.name}</td>
          <td>${i.brand}</td>
          <td>${i.category}</td>
          <td>${i.servingSize} (${i.servingUnitShort})</td>
          <td>${i.altServingSize} (${i.altServingUnitShort})</td>
          <td>${i.calories}</td>
          <td>${i.fat}</td>
          <td>${i.carbs}</td>
          <td>${i.protein}</td>
        </tr></c:forEach>
      </table>