package com.betteru.accounts.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betteru.accounts.Account;
import com.betteru.accounts.AccountSetting;
import com.betteru.accounts.AccountView;
import com.betteru.accounts.forms.EditAccountForm;

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
		//System.out.println(form);
		if(form.isValid()) {
			String b = form.getFieldValue(EditAccountForm.BIRTHDAY);
			String g = form.getFieldValue(EditAccountForm.GENDER);
			String u = form.getFieldValue(EditAccountForm.UNIT);
			String h = form.getFieldValue(EditAccountForm.HEIGHT);
			String w = form.getFieldValue(EditAccountForm.WEEKDAY);
			String m = form.getFieldValue(EditAccountForm.MULTIPLIER);
			System.out.println("gender: "+ g);
			System.out.println("unit: "+ u);
			System.out.println("week: "+ w);
			System.out.println("mult: " + m);
			/*
			Account a = new Account(av);
			AccountSetting as = new AccountSetting(av);
			a.setBirthday(b);
			a.setGender(g);
			a.setUnit(u);
			as.setHeight(h);
			as.setRecalcById(r);
			as.setMultiplierById(m);
			if( a.update() && as.update() ) {
			*/
			av.setBirthday(b);
			av.setGender(g);
			av.setUnitSystem(u);
			av.setHeight(h);
			av.setWeekday(w);
			av.setMultiplier(m);
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
