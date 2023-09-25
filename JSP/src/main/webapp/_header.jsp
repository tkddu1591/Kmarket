<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>

<html lang="en">
    <head>

        <meta charset="UTF-8"/>
        <title>케이마켓::대한민국 1등 온라인 쇼핑몰</title>
        <link rel="shortcut icon" type="image/x-icon" href="${ctxPath}/img/favicon.ico"/>
        <link rel="stylesheet"
              href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"/>
        <link rel="stylesheet" href="${ctxPath}/css/common.css"/>
        <link rel="stylesheet" href="${ctxPath}/product/css/product.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
        <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

        <script src="${ctxPath}/js/index.do"></script>
        <style>
            #bannerTop {
                display: none;
                width: 100%;
                height: 80px;
                border-bottom: 1px solid #e9e9e9;
                box-sizing: border-box;
            }

            #bannerTop.on {
                display: block;
            }

            #bannerTop > article {
                position: relative;
                width: 1200px;
                height: 100%;
                margin: 0 auto;
                overflow: hidden;
            }

            #bannerTop > article > .btnClose {
                position: absolute;
                right: 0px;
                top: 0px;
                width: 40px;
                height: 40px;
                background-image: url('${ctxPath}/img/ico_sprite.png');
                background-position: -122px -142px;
                background-color: transparent;
                font-size: 0;
                border: none;
                cursor: pointer;
            }

        </style>
        <script>
            $(function () {

                //탑 배너
                $('#bannerTop .btnClose').closest('#bannerTop').addClass('on');
                $('#bannerTop .btnClose').click(function () {
                    $(this).closest('#bannerTop').removeClass('on');
                });

                //인덱스 슬라이더
                const sliders = $('.slider>ul>li');

                let i = 0;
                setInterval(function () {
                    $(sliders.eq(i)).animate({
                        'left': '-100%'
                    }, 5000)
                    i++
                    if (i == 3)
                        i = 0;
                    $(sliders.eq(i)).css('left', '100%').animate({
                        'left': '0%'
                    }, 4900)


                }, 10000)

            });

        </script>
    </head>
    <body>
        <div id="bannerTop" class="" style="background: #e4dfdf;">
            <article>
                <a href="#"><img src="${ctxPath}/img/topBanner1.png"/></a>
                <button class="btnClose">close</button>
            </article>
        </div>
        <div id="wrapper">
            <header>
                <div class="top">
                    <div>
                        <c:if test="${sessUser.type eq 1}">
                            <a href="#">회원 ${sessUser.uid}</a>
                        </c:if>
                        <c:if test="${sessUser.type eq 2}">
                            <a href="#">판매자 ${sessUser.uid}</a>
                        </c:if>
                        <c:if test="${sessUser.type eq 9}">
                            <a href="#">관리자 ${sessUser.uid}</a>
                        </c:if>
                        <c:if test="${sessUser.uid eq null}">
                            <a href="${ctxPath}/member/login.do">로그인</a>
                            <a href="${ctxPath}/member/join.do">회원가입</a>
                        </c:if>
                        <c:if test="${sessUser.uid ne null}">
                            <a href="${ctxPath}/member/logout.do">로그아웃</a>
                        </c:if>
                        <c:if test="${sessUser.type eq 2 || sessUser.type eq 9}">
                            <a href="${ctxPath}/admin/index.do">관리페이지</a>
                        </c:if>
                        <c:if test="${not empty sessUser}">
                            <a href="#">마이페이지</a>
                            <a href="${ctxPath}/product/cart.do"><i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;장바구니</a>
                        </c:if>
                    </div>
                </div>
                <div class="logo">
                    <div>
                        <a href="${ctxPath}"><img src="${ctxPath}/img/header_logo.png" alt="로고"/></a>
                        <form action="${ctxPath}/product/list.do?">
                            <input type="text" name="search" value="${search}" minlength="2"/>
                            <button><i class="fa fa-search"></i></button>
                        </form>
                    </div>

                </div>
                <style>
                    .menuOn > a{
                        font-weight: bold;
                    }
                </style>
                <div class="menu">
                    <div>
                        <ul>
                            <li class="${cate1 eq '0' && condition eq '1'  ? 'menuOn' :''}" > <a href="${ctxPath}/product/list.do?condition=1">히트상품</a></li>
                            <li class="${cate1 eq '0' && condition eq '31' ? 'menuOn' :''}" > <a href="${ctxPath}/product/list.do?condition=31">추천상품</a></li>
                            <li class="${cate1 eq '0' && condition eq '51' ? 'menuOn' :''}" > <a href="${ctxPath}/product/list.do?condition=51">최신상품</a></li>
                            <li class="${cate1 eq '0' && condition eq '11' ? 'menuOn' :''}" > <a href="${ctxPath}/product/list.do?condition=11">인기상품</a></li>
                            <li class="${cate1 eq '0' && condition eq '101'? 'menuOn' :''}" > <a href="${ctxPath}/product/list.do?condition=101">할인상품</a></li>
                        </ul>
                        <ul>
                            <li><a href="#">쿠폰존</a></li>
                            <li><a href="#">사용후기</a></li>
                            <li><a href="#">개인결제</a></li>
                            <li><a href="${ctxPath}/cs/">고객센터</a></li>
                            <li><a href="${ctxPath}/cs/faq/list.do?cate1=20">FAQ</a></li>
                        </ul>
                    </div>
                </div>
            </header>