<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="./_header.jsp" %>
<style>
    .slider>ul{
        width: 985px;
        height: 447px;
        list-style: none;
        padding: 0;
        position: relative;
        overflow: hidden;
    }
    .slider>ul>li{
        width: 100%;
        height: 100%;
        position: absolute;
    }
    .slider>ul>li:nth-of-type(1){left: 0;top: 0;}
    .slider>ul>li:nth-of-type(2){left: 100%;top: 0%;}
    .slider>ul>li:nth-of-type(3){left: 200%;top: 0%;}
    .slider>ul>li:nth-of-type(4){left: 300%;top: 0%;}

    .hit img{
        width: 230px;
    }
    .recommend img{
        width: 230px;
    }
    .new img{
        width: 230px;
    }
    .discount img{
        width: 230px;
    }
    .best img{
        width: 50px;
    }
    .best li:nth-last-of-type(1) img{
        width: 230px;
    }

</style>


<main>
    <!-- 카테고리/베스트 상품 영역 -->
    <aside>
        <!-- 카테고리 -->
        <%@ include file="_category.jsp" %>
        <!-- 베스트상품 배너 -->
        <article class="best">
            <h1><i class="fas fa-crown"></i>베스트상품</h1>
            <ol>
                <c:forEach var="soldItem" items="${soldDTOS}">
                <li>
                    <a href="${ctxPath}/product/view.do?prodNo=${soldItem.prodNo}">
                        <div class="thumb">
                            <i>${soldItem.etc1}</i>
                            <img src="${ctxPath}${soldItem.thumb2}" alt="${soldItem.prodName}"/>
                        </div>
                        <h2>${soldItem.prodName}</h2>
                        <div class="org_price">
                            <del>${soldItem.priceWithComma}</del>
                            <span>${soldItem.discount}%</span>
                        </div>
                        <div class="dis_price">
                            <ins>${soldItem.discountPriceWithComma}</ins>
                        </div>
                    </a>
                </li>
                </c:forEach>
            </ol>
        </article>
    </aside>
    <section>
        <!-- 슬라이더 영역 -->
        <section class="slider">
            <ul>
                <li>
                    <a href="#"><img src="${ctxPath}/img/mainSlide1.png" alt="item1" /></a>
                </li>
                <li>
                    <a href="#"><img src="${ctxPath}/img/mainSlide2.png" alt="item2" /></a>
                </li>
                <li>
                    <a href="#"><img src="${ctxPath}/img/mainSlide3.png" alt="item3" /></a>
                </li>
                <li>
                    <a href="#"><img src="${ctxPath}/img/mainSlide4.png" alt="item4" /></a>
                </li>
            </ul>
        </section>


        <!-- 히트상품 영역 -->
        <section class="hit">
            <h3><span>히트상품${hitItem.prodNo}</span></h3>
            <c:forEach items="${hitDTOS}" var="hitItem">
                <article>
                    <a href="${ctxPath}/product/view.do?prodNo=${hitItem.prodNo}">
                        <div class="thumb">
                            <img src="${ctxPath}${hitItem.thumb2}" alt="t1"/>
                        </div>
                        <h2>${hitItem.prodName}</h2>
                        <p>${hitItem.descript}</p>
                        <c:if test="${hitItem.discount ne 0}">
                        <div class="org_price">
                            <del>${hitItem.priceWithComma}</del>
                            <span>${hitItem.discount}%</span>
                        </div>
                        </c:if>
                        <div class="dis_price">
                            <ins>${hitItem.discountPriceWithComma}</ins>
                            <c:choose>
                                <c:when test="${hitItem.delivery eq 0}">
                                    <span class="free">무료배송</span>
                                </c:when>
                                <c:otherwise><span>배송비 ${hitItem.delivery} 원</span></c:otherwise>
                            </c:choose>
                        </div>
                    </a>
                </article>
            </c:forEach>
        </section>
        <!-- 추천상품 영역 -->
        <section class="recommend">
            <h3><span>추천상품</span></h3>
            <c:forEach items="${goodDTOS}" var="dto">
                <article>
                    <a href="${ctxPath}/product/view.do?prodNo=${dto.prodNo}">
                        <div class="thumb">
                            <img src="${ctxPath}${dto.thumb2}" alt="t1"/>
                        </div>
                        <h2>${dto.prodName}</h2>
                        <p>${dto.descript}</p>
                        <c:if test="${dto.discount ne 0}">
                            <div class="org_price">
                                <del>${dto.priceWithComma}</del>
                                <span>${dto.discount}%</span>
                            </div>
                        </c:if>
                        <div class="dis_price">
                            <ins>${dto.discountPriceWithComma}</ins>
                            <c:choose>
                                <c:when test="${dto.delivery eq 0}">
                                    <span class="free">무료배송</span>
                                </c:when>
                                <c:otherwise><span>배송비 ${dto.delivery} 원</span></c:otherwise>
                            </c:choose>
                        </div>
                    </a>
                </article>
            </c:forEach>
        </section>
        <!-- 최신상품 영역 -->
        <section class="new">
            <h3><span>최신상품</span></h3>
            <c:forEach items="${newDTOS}" var="dto">
                <article>
                    <a href="${ctxPath}/product/view.do?prodNo=${dto.prodNo}">
                        <div class="thumb">
                            <img src="${ctxPath}${dto.thumb2}" alt="t1"/>
                        </div>
                        <h2>${dto.prodName}</h2>
                        <p>${dto.descript}</p>
                        <c:if test="${dto.discount ne 0}">
                            <div class="org_price">
                                <del>${dto.priceWithComma}</del>
                                <span>${dto.discount}%</span>
                            </div>
                        </c:if>
                        <div class="dis_price">
                            <ins>${dto.discountPriceWithComma}</ins>
                            <c:choose>
                                <c:when test="${dto.delivery eq 0}">
                                    <span class="free">무료배송</span>
                                </c:when>
                                <c:otherwise><span>배송비 ${dto.delivery} 원</span></c:otherwise>
                            </c:choose>
                        </div>
                    </a>
                </article>
            </c:forEach>
        </section>
        <!-- 할인상품 영역 -->
        <section class="discount">
            <h3><span>할인상품</span></h3>
            <c:forEach items="${discountDTOS}" var="dto">
                <article>
                    <a href="${ctxPath}/product/view.do?prodNo=${dto.prodNo}">
                        <div class="thumb">
                            <img src="${ctxPath}${dto.thumb2}" alt="t1"/>
                        </div>
                        <h2>${dto.prodName}</h2>
                        <p>${dto.descript}</p>
                        <c:if test="${dto.discount ne 0}">
                            <div class="org_price">
                                <del>${dto.priceWithComma}</del>
                                <span>${dto.discount}%</span>
                            </div>
                        </c:if>
                        <div class="dis_price">
                            <ins>${dto.discountPriceWithComma}</ins>
                            <c:choose>
                                <c:when test="${dto.delivery eq 0}">
                                    <span class="free">무료배송</span>
                                </c:when>
                                <c:otherwise><span>배송비 ${dto.delivery} 원</span></c:otherwise>
                            </c:choose>
                        </div>
                    </a>
                </article>
            </c:forEach>
        </section>
    </section>
</main>
<%@include file="./_footer.jsp" %>
