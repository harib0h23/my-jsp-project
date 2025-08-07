package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userid"); //html파일에 이름과 같아야함
		String pw = request.getParameter("password");
	
		System.out.println("아이디 : "+id);
		System.out.println("비밀번호 : "+pw);
		
		// get 방식에서의 한글처리
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();		
		out.println("<html><body>");
		out.println("아이디 : "+id+"<br>");   //브라우저는 태그만 먹는다. ln은 안먹음
		out.print("비밀번호 : "+pw+"<br>");
		out.println("</body></html>");
		
	}

}
