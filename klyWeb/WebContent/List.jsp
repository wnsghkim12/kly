<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.BoardBean" %>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
    #leftbody{
        padding: 5px;
    }
    #category{
        margin: 5px 5px 20px 5px;
    }
    #register{
        margin: 5px 5px 20px 5px;
    }
</style>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<title> Title </title>
</head>

<body>
<nav class="navbar navbar-expand-md bg-info navbar-dark">
  <a class="navbar-brand" href="#">Navbar</a>
<div class="mr-auto"></div>
<div class="btn-group btn-group-lg">
<button type="button" class="btn btn-outline-dark" >로그인</button>
<button type="button" class="btn btn-outline-dark" >회원가입</button>
</div>
</nav>
<div class="main" style="height: 1080px; width: 1920px">
<div class="bg-dark" id="leftbody" style="height: 1080px; width: 250px;float: left;">
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.href='boardList.kly'">전체보기</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=유머'">유머</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=정보'">정보</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=게임'">게임</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=감동'">감동</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=스포츠'">스포츠</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=취미'">취미</button>
    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=동물'">동물</button>
    <button type="button" class="btn btn-success" style="width:95%; margin: 5px 5px 80px 5px;" id="category" onclick="location.herf='boardList.kly?category=기타'"> >기타</button>
    <button type="button" class="btn btn-primary" style="width:95%;" id="register" onclick="location='boardwrite.kly'">게시글 등록</button>
    <form action="./boradList.kly" method="post">
    <div class="input-group mb-3">
      <input type="text" class="form-control" placeholder="Search">
        <div class="input-group-append">
        <button class="btn btn-success" type="submit">검색</button> 
        </div>
    </div>
    </form>
    </div>
<div class="bg-light" id="leftbody" style="height: 1080px; width: 1670px; float:right">
    <div class="dropdown">
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
          정렬 기준
        </button>
        <div class="dropdown-menu">
          <a class="dropdown-item" href="#">추천 수</a>
          <a class="dropdown-item" href="#">조회 수</a>
    </div>
  </div>
    <div>
    <c:forEach var = "board" items = "${boardlist}" >
        <div class="videoplay">
            <button class="btn" id="btn" type="submit"><img class="btn-img" src=""></button>
            <span class="videotitle"></span>
            <span class="writer"></span>
            <div>
                <span class="videoCategory">${board.board_category}</span>
                <span class="videoLike">${board.board_likecount}</span>
                <span class="videoComment">${board.board_comment}</span>
                <span class="videoUptime">${board.board_date}</span>
                <span class="videoReadCount">${board.board.readcount}</span>
            </div>
        </div>
        </c:forEach>
    </div>
</div>
</div>
    <button type="button" class="btn btn-default btn-sm btn-block">더보기</button>
</body>
</html>
