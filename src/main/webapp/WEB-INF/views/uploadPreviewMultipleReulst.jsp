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
			alert("D");
			var path = $(this).next().attr("href").split("=")[1];
			
			var originalFile = path.substr(0, path.indexOf("s_")) + path.substr(path.indexOf("s_") + 2);
			
			$(this).next().attr("href", "displayFile?filename=" + originalFile);
			var $a = $(this).next();
			
			$a.trigger("click");
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
				<a href="displayFile?filename=${item }">링크</a>
			</div>
		</c:forEach>
	
	</div>
</body>
</html>