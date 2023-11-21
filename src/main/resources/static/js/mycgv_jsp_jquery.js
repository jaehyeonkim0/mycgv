$(document).ready(function(){

	/**
	 * 아이디 중복 체크
	 */
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

	/**
	 * 회원가입 폼 체크 - 주소찾기 : daum API
	 */
	$("#btnSearchAddr").click(function(){
		new daum.Postcode({
	        oncomplete: function(data) {
	        	//alert(data.address);
	        	$("#addr1").val("("+data.zonecode+") "+ data.address);
	        	$("#addr2").focus();
	        }
	    }).open();
		
	}); //search address


	/**
	 * 회원가입 폼 체크 - 비밀번호 & 비밀번호 확인
	 */
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

	/**
	 * 로그인 폼 - 다시쓰기
	 */
	$("#btnLoginReset").click(function(){
		$("#id").val("").focus();
		$("#pass").val("");
	}); //btnLoginReset click


}); //ready





