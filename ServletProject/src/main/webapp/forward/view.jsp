<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
	String code = request.getParameter("code");  //<select name="code">
	
	String viewPageURI = null;
	
	if(code.equals("A")){  //<option value="A">
		viewPageURI = "viewModule/a.jsp";
	}else if(code.equals("B")){
		viewPageURI = "viewModule/b.jsp";
	}else if(code.equals("C")){
		viewPageURI = "viewModule/c.jsp";
	}
	
	//액션태그로 페이지 이동
%>	
<jsp:forward page="<%=viewPageURI %>" />


</body>
</html>