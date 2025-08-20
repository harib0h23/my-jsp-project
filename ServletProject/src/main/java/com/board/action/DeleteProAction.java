package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num")); // 글번호
	 	String pageNum = request.getParameter("pageNum");  // 페이지번호
	 	String pass = request.getParameter("pass");
	 	BoardDAO dbPro = BoardDAO.getInstance();
	 	
	 	int check = dbPro.deleteArticle(num, pass);
	 	
	 	// content.jsp 페이지에서 사용할 속성 저장
	 	request.setAttribute("pageNum", pageNum);
	 	request.setAttribute("check", check);	
		
		return "/board/deleteProc.jsp";
	}

}
