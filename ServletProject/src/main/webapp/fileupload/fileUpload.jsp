<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.oreilly.servlet.MultipartRequest"%>    
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>    
<%@ page import="java.util.*" %>    
 <%
	String uploadPath=request.getRealPath("upload"); //upload폴더
	int size = 10*1024*1024; //10메가
	String name = "";
	String subject="";
	String filename1 = "";
	String filename2 = "";
	String origfilename1="";
	String origfilename2="";
	
	try{
		//객체만들기
		MultipartRequest multi 
			= new MultipartRequest(request, uploadPath, 
					size, "utf-8", new DefaultFileRenamePolicy());
		name = multi.getParameter("name");
		subject = multi.getParameter("subject");
		
		Enumeration files = multi.getFileNames(); //파일이 여러개일때
		
		String file1 = (String)files.nextElement(); // 객체이기 때문에 형변환 필요
		filename1 = multi.getFilesystemName(file1);
		origfilename1 = multi.getOriginalFileName(file1);
		
		String file2 = (String)files.nextElement(); // 객체이기 때문에 형변환 필요
		filename2 = multi.getFilesystemName(file2);
		origfilename2 = multi.getOriginalFileName(file2);
	}catch(Exception e){
		e.printStackTrace();
	}		
 %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<form action="fileCheck.jsp" method="post" name="filecheck">
<input type="hidden" name="name" value="<%=name %>">
<input type="hidden" name="subject" value="<%=subject %>">
<input type="hidden" name="filename1" value="<%=filename1 %>">
<input type="hidden" name="filename2" value="<%=filename2 %>">
<input type="hidden" name="origfilename1" value="<%=origfilename1 %>">
<input type="hidden" name="origfilename2" value="<%=origfilename2 %>">
</form>
<a href="#" onclick="javascript:filecheck.submit()">업로드 확인</a>


</body>
</html>