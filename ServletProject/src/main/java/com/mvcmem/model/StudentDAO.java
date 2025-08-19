package com.mvcmem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StudentDAO {

	
	private static StudentDAO instance = null;
	private StudentDAO() {	}
	
	public static StudentDAO getInstance() {
		
		if(instance == null) {
			synchronized (StudentDAO.class) {
				instance = new StudentDAO();
			}
		}
		return instance;
	}
	
	private Connection getConnection() {
		
		Connection con = null;
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			con = ds.getConnection();
		} catch (NamingException ne) {
			System.out.println("Connection　생성 실패 !!!");
		}catch(SQLException s) {
			System.out.println("Connection 생성 실패 !!!!");
			s.printStackTrace();
		}
		return con;
	}
	
	
	public boolean idCheck(String id) {
		boolean result = true;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			String sql1 = "select * from student where id=?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (!rs.next())
				result = false;

		} catch (SQLException s) {
			s.printStackTrace();

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException ss) {
				ss.printStackTrace();

			}

			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}

		}

		return result;
	}


	public Vector<ZipCodeVO> zipcodeRead(String dong) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<ZipCodeVO> vecList = new Vector<ZipCodeVO>();	
		
		try {
			con = getConnection();
			String sql="select * from zipcode where dong like '"+dong+"%'";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ZipCodeVO tZipcode = new ZipCodeVO();
				tZipcode.setZipcode(rs.getString("zipcode"));
				tZipcode.setSido(rs.getString("sido"));
				tZipcode.setGugun(rs.getString("gugun"));
				tZipcode.setDong(rs.getString("dong"));
				tZipcode.setRi(rs.getString("ri"));
				tZipcode.setBunji(rs.getString("bunji"));
				vecList.addElement(tZipcode);
			}
			
			
		}catch(SQLException s) {
			s.printStackTrace();
			
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException ss) {
				ss.printStackTrace();

			}

			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}

		}

		return vecList;
	}
		
	public boolean memberInsert(StudentVO vo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
	    boolean flag = false;
		
	    try {
	    	con = getConnection();
	    	String sql = "insert into student values(?,?,?,?,?,?,?,?,?,?)";
	    	pstmt = con.prepareStatement(sql);
	    	pstmt.setString(1, vo.getId());
	    	pstmt.setString(2,vo.getPass());
	    	pstmt.setString(3,vo.getName());
	    	pstmt.setString(4,vo.getPhone1());
	    	pstmt.setString(5,vo.getPhone2());
	    	pstmt.setString(6,vo.getPhone3());
	    	pstmt.setString(7,vo.getEmail());
	    	pstmt.setString(8,vo.getZipcode());
	    	pstmt.setString(9,vo.getAddress1());
	    	pstmt.setString(10,vo.getAddress2());
	    	
	    	int count = pstmt.executeUpdate();
	    	if(count > 0) flag = true;
	    	
	    
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
			
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException ss) {
				ss.printStackTrace();

			}
			
		}
		
		return flag;
	}
	
	public int loginCheck(String id, String pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = -1;  
		
		try {
			con = getConnection();
			
			String sql = "select pass from student where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dbPass = rs.getString("pass");
				if(pass.equals(dbPass)) {
					check=1;  
				}else {
					check = 0;
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException ss) {
				ss.printStackTrace();

			}

			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}
			
			
		}
		return check;
		
	}
	
	public StudentVO getMember(String id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	    StudentVO vo = null;
	    
	    try {
	    	String sql ="select * from student where id=?";
	    	con = getConnection();
	    	pstmt = con.prepareStatement(sql);
	    	pstmt.setString(1, id);
	    	rs = pstmt.executeQuery();
	    	
	    	if(rs.next()) { 
	    		
	    		vo = new StudentVO();
	    		vo.setId(rs.getString("id"));
	    		vo.setPass(rs.getString("pass"));
	    		vo.setName(rs.getString("name"));
	    		vo.setPhone1(rs.getString("phone1"));
	    		vo.setPhone2(rs.getString("phone2"));
	    		vo.setPhone3(rs.getString("phone3"));
	    		vo.setEmail(rs.getString("email"));
	    		vo.setZipcode(rs.getString("zipcode"));
	    		vo.setAddress1(rs.getString("address1"));
	    		vo.setAddress2(rs.getString("address2"));
	    	}
	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException ss) {
				ss.printStackTrace();

			}

			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}
	    	
	    }
	    return vo;
	}
	
	
	public void updateMember(StudentVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			String sql="update student set pass=?, phone1=?, phone2=?, "
					+ "phone3=?, email=?, zipcode=?, "
					+ "address1=?, address2=? where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getPhone1());
			pstmt.setString(3, vo.getPhone2());
			pstmt.setString(4, vo.getPhone3());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZipcode());
			pstmt.setString(7, vo.getAddress1());
			pstmt.setString(8, vo.getAddress2());
			pstmt.setString(9, vo.getId());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException ss) {
				ss.printStackTrace();

			}

	    }
	}
	
	public int deleteMember(String id, String pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		String dbPass="";
		
		try {
			String sql1="select pass from student where id=?";
			con = getConnection();
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbPass= rs.getString("pass");
				if(dbPass.equals(pass)) {
					String sql2="delete from student where id=?";
					pstmt = con.prepareStatement(sql2);
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					result=1;
				}else {
					result=0;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}
			
			try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException ss) {
			ss.printStackTrace();
		}

		try {
			if (con != null)
				con.close();
		} catch (SQLException ss) {
			ss.printStackTrace();

		}

		
			
		}
		return result;
	}
	
	
	
	
	
	
}