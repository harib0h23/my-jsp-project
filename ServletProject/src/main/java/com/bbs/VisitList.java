package com.bbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/VisitList")
public class VisitList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//응답
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			out.println("<html>");
			out.print("<head><title>방명록 리스트</title></head>");
			out.print("<body>");
			out.println("<h1 align=center>방명록 리스트</h1>");
			String sql= "select no,writer,memo,regdate "  // 식별자 = 쿼리의 컬럼명
					+ "from visit order by no desc";
			//데이터베이스 연결
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;			
			
			try {
				//데이터 가져오기
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				String user = "scott";
				String pass = "tiger";
				con = DriverManager.getConnection(url, user, pass);
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {// 데이터가 존재한다면, 결과가 있다면
					int no = rs.getInt("no");
					String writer = rs.getString("writer");
					String memo = rs.getString("memo");
					Date regdate = rs.getDate("regdate");
					out.println("<table align=center width=500 border=1>"); //table전체를 감쌈
					out.println("<tr>");  // 작성자, 날짜, 내용  table row
					out.println("<th width=50>번호</th>"); //table header
					out.println("<td width=50 align=center>"+no+"</td>"); //table data
					
					out.println("<th width=70>작성자</th>");
					out.println("<td width=50 align=center>"+writer+"</td>");
					
					out.println("<th width=50>날짜</th>");
					out.println("<td width=100 align=center>"+regdate+"</td>");					
					out.println("</tr>");	
					
					out.println("<tr>");
					out.println("<th width=50>내용</th>");
					out.println("<td colspan=5>&nbsp;"  //&nbsp; == 스페이스 한번
							+ "<textarea rows=3 cols=50>"
							+memo+"</textarea></td>");
					out.println("</tr>");					
					
				}	
				out.println("</table>");
				out.println("<p>");
				
			} catch (SQLException se) {
				se.printStackTrace();
				
			} catch(ClassNotFoundException ce){
				ce.printStackTrace();
			} finally {
				try {
					if(rs!=null)rs.close();
				} catch (SQLException ss) {
					ss.printStackTrace();
				}
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
			out.print("<p align=center><a href=/bbs/write.html>글쓰기</a></p>");
			out.print("</body>");
			out.print("</html>");
			
		} finally {
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
