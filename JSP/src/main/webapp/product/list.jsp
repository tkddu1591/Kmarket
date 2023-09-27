<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../_header.jsp" %>
<main id="product">

    <aside>
        <!-- 카테고리 -->
        <%@ include file="../_category.jsp" %>
    </aside>

    <section class="list">
        <!-- 제목, 페이지 네비게이션 -->
        <nav>
            <h1>상품목록</h1>

            <c:if test="${not empty KmProductDTOS &&empty search}">
            <c:choose>
            <c:when test="${cate2!= 0}">
                <p>HOME > <span>${c1Name}</span> > <strong>${c2Name}</strong></p>
            </c:when>
            <c:when test="${cate1!= 0}">
                <p>HOME > <span>${c1Name}</span></p>
            </c:when>
            <c:otherwise>
            <p>상품목록
            <p>

                </c:otherwise>

                </c:choose>
                </c:if>
        </nav>

        <!-- 정렬 메뉴 -->

        <c:if test="${not empty KmProductDTOS &&cate1!=0 && not empty cate1}">
                <ul class="sort">
                    <li><a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=11"
                           class="${condition eq '11' ? 'on' : ''}">판매많은순</a></li>
                    <li><a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=22"
                           class="${condition eq '22' ? 'on' : ''}">낮은가격순</a></li>
                    <li><a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=21"
                           class="${condition eq '21' ? 'on' : ''}">높은가격순</a></li>
                    <li><a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=31"
                           class="${condition eq '31' ? 'on' : ''}">평점높은순</a></li>
                    <li><a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=41"
                           class="${condition eq '41' ? 'on' : ''}">후기많은순</a></li>
                    <li><a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=51"
                           class="${condition eq '51' ? 'on' : ''}">최근등록순</a></li>
                </ul>
        </c:if>
        <!-- 상품목록 -->
        <table border="0">
            <c:if test="${empty KmProductDTOS}">
                <tr class="empty">
                    <td colspan="7">검색하신 상품이 없습니다.</td>
                </tr>
            </c:if>
            <c:forEach var="dto" items="${KmProductDTOS}">
                <tr>
                    <td>
                        <a href="${ctxPath}/product/view.do?prodNo=${dto.prodNo}&cate1=${cate1}&cate2=${cate2}"
                           class="thumb"><img src="${ctxPath}${dto.thumb1}" alt="상품이미지"/></a></td>
                    <td>
                        <h3 class="name">${dto.prodName}</h3>
                        <a href="${ctxPath}/product/view.do?prodNo=${dto.prodNo}&cate1=${cate1}&cate2=${cate2}"
                           class="desc">${dto.descript}</a>
                    </td>
                    <td>
                        <ul>
                            <li>
                                <ins class="dis-price">${dto.discountPriceWithComma}</ins>
                            </li>
                            <c:if test="${dto.discount ne 0}">
                                <li>
                                    <del class="org-price">${dto.priceWithComma}</del>
                                    <span class="discount">${dto.discountWithPer}</span>
                                </li>
                            </c:if>
                            <li><span
                                    class="${dto.delivery eq 0 ? 'free-delivery' : ''}">배송비 ${dto.deliveryWithComma} 원</span>
                            </li>
                            <c:if test="${dto.stock <= 20 }">
                                <li class="soldOut">
                                    <span>품절임박!!</span>
                                </li>
                            </c:if>
                        </ul>
                    </td>
                    <td>
                        <h4 class="seller"><i class="fas fa-home"></i> ${dto.seller}</h4>
                        <c:choose>
                            <c:when test="${dto.level eq 5}">
                                <h5>일반판매자</h5>
                            </c:when>
                            <c:when test="${dto.level eq 6}">
                                <h5 class="badge power">파워판매자</h5>
                            </c:when>
                            <c:when test="${dto.level eq 7}">
                                <h5 class="badge great">그레이트판매자</h5>
                            </c:when>
                            <c:when test="${dto.level eq 8}">
                                <h5 class="badge great power">파워&그레이트</h5>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${dto.rating >= 4.5}"><h5 class="rating star5"><a
                                    href="${ctxPath}/product/view.do?prodNo=${dto.prodNo}&cate1=${cate1}&cate2=${cate2}&scrollDown=1">상품평 (${dto.review})</a>
                            </h5></c:when>
                            <c:when test="${dto.rating >= 3.5}"><h5 class="rating star4"><a
                                    href="${ctxPath}/product/view.do?prodNo=${dto.prodNo}&cate1=${cate1}&cate2=${cate2}&scrollDown=1">상품평 (${dto.review})</a>
                            </h5></c:when>
                            <c:when test="${dto.rating >= 2.5}"><h5 class="rating star3"><a
                                    href="${ctxPath}/product/view.do?prodNo=${dto.prodNo}&cate1=${cate1}&cate2=${cate2}&scrollDown=1">상품평 (${dto.review})</a>
                            </h5></c:when>
                            <c:when test="${dto.rating >= 1.5}"><h5 class="rating star2"><a
                                    href="${ctxPath}/product/view.do?prodNo=${dto.prodNo}&cate1=${cate1}&cate2=${cate2}&scrollDown=1">상품평 (${dto.review})</a>
                            </h5></c:when>
                            <c:when test="${dto.rating >= 0.5}"><h5 class="rating star1"><a
                                    href="${ctxPath}/product/view.do?prodNo=${dto.prodNo}&cate1=${cate1}&cate2=${cate2}&scrollDown=1">상품평 (${dto.review})</a>
                            </h5></c:when>
                            <c:otherwise>
                                <h6>상품평이 없습니다.</h6>
                            </c:otherwise>
                        </c:choose>
                    </td>

                </tr>
            </c:forEach>

        </table>

        <!-- 상품목록 페이지번호 -->
        <c:choose>
            <c:when test="${empty search}">
                <div class="paging">
            <span class="prev">
                <c:if test="${pageGroupStart > 1}">
                    <a href="${ctxPath}P/product/list.do?cate1=${cate1}&cate2=${cate2}&pg=1&condition=${condition}"
                       class="start">처음으로</a>
                    <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&pg=${pageGroupStart - 1}&condition=${condition}"
                       class="prev">이전</a>
                </c:if>
            </span>
                    <span class="num">
                <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                   <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&pg=${i}&condition=${condition}"
                      class="num ${currentPage == i?'on':'off'}">${i}</a>
                </c:forEach>
            </span>
                    <span class="next">
                <c:if test="${pageGroupEnd < lastPageNum}">
                    <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&pg=${pageGroupEnd + 1}&condition=${condition}"
                       class="next">다음</a>
                    <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&pg=${lastPageNum}&condition=${condition}"
                       class="last">마지막</a>
                </c:if>
            </span>
                </div>
            </c:when>
            <c:otherwise>
                <div class="paging">
            <span class="prev">
                <c:if test="${pageGroupStart > 1}">
                    <a href="${ctxPath}P/product/list.do?&pg=1&search=${search}"
                       class="start">처음으로</a>
                    <a href="${ctxPath}/product/list.do?&pg=${pageGroupStart - 1}&search=${search}"
                       class="prev">이전</a>
                </c:if>
            </span>
                    <span class="num">
                <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                   <a href="${ctxPath}/product/list.do?&pg=${i}&search=${search}"
                      class="num ${currentPage == i?'on':'off'}">${i}</a>
                </c:forEach>
            </span>
                    <span class="next">
                <c:if test="${pageGroupEnd < lastPageNum}">
                    <a href="${ctxPath}/product/list.do?cate1=pg=${pageGroupEnd + 1}&search=${search}"
                       class="next">다음</a>
                    <a href="${ctxPath}/product/list.do?pg=${lastPageNum}&search=${search}"
                       class="last">마지막</a>
                </c:if>
            </span>
                </div>
            </c:otherwise>
        </c:choose>

    </section>
</main>
<%@ include file="../_footer.jsp" %>