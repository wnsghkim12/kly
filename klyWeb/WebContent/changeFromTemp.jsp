<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.MemberBean" %>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
	/* 로그인 사용자만 접속 가능 */
	MemberBean loginInfo = (MemberBean) session.getAttribute("loginInfo");

	if(loginInfo == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인 하셔야 이용하실 수 있는 서비스 입니다.')");
		script.println("location.href='index.jsp'");
		script.println("</script>");
		script.close();
	} else {
		if(loginInfo.getMEMBER_CHECKED()==0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이메일 인증 후 이용해 주세요.')");
			script.println("location.href='emailSendConfirm.jsp'");
			script.println("</script>");
			script.close();
		}
	}
	

%> --%>
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
    <h2 class="h1 ml-4 mt-4">My Page</h2>
    <div class="row mt-4 mb-4">
        
        <!-- 좌측 링크 -->
        <div class="col-md-4 col-lg-2">
            <div class="container">
				<div class="list-group">
                    <a class="list-group-item active" href="myPage.jsp">정보변경</a>
                    <a class="list-group-item" href="myPageList.jsp?order=upload">게시물 관리</a>
					<a class="list-group-item" href="myPageDrop.jsp">회원탈퇴</a>
                </div>
            </div>
        </div>
        
        <div class="col-md-7 col-lg-10">
            <div class="container">
                <h2>비밀번호 변경</h2>
                <form class="form mt-5" action="memberInfoRivision.kly">
                    <div class="form-group">
                       <div class="row mb-3">
                            <div class="col-sm-3" style="text-align: center;">
                                <label><h5>아이디</h5></label>
                            </div>
                            <div class="col-sm-9">
                                <input class="form-control" type="text" readonly="readonly" value="${loginInfo.getMEMBER_ID()}"/>
                           </div>
                        </div>
                        
                        <div class="row mb-3">
                            <div class="col-sm-3" style="text-align: center;">
                                <label><h5>현재 비밀번호</h5></label>
                            </div>
                            <div class="col-sm-9">
                                <input class="form-control" name="currentPassword" type="text" placeholder="현재 비밀번호를 입력해 주세요."/>
                           </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3" style="text-align: center;">
                                <label><h5>바꿀 비밀번호</h5></label>
                            </div>
                            <div class="col-sm-9">
                                <input class="form-control" name="changePassword" type="text" placeholder="바꿀 비밀번호를 입력해 주세요."/>
                           </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3" style="text-align: center;">
                                <label><h5>비밀번호 확인</h5></label>
                            </div>
                            <div class="col-sm-9">
                                <input class="form-control" type="text" placeholder="바꿀 비밀번호를 한번 더 입력해 주세요."/>
                           </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3" style="text-align: center;">
                                <label><h5>이메일</h5></label>
                            </div>
                            <div class="col-sm-9">
                                <input class="form-control" type="text" readonly="readonly" value="${loginInfo.getMEMBER_EMAIL()}"/>
                           </div>
                        </div>
                        
                        <div class="row text-right">
                            <div class="col">
                                <button class="btn btn-primary" type="submit">비밀 번호 변경</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        
        </div>
        
    </div>
    
    <!-- 하단바(footer) -->
    <div class="jumbotron text-center">
        <p>&copy; 2018 kly</p>
    </div>
   
  </body>
</html>
