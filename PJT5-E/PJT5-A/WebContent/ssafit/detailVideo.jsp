<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SSAFY GYM</title>
<link rel="icon" href="img/favicon.svg">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<style>
    table {
        width: 100%;
        border-collapse: collapse; 
        border: 1px solid #ddd;
        margin-bottom: 20px;
    }

    th {
        background-color: #f2f2f2;
        text-align: left;
        padding: 8px;
    }
    tr {
        border-bottom: 1px solid #ddd;
    }

    tr:nth-child(odd) {
        background-color: #f9f9f9;
    }

    td {
        padding: 8px; 
    }
</style>
</head>
<body>

	<%@ include file="./header.jsp"%>



	<main id="video-detail">

	<section>
		<div id="detail1">

			<div class="card-body">
				<h5 class="card-title">${video.title}</h5>
				<iframe class="card-img-top" width="700" height="800"
					src="https://www.youtube.com/embed/${video.youtubeId}"
					title="YouTube video player" frameborder="0"
					allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
					allowfullscreen></iframe>
				<p class="card-text">${video.channelName}</p>
			</div>

		</div>
	</section>
	<div id="review-box">
		<h2>REVIEWS</h2>


		<form class="row gx-3 gy-2 align-items-end" id="reviewForm"
			action="ssafit" method="POST">
			<input type="hidden" name="act" value="writeReview"> <input
				type="hidden" name="youtubeId" value="${video.youtubeId}">
			<div class="col-sm-3">
				<label class="visually-hidden" for="specificSizeInputGroupUsername">Username</label>
				<div class="input-group">
					<div class="input-group-text">@</div>
					<input type="text" class="form-control"
						id="specificSizeInputGroupUsername" name="writer"
						placeholder="Writer">
				</div>
			</div>
			<div class="col-sm-6">
				<label for="floatingInputValue"></label> <input type="text"
					class="form-control" id="floatingInputValue" name="content"
					placeholder="write your review" />
			</div>
			<div class="col-auto"></div>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary">comment</button>
			</div>
		</form>
		<hr>


		<div id="reviews">
			<h2>리뷰 목록</h2>
			<hr>
			<table>
				<tr>
					<th>글쓴이</th>
					<th>내용</th>
					<th>작성일</th>
					<th></th>
				</tr>

				<c:forEach items="${reviewList}" var="review">
					<tr>
						<td>${review.writer }</td>
						<td>${review.content }</td>
						<td>${review.regDate }</td>
						<td>
						<!-- onclick으로 전달하는 매개변수가 문자열이면 작은따옴표로 묶어줘야한다. -->
							<button onclick="editReview(${review.id}, '${review.content}', '${review.youtubeId}')">수정</button>
							<button>
							<a href="ssafit?act=deleteReview&reviewId=${review.id}&youtubeId=${review.youtubeId}">삭제</a>
							</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- 리뷰상세페이지 대신 해당 페이지내에서 리뷰수정이 가능하도록 -->
		<div id="editForm" style="display: none;">
			<form action="ssafit?act=editReview" method="POST">
				<input type="hidden" name="youtubeId" id="editYoutubeId">
				<input type="hidden" name="reviewId" id="editReviewId">
				<textarea name="editedContent" id="editedContent"></textarea>
				<button type="submit">저장</button>
			</form>
		</div>

	</div>


	</main>

	<footer>
		<p>
			JIMYEONG SEOK & DONGHUK LEE & LAINLNYA<br> <a href="#">ssafy@ssafy.com</a>
		</p>
	</footer>

<!-- 자바스크립트 파트 -->
	<script>
	/* content와 reviewId를 button의 onclick속성에 저장해놓음으로써 구현가능 */
    function editReview(reviewId, content, youtubeId) {
        // 리뷰 수정 폼을 보이도록 설정
        document.getElementById("editForm").style.display = "block";

        // 선택한 리뷰의 내용을 수정 폼에 설정
        document.getElementById("editedContent").value = content;

        // 수정할 리뷰의 Id를 폼에 설정
        document.getElementById("editReviewId").value = reviewId;
        // 수정할 리뷰의 youtubeId를 폼에 설정
        document.getElementById("editYoutubeId").value = youtubeId;
    }
</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>