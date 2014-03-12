package com.betteru.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthenticatedFilter
 */
@WebFilter("/AuthenticatedFilter")
public class AuthenticatedFilter implements Filter {
	private String contextPath;

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if(isAuthenticatedNeeded(req) && req.getSession().getAttribute("userid") == null) {
			req.getSession().setAttribute("errors", "You must be logged in to view this page. Please log in.");
			res.sendRedirect(contextPath + "/");
		}
		else {
			chain.doFilter(request, response);
		}
	}
	
	public boolean isIndex(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return uri.matches(contextPath + "/(index.jsp)?");
	}
	
	private boolean isAuthenticatedNeeded(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return !uri.matches(contextPath + "/((info|index)\\.jsp)?");
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		contextPath = fConfig.getServletContext().getContextPath();
	}

}
