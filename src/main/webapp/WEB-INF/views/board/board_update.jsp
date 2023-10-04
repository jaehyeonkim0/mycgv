<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9005/mycgv/css/mycgv.css">
<script src="http://localhost:9005/js/jquery-3.6.4.min.js"></script>
<%--<script src="http://localhost:9005/js/mycgv_jsp_jquery.js"></script>--%>
<%--<script>--%>

<%--$(document).ready(function(){--%>
<%--	--%>
<%--	$("#file1").on("change", function(){--%>
<%--		if(window.FileReader){--%>
<%--			let fname = $(this)[0].files[0].name;--%>
<%--			//FileReader = 파일 고르는 창; $(this)[0] = 파일 고르는 창이 여러개 있을 수 있으니까 첫번째(?), $(this)[0].files[0]= 파일 고르는창에서 파일을 하나 이상 고를 수 있기 때문에 첫번째 파일 이름--%>
<%--			$("#update_file").text(fname);--%>
<%--		}--%>
<%--	});--%>
<%--	--%>
<%--});--%>

<%--</script>--%>
<style>
	#update_file {
		border: 1px solid white;
		position: relative;
		left: 115px;
		top: -29px;
		padding: 0 30px 0 0px;
		background: white;
		display: inline-block;
	}
</style>
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="board">
			<h1 class="title">게시판</h1>
			<form name="updateForm" action="/board_update" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="btitle" value="${board.btitle }">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="5" cols="30" name="bcontent">${board.bcontent }</textarea>
						</td>
					</tr>
					<input type="hidden" name="bid" value="${board.bid }">
					<input type="hidden" name="id" value="${board.id }">
					<input type="hidden" name="bhits" value="${board.bhits }">

<%--					<tr>--%>
<%--						<th>작성자</th>--%>
<%--						<td>--%>
<%--							<input type="hidden" name="id" value="${board.id }">--%>
<%--						</td>--%>
<%--					</tr>--%>
					<tr>
						<td colspan="2">
							<button type="submit" class="btn_style" id="btnBoardUpdate">수정완료</button>
							<button type="reset" class="btn_style">다시쓰기</button>
							<a href="/board_content/${board.bid }">
								<button type="button" class="btn_style">이전페이지</button></a>
							<a href="/board_list">
								<button type="button" class="btn_style">리스트</button></a>							
						</td>				
					</tr>
				</table>
			</form>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
















