package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/Sport")
public class SportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식에서의 한글처리
		request.setCharacterEncoding("utf-8");
		
		String[] sports = request.getParameterValues("sports"); // 여러개가 올수있음  html파일의 name값과 동일
		String gender = request.getParameter("gender");  // 값이 하나만 있음

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();	
		
		out.println("<html><body>");
		for(String sport : sports) {
		out.println("좋아하는 운동 : "+sport+"<br>");
		}
		out.println("성별 : "+gender+"<br>");
		out.println("</body></html>");
	}

}
