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
			
			var file = document.getElementById("file");
			$(file.files).each(function(i, file){
				var reader = new FileReader();
				reader.onload = function(e){
					var imgObj = $("<img>").attr("src", e.target.result);
					$("#dropBox").append(imgObj);
				}
				
				reader.readAsDataURL(file);
			})			
			// reader.readAsDataURL($(this)[0].files[0]);
		
		})
		
	})
</script>
</head>
<body>
	<form id="f1" action="uploadPreviewMultiple" method="post" enctype="multipart/form-data">
		작성자 이름 : <input type="text" name="writer" placeholder="작성자 이름"><br>
		파일 선택 : <input type="file" name="files" id="file" multiple="multiple"><br>
		<input type="submit" value="전송">
	</form>
	<div id="dropBox">
		
	</div>
</body>
</html>