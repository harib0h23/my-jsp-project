<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="view/color.jsp" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>

<body>
<div align="center">
<b>글 수정</b><br>
<form action="/board/updatePro.bdo?pageNum=${pageNum}" method="post" name="writeForm"
onsubmit="return writeSave()">

<table width="400" border="1" cellpadding="0" cellspacing="0" 
align="center" bgcolor="${bodyback_c}">

<tr>
	<td width="70" bgcolor="${value_c}" align="center">이름</td>
	<td width="330">
		<input type="text" size="12" maxlength="12" name="writer" 
		value="${article.writer}">
		<input type="hidden" name="num" value="${article.num}">		
	</td>
</tr>

<tr>
	<td width="70" bgcolor="${value_c}" align="center">이메일</td>
	<td width="330">
		<input type="text" size="30" maxlength="30" name="email" 
		value="${article.email}">
	</td>
</tr>

<tr>
	<td width="70" bgcolor="${value_c}" align="center">제목</td>
	<td width="330">
		<input type="text" size="12" maxlength="12" name="subject"
		 value="${article.subject}">
	</td>
</tr>

<tr>
	<td width="70" bgcolor="${value_c}" align="center">내용</td>
	<td width="330">
		<textarea rows="13" cols="50" name="content" >${article.content}</textarea>
	</td>
</tr>

<tr>
	<td width="70" bgcolor="${value_c}" align="center">비밀번호</td>
	<td width="330">
		<input type="password" size="10" maxlength="10" name="pass">
	</td>
</tr>

<tr>
	<td align="center" colspan="2" bgcolor="${value_c}">
		<input type="submit" value="글수정">	
		<input type="reset" value="다시 작성">	
		<input type="button" value="글목록"
		onclick="document.location.href='/board/list.bdo?pageNum=${pageNum}'">
	</td>
</tr>
</table>
</form>
</div>

</body>
</html>