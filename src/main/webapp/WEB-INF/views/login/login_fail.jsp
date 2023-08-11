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
</head>
<body>
	<!-- header -->
	<!-- <iframe src="http://localhost:9005/header.jsp"
			scrolling="no" width="100%" height="149px" frameborder=0></iframe> -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="login">
			<h1 class="title">로그인 실패</h1>
			<img src="https://nenechicken.com/17_new/images/menu/30005.jpg">
			<a href="http://localhost:9005/login.do">
				<button type="button" class="btn_style">로그인 페이지 이동</button>
			</a>
		</section>
	</div>
	
	<!-- footer -->
	<!-- <iframe src="http://localhost:9005/footer.jsp"
			scrolling="no" width="100%" height="500px" frameborder=0></iframe> -->	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
















