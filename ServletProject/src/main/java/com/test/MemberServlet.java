package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/Member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
		
		Enumeration<String> enu = request.getParameterNames();//names와 values
		
		while(enu.hasMoreElements()) {  // 있는지 없는지 판정
			String name = enu.nextElement();   //가져옴
					String value = request.getParameter(name);
			out.println(name+" : "+value+"<br>");
		}
		out.println("</body></html>");
	}

}
