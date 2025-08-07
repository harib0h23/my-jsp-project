<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
속성과 스코프
---------------------------------------------------------------------------------------
구분                               접근                     생존범위                       사용
ServletContext		 어플리케이션 전체	    어플리케이션 종료 전           공유되는 자원 전부
HttpSession			 서블릿이나 JSP	    HttpSession 종료 전         사용자 상태 유지
HttpServletRequest     Request에	    사용자가 유지되는 동안   MVC패턴에서 Model(java) 정보를 
						      접근 가능한 것만                                   View(jsp)에 전달할 때 
PageContext			해당 페이지 내에서만   JSP페이지 내에서만 유효
Scope 순서 (좁은 → 넓은)
PageContext (한 JSP 파일)
HttpServletRequest (한 요청)
HttpSession (한 사용자)
ServletContext (모든 사용자)
 --%>

<%  // 속성 저장
// PageContext Scope에 속성
 pageContext.setAttribute("pageAttribute", "홍길동");
 //pageContext.setAttribute("pageAttribute", "홍길동", pageContext.PAGE_SCOPE); 
// HttpServeltRequest
 request.setAttribute("requestAttribute", "010-1234-5678");
//HttpSession
 session.setAttribute("sessionAttribute", "hong@naver.com");
//ServletContext
 application.setAttribute("applicationAttribute", "GlobalIn(주)");
    // 안보임. 가져올 땐 getAttribute
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<ul>
<li>이름 : <%=pageContext.getAttribute("pageAttribute") %></li>
<li>전화번호 : <%=request.getAttribute("requestAttribute") %></li>
<li>이메일 : <%=session.getAttribute("sessionAttribute") %></li>
<li>회사 : <%=application.getAttribute("applicationAttribute") %></li>

</ul>




</body>
</html>