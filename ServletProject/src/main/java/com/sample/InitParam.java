package com.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*   초기 파라미터값 설정
 *   
 *   1. ServletConfig
 *      - ServletConfig 객체는 Container가 서블릿을 생성할 때 생성되는 객체이다.
 *      - Web.xml에 설정된 값을 이름/값의 쌍으로 된 초기화 파라미터값을 읽어서 저장한다.
 *      - ServletConfig 객체는 Servlet 객체당 한 개씩 생성한다.
 *      - Servlet에서는 getServletConfig()를 이용해서 객체를 얻어온다.
 *      
 *   2. ServletContext
 *      - ServletContext 객체는 web application 당 하나씩 생성한다.
 *      - web application 전체에서 참조할 수 있는 초기화 파라미터를 저장한다.
 *      - Servlet에서는 getServletContext()를 이용해서 객체를 얻어온다.
 *      
 *      web.xml에 설정하는 방법
 *        ServletContext
 *        <context-param>
 *     			<param-name>company</param-name>
 *     			<param-value>GlobalIn(주)</param-value>        
 *        </context-param>
 *        
 *        ServletConfig    
 *       <servlet>
 *   		<servlet-name>myResponse</servlet-name>
 *    		<servlet-class>com.test.ResponseServlet</servlet-class>
 *      	  <init-param>
 *     			<param-name>company</param-name>
 *     			<param-value>GlobalIn(주)</param-value>        
 *        	  </init-param>
 * 		  </servlet>
 *			<servlet-mapping>
 *   		<servlet-name>myResponse</servlet-name>
 *  		<url-pattern>/Response</url-pattern>
 * 			</servlet-mapping>  
 *   
 */


//@WebServlet("/InitParam")
public class InitParam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String company; //ServletContext
	private String manager; //ServletContext
	private String tel;  //ServletConfig    
	private String email;  //ServletConfig    

	public void init() throws ServletException {
		// ServletContext의 초기 파라미터 값을 읽어옴
		company = getServletContext().getInitParameter("company");
		manager = getServletContext().getInitParameter("manager");
		// ServletConfig의 초기 파라미터 값을 읽어옴
		tel = getServletConfig().getInitParameter("tel");
		email = getServletConfig().getInitParameter("email");
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();  //out에서 예외 발생할 수 있음
		
		try {
			out.println("<html><body>");
			out.println("<ul>");
			out.println("<li> 회사명 : "+company+"</li>");
			out.println("<li> 관리자 : "+manager+"</li>");
			out.println("<li> 전화번호 : "+tel+"</li>");
			out.println("<li> 이메일 : "+email+"</li>");
			out.println("</ul>");
			out.println("</body></html>");			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			out.close();
		}		
	}

	// get, post 두 방식 다 씀
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		processRequest(request, response);
	}

}
