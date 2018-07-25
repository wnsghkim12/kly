<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.MemberBean" %>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="./style.css" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <title>kly</title>
  </head>
  <body>
  
    <%@include file="./navbarTemplate.jsp" %>
    
    <!-- 내용 부분 -->
    <div class="row mt-4 mb-4">
		<div class="col-md-4 col-lg-2">
			<div class="container">
				<div class="list-group">
					<a class="list-group-item active" href="boardManagement.jsp">게시물 관리(관리자)</a>
					<a class="list-group-item" href="commentManagement.jsp">댓글 관리(관리자)</a>
					<a class="list-group-item" href="userManagement.jsp">사용자 관리(관리자)</a>
				</div>
			</div>
		</div>
    <div class="container">
	    <table class="table table-hover mt-3">
			  
			<tr>
				<th>사용자 ID</th>
				<th>회원가입 일시</th>
				<th>회원 삭제</th>
				<th>회원 정지</th>
				<th>회원 정지해제</th>
		    </tr>
		    <tr>
				<td>user1</td>
				<td>2018-07-24</td>
				<td><button type="button" class="btn">삭제</button></td>
				<td><button type="button" class="btn">정지</button></td>
				<td><button type="button" class="btn">해제</button></td>
		    </tr>

	    </table>
		    <div style="padding: 10px; text-align:center;">
		    	<button type="button" class="btn btn-primary">이전</button>&nbsp;&nbsp;<button type="button" class="btn btn-primary">다음</button></td>
		    </div>
	    </div>
    </div>

    
    <!-- 하단바(footer) -->
    <div class="jumbotron text-center">
        <p>&copy; 2018 kly</p>
    </div>
   
  </body>
</html>