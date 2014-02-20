package com_old.betteru.accounts.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com_old.betteru.accounts.Account;
import com_old.betteru.accounts.TempAccount;
import com_old.betteru.accounts.forms.CreateAccountForm;
import com_old.betteru.accounts.forms.LoginForm;

/**
 * Servlet implementation class LoginPageServlet
 */
public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/accounts/login.jsp");
		request.setAttribute("modals", new CreateAccountForm().asModal());
		request.setAttribute("form", new LoginForm().toHtml());
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginForm loginForm = new LoginForm(request);
		CreateAccountForm createForm = new CreateAccountForm(request);
		
		if(loginForm.isFromRequest(request)) {
			Account a = new Account(loginForm.getFieldValue("username"), loginForm.getFieldValue("password"));
			if(loginForm.isValid() && a.isValidUser()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("userid", a.getPrimaryKey());
				a.updateLastLogin();
				response.sendRedirect("accounts/index.jsp");
				return;
			}
			else {
				request.setAttribute("errors", "Invalid username or password.");
			}
		}
		else if(createForm.isFromRequest(request)) {
			TempAccount ta = new TempAccount(createForm.getFieldValue("username"), createForm.getFieldValue("password"));
			if(createForm.isValid() && ta.create() && Account.createFromTemp(ta)) {
				request.setAttribute("success", "You have successfully created your account.  Please log in.");
			}
			else {
				request.setAttribute("errors", "Username is already being used. Please try another.");
			}
			
		}
		else {
			request.setAttribute("errors", "Came from an invalid form.");
		}

		doGet(request, response);
	}

}