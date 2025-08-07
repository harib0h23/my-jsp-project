<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
// 에러 응답코드 : 200 ... 지정하지 않으면 톰캣에서 가지고 있는 에러가 뜸
/*
	응답코드 200을 지정하지 않으면 웹브라우저에서는 404 또는 500인 경우,
	웹브라우저가 자체적으로 404 또는 500에러일 때 보여주는 화면을 출력함.
*/
response.setStatus(HttpServletResponse.SC_OK); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500에러 발생</title>
</head>
<body>
<b>서비스 처리 과정에서 에러가 발생하셨습니다</b><br>
빠른 시간 안에 문제를 해결하도록 노력하겠습니다.
</body>
</html>