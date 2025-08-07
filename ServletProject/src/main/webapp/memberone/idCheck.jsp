<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="com.memberone.StudentDAO"/>

<%
	/* 파라미터가져오기 */
	String id = request.getParameter("id");  //입력한 아이디값
	boolean check = dao.idCheck(id);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 증복체크</title>
<link href="style.css" rel="sylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="#ffffcc">
<br>
<div align="center">
<b><%=id /*위 파라미터로 가져와 담은 값 */%></b>
<%
if(check){
	out.println("는 이미 존재하는 ID입니다.<br>");
}else{
	out.println("사용 가능한 ID입니다.<br>");
}
%>

<a href="#" onclick="javascript:self.close()">닫기</a> <!-- 현재 창을 닫음 -->
</div>

</body>
</html>