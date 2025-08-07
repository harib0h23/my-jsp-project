package com.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@WebServlet("/LoginCheck")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8"); //클라이언트(브라우저)가 POST 방식으로 전송한 데이터를 UTF-8로 인코딩해서 읽겠다는 의미
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		String dbId="admin";  // 임의로 지정 ---- 데이터베이스에서 가져와야함.
		String dbPw="1234";  //
		
		if(dbId.equals(id) && dbPw.equals(pw)) {  // 아이디와 비번이 맞으면 세션에 저장함
			HttpSession session = request.getSession();
			session.setAttribute("user", id); //세션에 저장함			
		}
		
		response.sendRedirect("Login");	
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		processRequest(request, response);
	}

}
