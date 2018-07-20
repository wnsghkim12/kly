<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="./style.css" type="text/css">
    
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    
    <script src="./script.js"></script>
    <title>kly</title>
  </head>
  <body>

    <!-- 상단 고정바(navbar) -->
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- Brand/logo -->
        <a href="./index.jsp"><img src="./images/logo.png" style="height: 45px" /></a>

        <!-- Links -->
        <ul class="navbar-nav mr-auto">
            <li class="form-control">
            
            </li>
        </ul>
        
        
        <c:choose>
        	<c:when test="${empty loginInfo.getMEMBER_ID()}">
        		<button class="btn btn-primary" data-toggle="modal" data-target="#loginForm">
					로그인
				</button>
        		<button class="btn btn-primary ml-2" data-toggle="modal" data-target="#joinForm">
					회원가입
        		</button>
        			
        	</c:when>

        	<c:otherwise>
        		${loginInfo.getMEMBER_ID()}
        	    <button class="btn btn-primary" onclick="location.href='./myPage.html'">
        	    	마이페이지
        	    </button>
        	    <button class="btn btn-danger" onclick="location.href='./memberLogout.kly'">
        	    	로그아웃
        	    </button>
        	</c:otherwise>
        </c:choose>

        <!-- 로그인(modal) -->
        <div class="modal" id="loginForm">
            <div class="modal-dialog">
                <div class="modal-content">
                    
                       <div class="modal-header">
                        <h4 class="modal-title">로그인</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    
                    <form action="memberLogin.kly" method="post">
                        <div class="form-group">
                            <div class="modal-body">
                                    <div class="container">
                                       <a href="./images/index.jsp"><img src="./images/logo.png" style="display:block; height: 45px" /></a>
                                    </div>
                                    
                                    <label><h5>아이디</h5></label>
                                    <input class="form-control" name="loginId" type="text" id="id" />
                                    <label><h5>비밀번호</h5></label>
                                    <input class="form-control" name="loginPwd" type="password" id="pwd" />
                                    <a href="#"><u>혹시 비밀번호를 잊어버리셨나요?</u></a>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary" type="submit">로그인</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        <!-- 회원가입(modal) -->
        <div class="modal" id="joinForm">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">회원가입</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <form action="memberJoin.kly" method="post">
                        <div class="form-group">
                            <div class="modal-body">
                                <label><h5>아이디</h5></label>
                                <input class="form-control" name="MEMBER_ID" type="text" id="id" />
                                   
                                <div class="row mt-2 mb-2">
                                   <div class="col-sm">
                                       <label><h5>비밀번호</h5></label>
                                        <input class="form-control" name="MEMBER_PW" type="password" id="pwd" />
                                    </div>
                                    <div class="col-sm">
                                        <label><h5>비밀번호 확인</h5></label>
                                        <input class="form-control" type="password" id="pwd" />
                                    </div>
                                </div>
                                
                                <label><h5>이메일</h5></label>
                                <input class="form-control" name="MEMBER_EMAIL" type="email" id="id" />
                                
                                    
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary" type="submit">가입</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </nav>

	<!-- 내용 부분 -->
	<div class="container">
        <img class="mx-auto d-block" src="./images/logo_black.png" style="height: 300px"/>
        
        <!-- 검색메뉴 -->
        <form class="form-inline flex-column" action="index.html" method="post">
            <div class="form-group">
                <div class="row">
                    <div class="col-sm-9">     
                        <input type="text" class="form-control" id="search">
                    </div>
                    <div class="col-sm-3">
                        <button type="submit" class="btn btn-success">검색</button>               
                    </div>
                </div>
            </div>
        </form>
        
        <table class="table table-hover mt-3">
            <tr>
                <td colspan="4" style="text-align: center;">
                    <button class="btn btn-info" type="button" onclick="location.href='./list.html'">전체보기</button>
                </td>
                <td>
                    <select>
                        <option>추천순</option>
                        <option>조회순</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>번호</th>
                <th colspan="2">제목</th>
                <th>좋아요</th>
                <th>조회수</th>
            </tr>
            <tr>
                <td>1</td>
                <td>img</td>
                <td>subject</td>
                <td>12</td>
                <td>2</td>
            </tr>
            <tr>
                <td>2</td>
                <td>img</td>
                <td>subject</td>
                <td>12</td>
                <td>2</td>
            </tr>
            <tr>
                <td>3</td>
                <td>img</td>
                <td>subject</td>
                <td>12</td>
                <td>2</td>
            </tr>
            
        </table>
    </div>
    
    <!-- 하단바(footer) -->
    <div class="jumbotron text-center">
        <p>&copy; 2018 kly</p>
    </div>
    
  </body>
</html>
