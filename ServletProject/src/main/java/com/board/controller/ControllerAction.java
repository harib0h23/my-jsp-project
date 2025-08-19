package com.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.CommandAction;


public class ControllerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 명령어와 명령어 처리클래스가 매핑되어 있는 properties 파일을 읽어서 Map 객체인
		// commandMap에 저장함
	private Map<String, Object> commandMap =
				new HashMap<>();
	
	public void init(ServletConfig config) throws ServletException {
		
		String props = config.getInitParameter("propertyConfig");
		Properties pr = new Properties();
		String path = config.getServletContext().getRealPath("/WEB-INF");
		FileInputStream f = null;
		try {
			f = new FileInputStream(new File(path, props));
			pr.load(f);
		}catch(IOException e) {
			throw new ServletException(e);
		}finally {
			if(f != null)
				try {
				f.close();}catch(IOException ex) {ex.printStackTrace();}
		}
		
		Iterator<Object> keyIter = pr.keySet().iterator();
		// 객체를 하나씩 꺼내서 그 객체명으로 Properties 객체에 저장된 객체에 접근함
		while(keyIter.hasNext()) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			
			try {// 해당 문자열을 클래스로 만드는 작업
				Class commandClass=Class.forName(className);
				// 해당 클래스의 객체를 생성
				Object commandInstance = commandClass.newInstance();
				// Map 객체인 commandMap에 객체를 저장
				commandMap.put(command, commandInstance);
			}catch(ClassNotFoundException cnfe) {
				throw new ServletException(cnfe);
			}catch(InstantiationException e) {
				throw new ServletException(e);
			}catch(IllegalAccessException ie) {
				throw new ServletException(ie);
			}
		}
		
	}

	// 사용자의 요청을 분석해서 해당 작업을 처리하는 메소드
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String view = null;
	     CommandAction com = null;
	     
	     try {
	    	 String command = request.getRequestURI();
	    	 if(command.indexOf(request.getContextPath())== 0) {
	    		 command = 
	    				 command.substring(request.getContextPath().length());
	    	 }
	    	 com = (CommandAction)commandMap.get(command);
	    	 view = com.requestPro(request, response);
	     }catch(Throwable e) {
	    	 throw new ServletException(e);
	     }
   	
   	RequestDispatcher dispatcher = request.getRequestDispatcher(view);
   	
   	dispatcher.forward(request, response);
	
	
	
	
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		requestPro(request, response);
	}

}