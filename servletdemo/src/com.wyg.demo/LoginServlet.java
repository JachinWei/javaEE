package com.wyg.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet()被执行到了");
	}

	//service有的话，doGet和doPost方法都不会被执行到，直接执行service
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		String name = (String) req.getParameter("name");
		String pwd = (String) req.getParameter("pwd");
		if(name == null && pwd == null){
			((HttpServletResponse) resp).sendRedirect("fail.html");
			return;
		}
		if (name.equals("wyg") && pwd.equals("123456")) {
			req.getRequestDispatcher("success.html").forward(req, resp);
		} else {
			((HttpServletResponse) resp).sendRedirect("fail.html");
		}
	}

}
