<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
<script type="text/javascript" src="script.js">
function checkIt(){
	
	if(!document.myForm.pass.value){
alert("비밀번호를 확인을 입력해주세요.");
document.myForm.repass.focus();
return false;
}
return ture;
}

</script>

</head>
<body onload="javascript:document.myForm.pass.focus()">
<form action="member.mdo?cmd=deleteProc" name="myForm" onsubmit="return checkIt()" method="post">
<table width="260" border="1" align="center">
	<tr>
		<td colspan="2" align="center">
			<b>회원탈퇴</b>
		</td>
	</tr>
	
	<tr>
		<td width="150"><b>비밀번호 입력</b></td>
		<td width="110">
			<input type="password" name="pass" size="15">
		</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="회원탈퇴">
			<input type="button" value="취소" 
			onclick="javascript:window.location='member.mdo?cdm=login'">
		</td>
	</tr>

</table>
</form>

</body>
</html>