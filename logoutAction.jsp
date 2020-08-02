<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		session.invalidate();
		//세션 정보 제거
	%>
	
	<script>
		location.href = 'main.jsp';
	</script>
</body>
</html>