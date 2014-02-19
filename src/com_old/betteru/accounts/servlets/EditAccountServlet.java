package com_old.betteru.accounts.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.mlaursen.bootstrap.forms.fields.DropdownChoice;
import com_old.betteru.accounts.AccountView;
import com_old.betteru.accounts.forms.EditAccountForm;

/**
 * Servlet implementation class EditAccountServlet
 */
public class EditAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountView a = new AccountView(request);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/accounts/settings.jsp");
		request.setAttribute("form", new EditAccountForm(a).toHtml());
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = (String) request.getSession().getAttribute("userid");
		AccountView av = new AccountView(userid);
		EditAccountForm form = new EditAccountForm(request, av);
		if(form.isValid()) {
			String birthday = form.getBirthday();
			String gender = form.getGender();
			String unitSystem = form.getUnitSystem();
			String height = form.getHeight();
			String weekday = form.getWeekday();
			String multiplier = form.getMultiplier();
			av.setBirthday(birthday);
			av.setGender(gender);
			av.setUnitSystem(unitSystem);
			av.setHeight(height);
			av.setWeekday(weekday);
			av.setMultiplier(multiplier);
			System.out.println(av);
			if(av.update()) {
				request.setAttribute("success", "Your settings have been successfully updated!");
			}
			else {
				request.setAttribute("nonFieldError", "There was an error updating your account settings.  Please try again.");
			}
		}
		request.setAttribute("form", form.toHtml());
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/accounts/settings.jsp");
		rd.forward(request, response);
	}

}
