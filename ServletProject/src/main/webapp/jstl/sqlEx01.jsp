<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<!-- db연결 -->
<!-- context.xml에 있는 Resource name -->
<sql:query var="rs" dataSource="jdbc/myoracle"> 
	select * from tempmember
</sql:query>
<table border="1">
<!-- 필드명 출력 -->
	<tr>
		<c:forEach var="columnName" items="${rs.columnNames}">
			<th><c:out value="${columnName}"/></th>
		</c:forEach>
	</tr>
	<%-- 레코드 수만큼 반복 수행 --%>
	<c:forEach var="row" items="${rs.rowsByIndex}">
		<tr>
			<!-- 레코드 필드 수만큼 반복 수행 -->
			<c:forEach var="column" items="${row}" varStatus="i">			
			<td>
				<!-- 해당 필드값이 널이 아닌 경우 -->
				<c:if test="${column ne null}">
					<c:out value="${column}"/>
				</c:if>
				<!-- 해당 필드값이 널인 경우 -->
				<c:if test="${column eq null}">
					&nbsp;
				</c:if>
			</td>
			</c:forEach>
		</tr>
	</c:forEach>

</table>
</body>
</html>