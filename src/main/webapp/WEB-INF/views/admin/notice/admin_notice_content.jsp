<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9005/css/mycgv_jsp.css">
</head>
<body>
	<!-- header -->
	<jsp:include page="../../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="board">
			<h1 class="title">관리자 - 공지사항</h1>
			<table class="board_content">
				<tr>
					<th>제목</th>
					<td>${notice.ntitle }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
					<c:if test="${notice.nsfile1 != null }">
						<img src="http://localhost:9005/upload/${notice.nsfile1 }">
					</c:if><br>
					${notice.ncontent }
					</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${notice.nhits }</td>
				</tr>
				<tr>
					<th>작성일자</th>
					<td>${notice.ndate }</td>
				</tr>
				<tr>
					<td colspan="2">
						<a href="/admin/notice_update/${notice.nid }/${page}/">
							<button type="button">수정하기</button></a>
						<a href="/admin/notice_delete/${notice.nid }/${page}">
							<button type="button">삭제하기</button></a>
						<a href="/admin/notice_list/${page}/">
							<button type="button">리스트</button></a>
						<a href="/admin/index">
						<button type="button">관리자홈</button></a>
					</td>
				</tr>
			</table>
		</section>
	</div>
	
	<!-- footer -->
	<!-- <iframe src="http://localhost:9005/footer.jsp"
			scrolling="no" width="100%" height="500px" frameborder=0></iframe> -->	
	<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>
















