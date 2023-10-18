<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>헬스장 찾기</title>
    <title>SSAFY GYM</title>
    <link rel="icon" href="img/favicon.svg">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/findGym.css">
 
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>

<%@ include file="./header.jsp"%>
<body>
	<div class="view">가까운 헬스장 목록</div>
	<div class="map_wrap">
	
    <div id="map"></div>

    <div id="menu_wrap" class="bg_white">
        <ul id="placesList"></ul>
        <div id="pagination"></div>
    </div>
</div>
	


	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=706348d9a8a36c6e86f0aeb5d6d4081b&libraries=services"></script>
	<script src="js/search.js"></script>
</body>
</html>