<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(function(){
		$("#file").change(function(){
			$("#dropBox").empty();
			
			var reader = new FileReader();
			reader.onload = function(e){
				var imgObj = $("<img>").attr("src", e.target.result);
				$("#dropBox").append(imgObj);
			}
			
			/* 제이쿼리 객체가 하나일 경우 $(this)[0]로 표기하면 자바 스크립트 객체가 됨. */
			// var file = document.getElementById("file");
			// file.files[0] 으로 해도 됨
			
			
			reader.readAsDataURL($(this)[0].files[0]);
		
		})
		
	})
</script>
<style>
	#dropBox{
		width: 300px;
		height: 300px;
		border: 1px solid #ccc;
		overflow: auto;
	}
	
	#dropBox img{
		max-width: 100%;
		max-height: 100%;
	}
</style>
</head>
<body>
	<form id="f1" action="uploadPreview" method="post" enctype="multipart/form-data">
		작성자 이름 : <input type="text" name="writer" placeholder="작성자 이름"><br>
		파일 선택 : <input type="file" name="file" id="file"><br>
		<input type="submit" value="전송">
	</form>
	<div id="dropBox">
		
	</div>
</body>
</html>