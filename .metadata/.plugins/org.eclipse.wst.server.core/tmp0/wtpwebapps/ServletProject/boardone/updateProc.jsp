<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.boardone.BoardDAO" %>    
<%@ page import="java.sql.Timestamp" %>    

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="article" class="com.boardone.BoardVO" scope="page">
	<jsp:setProperty name="article" property="*"/>
</jsp:useBean>

<%
	String pageNum= request.getParameter("pageNum");
	//디비연결
	BoardDAO dbPro = BoardDAO.getInstance();
	int check=dbPro.updateArticle(article); //useBean id

	if(check==1){
	

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta http-equiv="refresh" content="0;url=list.jsp?pageNum=<%=pageNum%>">
</head>
<body>
<%}else{ %>
	<script type="text/javascript">
	alert("비밀번호가 일치하지 않습니다.");
	history.go(-1);  /* 다시 이전페이지로 이동 */
	</script>
<%} %>
</body>
</html>