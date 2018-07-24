<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.BoardBean" %>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
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
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
		    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly'">전체보기</button>
		    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=유머'">유머</button>
		    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=정보'">정보</button>
		    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=게임'">게임</button>
		    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=감동'">감동</button>
		    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=스포츠'">스포츠</button>
		    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=취미'">취미</button>
		    <button type="button" class="btn btn-success" style="width:95%;" id="category" onclick="location.herf='boardList.kly?category=동물'">동물</button>
		    <button type="button" class="btn btn-success" style="width:95%; margin: 5px 5px 80px 5px;" id="category" onclick="location.herf='boardList.kly?category=기타'" >기타</button>
		    <button type="button" class="btn btn-primary" style="width:95%;" id="register" onclick="location='boardWrite.jsp'">게시글 등록</button>
		    
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
		        	<a class="dropdown-item" href="#" onclick="location.href='boardList.kly?array=추천&category=${board.board_category}'">추천 수</a>
		        	<a class="dropdown-item" href="#" onclick="location.href='boardList.kly?array=조회&category=${board.board_category}'">조회 수</a>
		    	</div>
			</div>
		<div>
		
		    <c:forEach var = "board" items ="${boardlist}">
		        <div class="videoplay">
		
		    <!--   <iframe height="409" width="726" frameborder="0" src="https://www.youtube.com/embed/5YTHmBnT3wg?autoplay=1&amp;version=3&amp;hd=1&amp;modestbranding=1&amp;rel=0&amp;showinfo=0&amp;fs=1" webkitallowfullscreen="" mozallowfullscreen="" allowfullscreen=""></iframe> -->
		            <button class="btn" id="btn" onclick="document.getElementById('id01').style.display='block'">${board.BOARD_SUBJECT}<!--<img class="btn-img" src="./images/logo_black.png"> --></button>
		            <div id="id01" class="w3-panel w3-black w3-display-container" style="display:none">
		 	 			<span onclick="this.parentElement.style.display='none'">x</span>
					 	<div style="display:table; width:100%">
						 	<iframe width="1280" height="720" src="https://www.youtube.com/embed/UWw0sR-1dgk" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
						 	<div>
							 	<div>
							 	<c:forEach var = "comment" items = "${commentlist}" >
								 	<button type="button" class="btn btn-success">추천</button>${commnet.comment_like}
								 	<button type="button" class="btn btn-danger">신고</button>${commnet.comment_notlike}
							 	</c:forEach>
							 	</div>
						 	</div>
					 	</div>
		
					</div>
		            <span class="videotitle">${board.board_subject}</span>
		            <span class="writer">${board.member_id}</span>
		            <div>
		                <span class="videoCategory">분류 :${board.board_category}</span>
		                <span class="videoLike">추천:${board.board_likecount}</span>
		                <%-- <span class="videoComment">${board.board_comment}</span> --%>
		                <span class="videoUptime">${board.board_date}</span>
		                <span class="videoReadCount">조회:${board.board_readcount}</span>
		            </div>
		        </div>
		        </c:forEach>
		    </div>
		</div>
	</div>
	    <button type="button" class="btn btn-default btn-sm btn-block">더보기</button>
</body>
</html>


<%-- <table id="table" class="table">
    <thead>
        <tr>
            <th>지역</th>
            <th>콘텐츠명</th>
            <th>콘텐츠 구분</th>
            <th>페이지 뷰</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${conList }" var="resultList" varStatus="status">
        <tr>
            <td>${resultList.area}</td>
            <td>${resultList.name}</td>
            <td>${resultList.gubun}</td>
            <td>${resultList.cnt}</td>
        </tr>
        </c:forEach>
        <tr id='addbtn'><td colspan="5"><div class="btns"><a href="javascript:moreList();" class="btn btn-primary">더보기</a></div></td></tr>
    </tbody>
</table>
<script >
function moreList(){
    $.ajax({
        url : "/admin/jsonlist",
        type : "POST",
        cache : false,
        dataType: 'json',
        data : "conectType="+conectType +"&eDate="+eDate+"&sDate="+sDate+"&codeId="+codeId+"&limit="+limit,
        success : function(data){
            //console.log(data);
            var content="";
            for(var i=0; i<data.hashMapList.length; i++){
                content +=
                "<tr>"+
                    "<td>"+data.hashMapList[i].area+"</td>"+
                    "<td>"+data.hashMapList[i].name+"</td>"+
                    "<td>"+data.hashMapList[i].gubun+"</td>"+
                    "<td>"+data.hashMapList[i].cnt+"</td>"+
                "</tr>";
            }
            content+="<tr id='addbtn'><td colspan='5'><div class='btns'><a href='javascript:moreList();' class='btn'>더보기</a></div>  </td></tr>";
            $('#addbtn').remove();//remove btn
            $(content).appendTo("#table");
        }, error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
           }
    });
};
</script>
 --%>