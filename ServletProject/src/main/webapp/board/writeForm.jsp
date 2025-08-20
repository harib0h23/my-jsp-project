<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 세팅한 컬러값 -->
<%@ include file="view/color.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>

<body bgcolor="${bodyback_c}">
<div align="center"><b>글쓰기</b></div><br>
<form action="/board/writePro.bdo" method="post" name="writeForm"
onsubmit="return writeSave()"> <!-- 자바스크립트 파일 필요 -->

<input type="hidden" name="num" value="${num}">
<input type="hidden" name="ref" value="${ref}">
<input type="hidden" name="step" value="${step}">
<input type="hidden" name="depth" value="${depth}"> 

<table width="400" border="1" cellpadding="0" cellspacing="0" 
align="center" bgcolor="${bodyback_c}">

<tr>
	<td align="right" colspan="2" bgcolor="${value_c}">
		<a href="/borad/list.bdo">글목록</a>		
	</td>
</tr>

<!-- 이름 이메일 제목 내용 비밀번호 버튼 -->

<tr>
	<td width="70" bgcolor="${value_c}" align="center">이름</td>
	<td width="330">
		<input type="text" size="12" maxlength="12" name="writer">		
		
	</td>
</tr>

<tr>
	<td width="70" bgcolor="${value_c}" align="center">이메일</td>
	<td width="330">
		<input type="text" size="30" maxlength="30" name="email">
	</td>
</tr>

<tr>
	<td width="70" bgcolor="${value_c}" align="center">제목</td>
	<td width="330">
		<c:if test="${num == 0}">
			<input type="text" size="12" maxlength="12" name="subject">
		</c:if>
		<c:if test="${num != 0}">			
		<input type="text" size="12" maxlength="12" name="subject" value="[답변]">
		</c:if>
	</td>
</tr>

<tr>
	<td width="70" bgcolor="${value_c}" align="center">내용</td>
	<td width="330">
		<textarea rows="13" cols="50" name="content"></textarea>
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
		<input type="submit" value="글쓰기">	
		<input type="reset" value="다시 작성">	
		<input type="button" value="글목록"
		onclick="window.location='/board/list.bdo'">
	</td>
</tr>

</table>
</form>
</body>
</html>