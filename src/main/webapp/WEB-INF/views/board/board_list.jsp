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
<script src="http://localhost:9005/js/jquery-3.6.4.min.js"></script>
<script src="http://localhost:9005/js/mycgv_jsp_jquery.js"></script>
<script src="http://localhost:9005/js/am-pagination.js"></script>
<script>
	$(document).ready(function(){
		var pager = jQuery('#ampaginationsm').pagination({
		
		    maxSize: '${page.pageCount}',		// max page size(pageCount)
		    totals: '${page.dbCount}',		// total pages(total rows;dbCount)
		    page: '${page.reqPage}',		// initial page(reqPage)
		    pageSize: '${page.pageSize}',	// max number items per page
		
		    // custom labels		
		    lastText: '&raquo;&raquo;', 		
		    firstText: '&laquo;&laquo;',		
		    prevText: '&laquo;',		
		    nextText: '&raquo;',
				     
		    btnSize:'sm'	// 'sm'  or 'lg'		
		});
		
		jQuery('#ampaginationsm').on('am.pagination.change',function(e){
			   jQuery('.showlabelsm').text('The selected page no: '+e.page);
	           $(location).attr('href', "http://localhost:9005/board_list/"+e.page+"/");
	    });
		
 	});
</script> 
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
			<table class="board_list">
				<tr>
					<td colspan="5">
						<a href="/board_write">
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
				<c:forEach var="board" items="${list }">
				<tr>
					<td>${board.rno }</td>
					<td><a href="/board_content/${board.bid }/${page.reqPage}">${board.btitle }</a></td>
					<td>${board.bhits }</td>
					<td>${board.id }</td>
					<td>${board.bdate }</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5"><div id="ampaginationsm"></div></td>
				</tr>
			</table>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>

