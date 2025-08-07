<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
    
<% response.setStatus(HttpServletResponse.SC_OK); %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외 발생</title>
</head>
<body>
요청 처리 과정에서 예외가 발생하였습니다.<br>
빠른 시간 내에 정상화가 될 수 있도록 노력하겠습니다.<br>
부디 너그러운 마음으로 양해 부탁드립니다.<br>
<br><br>
에러 타입 : <%=exception.getClass().getName() %><br><br>
에러 메시지 : <b><%=exception.getMessage()%></b>
</body>
</html>