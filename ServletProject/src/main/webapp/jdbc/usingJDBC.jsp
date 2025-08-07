<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String id="",
			passwd="",
			name="",
			mem_num1="",
			mem_num2="",
			e_mail="",
			phone="",
			zipcode="",
			address="",
			job="";
	
	int counter=0;
	try{
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String pass = "tiger";
		
		con=DriverManager.getConnection(url, user, pass);
		stmt = con.createStatement();
		rs = stmt.executeQuery("select * from tempmember");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP에서 데이터베이스 연동</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head> 
<body bgcolor=#ffffcc>
<h2>JSP 스크립트릿을 이용해서 데이터베이스 연동</h2>
<h3>회원정보</h3>
<table bordercolor="#0000ff" border="1">
	<tr>
		<td> <strong>ID</strong> </td>
		<td> <strong>PASSWD</strong> </td>
		<td> <strong>NAME</strong> </td>
		<td> <strong>MEM_NUM1</strong> </td>
		<td> <strong>MEM_NUM2</strong> </td>
		<td> <strong>E_MAIL</strong> </td>
		<td> <strong>PHONE</strong> </td>
		<td> <strong>ZOPCODE/ADDRESS</strong> </td>
		<td> <strong>JOB</strong> </td>		
	</tr>
	
	<%
	if(rs != null){ //데이터가 있으면
		while(rs.next()){  // 계속 가져옴
			id = rs.getString("id");	
			passwd = rs.getString("passwd");
			name= rs.getString("name");
			mem_num1= rs.getString("mem_num1");
			mem_num2= rs.getString("mem_num2");
			e_mail=rs.getString("e_mail");
			phone=rs.getString("phone");
			zipcode=rs.getString("zipcode");
			address=rs.getString("address");
			job=rs.getString("job");
			
	
	
	
	
	%>
	<tr>
		<td><%=id %></td>
		<td><%=passwd %></td>
		<td><%=name %></td>
		<td><%=mem_num1 %></td>
		<td><%=mem_num2 %></td>
		<td><%=e_mail %></td>
		<td><%=phone %></td>
		<td><%=zipcode+"\n"+address %></td>
		<td><%=job %></td>
		
	
	<%
		counter++;
		}  // while문 끝
	}  // if문 끝
		
	
	%>
	</tr>
	


</table>
<br>
total records : <%=counter %>

<%

	}catch (SQLException se) {
		se.printStackTrace();		
	}catch(Exception ce){
		ce.printStackTrace();
	}finally {
		try {
			if(rs!=null)rs.close();
		} catch (SQLException ss) {
			ss.printStackTrace();
		}
		try {
			if(stmt!=null)stmt.close();
		} catch (SQLException ss) {
			ss.printStackTrace();
		}
		try {
			if(con	!=null)con.close();
		} catch (SQLException ss) {
			ss.printStackTrace();
		}finally{
		
		
		}
	}

%>
</body>
</html>