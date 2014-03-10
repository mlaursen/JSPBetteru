<%@page contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:nocontainer>
  <jsp:attribute name="title">View Meals</jsp:attribute>
  <jsp:attribute name="additional_css">
  <link href="${pageContext.request.contextPath}/css/meals.css"
      rel="stylesheet" />
  </jsp:attribute>
  <jsp:attribute name="content">
  <div class="meal-container">
    <jsp:include page="meal_table.jsp"><jsp:param
          value="${meals}" name="meals" /></jsp:include>
  </div>
  </jsp:attribute>
  <jsp:attribute name="additional_js">
  <script type="text/javascript">
  $(".box").hover(function() {
      $(this).attr('title', "Click to add this meal to your current day.");
  });
  $(".box").click(function () {
      id = $(this).attr('id');
      window.location = "/goals/add/" + id;
  });
</script>
  </jsp:attribute>
</t:nocontainer>