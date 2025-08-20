package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class BoardDAO {

	private static BoardDAO instance = null;

	public BoardDAO() {	}

	public static BoardDAO getInstance() {

		if (instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}

	//여기서부터 게시판에 작업할 기능을 하나씩 메소드로 구현하여 추가하면 됨

		/* 실제 데이터베이스에 데이터를 저장할 메소드를 구현
		 * 
		 * 		insertArticle(BoardVO article)
		 * 		
		 */
		
		public void insertArticle(BoardVO article) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int num = article.getNum();
			int ref = article.getRef();
			int step = article.getStep();
			int depth = article.getDepth();
			
			int number = 0;
			
			String sql = "";
			
			try {
				
				con = ConnUtil.getConnection();
				pstmt = con.prepareStatement("select max(num) from board");
				rs = pstmt.executeQuery();
				
				if(rs.next()) number = rs.getInt(1)+1;
				else				number = 1;
				
				if(num != 0) { //답변글 (댓글일 경우)
					
					sql = "update board set step=step+1 where ref=? and step > ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, ref);
					pstmt.setInt(2, step);
					pstmt.executeUpdate();
					step = step + 1;
					depth = depth + 1;
				}else {
					ref = number;
					step = 0;
					depth = 0;
				}
				
				sql = "insert into board(num, writer, email, subject, pass, "
						+ "regdate, ref, step, depth, content, ip) "
						+ "values(board_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,  article.getWriter());
				pstmt.setString(2,  article.getEmail());
				pstmt.setString(3, article.getSubject());
				pstmt.setString(4,  article.getPass());
				pstmt.setTimestamp(5,  article.getRegdate());
				pstmt.setInt(6, ref);
				pstmt.setInt(7,  step);
				pstmt.setInt(8,  depth);
				pstmt.setString(9, article.getContent());
				pstmt.setString(10,  article.getIp());
				pstmt.executeUpdate();
		
			}catch(Exception e) { 
				e.printStackTrace();
			}finally {
				try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			
		}// end insertArticle
		
		
		/*		글 목록 화면 만들기
		 * 		전체 글의 개수를 가져올 메소드를 구현함
		 * 		int getArticleCount() 
		 */
		public int getArticleCount() {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int x = 0;
			
			try {
				
				con = ConnUtil.getConnection();
				
				String sql = "select count(*) from board";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x = rs.getInt(1);
				}
				
			}catch(Exception e) { 
				e.printStackTrace();
			}finally {
				try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			return x;
		}
		
		/*		데이터베이스에 있는 전체 글을 가져다가 리스트에 저장함
		 */
		
		public List<BoardVO> getArticles(int start, int end){
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<BoardVO> articleList = null;
			
			try {
				
				con = ConnUtil.getConnection();
				
				//String sql = "select * from board order by num desc";
				String sql = "select * from (select rownum rnum, num, writer, email, "
						+ "subject, pass, regdate, readcount, ref, step, depth, content, "
						+ "ip from (select * from board order by ref desc, step asc)) "
						+ "where rnum >=? and rnum <=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					//articleList = new ArrayList<BoardVO>(end - start + 1);
					articleList = new ArrayList<BoardVO>(5);
					
					do {
						BoardVO article = new BoardVO();
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setEmail(rs.getString("email"));
						article.setSubject(rs.getString("subject"));
						article.setPass(rs.getString("pass"));
						article.setReadcount(rs.getInt("readcount"));
						article.setRef(rs.getInt("ref"));
						article.setStep(rs.getInt("step"));
						article.setDepth(rs.getInt("depth"));
						article.setRegdate(rs.getTimestamp("regdate"));
						article.setContent(rs.getString("content"));
						article.setIp(rs.getString("ip"));
						
						articleList.add(article);
						
					}while(rs.next());
					
				}	
			}catch(Exception e) { 
				e.printStackTrace();
			}finally {
				try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			return articleList;
		}
		
		/*		글 내용 보기
		 * 
		 * 		글의 번호를 매개변수로 해서 하나의 글에 대한 상세정보를 데이터베이스에서 가져옴
		 * 	
		 */
		
		public BoardVO getArticle(int num) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BoardVO article = null;
			
			try {
				
				con = ConnUtil.getConnection();
				String sql1 = "update board set readcount=readcount+1 where num=?";
				pstmt = con.prepareStatement(sql1);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				
				String sql2 = "select * from board where num=?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					article = new BoardVO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
				}
			}catch(Exception e) { 
				e.printStackTrace();
			}finally {
				try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			
			return article;

		}
		
		/*
		 *  글 수정 시에는 글 목록보기와 다르게 조회수를 증가시킬 필요가 없다.
		 * 	 조회수를 증가시키는 부분을 제외하고 num에 해당하는 게시글만 가져오는 메소드를 구현
		 */
		
		public BoardVO updateGetArticle(int num) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BoardVO article = null;
			
			try {
				
				con = ConnUtil.getConnection();
				
				String sql = "select * from board where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					article = new BoardVO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
				}
			}catch(Exception e) { 
				e.printStackTrace();
			}finally {
				try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			
			return article;
			
		}
		
		
		/* 	updateForm.jsp 에서 비밀번호를 입력하고 글 수정 버튼을 클릭하면
		 * 		데이터베이스에서 실제 글이 수정 되어야한다. 
		 * 
		 */
		
		public int updateArticle(BoardVO article) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String dbpasswd = "";
			String sql = "";
			int result = -1;
			
			try {
				
				con = ConnUtil.getConnection();
				pstmt = con.prepareStatement("select pass from board where num=?");
				pstmt.setInt(1, article.getNum());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dbpasswd = rs.getString("pass");
					if(dbpasswd.equals(article.getPass())) {
						//데이터베이스에 저장된 비밀번호와 내가 입력한 비밀번호가 일치하면 글 수정 처리.\
						sql="update board set writer=?, email=?, subject=?, content=? where num=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, article.getWriter());
						pstmt.setString(2, article.getEmail());
						pstmt.setString(3, article.getSubject());
						pstmt.setString(4, article.getContent());
						pstmt.setInt(5, article.getNum());
						pstmt.executeUpdate();
						result = 1;
					}else {
						result = 0;
					}
				}
			}catch(Exception e) { 
				e.printStackTrace();
			}finally {
				try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			
			return result;
			
		}
		
		
		public int deleteArticle(int num, String pass) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String dbpasswd = "";
			String sql = "";
			int result = -1;
			
			try {
				
				con = ConnUtil.getConnection();
				pstmt = con.prepareStatement("select pass from board where num=?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dbpasswd = rs.getString("pass");
					if(dbpasswd.equals(pass)) {
						sql="delete from board where num=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, num);
						pstmt.executeUpdate();
						result = 1;
					}else {
						result = 0;
					}
				}
			}catch(Exception e) { 
				e.printStackTrace();
			}finally {
				try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			
			return result;
			
		}
		
		
		// 검색한 내용이 몇개인지를 반환하는 메소드(검색조건, 검색내용)	**복사 model.dao

		public int getArticleCount(String what, String content) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int x = 0;
			
			try {
				
				con = ConnUtil.getConnection();
				
				//String sql = "select count(*) from board";
				String sql = "select count(*) from board where "+what+" like '%"+content+"%'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x = rs.getInt(1);
				}
				
			}catch(Exception e) { 
				e.printStackTrace();
			}finally {
				try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			return x;
		}
		
		/*		데이터베이스에 있는 전체 글을 가져다가 리스트에 저장함
		 */
		
		public List<BoardVO> getArticles(String what, String content, int start, int end){
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<BoardVO> articleList = null;
			
			try {
				
				con = ConnUtil.getConnection();
				
				//String sql = "select * from board order by num desc";
				String sql = "select * from (select rownum rnum, num, writer, email, "
						+ "subject, pass, regdate, readcount, ref, step, depth, content, "
						+ "ip from (select * from board where "
						+what+" like '%"+content+"%' order by ref desc, step asc)) "
						+ "where rnum >=? and rnum <=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					articleList = new ArrayList<BoardVO>(end - start + 1);
					
					do {
						BoardVO article = new BoardVO();
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setEmail(rs.getString("email"));
						article.setSubject(rs.getString("subject"));
						article.setPass(rs.getString("pass"));
						article.setReadcount(rs.getInt("readcount"));
						article.setRef(rs.getInt("ref"));
						article.setStep(rs.getInt("step"));
						article.setDepth(rs.getInt("depth"));
						article.setRegdate(rs.getTimestamp("regdate"));
						article.setContent(rs.getString("content"));
						article.setIp(rs.getString("ip"));
						
						articleList.add(article);
						
					}while(rs.next());
					
				}	
			}catch(Exception e) { 
				e.printStackTrace();
			}finally {
				try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			return articleList;
		}
	
	
	
}
