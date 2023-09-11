$(document).ready(function(){

	/*****************************************
					공지사항 게시판 폼 체크
	*/
	$("#btnNoticeWrite").click(function(){
		if($("input[name='ntitle']").val()=="") {
			alert("제목을 입력해주세요");
			$("input[name='ntitle']").focus();
			return false;
		}else if($("textarea[name='ncontent']").val()=="") {
			alert("내용을 입력해주세요");
			$("textarea[name='ncontent']").focus();
			return false;
		}else {
			writeForm.submit();
		}
	
	});

	/*******************************************
				회원가입 폼 - 아이디 중복체크
	 ********************************************/
	$("#btnBoardUpdate").click(function(){
		if($("input[name='btitle']").val()=="") {
			alert("제목을 입력해주세요");
			$("input[name='btitle']").focus();
			return false;
		}else {
			updateForm.submit();
		}
	});

	/*******************************
			게시판 - 등록 폼
	 *******************************/
	$("#btnBoardWrite").click(function(){
		
		if($("#btitle").val()=="") {
			alert("제목을 입력해주세요");
			$("#btitle").focus();
			return false;
		}else {
			writeForm.submit();
		}
		
	});

	/*******************************************
		회원가입 폼 - 아이디 중복체크
	********************************************/
	$("#btnIdCheck").click(function(){
		if($("#id").val() == ""){
			alert("아이디를 입력해주세요");
			$("#id").focus();
			return false;
		}else{
			$.ajax({
				url : "idCheck/" + $("#id").val(),
				success : function(result){
					if(result == "1"){
						$(".joinError").css("display", "none");
						$("#idcheck_msg").text("이미 사용중인 아이디 입니다. 다시 입력해주세요")
						.css("color","red").css("font-size","11px").css("display","block")
						.css("padding","8px 0px 8px 160px");
						$("#id").val("").focus();
					}else if(result == "0"){
						$(".joinError").css("display", "none");
						$("#idcheck_msg").text("사용 가능한 아이디 입니다")
						.css("color","blue").css("font-size","11px").css("display","block")
						.css("padding","8px 0px 8px 160px");
						$("#password").focus();
					}
				}
			});
		}
	});

	/*******************************************
		회원가입 폼 체크 - 주소찾기 : daum API 
	********************************************/
	$("#btnSearchAddr").click(function(){
		new daum.Postcode({
	        oncomplete: function(data) {
	        	//alert(data.address);
	        	$("#addr1").val("("+data.zonecode+") "+ data.address);
	        	$("#addr2").focus();
	        }
	    }).open();
		
	}); //search address


	/*******************************************
		회원가입 폼 체크 - 비밀번호 & 비밀번호 확인 
	********************************************/
	$("#cpassword").on("blur", function(){
		if($("#password").val() != "" && $("#cpassword").val() != ""){
			if($("#password").val() == $("#cpassword").val()){
				$("#cmsg").text("비밀번호가 동일합니다").css("color","blue")
					.css("font-size","11px").css("display","block");
				$("#name").focus();
			}else{
				$("#cmsg").text("비밀번호가 동일하지 않습니다. 다시 입력해주세요")
				.css("color","red").css("font-size","11px").css("display","block");
				
				$("#password").val("").focus();
				$("#cpassword").val("");
			}
		}
	}); //cpass blur

	/*******************************
		로그인 폼 체크

	$("#btnLogin").click(function(){
		if($("#id").val() == ""){
			alert("아이디를 입력해주세요");
			$("#id").focus();
			return false;
		}else if($("#pass").val() == ""){
			alert("패스워드를 입력해주세요");
			$("#pass").focus();
			return false;			
		}else{
			//서버전송
			loginForm.submit();
		}
	});	//btnLogin click
	 *******************************/
	/*******************************
		로그인 폼 - 다시쓰기 
	*******************************/
	$("#btnLoginReset").click(function(){
		$("#id").val("").focus();
		$("#pass").val("");
	}); //btnLoginReset click


	/*******************************
	 회원가입 폼 체크 - 유효성체크(값의 유무만 확인)

	 $("#btnJoin").click(function(){
		if($("#id").val() == ""){
			alert("아이디를 입력해주세요");
			$("#id").focus();
			return false;
		}else if($("#idcheck_msg").text() == ""){
			alert("중복체크를 진행해주세요");
			$("#btnIdCheck").focus();
			return false;
		}else if($("#pass").val() == ""){
			alert("패스워드를 입력해주세요");
			$("#pass").focus();
			return false;
		}else if($("#cpass").val() == ""){
			alert("패스워드 확인을 입력해주세요");
			$("#cpass").focus();
			return false;
		}else if($("#name").val() == ""){
			alert("성명을 입력해주세요");
			$("#name").focus();
			return false;
		}else if($("input[name='gender']:checked").length == 0){
			alert("성별을 선택해주세요");
			return false;
		}else if($("#email1").val() == ""){
			alert("이메일 주소를 입력해주세요");
			$("#email1").focus();
			return false;
		}else if($("#email2").val() == ""){
			alert("이메일 주소를 선택해주세요");
			$("#email3").focus();
			return false;
		}else if($("#addr1").val() == ""){
			alert("주소를 선택해주세요");
			$("#btnSearchAddr").css("border","1px solid gray");
			return false;
		}else if($("#addr2").val() == ""){
			alert("상세주소를 입력해주세요");
			$("#addr2").focus();
			return false;
		}else if($("input[name='tel']:checked").length == 0){
			alert("통신사를 선택해주세요");
			return false;
		}else if($("#phone1").val() == "default"){
			alert("폰번호를 선택해주세요");
			$("#phone1").focus();
			return false;
		}else if($("#phone2").val() == ""){
			alert("폰번호를 입력해주세요");
			$("#phone2").focus();
			return false;
		}else if($("#phone3").val() == ""){
			alert("폰번호를 입력해주세요");
			$("#phone3").focus();
			return false;
		}else if($("input[name='hobby']:checked").length == 0){
			alert("취미를 선택해주세요");
			return false;
		}else{
			//서버전송
			joinForm.submit();
		}
	});	//btnJoin
	 *******************************/
	

}); //ready





