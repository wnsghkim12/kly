<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <h2 class="h1 ml-4 mt-4">My Page</h2>
    <div class="row mt-4 mb-4">
        
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
                                <input class="form-control" type="text" value="userId"/>
                           </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3" style="text-align: center;">
                                <label><h5>바꿀 비밀번호</h5></label>
                            </div>
                            <div class="col-sm-9">
                                <input class="form-control" type="text" value="userId"/>
                           </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3" style="text-align: center;">
                                <label><h5>비밀번호 확인</h5></label>
                            </div>
                            <div class="col-sm-9">
                                <input class="form-control" type="text" value="userId"/>
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
