<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page session="true" %>
<%@ taglib prefix="elfunc" uri="/WEB-INF/tlds/el-functions.tld" %>

<%

	// 사용자 정의 태그 = 커스텀 태그
	// 일본에서 많이 사용함
	// xml에 미리 정의 해놓고 가져다 쓰는 것
	
	Date today = new Date();
	request.setAttribute("today", today);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 함수 호출</title>
</head>
<body>
오늘은 <b>${elfunc:dateFormat(today) }</b>일 입니다.
</body>
</html>