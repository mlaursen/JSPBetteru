package com.betteru.ingredients.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betteru.databasechoices.ingredients.Brand;
import com.betteru.ingredients.forms.CreateIngredientForm;
import com.betteru.ingredients.objects.Ingredient;
import com.github.mlaursen.bootstrap.forms.fields.TextAction;
import com.github.mlaursen.database.managers.ObjectManager;

/**
 * Servlet implementation class CreateIngredient
 */
public class CreateIngredient extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ObjectManager manager;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateIngredient() {
        super();
        manager = new ObjectManager(Ingredient.class, Brand.class);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/ingredients/createingredient.jsp");
		request.setAttribute("form", new CreateIngredientForm().toHtml());
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/ingredients/createingredient.jsp");
		CreateIngredientForm f = new CreateIngredientForm(request);
		if(f.isValid()) {
			TextAction brands = (TextAction) f.getField(CreateIngredientForm.BRANDS);
			boolean success = true;
			if(brands.getChosen() == 0)
				success = manager.create(new Brand(brands.getValue()));
			if(success) {
				Ingredient i = new Ingredient(f);
				success = manager.create(i);
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
