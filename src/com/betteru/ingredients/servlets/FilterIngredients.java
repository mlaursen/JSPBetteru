package com.betteru.ingredients.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betteru.ingredients.objects.Ingredient;
import com.github.mlaursen.database.managers.ObjectManager;


/**
 * Servlet implementation class FilterIngredients
 */
public class FilterIngredients extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ObjectManager manager;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterIngredients() {
        super();
        manager = new ObjectManager(Ingredient.class);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/ingredients/load_table.jsp");
		request.setAttribute("ingredients", manager.filter(Ingredient.class, request.getParameter("category"), request.getParameter("brand")));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
