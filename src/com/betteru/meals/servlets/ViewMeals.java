package com.betteru.meals.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betteru.meals.objects.MealPartView;
import com.betteru.meals.objects.MealView;
import com.github.mlaursen.database.managers.ObjectManager;

/**
 * Servlet implementation class ViewMeals
 */
public class ViewMeals extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ObjectManager manager;
	public ViewMeals() {
		super();
		this.manager = new ObjectManager(MealView.class, MealPartView.class);
		//manager.addPackageWithView(MealPart.class, MealPartView.class);
		//manager.addPackageWithView(Meal.class, MealView.class);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/meals/all_meals.jsp");
		List<MealView> meals = manager.getAll(MealView.class);
		MealView.generateMealParts(meals, manager);
		request.setAttribute("meals", meals);
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}
