<%--
  Created by IntelliJ IDEA.
  User: HAPPY
  Date: 2024-01-29
  Time: 오후 4:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>예약</title>
</head>
<link rel="stylesheet" href="http://localhost:9005/css/mycgv_jsp.css">
<link rel="stylesheet" href="http://localhost:9005/css/am-pagination.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="http://localhost:9005/js/jquery-3.6.4.min.js"></script>
<script src="/js/mycgv_reserve.js"></script>


<body>

<jsp:include page="../header.jsp"></jsp:include>
<section class="section4">
    <div>
        <table class="reserve_table">
            <tr>
                <td colspan="4" class="reserve_func_button">
                    <a href="#">English</a>
                    <a href="#">상영시간표</a>
                    <a href="#">예매 다시하기</a>
                </td>
            </tr>
            <tr>
                <th>영화</th>
                <th>극장</th>
                <th>날짜</th>
                <th>시간</th>
            </tr>
            <tr>
                <td>
                    <%-- bootstrap --%>
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">전체</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">아트하우스</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">특별관</button>
                        </li>
                    </ul>
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">
                            <table>
                                <c:forEach var="movie" items="${movieDtoList}">
                                    <tr>
                                        <td class="movieList"><a href="#" class="nameLink">${movie.ageLimit} ${movie.name}</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <div class="tab-pane fade" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">profile</div>
                        <div class="tab-pane fade" id="contact-tab-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="0">contact</div>
                    </div>
                </td>
            </tr>


        </table>
    </div>
</section>






<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
