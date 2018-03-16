<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	텍스트 : ${test }<br>
	<c:forEach var="img" items="${filename }">
		파일이름 : ${img }<br>
		<img src="${img }"><br>
	</c:forEach>
</body>
</html>