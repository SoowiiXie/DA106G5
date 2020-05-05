package com.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.staff.model.StaffVO;

public class Back_End_LoginFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		StaffVO staffVO = (StaffVO)session.getAttribute("staffVO");
		if(staffVO == null) {
			session.setAttribute("originalJSP", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/back_end/staff/login.jsp");  
			return;
		}else {
			chain.doFilter(request, response);
		}
		
	}
	
	@Override
	public void destroy() {
		
	}
}
