<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach items="${meals}" var="meal"><div class="box" id="meal_${meal.primaryKey}">
  <div class="contents">
    <div class="name">
      ${meal.name}
    </div>
    <hr />
    <div class="block nutritional-facts">
      <div class="head">Nutritional Facts</div>
      <div><strong>Calories: </strong>${meal.totalCalories}</div>
      <div><strong>Fat: </strong>${meal.totalFat}</div>
      <div><strong>Carbohydrates: </strong>${meal.totalCarbs}</div>
      <div><strong>Protein: </strong>${ meal.totalProtein}</div>
    </div>
    <hr />
    <div class="ingredients">
      <div class="head">
        Ingredients:
      </div>
      ${meal.ingredientList}
    </div>
    <hr />
    <strong>Description:</strong> ${meal.description}
  </div>
</div>
</c:forEach><span class="stretch"></span>