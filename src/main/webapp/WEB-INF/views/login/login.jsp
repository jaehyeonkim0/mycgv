<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9005/css/mycgv_jsp.css">
	<script src="http://localhost:9005/js/jquery-3.6.4.min.js"></script>
	<script src="http://localhost:9005/js/jwtTokenTest.js"></script>
	<script src="http://localhost:9005/js/rememberId.js"></script>
<%--<script src="http://localhost:9005/js/mycgv_jsp_jquery.js"></script>--%>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="login">
			<h1 class="title">로그인</h1>
			<form name="loginForm" action="/login" method="post">
				<ul>
					<li>
						<label>아이디</label>
						<input type="text" name="id">
						<span class="loginError">${valid_id}</span>
					</li>
					<li>
						<label>패스워드</label>
						<input type="password" name="password">
						<span class="loginError">${valid_pass}</span>
					</li>
					<li>
					<c:if test="${param.error != null}">
						<span class="loginError">아이디 또는 비밀번호를 잘못 입력했습니다. 다시 입력해주세요.</span>
					</c:if>
					</li>
					<li>
						<button type="submit" class="btn_style" id="btnLogin" >로그인</button>
						<button type="button" class="btn_style" id="btnLoginReset">다시쓰기</button>
					</li>
					<li class="social-li">
						<button type="button" id="kakaoLogin" class="social-button" onclick="kakaoLogin()">
							<img src="http://localhost:9005/images/kakao-button-m.png" class="kakao-button">
						</button>
						<button type="button" id="naverLogin" class="social-button" onclick="naverLogin()">
							<img src="http://localhost:9005/images/naver-button.png" class="naver-button">
						</button>
					</li>
					<li>
						<span><a href="#">아이디 찾기> </a></span>
						<span><a href="#">비밀번호 찾기> </a></span>
					</li>
				</ul>
			</form>
		</section>
	</div>
	<div class="duplicate-login-alert">
		<c:if test="${DUPLICATE_LOGIN eq 'true'}">
			<script> alert("다른 기기에서 로그인되어 현재 로그인이 종료되었습니다.") </script>
		</c:if>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
	<script src="http://localhost:9005/js/socialLogin.js"></script>
</body>
</html>

