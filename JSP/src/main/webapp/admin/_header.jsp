<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>케이마켓::관리자</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>    
    <script src="${ctxPath}/admin/js/gnb.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${ctxPath}/admin/css/admin.css">
</head>
<body>
    <div id="admin-wrapper">
        <header>
            <div>
                <a href="${ctxPath}/admin/index.do" class="logo"><img src="${ctxPath}/admin/img/admin_logo.png" alt="admin_logo"/></a>
                <p>
                
	            	<c:if test="${sessUser eq null}">
                    	<a href="${ctxPath}">HOME |</a>
		              <a href="${ctxPath}/member/login.do">로그인 |</a>
		              <a href="${ctxPath}/member/join.do">회원가입</a>
	            	</c:if>
	            	<c:if test="${sessUser ne null}">
                   	 <span>${sessUser.name}님 반갑습니다.</span>
                   	 <a href="${ctxPath}">HOME |</a>
		              <a href="${ctxPath}/member/logout.do">로그아웃 |</a>
                      <a href="${ctxPath}/cs/">고객센터</a>
	            	</c:if>
                </p>
            </div>
        </header>