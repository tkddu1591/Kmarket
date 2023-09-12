<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp" %>
<main id="product">
    <aside>
        <ul class="category">
            <li><i class="fa fa-bars" aria-hidden="true"></i>카테고리</li>
            <li>
                <a href="#"><i class="fas fa-tshirt"></i>패션·의류·뷰티</a>
                <ol>
                    <li><a href="#">남성의류</a></li>
                    <li><a href="#">여성의류</a></li>
                    <li><a href="#">잡화</a></li>
                    <li><a href="#">뷰티</a></li>
                </ol>
            </li>
            <li>
                <a href="#"><i class="fas fa-laptop"></i>가전·디지털</a>
                <ol>
                    <li><a href="#">노트북/PC</a></li>
                    <li><a href="#">가전</a></li>
                    <li><a href="#">휴대폰</a></li>
                    <li><a href="#">기타</a></li>
                </ol>
            </li>
            <li>
                <a href="#"><i class="fas fa-utensils"></i>식품·생필품</a>
                <ol>
                    <li><a href="#">신선식품</a></li>
                    <li><a href="#">가공식품</a></li>
                    <li><a href="#">건강식품</a></li>
                    <li><a href="#">생필품</a></li>
                </ol>
            </li>
            <li>
                <a href="#"><i class="fas fa-home"></i>홈·문구·취미</a>
                <ol>
                    <li><a href="#">가구/DIY</a></li>
                    <li><a href="#">침구·커튼</a></li>
                    <li><a href="#">생활용품</a></li>
                    <li><a href="#">사무용품</a></li>
                </ol>
            </li>
        </ul>
    </aside>

    <section class="list">
        <!-- 제목, 페이지 네비게이션 -->
        <nav>
            <h1>상품목록</h1>
            <p>HOME > <span>패션·의류·뷰티</span> > <strong>남성의류</strong></p>
        </nav>

        <!-- 정렬 메뉴 -->
        <ul class="sort">
            <li><a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=11" class="on">판매많은순</a></li>
            <li><a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=22">낮은가격순</a></li>
            <li><a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=21">높은가격순</a></li>
            <li><a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=31">평점높은순</a></li>
            <li><a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=41">후기많은순</a></li>
            <li><a href="/JSP/product/list.do?cate1=${cate1}&cate2=${cate2}&condition=51">최근등록순</a></li>
        </ul>

        <!-- 상품목록 -->
        <table border="0">
            <c:forEach var="dto" items="${KmProductDTOS}">
                <tr>
                    <td><a href="/JSP/product/view.jsp" class="thumb"><img src="https://via.placeholder.com/120x120"
                                                                           alt="상품이미지"/></a></td>
                    <td>
                        <h3 class="name">${dto.prodName}</h3>
                        <a href="/JSP/product/view.jsp" class="desc">${dto.descript}</a>
                    </td>
                    <td>
                        <ul>
                            <li>
                                <ins class="dis-price">${dto.totalWithComma}</ins>
                            </li>
                            <li>
                                <del class="org-price">${dto.priceWithComma}</del>
                                <span class="discount">${dto.discountWithPer}</span>
                            </li>
                            <li><span class="free-delivery">${dto.delivery}</span></li>
                        </ul>
                    </td>
                    <td>
                        <h4 class="seller"><i class="fas fa-home"></i>${dto.seller}</h4>
                        <h5 class="badge power">판매자등급</h5>
                        <h6 class="rating star1">상품평</h6>
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