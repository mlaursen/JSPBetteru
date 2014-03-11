package com.betteru.intake.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betteru.accounts.objects.Account;
import com.betteru.intake.objects.DailyIntake;
import com.betteru.intake.objects.DailyMealIntake;
import com.betteru.intake.objects.Formula;
import com.betteru.intake.objects.Weight;
import com.betteru.meals.objects.MealView;
import com.github.mlaursen.database.managers.ObjectManager;

/**
 * Servlet implementation class ViewDailyIntake
 * 
 * @author mlaursen
 */
public class ViewDailyIntake extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ObjectManager manager;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewDailyIntake() {
		super();
		manager = new ObjectManager(DailyIntake.class, DailyMealIntake.class, Formula.class, Account.class, Weight.class, MealView.class);
		manager.renamePackage(Formula.class, DailyIntake.class);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/intake/view.jsp");
		String userid = (String) request.getSession().getAttribute("userid");
		userid = userid == null ? "0" : userid;
		List<Formula> formulas = manager.executeCustomGetAllProcedure(Formula.GET_FROM_FORMULA, Formula.class, userid, null);
		Formula.generateMeals(formulas, manager);
		request.setAttribute("formulas", formulas);
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
