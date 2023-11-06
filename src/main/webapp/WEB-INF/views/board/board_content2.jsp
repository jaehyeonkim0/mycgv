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
<style>
	.btn {
		display: none;
	}
</style>
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
					<c:if test="${board.fileAttached == 1}">
<%--					<c:if test="${board.fileAttached != 0 && board.dbFileSave != 0}">--%>
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
		</section>

		<section class="commentWrite">
			<h3 class="comments-title2">Comments</h3>
			<div class="form-block">
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<textarea class="form-input" id="commentContents"></textarea>
							<c:choose>
								<c:when test="${sessionScope.loginUser.id != null}">
									<input type="hidden" name="id" id="id" value="${sessionScope.loginUser.id}">
								</c:when>
								<c:otherwise>
									<input type="hidden" name="id" id="id" value="">
								</c:otherwise>
							</c:choose>
						</div>
						<button class="btn btn-primary pull-right" id="comment-insert">입력</button>
					</div>
				</div>
			</div>
		</section>
		<!-- 댓글 출력 부분 -->
		<section class="commentList">
			<div id="comment-start-section">
				<div class="be-comment-block">
					<c:forEach var="comment" items="${commentDtoList}">
						<div class="be-comment">
							<div class="be-img-comment">
								<a href="blog-detail-2.html">
									<img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="" class="be-ava-comment">
								</a>
							</div>
							<div class="be-comment-content">
								<span class="be-comment-name">
									${comment.commentWriter}
								</span>
								<span class="be-comment-time">
									<i class="fa fa-clock-o"></i>
									${comment.commentCreatedTime}
								</span>
								<p class="be-comment-text">
									${comment.commentContents}
								</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</section>
	</div>
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
<script>

	$(document).ready(function(){

		$("#comment-insert").click(function(){
			let loginOrNot = document.getElementById("id").value;

			if (loginOrNot != "") {
				commentWrite();
			} else {
				if (confirm("로그인 후 이용 가능합니다. 로그인 하시겠습니까?")) {
					location.href = "/login";
				} else {
					return false;
				}

			}
		})

		const textarea = document.getElementById("commentContents");
		const button = document.getElementById("comment-insert");

		textarea.addEventListener("click", function (event) {
			button.style.display = "inline-block";

			event.stopPropagation();
		});

		document.addEventListener("click", function () {
			button.style.display = "none";
		});
	});

	function commentWrite() {
		const writer = document.getElementById("id").value;
		const contents = document.getElementById("commentContents").value;
		console.log("작성자: ", writer);
		console.log("내용: ", contents);
		const bid = ${board.bid};
		$.ajax({
			// 요청방식: post, 요청주소: /comment/save, 요청데이터: 작성자, 작성내용, 게시글번호
			type: "POST",
			url: "/comment/save",
			data: {
				"commentWriter": writer,
				"commentContents": contents,
				"bid": bid
			},
			success: function (res) {
				console.log("요청성공", res);
				let output = "<div class='be-comment-block'>";
				for (let i in res) {
					output += "<div class='be-comment'><div class='be-img-comment'>";
					output += "<a href='blog-detail-2.html'><img src='https://bootdey.com/img/Content/avatar/avatar1.png' alt='' class='be-ava-comment'></a></div>";
					output += "<div class='be-comment-content'><span class='be-comment-name'>";
					output += res[i].commentWriter + "</span><span class='be-comment-time'><i class='fa fa-clock-o'></i>";
					output += res[i].commentCreatedTime + "</span><p class='be-comment-text'>";
					output += res[i].commentContents + "</p>";
					output += "</div></div>";
				}
				output += "</div>";

				document.getElementById('comment-start-section').innerHTML = output;
				document.getElementById('id').value = '';
				document.getElementById('commentContents').value = '';
			},
			error: function (err) {
				console.log("요청실패", err);
			}
		});

	}
</script>
