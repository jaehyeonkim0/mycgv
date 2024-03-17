<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9005/css/mycgv_jsp.css">
<link rel="stylesheet" href="http://localhost:9005/css/am-pagination.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="http://localhost:9005/js/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>
	<%-- <h1>${name }</h1> --%> 
	<%-- EL태그는 expression(<%= %>)의 다른 모습일 뿐 for문을 돌리진 못한다 --%>
	
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="board">
			<h1 class="title">게시판</h1>
			<div class="d-flex">
				<div class="p-2 flex-grow-1">

				</div>
			</div>
			<table class="board_list">
				<tr>
					<td>
						<div class="d-flex justify-content-start">
							<div class="dropdown">
								<a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
									최신순
								</a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="#">Action</a></li>
									<li><a class="dropdown-item" href="#">Another action</a></li>
									<li><a class="dropdown-item" href="#">Something else here</a></li>
								</ul>
							</div>
						</div>
					</td>
					<td colspan="4">
						<a href="/auth/board_write">
							<button type="button" class="btn_style2">글쓰기</button>
						</a>
					</td>
				</tr>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>조회수</th>
					<th>작성자</th>
					<th>작성일자</th>
				</tr>
				<c:forEach var="board" items="${board }" varStatus="loop">
					<tr>
						<td>${page.dbCount - (page.size * page.reqPage) - loop.index}</td>
						<td><a href="/auth/board_content/${board.bid }/${page.reqPage}&searchText=${param.searchText}">${board.btitle }</a></td>
						<td>${board.bhits }</td>
						<td>${board.id }</td>
						<td>${board.boardUpdatedTime != null ? board.boardUpdatedTime : board.boardCreatedTime}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5" style="border: 1px solid white;"></td>
				</tr>
				<tr>
					<td colspan="5">
						<form class="d-flex justify-content-center" method="GET" action="/board_list">
							<div class="col-auto" style="margin-right:10px;">
								<label for="inputPassword2" class="visually-hidden">Password</label>
								<input type="text" class="form-control" id="inputPassword2" name="searchText" value="${param.searchText}">
							</div>
							<div class="col-auto">
								<button type="submit" class="btn btn-light">검색</button>
							</div>
						</form>
					</td>
				</tr>
			</table>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<script>
						console.log(${startBlockPage})
						console.log(${endBlockPage})
					</script>
					<c:if test="${startBlockPage > 1}">
						<li class="page-item">
							<a class="page-link" href="/board_list?page=0&searchText=${param.searchText}"><<</a>
						</li>
						<li class="page-item">
							<a class="page-link" href="/board_list?page=${startBlockPage - 2}&searchText=${param.searchText}">Previous</a>
						</li>
					</c:if>
					<c:forEach var="i" begin="${startBlockPage}" end="${endBlockPage}" step="1">
						<c:set var="isDisabled" value="${i eq page.reqPage + 1}" />
							<li class="page-item ${isDisabled ? 'disabled' : ''}">
								<a class="page-link" href="/board_list?page=${i-1}&searchText=${param.searchText}">${i}</a>
							</li>
					</c:forEach>
					<c:if test="${endBlockPage lt page.totalPage}">
						<li class="page-item">
							<a class="page-link" href="/board_list?page=${endBlockPage}&searchText=${param.searchText}">Next</a>
						</li>
						<li class="page-item">
							<a class="page-link" href="/board_list?page=${page.totalPage - 1}&searchText=${param.searchText}">>></a>
						</li>
					</c:if>
				</ul>
			</nav>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>

