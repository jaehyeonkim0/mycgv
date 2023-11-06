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
<script>

	var board = {
		// 여기에 board 객체의 필드와 값을 JavaScript 객체의 속성으로 할당
		size : ${board.originalFileName1.size()}
	};

	function fn_addFile(){
		$("#update_file1").append("<span id='update_file'></span>");
	}

$(document).ready(function(){

	$("#boardDtoFile").on("change", function(){
		if(window.FileReader){
			for(let i=0; i<board.size; i++) {
				let fname = $(this)[i].files[i].name;
				$("#update_file").text(fname);
			}
			//FileReader = 파일 고르는 창;
			//$(this)[0] = 파일 고르는 창이 여러개 있을 수 있으니까
			//첫번째(?), $(this)[0].files[0]= 파일 고르는창에서 파일을 하나 이상 고를 수 있기 때문에 첫번째 파일 이름

		}
	});


	let originalFileNameList = ${board.originalFileName1}; // board.originalFileName1 리스트 가져오기
	let container = document.getElementById("update_file_container"); // <span>을 추가할 부모 요소의 ID를 지정하세요

	// container가 null이 아니고 originalFileNameList가 배열인 경우에만 동작
	if (container !== null && Array.isArray(originalFileNameList)) {
		for (var i = 0; i < originalFileNameList.length; i++) {
			var span = document.createElement("span"); // <span> 요소 생성
			span.textContent = originalFileNameList[i]; // <span> 내용 설정
			container.appendChild(span); // 부모 요소에 <span> 추가
		}
	}

	// 만약 originalFileName1이 비어있거나 null이면 "선택된 파일없음" 메시지를 추가
	if (originalFileNameList == null || originalFileNameList.length === 0) {
		var span = document.createElement("span");
		span.textContent = "선택된 파일없음";
		container.appendChild(span);
	}





});

</script>
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
					<tr>
						<th>파일</th>
						<td>
<%--							<input type="file" name="boardDtoFile" id="boardDtoFile">--%>
							<input type="file" name="boardDtoFile1" multiple>
							<div id="update_file_container"></div>
							<c:choose>
								<c:when test="${board.fileAttached == 1 }">
									<c:forEach var="beforeUpdateOriginalFileName" items="${board.originalFileName1}">
										<span id="update_file">${beforeUpdateOriginalFileName}</span>
									</c:forEach>
									<c:forEach var="beforeUpdateSaveFileName" items="${board.saveFileName1}">
										<input type="text" name="fileNameBeforeUpdate1" value="${beforeUpdateSaveFileName}">
									</c:forEach>
								</c:when>
								<c:otherwise>
									<span id="update_file">선택된 파일없음</span>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<input type="hidden" name="bid" value="${board.bid }">
					<input type="hidden" name="id" value="${board.id }">
					<input type="hidden" name="bhits" value="${board.bhits }">
					<input type="hidden" name="fileAttached" value="${board.fileAttached}">

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
















