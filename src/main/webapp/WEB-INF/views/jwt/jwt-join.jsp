<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9005/css/mycgv_jsp.css">
<script src="http://localhost:9005/js/jquery-3.6.4.min.js"></script>
<script src="http://localhost:9005/js/mycgv_jsp_jquery.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<div class="content">
		<section class="join">
			<h1 class="title">회원가입</h1>
			<form name="joinForm" action="/jwt/register" method="post">
				<ul>
					<li>
						<label>아이디</label>
						<input type="text" name="id" placeholder="* 아이디는 필수 입력 항목입니다" class="input1"
							id="id" value="${oauthId == null ? memberDto.id : oauthId}" ${oauthId == null ? '' : 'readonly'}>
						<button type="button" class="btn_style2" id="btnIdCheck">중복체크</button>
						<span id="idcheck_msg"></span>
						<span class="joinError">${valid_id}</span>
					</li>
					<li>
						<label>비밀번호</label>
						<input type="password" name="password" class="input1" id="password">
						<span class="joinError">${valid_password}</span>
					</li>
					<li>
						<label>비밀번호 확인</label>
						<input type="password" name="cpassword" class="input1" id="cpassword">
                        <span id="cmsg"></span>
					</li>
					<li>
						<label>성명</label>
						<input type="text" name="name" class="input1" id="name" value="${name == null ? memberDto.name : name}">
						<span class="joinError">${valid_name}</span>
					</li>
					<li>
						<label>성별</label>
						<input type="radio" name="gender" value="m" ${memberDto.gender == 'm' ? 'checked' : ''}><span>남자</span>
						<input type="radio" name="gender" value="f" ${memberDto.gender == 'f' ? 'checked' : ''}><span>여자</span>
					</li>
					<li>
						<label>이메일</label>
						<input type="text" name="email" class="input1" value="${memberDto.email}" placeholder="* abc@example.com">
						<span class="joinError">${valid_email}</span>
					</li>
					<li>
						<label>주소</label>
						<input type="text" name="addr1" class="input1" id="addr1">
						<button type="button" class="btn_style2" id="btnSearchAddr">주소찾기</button>
					</li>
					<li>
						<label>상세주소</label>
						<input type="text" name="addr2" class="input1" id="addr2">
					</li>
					<li>
						<label>휴대폰</label>
<%--						<input type="radio" name="tel" value="skt" ${memberDto.tel == 'skt' ? 'checked' : ''}><span>SKT</span>--%>
<%--						<input type="radio" name="tel" value="lgu+" ${memberDto.tel == 'lgu+' ? 'checked' : ''}><span>LGU+</span>--%>
<%--						<input type="radio" name="tel" value="kt" ${memberDto.tel == 'kt' ? 'checked' : ''}><span>KT</span>--%>
<%--						<select name="phone1" id="phone1">--%>
<%--							<option value="">선택</option>--%>
<%--							<option value="011">011</option>--%>
<%--							<option value="010">010</option>--%>
<%--							<option value="017">017</option>--%>
<%--						</select>--%>
<%--						- <input type="text" name="phone2" id="phone2">--%>
<%--						- <input type="text" name="phone3" id="phone3">--%>
						<input type="text" name="pnumber" class="input1" value="${pnumber == null ? "" : pnumber}" placeholder="-  없이 작성하시오">
					</li>
					<li>
						<label>취미</label>
						<input type="checkbox" name="hobby" value="영화보기"><span>영화보기</span>
						<input type="checkbox" name="hobby" value="등산하기"><span>등산하기</span>
						<input type="checkbox" name="hobby" value="게임하기"><span>게임하기</span>
						<input type="checkbox" name="hobby" value="잠자기"><span>잠자기</span>
					</li>
					<li>
						<label>자기소개</label>
						<textarea name="intro" placeholder="*200자 이내로 작성해주세요"></textarea>
					</li>
					<li>
						<input type="hidden" name="social" value="${social == true ? true : false}">
					</li>
					<li>
						<button type="submit" class="btn_style" id="btnJoin">가입하기</button>
						<button type="reset" class="btn_style">다시입력</button>
					</li>			
				</ul>
			</form>
		</section>
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
