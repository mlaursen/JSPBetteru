<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="meal" required="true" %>
<div class="box" id="meal_${meal.id}">
  <div class="contents">
    <div class="name">
      ${meal.name}
    </div>
    <hr />
    <div class="block nutritional-facts">
      <div class="head">Nutritional Facts</div>
      <div><strong>Calories: </strong>${meal.total_calories}</div>
      <div><strong>Fat: </strong>${meal.total_fat}g</div>
      <div><strong>Carbohydrates: </strong>${meal.total_carbohydrates}g</div>
      <div><strong>Protein: </strong>${ meal.total_protein}g</div>
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