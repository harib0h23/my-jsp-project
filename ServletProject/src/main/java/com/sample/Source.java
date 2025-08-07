package com.sample;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*  (servlet을 이용한)페이지 이동방식 
 *     메소드 호출을 통해서 페이지를 이동할 수 있는 방법(두 가지)
 *    
 *     forward방법과 redirect방식
 *     
 *        구분                        forward방식                  redirect방식
 *        url                       url이 바뀌지 않음                  url이 바뀜
 *        요청객체와 응답객체           유지된다                    유지되지 않는다  
 *        속도                            빠르다                          느리다
 *        소속                        request객체                 response객체
 */

@WebServlet("/Source")
public class Source extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Source Start ...");
		// 첫번째 방법 forward방식으로 페이지 이동
		/*
		RequestDispatcher view = request.getRequestDispatcher("Destination"); //--> servlet
		view.forward(request, response);
		*/
		// 두번쨰 방법 redirect방식으로 페이지 이동
		response.sendRedirect("Destination");
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		processRequest(request, response);
	}

}
