<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.invalidate();  //무효화 시킴  --> 로그인 상태를 로그아웃 시킴
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta http-equiv="refresh" content="5;url=login.jsp"> <!-- 자동 갱신, 5초 후 login.jsp로 자동 이동  -->
</head>
<body>
<font size="5">
성공적으로 로그아웃 되었습니다.<br>
</font>

</body>
</html>