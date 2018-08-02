<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.BoardBean"%>
<%@ page import="bean.CommentBean"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<style>
#leftbody {
	padding: 5px;
}

#category {
	margin: 5px 5px 20px 5px;
}

#register {
	margin: 5px 5px 20px 5px;
}

#card {
	float: left;
	width: 260px;
	height: 240px;
	margin: 20px;
}

.view {
	float: left;
}

.card.active {
	display: block;
}

.is_comp.card:after {
	display: none;
}

.btn btn-default btn-sm btn-block, .main, .video {
	display: block;
}
</style>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	/* function loadNextMore() {
		var more = $('#more').val();
		more = parseInt(more);
		more += 4;
		$.ajax({
			type : 'post',
			url : 'moreList.kly',
			data : ({
				more : more
			}),
			success : function(data) {

			}
		})
	} */
	/* $(window).on('moreList', function() {
		load('#vedio', '4');
		$("#btn .btn btn-default btn-sm btn-block").on("click", function() {
			moreList('#vedio', '4', '#btn');
		})
	}); */

	function more() {
		var req = new XMLHttpRequest();
		req.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200){
				var arr = JSON.parse(this.responseText);
				
				document.getElementById("test").innerHTML = arr.name;
			}
		}
		req.open("GET", "test.kly", true);
		req.send();
	}
</script>
<title>Title</title>
</head>

<body>
	<!-- 상단바(footer) -->
	<%@include file="./navbarTemplate.jsp"%>
	<!--리스트 페이지의 몸통부분 -->
	<div class="main" style="height: 1080px; width: 1920px">
		<!--몸통부분의 왼쪽 부분으로 동영상을 분류 별로 선택해서 보는 기능 으로 
	category 값을 BoardCategoryAction 으로 넘김  -->
		<div class="bg-light" id="leftbody"
			style="height: 1080px; width: 250px; float: left;">
			<button type="button" class="btn btn-outline-info"
				style="width: 95%;" id="category"
				onclick="location='boardList.kly?'">전체보기</button>
			<button type="button" class="btn btn-outline-info"
				style="width: 95%;" id="category"
				onclick="location='boardCategory.kly?category=유머'">유머</button>
			<button type="button" class="btn btn-outline-info"
				style="width: 95%;" id="category"
				onclick="location='boardCategory.kly?category=정보'">정보</button>
			<button type="button" class="btn btn-outline-info"
				style="width: 95%;" id="category"
				onclick="location='boardCategory.kly?category=게임'">게임</button>
			<button type="button" class="btn btn-outline-info"
				style="width: 95%;" id="category"
				onclick="location='boardCategory.kly?category=감동'">감동</button>
			<button type="button" class="btn btn-outline-info"
				style="width: 95%;" id="category"
				onclick="location='boardCategory.kly?category=스포츠'">스포츠</button>
			<button type="button" class="btn btn-outline-info"
				style="width: 95%;" id="category"
				onclick="location='boardCategory.kly?category=취미'">취미</button>
			<button type="button" class="btn btn-outline-info"
				style="width: 95%;" id="category"
				onclick="location='boardCategory.kly?category=동물'">동물</button>
			<button type="button" class="btn btn-outline-info"
				style="width: 95%; margin: 5px 5px 80px 5px;" id="category"
				onclick="location='boardCategory.kly?category=기타'">기타</button>
			<button type="button" class="btn btn-outline-info"
				style="width: 95%;" id="register"
				onclick="location='boardWrite.jsp'">게시글 등록</button>
			<!-- 검색기능  태그나 게시글 제목을 입력해 검색할 수 있음-->
			<form action="./boardSearch.kly" method="post">
				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="Search"
						name="search">
					<div class="input-group-append">
						<button class="btn btn-success" type="submit">검색</button>
					</div>
				</div>
			</form>
		</div>
		<!-- 조회 수,추천 수 중 정렬기준을 정하는 기능 array와 category 값을 같이넘겨서 카테고리 내에서도 정렬 기준을 선택 할 수 있음(전체목록 정렬이 안됨)-->
		<div class="main" id="rightbody"
			style="height: 1080px; width: 1670px; float: right">
			<div class="dropdown">
				<button type="button"
					class="btn btn-outline-primary dropdown-toggle"
					data-toggle="dropdown">정렬 기준</button>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="#"
						onclick="location='boardArray.kly?array=추천&category=${category.BOARD_CATEGORY}'">추천
						수</a> <a class="dropdown-item" href="#"
						onclick="location='boardArray.kly?array=조회&category=${category.BOARD_CATEGORY}'">조회
						수</a>
				</div>
			</div>
			<!-- 간단한 정보만 출력하고 버튼 클릭시 영상과 함께 댓글을 볼 수 도있고 달 수 도있는 기능 
				display:none 으로 인해 보이지 않는 페이지가 버튼을 클릭하면 block으로 바뀌어서 볼수 있음 -->
			<c:forEach var="board" items="${boardlist}" varStatus="status">
				<div id="id0${status.count}" class="panel black display-container"
					style="display: none;">
					<span onclick="this.parentElement.style.display='none'"><i
						class="fa fa-close btn-light"></i></span>
					<div>
						<div class="view" style="width: 40%">
							<div class="list-group" style="float: right">
								<div class="rounded-top"
									style="border: 1.5px solid; height: 32px; width: 420px;">
									<span style="font-size: 25px;">&nbsp;${board.BOARD_SUBJECT}</span>
									<span style="color: skyblue">&nbsp;&nbsp;${board.MEMBER_ID}</span>
								</div>
								<div>${board.BOARD_VIDEO_URL}</div>
							</div>
						</div>
						<div class="view"
							style="position: relative; width: 58%; height: 360px">
							<div class="rounded"
								style="width: 400px; height: 347px; border: 1.5px solid">
								<div style="border-bottom: 1.5px solid">
									<!--하나의 게시물에 계정당 한번의 추천 , 신고를 할 수 있음 자신의 게시물만 삭제 할 수 있음 
		 								게시번호 값과 로그인한 계정의 id 값을 넘겨 비교해 이미 추천,신고를 한번 했으면 더이상 못함
		 								삭제도 마찬가지로 작성자 id와 로그인중인 계정을 비교해 다르면 권한이 없다며 삭제되지 않음-->
									<div>
										&nbsp;<img class="btn-img" src="./images/like.png"
											onclick="location='boardLike.kly?board_num=${board.BOARD_NUM}&member_id=${loginInfo.MEMBER_ID}'">
										<span style="color: blue">&nbsp;${board.BOARD_LIKECOUNT}&nbsp;</span>
										<div class="btn-group"
											style="text-align: right; width: 225px;">
											&nbsp;&nbsp;<img class="btn-img" src="./images/report.jpg"
												onclick="location='boardReport.kly?board_num=${board.BOARD_NUM}&member_id=${loginInfo.MEMBER_ID}'">
										</div>
										<span style="color:cadetblue;"><img src="./images/tag.png">#${board.BOARD_TAG }</span>
										&nbsp;<img class="btn-img"
												src="./images/delete.png"
												onclick="location='boardDelete.kly?board_num=${board.BOARD_NUM}&member_id=${loginInfo.MEMBER_ID}'">
									</div>
								</div>
								<div>
									<!-- if문으로 댓글이 가진 게시번호 값과 현재 보고있는 게시번호 값이 일치하는 댓글들을 출력 -->
									<div>
										<c:forEach var="comment" items="${commentlist}">
											<c:if test="${comment.BOARD_NUM == board.BOARD_NUM}">
												<div>${comment.MEMBER_ID}:${comment.COMMENT_CON}</div>
											</c:if>
										</c:forEach>
									</div>
									<div style="vertical-align: bottom;">
										<!-- 댓글을 등록하는 부분 -->
										<form
											action="./boardComment.kly?board_num=${board.BOARD_NUM}&member_id=${loginInfo.MEMBER_ID}"
											method="post">
											<div class="input-group mb-3"
												style="width: 396px; position: absolute; bottom: 0px; margin-left: 1px">
												<input type="text" class="form-control" name="comment">
												<div class="input-group-append">
												
													<button class="btn btn-success" type="submit">등록</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			<c:set var="i" value="0" />
			<c:set var="j" value="4" />
			<table id="video">
				<!--card클래스가 들어가는 테이블로 card가 4개가되면 다음줄로 넘기는 것을 반복 한다.-->
				<c:forEach var="board" items="${boardlist}" varStatus="status">
					<c:if test="${i%j == 0 }">
						<tr>
					</c:if>
					<td>
						<!-- 게시물 목록을 카드형식으로 보여준다. 클릭하면 숨겨진 패널(동영상,댓글)을 볼수 있다.  -->
						<div class="card" id="card" style="height: 220px">
							<div class="card-body text-center">
								<div class="videoplay">
									<img class="btn-img" src="./images/Lighthouse.jpg"
										style="width: 200px; height: 120px;"
										onclick="document.getElementById('id0${status.count}').style.display='block'">
									<div>
										<span class="writer" style="color: blueviolet;">${board.MEMBER_ID}</span>
										<span class="videotitle">${board.BOARD_SUBJECT}</span>
									</div>
									<div>
										<span class="videoCategory" style="border:1px solid lightpink; color:lightpink">분류:${board.BOARD_CATEGORY}</span>
										<span class="videoLike" style="color: blue"><img
											src="./images/like.png">${board.BOARD_LIKECOUNT}</span> <span
											class="videoReadCount"><img src="./images/view.jpg">${board.BOARD_READCOUNT}</span>
									</div>
									<div>
									<div id=test></div>
										<span class="videoUptime">${board.BOARD_DATE}</span>
										<span class="videoTag" style="color:cadetblue"><img src="./images/tag.png">#${board.BOARD_TAG}</span>
									</div>
								</div>
							</div>
						</div>
					</td>
					<c:if test="${i%j == j-1 }">
						</tr>
					</c:if>
					<c:set var="i" value="${i+1 }" />
				</c:forEach>
			</table>
		</div>
	</div>
	<!-- List페이지의 동영상게시 갯수를 누를때 마다 늘린다. 미구현  -->
	<div class="js-btn" id="btn">
		<%-- <input type="hidden" id="more" name="more" value="${more}"> --%>
		<button type="button" class="btn btn-default btn-sm btn-block"
			onclick="more()" id="more">더보기</button>
	</div>
</body>
</html>