package com.betteru.accounts.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.betteru.accounts.forms.EditAccountForm;
import com.betteru.accounts.objects.Account;
import com.betteru.accounts.objects.AccountSetting;
import com.betteru.accounts.objects.AccountView;
import com.betteru.accounts.objects.Weight;
import com.github.mlaursen.database.managers.ObjectManager;

/**
 * Servlet implementation class AddWeight
 */
public class AddWeightServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ObjectManager manager;
	
	public AddWeightServlet() {
		super();
		manager = new ObjectManager(Weight.class);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = (String) request.getSession().getAttribute("userid");
		Double weight = (Double) request.getAttribute("weight");
		System.out.println(manager.create(new Weight(userid, weight)));
		response.sendRedirect("intake/index.jsp");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("we here");
		response.sendRedirect("intake/index.jsp");
	}
	
}
