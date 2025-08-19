<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	    <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<meta http-equiv="refresh" content="0;url=member.mdo?cmd=login">
</head>

<body>
<c:set var="check" value="${check}"/>
<div align="center">
<c:if test="${check eq 0 }">
	<script type="text/javascript">
	alert("비밀번호가 일치하지 않습니다.");
	history.go(-1);  /* 다시 이전페이지로 이동 */
	</script>
</c:if>

<flont size="5" face="궁서체">
<b>회원정보가 삭제 되었습니다.</b><br>
안녕히 가십시오.<br>
</flont>
</div>
</body>
</html>