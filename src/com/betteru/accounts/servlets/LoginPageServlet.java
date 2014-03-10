package com.betteru.accounts.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.betteru.accounts.forms.CreateAccountForm;
import com.betteru.accounts.forms.LoginForm;
import com.betteru.accounts.objects.Account;
import com.betteru.accounts.objects.TempAccount;
import com.github.mlaursen.database.managers.ObjectManager;

/**
 * Servlet implementation class LoginPageServlet
 */
public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ObjectManager manager;
    public LoginPageServlet() {
        super();
        manager = new ObjectManager(Account.class, TempAccount.class);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/accounts/login.jsp");
		request.setAttribute("modals", new CreateAccountForm().asModal());
		request.setAttribute("form", new LoginForm().toHtml());
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginForm loginForm = new LoginForm(request);
		CreateAccountForm createForm = new CreateAccountForm(request);
		
		if(loginForm.isFromRequest(request)) {
			Account a = new Account(loginForm.getFieldValue("username"), loginForm.getFieldValue("password"));
			if(loginForm.isValid() && a.isValidUser(manager)) {
				HttpSession session = request.getSession(true);
				session.setAttribute("userid", a.getPrimaryKey());
				a.updateLastLogin(manager);
				response.sendRedirect("accounts/index.jsp");
				return;
			}
			else {
				request.setAttribute("errors", "Invalid username or password.");
			}
		}
		else if(createForm.isFromRequest(request)) {
			TempAccount ta = new TempAccount(createForm.getFieldValue("username"), createForm.getFieldValue("password"));
			if(createForm.isValid() && manager.create(ta) && manager.executeCustomProcedure(TempAccount.NEW_ACCOUNT, TempAccount.class, ta.getUsername())) {
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
