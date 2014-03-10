<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<h4>Friday, Ferbruary 28, 2014</h4>
<table
  class="table table-striped table-bordered table-hover table-condensed">
  <tr>
    <th></th>
    <th>Meal 01</th>
    <th>Meal 02</th>
    <th>Meal 03</th>
    <th>Meal 04</th>
    <th>Meal 05</th>
    <th>Expected Total</th>
    <th>My Total</th>
    <th>Remaining Total</th>
  </tr>
  <t:intake_table_macro_row colName="Calories" val="2218.36" />
  <t:intake_table_macro_row colName="Fat" val="22.44" />
  <t:intake_table_macro_row colName="Carbohydrates" val="138.47" />
  <t:intake_table_macro_row colName="Protein" val="187" />
</table>