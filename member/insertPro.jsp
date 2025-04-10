<%@page import="javax.naming.*"%>
<%@page import="javax.sql.DataSource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import ="java.sql.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String address = request.getParameter("address");

	//1. DB 연동 드라이버 로드
	//2. 연결 객체 생성
	Context initCtx = new InitialContext();
	DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/guo7404");
	Connection con = ds.getConnection();

	//3. 생성된 연결을 통해 SQL문 실행 의뢰 준비

	//4. SQL 실행
	String sql = "INSERT INTO member (stuID, name, address) VALUES (?, ?, ?)";
	PreparedStatement pstmt = con.prepareStatement(sql);
	pstmt.setString(1, id);
	pstmt.setString(2, name);
	pstmt.setString(3, address);
	pstmt.executeUpdate();

	
	//5. 객체 해제
	pstmt.close();
	con.close();
	//6. list.jsp로 이동	
	response.sendRedirect("list.jsp");
%>

<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- 	<meta charset="UTF-8"> -->
<!-- 	<title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->
<%-- 	<%=i %>행이 입력되었습니다! --%>
<!-- </body> -->
<!-- </html> -->