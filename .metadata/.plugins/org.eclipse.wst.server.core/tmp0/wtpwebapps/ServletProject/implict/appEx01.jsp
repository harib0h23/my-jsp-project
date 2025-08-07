<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>초기화 파라미터를 읽어오기</title>
<Style type="text/css">
li{
	/*list-style: none;   ul일때*/
	list-style-type: decimal;
}
</Style>
</head>
<body>
초기화 파라미터 목록
<ol>
<%
Enumeration<String> enumData = application.getInitParameterNames();
	while(enumData.hasMoreElements()){ // 아직 꺼내지 않은 요소가 남아있는지 확인(boolean반환)
		String initParamName = enumData.nextElement();//nextElement() 다음 요소를 꺼냄(Object반환)
%>
<li><%=initParamName %> : <%=application.getInitParameter(initParamName) %></li>

<%
	}
%>

</ol>



</body>
</html>