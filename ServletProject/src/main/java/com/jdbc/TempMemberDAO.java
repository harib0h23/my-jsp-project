package com.jdbc;

import java.sql.*;
import java.util.Vector;
import javax.sql.*;
import javax.naming.*;


public class TempMemberDAO {
/*
	private final String JDBC_DRIVER=
			"oracle.jdbc.driver.OracleDriver";
	private final String JDBC_URL=
			"jdbc:oracle:thin:@localhost:1521:orcl";
	private final String USER="scott";
	private final String PASS="tiger";
	
*/
	
	DataSource ds;
	public TempMemberDAO() {

		try {
			
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");			
			
			//Class.forName(JDBC_DRIVER);
		} catch (Exception e) {
			System.out.println("Error : JDBC 드라이버 로딩 실패!!!!");
		}
	} // 생성자
	
	//회원 목록을 가져옴
	public Vector<TempMemberVO> getMemberList(){
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Vector<TempMemberVO> vecList = new Vector<TempMemberVO>();
		
		try {
			con = ds.getConnection();
			
			//con = DriverManager.getConnection(JDBC_URL, USER, PASS);
			String sql = "select * from tempmember";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				TempMemberVO vo = new TempMemberVO();
				
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setMem_num1(rs.getString("mem_num1"));
				vo.setMem_num2(rs.getString("mem_num2"));
				vo.setEmail(rs.getString("e_mail"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("zipcode"));
				vo.setJob(rs.getString("job"));
				vecList.add(vo);
				
			}
			
			
		} catch (Exception e) {
			System.out.println("Exception"+e);
		}finally {
			try {
				if(rs!=null)rs.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}
			try {
				if(stmt!=null)stmt.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}
			try {
				if(con	!=null)con.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}finally{
			
			
			}
		}
		
		return vecList;
		
		
	}
	
	
	
	
	
}
