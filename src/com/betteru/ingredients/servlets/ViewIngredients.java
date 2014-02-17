package com.betteru.ingredients.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betteru.ingredients.database.Brand_Old;
import com.betteru.ingredients.database.Category_Old;
import com.betteru.ingredients.database.IngredientView;
import com.github.mlaursen.bootstrap.sidebar.SidebarNav;

/**
 * Servlet implementation class ViewIngredients
 */
public class ViewIngredients extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/ingredients/table.jsp");
		List<IngredientView> allIngs = new IngredientView().lookupAll();
		SidebarNav nav = new SidebarNav(Category_Old.getSidebarList(), Brand_Old.getSidebarList());
		request.setAttribute("filters", nav.toHtml());
		request.setAttribute("ingredients", allIngs);
		rd.forward(request, response);
	}
}
