<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">    
    <title>케이마켓::대한민국 1등 온라인 쇼핑몰</title>    
    <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico" />
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
    <link rel="stylesheet" href="${ctxPath}/css/common.css"/>
    <link rel="stylesheet" href="${ctxPath}/member/css/member.css"/> <!-- /member 경로 넣어줘야지!!! -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <script>
    	const success = ${success}; // 파라미터를 이렇게 받을 수도 있네
    	
    	if(success == 100){ 
    		alert('로그인에 실패했습니다. 아이디, 비번을 다시 확인하시기 바랍니다.'); // LoginController에서 login.do?success=100 리다이렉트할 때 이 success 값을 변수로 지정하고 if문 진행
    	}else if(success == 101){
    		alert('로그인을 먼저 하셔야 합니다.');
    	}
    </script>
</head>
<body>
    <div id="wrapper">
        <header>
            <div class="top">
                <div>
                    <a href="${ctxPath}/member/login.do">로그인</a>
                    <a href="${ctxPath}/member/join.do">회원가입</a>
                    <a href="#">마이페이지</a>
                    <a href="${ctxPath}/member/login.do?success=101"><i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;장바구니</a> <!-- 로그인페이지에서는 장바구니 들어갈 수 없으니까 로그인페이지 다시 띄우면서 login.do?success=101로 script 처리-->
                </div>
            </div>
            <div class="logo">
                <div>
                    <a href="${ctxPath}/index.do"><img src="../img/header_logo.png" alt="로고"/></a>
                </div>
            </div>
        </header>
       