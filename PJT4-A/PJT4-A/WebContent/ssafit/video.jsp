<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SSAFIT Video</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/video.css">
</head>
<body>
	<%@ include file="./header.jsp" %>

	<main class="d-flex">
	<section>
		<div id="video-search">
		<form action="ssafit?act=videoSearch" method="POST">
			<input type="text" name="keyword" placeholder="Search">
			<button>search</button>
		</form>
		</div>
		<div class="d-flex justify-content-start"
			class="p-3 mb-2 bg-light text-dark">
			<ul id="exercise-type" class="d-flex justify-content-around">
				<li>
				<button type="submit" class="btn btn-outline-secondary"><a href="ssafit?act=video">Popular</a></button>
				</li>
				<li><button type="submit" class="btn btn-outline-secondary"><a href="ssafit?act=wholevideo">Whole</a></button></li>
				<li><button type="submit" class="btn btn-outline-secondary"><a href="ssafit?act=uppervideo">Upper</a></button></li>
				<li><button type="submit" class="btn btn-outline-secondary"><a href="ssafit?act=lowervideo">Lower</a></button></li>
				<li><button type="submit" class="btn btn-outline-secondary"><a href="ssafit?act=abrominalvideo">Abromianal</a></button></li>

			</ul>
			<div></div>
		</div>

		<div id="video-area" class="row row-cols-1 row-cols-md-4 g-4"
			class="p-3 mb-2 bg-light text-dark">
			<c:forEach items="${videoList }" var="video">
				<div class="col">
					<div class="card h-100">
						<iframe class="card-img-top" width="450" height="230"
							src="https://www.youtube.com/embed/${video.youtubeId}"
							title="YouTube video player" frameborder="0"
							allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
							allowfullscreen></iframe>
						<div class="card-body">
							<h5 class="card-title">${video.channelName}</h5>
							<a href="ssafit?act=detailVideo&videoId=${video.youtubeId }">
								<p class="card-text">${video.title}</p>
							</a>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>

	</section>



	<aside>
		<div>
			<span><h3>&nbsp;&nbsp;Liked Video</h3></span>
			<div id="liked-video" class="d-flex flex-column"
				class="d-flex flex-wrap"></div>
		</div>
	</aside>

	</main>


	<footer>
		<p>
			JIMYEONG SEOK & DONGHUK LEE & LAINLNYA<br> <a href="#">ssafy@ssafy.com</a>
		</p>
	</footer>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>