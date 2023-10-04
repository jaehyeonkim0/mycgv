<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9005/css/mycgv_jsp.css">
<script src="http://localhost:9005/js/jquery-3.6.4.min.js"></script>
<%--<script src="http://localhost:9005/js/mycgv_jsp_jquery.js"></script>--%>
</head>
<body>
	<!-- header -->
	<jsp:include page="../../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="board">
			<h1 class="title">관리자 - 공지사항</h1>
			<form name="writeForm" action="/admin/notice_write" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="ntitle" value="${noticeDto.ntitle}">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="5" cols="30" name="ncontent">${noticeDto.ncontent}</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" id="btnNoticeWrite">등록완료</button>
							<button type="reset" id="btnNoticeReset">다시쓰기</button>
							<a href="/admin/notice_list/${page.reqPage}">
								<button type="button">리스트</button></a>
							<a href="/admin/index">
								<button type="button">관리자홈</button></a>
						</td>
					</tr>
				</table>
			</form>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>
















