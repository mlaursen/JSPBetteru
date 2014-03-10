package com.betteru.intake.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betteru.accounts.objects.Account;
import com.betteru.ingredients.forms.CreateIngredientForm;
import com.betteru.intake.objects.DailyIntake;
import com.betteru.intake.objects.DailyMealIntake;
import com.betteru.intake.objects.HarrisBenedict;
import com.betteru.intake.objects.MifflinStJoer;
import com.betteru.intake.objects.Weight;
import com.github.mlaursen.database.managers.ObjectManager;
import com.github.mlaursen.database.utils.ClassUtil;

/**
 * Servlet implementation class ViewDailyIntake
 */
public class ViewDailyIntake extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ObjectManager manager;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewDailyIntake() {
		super();
		manager = new ObjectManager(DailyIntake.class, DailyMealIntake.class, MifflinStJoer.class, HarrisBenedict.class, Account.class, Weight.class);
		manager.renamePackage(MifflinStJoer.class, DailyIntake.class);
		manager.renamePackage(HarrisBenedict.class, DailyIntake.class);
		System.out.println(manager);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/intake/view.jsp");
		System.out.println(manager.executeCustomGetAllProcedure(MifflinStJoer.MIFFLIN_ST_JOER, MifflinStJoer.class, 0, null));
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}
