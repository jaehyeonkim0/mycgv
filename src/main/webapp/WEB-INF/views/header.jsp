<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="http://localhost:9005/css/mycgv_jsp.css">
</head>
<body>
	<header>
		<div class="header_menu">
			<nav class="nav1">
				<sec:authorize access="isAnonymous()">
					<ul>
						<li><a href="http://localhost:9005/login">로그인</a></li>
						<li><a href="http://localhost:9005/join">회원가입</a></li>
						<li><a href="http://localhost:9005/mypage">마이페이지</a></li>
						<li><a href="#">고객센터</a></li>
						<li><a href="http://localhost:9005/notice_list/1/">공지사항</a></li>
						<li><a href="http://localhost:9005/board_list?page=0">게시판</a></li>
					</ul>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal.username" var="username"/>
					<sec:authentication property="principal.name" var="name"/>
					<sec:authentication property="principal.social" var="social"/>
					<ul>
						<c:if test="${username == 'guest'}">
							<li><a href="http://localhost:9005/login">로그인</a></li>
							<li><a href="http://localhost:9005/join">회원가입</a></li>
						</c:if>
						<c:if test="${username != 'guest'}">
							<li class="LoginUserName">${name}님 반갑습니다</li>
							<li><a href="http://localhost:9005/logout">로그아웃</a></li>
						</c:if>
						<li><a href="http://localhost:9005/mypage">마이페이지</a></li>
						<li><a href="#">고객센터</a></li>
						<li><a href="http://localhost:9005/notice_list/1/">공지사항</a></li>
<%--						<li><a href="http://localhost:9005/board_list/1/">게시판</a></li>--%>
						<li><a href="http://localhost:9005/board_list?page=0">게시판</a></li>
						<li><a href="http://localhost:9005/board_list_json">게시판(JSON)</a></li>
						<c:if test="${username == 'admin' }">
							<li id="admin-menu"><a href="http://localhost:9005/admin/index">ADMIN</a></li>
						</c:if>
					</ul>
				</sec:authorize>
<%--				<sec:authorize access="isAuthenticated()">--%>
<%--					<ul>--%>
<%--						<li><a href="http://localhost:9005/logout">로그아웃</a></li>--%>
<%--						<li><a href="http://localhost:9005/mypage">마이페이지</a></li>--%>
<%--						<li><a href="#">고객센터</a></li>--%>
<%--						<li><a href="http://localhost:9005/notice_list/1/">공지사항</a></li>--%>
<%--						<li><a href="http://localhost:9005/board_list?page=0">게시판</a></li>--%>
<%--						<li><a href="http://localhost:9005/board_list_json">게시판(JSON)</a></li>--%>
<%--					</ul>--%>
<%--				</sec:authorize>--%>
			</nav>
			<div>
				<a href="http://localhost:9005/">
					<img src="http://localhost:9005/images/h1_cgv.png">
				</a>
				<div>
					<img src="http://localhost:9005/images/h2_cultureplex.png" >
					<nav class="nav2">
						<ul>
							<li><a href="#">영화</a></li>
							<li><a href="#">예매</a></li>
							<li><a href="#">극장</a></li>
							<li><a href="#">이벤트&컬처</a></li>
						</ul>
					</nav>				
				</div>
			</div>
		</div>
	</header>
</body>
</html>
