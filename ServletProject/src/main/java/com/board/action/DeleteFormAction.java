package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;

public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num = Integer.parseInt(request.getParameter("num")); // 글번호
	 	String pageNum = request.getParameter("pageNum");  // 페이지번호
	 		
	 	// content.jsp 페이지에서 사용할 속성 저장
	 	request.setAttribute("num", num);	
	 	request.setAttribute("pageNum", pageNum);	

		return "/board/deleteForm.jsp";
	}

}
