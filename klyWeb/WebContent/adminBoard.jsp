<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.MemberBean" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import ="bean.BoardBean"%>
<%@ page import ="bean.PageInfo"%>
<%@ page import ="java.util.*" %>

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
					<a class="list-group-item active" href="BoardSuspendList.kly">게시물 관리(관리자)</a>
					<a class="list-group-item" href="adminComment.kly">댓글 관리(관리자)</a>
					<a class="list-group-item" href="adminMember.jsp">사용자 관리(관리자)</a>
				</div>
			</div>
		</div>
		
		<div class="col-md-7 col-lg-10">
			<table class="table table-hover">
				
				<%ArrayList<BoardBean> boardList = (ArrayList<BoardBean>)request.getAttribute("boardList");
					//페이징 정보 가져오기
					PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
					int nowPage = pageInfo.getPage();
					int startPage = pageInfo.getStartPage();
					int endPage = pageInfo.getEndPage();
					int maxPage = pageInfo.getMaxPage();
					int listCount = pageInfo.getListCount();
					
				%>
				
				<%for(int i=0;i<boardList.size();i++){ %>
				
				
				
				
				<tr>
					<td rowspan="3" style="width: 15%;"><img src="./images/Lighthouse.jpg" class="img-thumbnail" alt="thumbnail" width="210"></td>
					<td style="width: auto;">제목 &nbsp;<%=boardList.get(i).getBOARD_SUBJECT()%></td>
					<td rowspan="3" style="width: 10%; vertical-align: middle"><button type="button" class="btn">삭제</button>&nbsp;&nbsp;<button type="button" class="btn">해제</button></td>
				</tr>
			<%-- 	<tr>
					<td style="width: auto;">신고 수 &nbsp;<%=boardList.get(i).getBOARD_REPORT_NUM()%></td>
				</tr>
				<tr>
					<td style="width: auto;">신고 시간 &nbsp;<%=boardList.get(i).getBOARD_REPORT_DATE()%></td>
				</tr> --%>
			
				<tr>
					<td rowspan="3" style="width: 15%;"><img src="./images/Lighthouse.jpg" class="img-thumbnail" alt="thumbnail" width="210"></td>
					<td style="width: auto;">제목 &nbsp;subject</td>
					<td rowspan="3" style="width: 10%; vertical-align: middle"><button type="button" class="btn">삭제</button>&nbsp;&nbsp;<button type="button" class="btn">해제</button></td>
				</tr>
				<tr>
					<td style="width: auto;">신고 수 &nbsp;report Num</td>
				</tr>
				<tr>
					<td style="width: auto;">신고 시간 &nbsp;report time</td>
				</tr>
			
				<tr>
					<td rowspan="3" style="width: 15%;"><img src="./images/Lighthouse.jpg" class="img-thumbnail" alt="thumbnail" width="210"></td>
					<td style="width: auto;">제목 &nbsp;subject</td>
					<td rowspan="3" style="width: 10%; vertical-align: middle"><button type="button" class="btn">삭제</button>&nbsp;&nbsp;<button type="button" class="btn">해제</button></td>
				</tr>
				<tr>
					<td style="width: auto;">신고 수 &nbsp;report Num</td>
				</tr>
				<tr>
					<td style="width: auto;">신고 시간 &nbsp;report time</td>
				</tr>
			 <%} %>
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