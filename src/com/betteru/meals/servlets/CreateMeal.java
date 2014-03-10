package com.betteru.meals.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betteru.meals.forms.CreateMealForm;
import com.github.mlaursen.database.managers.ObjectManager;

/**
 * Servlet implementation class CreateMeal
 */
public class CreateMeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ObjectManager manager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMeal() {
        super();
        this.manager = new ObjectManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/meals/create_meal.jsp");
		request.setAttribute("form", new CreateMealForm().toHtml());
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
