<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SSAFIT Login</title>
    <link rel="icon" href="img/favicon.svg">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="css/signin.css">
</head>
<body>
    <%@ include file="./header.jsp" %>

    <main class = "d-flex flex-column">

        
        <div class="login-title">
            <span>
                <a class="navbar-brand" href="ssafit?act=main">
                    <img src="img/logo.png" alt="Bootstrap" width="280" height="160">
                </a>
                <h1>SSAFIT GYM</h1>
            </span>

        </div>
       
        <section class = "d-flex flex-column">
        <div class="login-form">
            <h2>LOGIN</h2>
            <form method="POST" action="ìë²ìurl" id="login-form">
                <input type="text" name="userName" placeholder="ID">
                <br>
                <input type="password" name="userPassword" placeholder="Password">
                <br>
                <input type="submit" value="Login">
            </form>
            <a href="ssafit?act=signup">Forget your ID?</a>

        </div>
        <div id="findIdPw" class="d-flex justify-content-around">

            <div><a target="_blank" href="" class="find_text">Find password</a></div>
            <div><a target="_blank" href="" class="find_text">Find ID</a></div>

        </div>
        </section>
        


    </main>
    <footer>
        <p>HYUNSOO CHO & JIMYEONG SEOK<br>
        <a href="#">ssafy@ssafy.com</a></p>
    </footer>


    
</body>
</html>