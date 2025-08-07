<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<!DOCTYPE html> <!-- HTML5 없으면 HTML4 -->
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<h2>JSP Script</h2>
	<%!  // 이부분은 JAVA .. 선언문   따로
		String declation = "선언문입니다.";
		
		public String declationMethod(){
			return declation;
		}
	%>
    <%  // 이부분은 JAVA    연산처리 
    	String scriptlet = "스크립트릿입니다.";
    	String comment = "주석문입니다.";
    	
    	out.println("내장 객체를 이용한 출력 : "+declation+"<br><br>");    
    %>
    선언문 출력(변수) : <%= declation %><br><br>
    선언문 출력(메소드) : <%= declationMethod() %><br><br>
    스크립트릿 출력 : <%=scriptlet %><br><br>
    
    <!-- JSP 페이지에서 사용하는 HTML의 주석 -->
    <!-- HTML 주석 : <%=comment%> --><br><br>
	<%-- JSP 주석 : <%=comment%> --%><br><br>
	     
</body>
</html>