package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.net.httpserver.HttpsServer;


//@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			HttpSession session = request.getSession(false);
			if(session != null) {  // 로그인 정보가 존재했을 경우(로그인에 성공했을 때)
				String sessionId = session.getId();
				System.out.println("세션 아이디 : "+sessionId);
				
				String user = (String)session.getAttribute("user"); //로그인
				
				out.println("<html>");
				out.println("<body>");
				out.println("<table width='300' border='1'>");
				
				out.println("<tr>"); // 하나의 행
				out.println("<td width='300' align='center'>"
				+user+"님 로그인되었습니다.</td>");
				out.println("</tr>");  // 한 행의 종료
				
				out.println("<tr>"); // 두번째 행  
				out.println("<td align='center'>");
				out.println("<a href='#'>회원정보</a>");  
				out.println("<a href='Logout'>로그아웃</a>");
				out.println("</td>");  
				out.println("</tr>");  // 두번째 행 종료
				
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
			}else { // 로그인이 아닌 경우
				out.println("<html>");
				out.println("<body>");
				out.println("<form method='post' action='LoginCheck'>");
				out.println("<table width='300' border='1'>");
			
				out.println("<tr>");
				out.println("<th width='100'>아이디</th>"); //tableheader
				out.println("<td width='200'>&nbsp;<input type='text' name='id'></td>");		//tabledata
				out.println("</tr>");  //아이디
				
				out.println("<tr>");
				out.println("<th width='100'>비밀번호</th>"); //tableheader
				out.println("<td width='200'>&nbsp;<input type='password' name='pw'></td>");		//tabledata
				out.println("</tr>");  //비밀번호
				
				out.println("<tr>");
				out.println("<td colspan='2' align='center'>");
				out.println("<input type='button' value='회원가입'>");
				out.println("<input type='submit' value='로그인'>");			
				out.println("</tr>");  // 두 버튼
			
				out.println("</table>");
				
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			}
			
			
			
		}finally {  // 위예외처리가 있으므로 catch구문 쓰지 않아도 됨
			out.close();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}

}
