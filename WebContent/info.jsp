<%@page contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:base>
  <jsp:attribute name="title">Betteru - Information</jsp:attribute>
  <jsp:attribute name="content">
  <div class="span8 row-fluid">
  <h3>Betteru - Information</h3>
  <p>Betteru is a web based application for tracking your daily intake of calories and macronutrients.</p>
  <p>Before getting started, it is assumed that the user knows the following:</p>
  <ul>
  <li>TDEE</li>
  <li>Calculating TDEE</li>
  <li>Macronutrients</li>
  <li>Activity Multiplier</li>
  </ul>
  </div>
  <div class="span8 row-fluid">
  <h3>How it Works</h3>
  <p>
  To use the website, you must create a website and give basic information. Your height, gender, activity level, unit system,
  weekday for recalculating your TDEE, and your birthday are required.  None of your information will be used for anything other than 
  calculating TDEE. Your birthday is used to always automatically update your age.
  </p>
  <p>
  Once your account is fully set up, you can begin. The Daily Intake page will show your weekly goals for calories and macronutrients
  based on the information given.  For each day, you can specify a calorie surplus or deficit and the information will be updated. Once you
  have been a user for a while, you can check out the statistics page which will show a graph of your weight change over the course of the
  dates given.
  </p>
  </div>
  <div class="span8 row-fluid">
  <h3>Would You Like to Contribute?</h3>
  <p>
  This is an open-source project and you are more than welcome to contribute. The git-repository is located at:
  <t:hrefexternal link="https://github.com/mlaursen/jspbetteru" linktext="https://github.com/mlaursen/jspbetteru" />
  </p>
  </div>
  </jsp:attribute>
</t:base>