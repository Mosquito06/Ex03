<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(function(){
		$(document).on("click", "img", function(){
			var path = $(this).next().attr("href");
			var front = path.substr(0, path.indexOf("s_"));
			var end =  path.substr(path.indexOf("s_") + 2)
			
			
			var originalFile =  front + end;
			alert(originalFile);
			
			$(this).parents("div").append("<img src='displayFile?filename=" + originalFile + "'>");
		})
	})

</script>
<style>
	
</style>
</head>
<body>
	<div>${writer }</div>
	<div>
		<c:forEach var="item" items="${list }">
			<div>
				파일 경로 : ${item } <br>
				<img src="displayFile?filename=${item }">
			</div>
		</c:forEach>
	
	</div>
</body>
</html>