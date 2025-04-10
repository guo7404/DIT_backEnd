<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<%

	Context initCtx = new InitialContext();
	DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/guo7404");
	Connection con = ds.getConnection();

	String sql = "select * from member";
	
	PreparedStatement pstmt = con.prepareStatement(sql);
	ResultSet rs = pstmt.executeQuery();
	
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>사용자 목록</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container"><br>	
	<h1 class="text-center font-weight-bold">사용자 정보</h1>
	<br>
	<table class="table table-hover">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>주소</th>
		</tr>
<% 
	//5. 결과집합 처리 
	while(rs.next()){
	String id = rs.getString("stuID");
	String name = rs.getString("name");
	String address = rs.getString("address");
	
%>	
		<tr>
			<td><a href="updateForm.jsp?id="><%=id %></a></td>
			<td><%=name %></td>
			<td><%=address %></td>
		</tr>
<%}
	//6. 연결 해제
	rs.close();
	pstmt.close();
	con.close();
%>
	</table>
	</div>	
</body>
</html>