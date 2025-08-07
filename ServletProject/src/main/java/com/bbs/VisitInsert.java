package com.bbs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

//@WebServlet("/VisitInsert")
public class VisitInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글처리
		request.setCharacterEncoding("utf-8");
		
		// 사용자(클라이언트)의 요청으로 전송된 값 읽어오기
		String writer = request.getParameter("writer");  //html파일에 쓴 그대로
		String memo = request.getParameter("memo");
		
		//System.out.println("작성자 : "+writer);  확인
		//System.out.println("내용 : "+memo);
		String sql = "insert into visit(no, writer, memo, regdate) "
				+ "values(visit_seq.nextval, ?, ?, sysdate)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String pass = "tiger";
			con = DriverManager.getConnection(url, user, pass);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, memo);
			
			// insert update delete 일 때,
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException ne) {
			ne.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}finally{
			try {
				if(pstmt!=null)pstmt.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}
			try {
				if(con	!=null)con.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}
		}
		response.sendRedirect("VisitList");  // 글 작성 후 목록을 강제로 보여줘야함
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}

}
