<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요청 파라미터 출력</title>
</head>
<body>
name : <%=request.getParameter("name") %><br>
address : <%=request.getParameter("address") %><br>
<br><br>
<%
	String[] values = request.getParameterValues("pet");
		if(values != null){
			for(int i = 0; i < values.length ; i++){
%>
			<%=values[i] %> <!--  출력  -->
<% 		}
		}
%>
<br><br>
<%
Enumeration<String> enumData = request.getParameterNames();
	while(enumData.hasMoreElements()){
		String name = enumData.nextElement(); // 위 <String>이없으면 형변환필요(String)
%>
		<%=name %>
<%} %>

<br><br>
<%
Map parameterMap = request.getParameterMap();
String[] nameParam = (String[])parameterMap.get("name");
	if(nameParam != null){
%>
	name = <%=nameParam[0] %>
<%} %>



</body>
</html>