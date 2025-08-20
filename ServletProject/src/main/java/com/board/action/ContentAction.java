package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;

// 글 내용 상세보기
public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num")); // 글번호
	 	String pageNum = request.getParameter("pageNum");  // 페이지번호
	 
	 	BoardDAO dbPro = BoardDAO.getInstance();
	 	BoardVO article = dbPro.getArticle(num);  // 해당 번호의 글 가져옴
	 	
	 	// content.jsp 페이지에서 사용할 속성 저장
	 	request.setAttribute("num", num);
	 	request.setAttribute("pageNum", pageNum);
	 	request.setAttribute("article", article);	
		
		return "/board/content.jsp";
	}

}
