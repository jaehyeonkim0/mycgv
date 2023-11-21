$(document).ready(function(){

    $("#boardImages").on("change", function(event){
        if(window.FileReader){

            //파일 입력 요소(#boardDtoFile1)에서 선택한 파일들의 목록을 가져옵니다.
            let files = event.target.files;
            //빈 문자열을 선언하여 선택한 파일들의 이름을 저장할 변수를 초기화
            let fileNames = "";

            //선택한 파일들의 목록을 순회하면서 각 파일의 이름을 fileNames 변수에 추가
            for(let i = 0; i < files.length; i++) {
                fileNames += files[i].name + ", ";
            }
            //마지막 콤마 제거; 마지막에 추가된 콤마와 공백 두 글자를 제거하여 파일 이름 간의 구분을 위한 쉼표와 공백을 정리
            fileNames = fileNames.slice(0, -2);

            //<span> 요소에 있는 내용을 모두 비움
            $("#update_file").empty();

            // 새로운 파일명을 표시합니다.
            $("#update_file").html(fileNames);

            // for(let i=0; i<board.size; i++) {
            // 	let fname = $(this)[i].files[i].name;
            // 	$("#update_file").text(fname);
            // }
            //FileReader = 파일 고르는 창;
            //$(this)[0] = 파일 고르는 창이 여러개 있을 수 있으니까
            //첫번째(?), $(this)[0].files[0]= 파일 고르는창에서 파일을 하나 이상 고를 수 있기 때문에 첫번째 파일 이름
        }
    });

});