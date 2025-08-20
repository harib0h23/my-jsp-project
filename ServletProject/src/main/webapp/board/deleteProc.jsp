<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<c:if test="${check==1 }">
	<meta http-equiv="refresh" content="0;url=/board/list.bdo?pageNum=${pageNum }">
</c:if>

<c:if test="${check==0}">
	<script type="text/javascript">
	alert("비밀번호가 일치하지 않습니다.");
	history.go(-1);  /* 다시 이전페이지로 이동 */
	</script>
	<br>
	<a href="javascript:history.go(-1)">글 삭제 폼</a>
</c:if>	
