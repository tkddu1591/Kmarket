<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp" %>
<main id="product">

    <%@ include file="_aside.jsp" %>

    <section class="list">
        <!-- 제목, 페이지 네비게이션 -->
        <nav>
            <h1>상품목록</h1>
            <p>HOME > <span>패션·의류·뷰티</span> > <strong>남성의류</strong></p>
        </nav>

        <!-- 정렬 메뉴 -->
        <ul class="sort">
            <li><a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=11"
                   class="${condition eq '11' ? 'on' : ''}">판매많은순</a></li>
            <li><a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=22"
                   class="${condition eq '22' ? 'on' : ''}">낮은가격순</a></li>
            <li><a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=21"
                   class="${condition eq '21' ? 'on' : ''}">높은가격순</a></li>
            <li><a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=31"
                   class="${condition eq '31' ? 'on' : ''}">평점높은순</a></li>
            <li><a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=41"
                   class="${condition eq '41' ? 'on' : ''}">후기많은순</a></li>
            <li><a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=51"
                   class="${condition eq '51' ? 'on' : ''}">최근등록순</a></li>
        </ul>

        <!-- 상품목록 -->
        <table border="0">
            <c:forEach var="dto" items="${KmProductDTOS}">
                <tr>
                    <td><a href="/JSP/product/view.do?prodNo=${dto.prodNo}&cate1=${cate1}&cate2=${cate2}" class="thumb"><img src="https://via.placeholder.com/120x120"
                                                                           alt="상품이미지"/></a></td>
                    <td>
                        <h3 class="name">${dto.prodName}</h3>
                        <a href="/JSP/product/view.do?prodNo=${dto.prodNo}&cate1=${cate1}&cate2=${cate2}" class="desc">${dto.descript}</a>
                    </td>
                    <td>
                        <ul>
                            <li>
                                <ins class="dis-price">${dto.discountPriceWithComma}</ins>
                            </li>
                            <li>
                                <del class="org-price">${dto.priceWithComma}</del>
                                <span class="discount">${dto.discountWithPer}</span>
                            </li>
                            <li><span class="${dto.delivery eq 0 ? 'free-delivery' : ''}">배송비 ${dto.deliveryWithComma} 원</span></li>
                        </ul>
                    </td>
                    <td>
                        <h4 class="seller"><i class="fas fa-home"></i> ${dto.company}</h4>
                        <h5 class="badge great">일반판매자</h5>
                        <c:choose>
                        <c:when test="${dto.rating >= 4.5}"><h6 class="rating star5"></c:when>
                        <c:when test="${dto.rating >= 3.5}"><h6 class="rating star4"></c:when>
                        <c:when test="${dto.rating >= 2.5}"><h6 class="rating star3"></c:when>
                        <c:when test="${dto.rating >= 1.5}"><h6 class="rating star2"></c:when>
                        <c:otherwise><h6 class="rating star1"></c:otherwise>


                            </c:choose>
                            상품평</h6>
                    </td>
                </tr>
            </c:forEach>

        </table>

        <!-- 상품목록 페이지번호 -->
        <div class="paging">
            <span class="prev">
                <c:if test="${pageGroupStart > 1}">
                    <a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&pg=1"
                       class="start">처음으로</a>
                    <a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&pg=${pageGroupStart - 1}"
                       class="prev">이전</a>
                </c:if>
            </span>
            <span class="num">
                <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                   <a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&pg=${i}&"
                      class="num ${currentPage == i?'current':'off'}">${i}</a>
                </c:forEach>
            </span>
            <span class="next">
                <c:if test="${pageGroupEnd < lastPageNum}">
                    <a href="JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&pg=${pageGroupEnd + 1}"
                       class="next">다음</a>
                    <a href="JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&pg=${lastPageNum}"
                       class="last">마지막</a>
                </c:if>
            </span>
        </div>

    </section>
</main>
<%@ include file="_footer.jsp" %>