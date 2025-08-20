package com.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;

// 글 수정 처리 구현
public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		BoardVO article = new BoardVO();	
		
		article.setNum(Integer.parseInt(request.getParameter("num"))); 
		article.setWriter(request.getParameter("writer"));
		article.setEmail(request.getParameter("email"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setPass(request.getParameter("pass"));

		BoardDAO dbPro = BoardDAO.getInstance();
		
		int check = dbPro.updateArticle(article);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		return "/board/updateProc.jsp";
	}

}
