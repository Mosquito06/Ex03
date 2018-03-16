<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	텍스트 : ${test }<br>
	파일이름 : ${filename }<br>
	<img src="displayFile?filename=${filename} ">
</body>
</html>