<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>케이마켓 고객센터</title>
    <link rel="shortcut icon" type="image/x-icon" href="${ctxPath}/img/favicon.ico" />
    <link rel="stylesheet" href="${ctxPath}/cs/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  </head>
  <body>
    <div id="wrapper">
      <header>
        <div class="top">
          <div>
            <p>
            	<c:if test="${sessUser eq null}">
	              <a href="${ctxPath}/member/login.do">로그인</a>
	              <a href="${ctxPath}/member/join.do">회원가입</a>
            	</c:if>
            	<c:if test="${sessUser.type eq 1}">
                    <a href="#">회원 ${sessUser.uid}</a>
                </c:if>
                <c:if test="${sessUser.type eq 2}">
                    <a href="#">판매자 ${sessUser.uid}</a>
                </c:if>
                <c:if test="${sessUser.type eq 9}">
                    <a href="#">관리자 ${sessUser.uid}</a>
                </c:if>
                <c:if test="${sessUser.type eq 2 || sessUser.type eq 9}">
                    <a href="${ctxPath}/admin/index.do">관리페이지</a>
                </c:if>
            	<c:if test="${sessUser ne null}">
	              <a href="#">마이페이지</a>
	              <a href="${ctxPath}/member/logout.do">로그아웃</a>
                  <a href="${ctxPath}/product/cart.do"><i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;장바구니</a>
            	</c:if>
            </p>
          </div>
        </div>
        <div class="logo">
          <div>
            <a href="${ctxPath}/cs/"><img src="${ctxPath}/cs/images/logo.png" alt="로고" />고객센터</a>
          </div>
        </div>
      </header>
      <section id="cs">