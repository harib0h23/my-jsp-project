<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.boardone.BoardDAO" %>    
<%@ page import="com.boardone.BoardVO" %>
<%@ page import="java.util.List" %>    
<%@ page import="java.text.SimpleDateFormat" %>    
<%@ include file="view/color.jsp" %>
 
 <%!
 	int pageSize = 5;
 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 	
 %>
 
 <%
 
  String searchWhat = request.getParameter("searchWhat"); //어디서 검색?
  String searchText = request.getParameter("searchText");  // 무엇을 검색?

  String pageNum = request.getParameter("pageNum");
  if (pageNum == null) {
  	pageNum = "1"; //문자열
  }

  int currentPage = Integer.parseInt(pageNum);//문자열 인트형으로
  int startRow = (currentPage - 1) * pageSize + 1; //시작행
  int endRow = currentPage * pageSize; //끝행

  int count = 0;
  int number = 0;
  List<BoardVO> articleList = null;

  BoardDAO dbPro = BoardDAO.getInstance();

/*   // 전체글 가져오기
  count = dbPro.getArticleCount();

  if (count > 0) {//글이 있을 때
  	//articleList = dbPro.getArticles();
  	articleList = dbPro.getArticles(startRow, endRow); //글을 가져다가 리스트에 저장한다
  } //*** 검색일때와 아닐때 두가지로 if문 만듬
  //number = count; 	//브라우저에 number출력		
  number = count - (currentPage - 1) * pageSize; */
  
  if (searchText != null) {
      // 검색 조건이 있을 경우
      count = dbPro.getArticleCount(searchWhat, searchText);
      if (count > 0) {
          articleList = dbPro.getArticles(startRow, endRow, searchWhat, searchText);
      }
  } else {
      // 검색이 아닐 경우
      count = dbPro.getArticleCount();
      if (count > 0) {
          articleList = dbPro.getArticles(startRow, endRow);
      }
  }

  number = count - (currentPage - 1) * pageSize;
  
  
  
  
  %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c %>">
<div align="center"><b>글 목록(전채 글 :<%=count %>)</b>

<table width="700">
	<tr>
		<td align="right" bgcolor="<%=value_c%>">
		<a href="writeForm.jsp">글쓰기</a></td>
	</tr>


</table>
<%
if(count==0){  //글이 없을때
%>
<table width="700" border="1" cellpadding="0" cellspacing="0"	>
	<tr>
		<td align="center">
			게시판에 저장된 글이 없습니다.
		</td>
	</tr>
</table>
<%}else{ //게시글이 있을때%>
<table width="700" border="1" cellpadding="0" cellspacing="0"	 align="center">
	<tr height="30" bgcolor="<%=value_c%>"> <!-- 타이틀 -->
		<td align="center" width="50">번호</td>
		<td align="center" width="250">제목</td>
		<td align="center" width="100">작성자</td>
		<td align="center" width="150">작성일</td>
		<td align="center" width="50">조회</td>
		<td align="center" width="100">ip</td>
	</tr>  
	<%
		for(int i = 0; i<articleList.size(); i++){
			BoardVO article = (BoardVO)articleList.get(i);
	%>
	<tr height="30">
		<td align="center" width="50"><%=number-- %></td>
		<td width="250">  <!-- 제목을 눌렀을 때 글 상태보기 -->
			<% 
				int wid=0;
				if(article.getDepth()>0){
					wid=5*(article.getDepth());
				
			%>
			<!-- 이미지불러오기 -->
			<img src="img/level.gif" width="<%=wid%>" height="16">
			<img src="img/re.gif">
			<%}else{ %>
			<img src="img/level.gif" width="<%=wid%>" height="16">
			<%} %>
			<a href="content.jsp?num=<%=article.getNum()%>&pageNum=<%=currentPage%>">
				<%=article.getSubject() %>
			</a>
			<% if(article.getReadcount() >= 5){ %>
				<img src="img/hot.gif" border="0" height="16">
			<% } %>
		</td>
		<td align="center" width="100">
			<a href="mailto:<%= article.getEmail()%>">
				<%= article.getWriter() %>
			</a>
		</td>
		<td align="center" width="150">
			<%=sdf.format(article.getRegdate()) %>
		</td>
		<td align="center" width="50">
			<%= article.getReadcount() %>		
		</td>
		<td align="center" width="100">
			<%=article.getIp() %>
		</td>
	</tr>
	<%} %>
</table>

<%} %>

		<%
 	if(count>0){ //있을때
		int pageBlock = 5;
		int imsi = count % pageSize == 0 ? 0 : 1;
		int pageCount = count / pageSize + imsi;
		
		int startPage =(int)((currentPage-1)/pageBlock)*pageBlock+1;
		//시작페이지 구하는 공식
		//끝 페이지		
		int endPage = startPage + pageBlock -1;
		
		if(endPage > pageCount) endPage=pageCount; //!!!!수정!!!!
		
		if(startPage > pageBlock){ 
			
			
	%>

		<a href="list.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a>

		<%
		} //end if문
		for(int i = startPage ; i <= endPage && i <= pageCount ; i++){
	%>
	<a href="list.jsp?pageNum=<%=i%>">[<%=i %>]</a>		
					
	<%
		} //end for문		
	if(endPage < pageCount){		
	%>	
	<a href="list.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a>	

<%
	}	//end if문	
}
%>			

<form action="list.jsp">
	<select name="searchWhat">
		<option value="writer"  <%= "writer".equals(searchWhat) ? "selected" : "" %>>작성자</option>
		<option value="subject"  <%= "subject".equals(searchWhat) ? "selected" : "" %>>제목</option>
		<option value="content"  <%= "content".equals(searchWhat) ? "selected" : "" %>>내용</option>
	</select>
		<input type="text" name="searchText" value="<%= searchText != null ? searchText : "" %>">
		<input type="submit" value="검색">
</form>



</div>

</body>
</html>


