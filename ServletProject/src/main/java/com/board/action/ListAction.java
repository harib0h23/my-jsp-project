package com.board.action;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;

// 글 목록을 처리함

public class ListAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		

	     String pageNum = request.getParameter("pageNum");
	     if(pageNum == null){
	    	 pageNum="1";
	     }
	     
	     int pageSize = 5;
	     int currentPage = Integer.parseInt(pageNum);
	     int startRow =(currentPage - 1) * pageSize + 1;
	     int endRow = currentPage * pageSize;
	         
	     int count = 0;
	     int number= 0;
	     List<BoardVO> articleList = null;
	     
	     BoardDAO dbPro = BoardDAO.getInstance();
	     
    	 count = dbPro.getArticleCount(); //전체글 개수
   
	     if(count > 0) {// 글이 존재한다면
	    	 articleList = dbPro.getArticles(startRow, endRow);
	     }else {	 
	 	      articleList = Collections.emptyList();
	     } 
	    
	     number = count - (currentPage - 1) * pageSize;
	    
	     //뷰에서 사용할 속성 저장
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("number", number);
		request.setAttribute("articleList", articleList);

		return "/board/list.jsp";
	}

}
