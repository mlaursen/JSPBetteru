package com_old.betteru.ingredients.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.mlaursen.bootstrap.forms.fields.TextAction;
import com_old.betteru.ingredients.database.Brand;
import com_old.betteru.ingredients.database.Ingredient;
import com_old.betteru.ingredients.forms.CreateIngredientForm;

/**
 * Servlet implementation class CreateIngredient
 */
public class CreateIngredient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateIngredient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/ingredients/createingredient.jsp");
		request.setAttribute("form", new CreateIngredientForm().toHtml());
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/ingredients/createingredient.jsp");
		CreateIngredientForm f = new CreateIngredientForm(request);
		if(f.isValid()) {
			TextAction brands = (TextAction) f.getField(CreateIngredientForm.BRANDS);
			boolean success = true;
			if(brands.getChosen() == 0)
				success = new Brand(brands.getValue()).create();
			if(success) {
				Ingredient i = new Ingredient(f);
				success = i.create();
			}
			
			if(!success) {
				request.setAttribute("errors", "There was an error creating the ingredient. Please try again.");
				request.setAttribute("form", f.toHtml());
				rd.forward(request, response);
			}
			else {
				response.sendRedirect("index.jsp");
			}
		}
		else {
			request.setAttribute("form", f.toHtml());
			rd.forward(request, response);
		}
	}

}