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
        <form name="joinForm" action="/signup" method="post">
<%--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
            <ul>
                <li>
                    <label>아이디</label>
                    <input type="text" name="id" class="input1" id="id" placeholder="* 아이디는 필수 입력 항목입니다"
                           value="${memberDto.id}">
                    <span class="joinError">${valid_id}</span>
                </li>
                <li>
                    <label>비밀번호</label>
                    <input type="password" name="password" class="input1">
                    <span class="joinError">${valid_password}</span>
                </li>
                <li>
                    <label>성명</label>
                    <input type="text" name="name" class="input1" value="${memberDto.name}">
                    <span class="joinError">${valid_name}</span>
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
