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
  
	<!-- 상단바(footer) -->
    <%@include file="./navbarTemplate.jsp" %>

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
