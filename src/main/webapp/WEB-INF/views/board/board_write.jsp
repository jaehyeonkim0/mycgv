<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9005/css/mycgv_jsp.css">
<script src="http://localhost:9005/js/jquery-3.6.4.min.js"></script>
<script>
	function fn_addFile(){
		$("#d_file").append("<br>" + "<input type='file' name='boardDtoFile1' />");
	}
</script>
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
			<form name="writeForm" action="board_write" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<th class="test">제목</th>
						<td>
							<input type="text" name="btitle" id="btitle" placeholder="*제목은 반드시 입력해주세요"
							value="${valid_btitle}">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="5" cols="30" name="bcontent">${valid_bcontent}</textarea>
						</td>
					</tr>
<%--					<tr>--%>
<%--						<th>작성자</th>--%>
<%--						<td>--%>
<%--							<input type="text" name="id" value="${member_id}" disabled>--%>
<%--							<input type="hidden" name="id" value="${member_id}">--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--					<tr>--%>
<%--						<th>업로드</th>--%>
<%--						<td>--%>
<%--							<input type="file" name="boardDtoFile" >--%>
<%--						</td>--%>
<%--					</tr>--%>
					<tr>
						<th>업로드</th>
						<td>
							<input type="file" name="boardDtoFile1" multiple>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" class="btn_style" id="btnBoardWrite">등록완료</button>
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

















