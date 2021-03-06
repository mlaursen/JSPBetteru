package com.betteru.accounts.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betteru.accounts.forms.EditAccountForm;
import com.betteru.accounts.objects.Account;
import com.betteru.accounts.objects.AccountSetting;
import com.betteru.accounts.objects.AccountView;
import com.github.mlaursen.database.managers.ObjectManager;

/**
 * Servlet implementation class EditAccountServlet
 */
public class EditAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ObjectManager manager;   
    
    public EditAccountServlet() {
        super();
        manager = new ObjectManager(Account.class, AccountView.class, AccountSetting.class);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = (String) request.getSession().getAttribute("userid");
		AccountView a = manager.executeCustomGetProcedure(AccountView.GET_FROM_VIEW, AccountView.class, userid);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/accounts/settings.jsp");
		request.setAttribute("form", new EditAccountForm(a).toHtml());
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = (String) request.getSession().getAttribute("userid");
		AccountView av = manager.executeCustomGetProcedure(AccountView.GET_FROM_VIEW, AccountView.class, userid);
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
			Account a = new Account(av);
			AccountSetting as = new AccountSetting(av);
			if(manager.update(a) && manager.create(as)) {
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
