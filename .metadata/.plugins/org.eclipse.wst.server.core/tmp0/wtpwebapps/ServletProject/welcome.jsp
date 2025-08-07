<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Globalin Mall</title>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: 'Helvetica Neue', sans-serif;
	background-color: #f0f2f5;
	display: flex;
	flex-direction: column;
	min-height: 100vh;
}

header {
	background-color: #2980b9;
	color: white;
	padding: 20px;
	text-align: center;
	font-size: 1.8em;
}

main {
	flex: 1;
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 40px 0;
}

.card {
	background: white;
	padding: 40px 60px;
	border-radius: 16px;
	box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
	text-align: center;
}

.icon {
	font-size: 50px;
	margin-bottom: 20px;
	color: #3498db;
}

h1 {
	font-size: 2.5em;
	margin: 10px 0;
	color: #2c3e50;
}

h3 {
	font-size: 1.3em;
	margin-bottom: 30px;
	color: #555;
}

.buttons {
	display: flex;
	justify-content: center;
	gap: 20px;
}

.btn {
	padding: 12px 24px;
	border: none;
	border-radius: 8px;
	font-size: 1em;
	cursor: pointer;
	transition: all 0.3s ease;
	text-decoration: none;
	color: white;
}

.btn.login {
	background-color: #2980b9;
}

.btn.login:hover {
	background-color: #3498db;
}

.btn.signup {
	background-color: #2ecc71;
}

.btn.signup:hover {
	background-color: #27ae60;
}

footer {
	background-color: #34495e;
	color: white;
	text-align: center;
	padding: 15px;
}
</style>
</head>
<body>

	<!-- 	    Header
    <header>
        🛍️ Globalin Shopping Mall
    </header>
    
     -->
	<%@ include file="menu.jsp"%>

	<!-- Main Content -->
	<main>
		<div class="card">
			<div class="icon">🛒</div>
			<h1>Welcome!</h1>
			<h3>Globalin Marketへようこそ！</h3>
			<div class="buttons">
				<a href="login.jsp" class="btn login">ログイン</a> <a href="signup.jsp"
					class="btn signup">新規登録</a>
			</div>
			<br>


			<%
            	Date day = new Date();
            	String am_pm;
            	
            	int hour = day.getHours(); //시간을 가져오는 메소드
            	int minute = day.getMinutes();  // 분을 가져옴
            	int second	=	day.getSeconds(); // 초를 가져옴
            	
            	if(hour / 12 == 0){
            		am_pm="AM";
            	}else{
            		am_pm="PM";
            		hour = hour-12;
            	}
            	
            	String CT = hour+" : "+minute+" : "+second+" "+am_pm;
            	out.println("현재 접속 시간 "+CT+"\n");
            	
            %>



		</div>
	</main>

	<%@ include file="footer.jsp"%>

</body>
</html>
