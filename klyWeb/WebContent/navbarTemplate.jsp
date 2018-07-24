<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<!-- 상단 고정바(navbar) -->
<%

%>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- Brand/logo -->
        <a href="./index.jsp"><img src="./images/logo.png" style="height: 45px" /></a>

        <!-- Links -->
        <ul class="navbar-nav mr-auto">
            
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
        	    <button class="btn btn-primary" onclick="location.href='./memberDetail.kly'">
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
    
</body>
</html>