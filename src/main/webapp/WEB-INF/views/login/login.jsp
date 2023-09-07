<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9005/css/mycgv_jsp.css">
<script src="http://localhost:9005/js/jquery-3.6.4.min.js"></script>
<script src="http://localhost:9005/js/mycgv_jsp_jquery.js"></script>
<script>

let c = "${join_result }";

if(c == 'ok') {
	alert("회원가입 성공");
}
</script>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="login">
			<h1 class="title">로그인</h1>
			<form name="loginForm" action="/login?redirectURL=${param.redirectURL}" method="post" >
				<ul>
					<li>
						<label>아이디</label>
						<input type="text" name="id" id="id" value="${memberDto.id}">
						<span class="loginError">${valid_id}</span>
					</li>
					<li>
						<label>패스워드</label>
						<input type="password" name="pass" id="pass">
						<span class="loginError">${valid_pass}</span>
					</li>
					<li>
						<button type="submit" class="btn_style" id="btnLogin" >로그인</button>
						<button type="button" class="btn_style" id="btnLoginReset">다시쓰기</button>
					</li>
					<li>
						<span><a href="#">아이디 찾기> </a></span>
						<span><a href="#">비밀번호 찾기> </a></span>
					</li>
				</ul>
			</form>
		</section>
	</div>

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
















