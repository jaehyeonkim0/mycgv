<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9005/css/mycgv_jsp.css">
</head>
<body>
	<!-- header -->
	<!-- <iframe src="http://localhost:9005/header.jsp"
			scrolling="no" width="100%" height="149px" frameborder=0></iframe> -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="board">
			<h1 class="title">게시판</h1>
			<form name="writeForm" action="/board_write" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<th class="test">제목</th>
						<td>
							<input type="text" name="btitle">
							<span class="boardError">${valid_btitle}</span>
						</td>

					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="5" cols="30" name="bcontent"></textarea>
							<span class="boardError">${valid_bcontent}</span>
						</td>
					</tr>
					<tr>
						<th>업로드</th>
						<td>
							<input type="file" name="boardImages" multiple>
						</td>
					</tr>
					<tr>
						<th>작성자</th>
						<sec:authentication property="principal.username" var="username"/>
						<td>
							<input type="text" name="id" readonly value="${username}">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" class="btn_style">등록완료</button>
							<button type="reset" class="btn_style">다시쓰기</button>
							<a href="/board_list">
								<button type="button" class="btn_style">리스트</button></a>
							<a href="http://localhost:9005/">
								<button type="button" class="btn_style">홈으로</button></a>
						</td>				
					</tr>
				</table>
			</form>
		</section>
	</div>

	<!-- footer -->
	<!-- <iframe src="http://localhost:9005/footer.jsp"
			scrolling="no" width="100%" height="500px" frameborder=0></iframe> -->
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>

















