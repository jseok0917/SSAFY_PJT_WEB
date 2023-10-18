<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- header받아올 예정 -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SSAFY GYM</title>
    <link rel="icon" href="img/favicon.svg">
    <link rel="stylesheet" href="css/style.css">
 
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
  
</head>

<%@ include file="./header.jsp" %>

<body>



    <main>
        <div class="img">
            <div class="content">
                <h1>Watch the best videos for your workout</h1>
                <h2>KILL YOUR FAT</h2>
            </div>
            <div class="img-cover"></div>
        </div>
        

        <section>

            <h2>The Latest videos</h2>

   
            <div  id = "recent-videos" class="row row-cols-1 row-cols-md-3 g-4">

            </div>
            
        </section>

    </main>

<footer>
  <p>HYUNSOO CHO & JIMYEONG SEOK<br>
  <a href="#">ssafy@ssafy.com</a></p>
</footer>

    <script src="js/main.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>