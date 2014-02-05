<%@page contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:base>
  <jsp:attribute name="title">View Ingredients</jsp:attribute>
  <jsp:attribute name="container">-fluid</jsp:attribute>
  <jsp:attribute name="content"><div class="row-fluid">
    <t:sidebar contents="${filters}" />
    <div class="span10">
      <div id="table_content">
      <jsp:include page="load_table.jsp"><jsp:param value="${ingredients}" name="ingredients"/></jsp:include>
      </div>
    </div>
  </div>
  </jsp:attribute>
  <jsp:attribute name="additional_js">
  <script type="text/javascript">
  function activate(item) { 
    name = item.getAttribute('name');
    id = item.getAttribute('id');
    $('li[name='+name+']').removeClass('active');
    item.setAttribute('class','active');
    action = "${pageContext.request.contextPath}/ingredients/LoadTable";

    if(name === 'categories') {
      brnd = $('li[name=brands].active').attr('id');
      brnd = brnd.replace("brands_", '');
      id = id.replace('categories_', '');
      console.log(brnd);
      console.log(id);
      $.get(action, {category: id, brand: brnd}, function(data) { 
        $('#table_content').hide().html(data).fadeIn('fast');
      });
    }
    else if(name === 'brands') { 
      catg = $('li[name=categories].active').attr('id');
      catg = catg.replace("categories_", '');
      id = id.replace("brands_", '');
      console.log(catg);
      console.log(id);
      $.get(action, {category: catg, brand: id}, function(data) {
        $('#table_content').hide().html(data).fadeIn('fast');
      });
    }
  }
</script>
  </jsp:attribute>
</t:base>