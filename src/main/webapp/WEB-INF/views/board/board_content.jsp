<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="/css/mycgv_jsp.css">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<script src="http://localhost:9005/js/jquery-3.6.4.min.js"></script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="board">
			<h1 class="title">게시판</h1>
			<table class="board_content">
				<tr>
					<th>제목</th>
					<td>${board.btitle }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
					<c:if test="${board.fileAttached == 1 }">
						<c:forEach var="file" items="${board.saveFileName1}">
								<img src="/upload/${file }">
						</c:forEach>
					</c:if><br>
						${board.bcontent }<br><br><br>
					</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${board.bhits }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${board.id }</td>
				</tr>
				<tr>
					<th>작성일자</th>
					<td>${board.boardUpdatedTime != null ? board.boardUpdatedTime : board.boardCreatedTime}</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:choose>
							<c:when test="${sessionScope.loginUser.id == board.id}">
								<a href="/board_update/${board.bid }">
									<button type="button" class="btn_style">수정하기</button></a>
								<a href="/board_delete/${board.bid }">
									<button type="button" class="btn_style">삭제하기</button></a>
								<a href="/board_list?page=${page}">
									<button type="button" class="btn_style">리스트</button></a>
								<a href="http://localhost:9005/">
									<button type="button" class="btn_style">홈으로</button></a>
							</c:when>
							<c:otherwise>
								<a href="/board_list?page=${page}">
									<button type="button" class="btn_style">리스트</button></a>
								<a href="http://localhost:9005/">
									<button type="button" class="btn_style">홈으로</button></a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
			<div id="comment-write">
<%--				<input type="text" id="commentWriter" placeholder="작성자">--%>
				<input type="text" id="commentContents" placeholder="내용">
				<button id="comment-write-btn" onclick="commentWrite()">댓글작성</button>
			</div>

			<div id="comment-list" class="comment-list">
				<table>
					<tr></tr>
					<tr>
						<th>작성자</th>
						<th>내용</th>
						<th>작성시간</th>
					</tr>
					<c:forEach var="comment" items="${commentList}">
						<tr>
							<td>${comment.commentWriter}</td>
							<td>${comment.commentContents}</td>
							<td>${comment.commentCreatedTime}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</section>
	</div>
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
<script>

	function commentWrite() {
		const writer = document.getElementById("commentWriter").value;
		const contents = document.getElementById("commentContents").value;
		console.log("작성자: ", writer);
		console.log("내용: ", contents);
		const bid = ${board.bid};
		$.ajax({
			// 요청방식: post, 요청주소: /comment/save, 요청데이터: 작성자, 작성내용, 게시글번호
			type: "post",
			url: "/comment/save",
			data: {
				"commentWriter": writer,
				"commentContents": contents,
				"bid": bid
			},
			success: function (res) {
				console.log("요청성공", res);
				let output = "<table>";
				output += "<tr><th>댓글번호</th>";
				output += "<th>작성자</th>";
				output += "<th>내용</th>";
				output += "<th>작성시간</th></tr>";
				for (let i in res) {
					output += "<tr>";
					output += "<td>" + res[i].cid + "</td>";
					output += "<td>" + res[i].commentWriter + "</td>";
					output += "<td>" + res[i].commentContents + "</td>";
					output += "<td>" + res[i].commentCreatedTime + "</td>";
					output += "</tr>";
				}
				output += "</table>";
				document.getElementById('comment-list').innerHTML = output;
				document.getElementById('commentWriter').value = '';
				document.getElementById('commentContents').value = '';
			},
			error: function (err) {
				console.log("요청실패", err);
			}
		});

	}

</script>
