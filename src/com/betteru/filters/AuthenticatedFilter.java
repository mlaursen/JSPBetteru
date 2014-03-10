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
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if(!isIndex(req) && req.getSession().getAttribute("userid") == null) {
			res.sendRedirect(contextPath + "/");
		}
		else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}
	
	public boolean isIndex(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return uri.matches(contextPath + "/(index.jsp)?");
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		contextPath = fConfig.getServletContext().getContextPath();
	}

}
