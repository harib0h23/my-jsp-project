<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table width="400" border="1" cellpadding="0" cellspacing="0">
  <tr>
    <td width="150">제품번호	</td><th>XXXX</td>
  </tr>
  <tr>
    <td width="150">가격</td><td>10,000원</td>
  </tr>
  	<jsp:include page="infoSub.jsp" flush="false">
  	<jsp:param value="A" name="type"/>
  	</jsp:include>
  	
</table>
