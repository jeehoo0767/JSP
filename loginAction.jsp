<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="User.userDAO" %> 
<%@ page import="java.io.PrintWriter" %> <!-- 자바스크립트문을 작성하기 위해 사용 -->
<% request.setCharacterEncoding("UTF-8"); %> <!-- 모든 데이터를 UTF-8로 받음 -->
<jsp:useBean id="User" class="User.User" scope="page" /> <!-- scope => 현재의 페이지에서만 빈즈 사용 -->
<jsp:setProperty name="User" property="userID" />
<jsp:setProperty name="User" property="userPassword" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판!!</title>
</head>
<body>
	<%
		userDAO UserDAO = new userDAO();
		int result = UserDAO.login(User.getUserID(), User.getUserPassword()); 
		 /* login 페이지에서 ID, Password 입력된 값으로 넘어와서 login함수에 넣어서 실행
		 -2(오류), -1(아이디가 없음), 0(로그인 실패), 1(로그인 성공)이 저장*/
		 
		 String userID = null;
		 if(session.getAttribute("userID") != null)
		 {
			 userID = (String) session.getAttribute("userID");
		 }
		 if(userID != null)
		 {
			 PrintWriter script = response.getWriter();
			 script.println("<script>");
			 script.println("alert('이미 로그인이 되어있습니다.')");
			 script.println("location.href = 'main.jsp'");
			 script.println("</script>"); 
		 }
		 if(result == 1)
		 {
			 session.setAttribute("userID", User.getUserID());
			 PrintWriter script = response.getWriter();
			 script.println("<script>");
			 script.println("location.href = 'main.jsp'");
			 script.println("</script>"); 
		 }
		 
		 else if(result == 0)
		 {
			 PrintWriter script = response.getWriter();
			 script.println("<script>");
			 script.println("alert('비밀번호가 틀립니다.')");
			 script.println("history.back()"); //이전 페이지로 돌려보냄(login 페이지)
			 script.println("</script>");
		 }
		 
		 else if(result == -1)
		 {
			 PrintWriter script = response.getWriter();
			 script.println("<script>");
			 script.println("alert('존재하지 않는 아이디입니다!!')");
			 script.println("history.back()"); //이전 페이지로 돌려보냄(login 페이지)
			 script.println("</script>");
		 }
		 
	%>
</body>
</html>