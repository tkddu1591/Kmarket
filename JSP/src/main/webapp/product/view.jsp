<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../_header.jsp" %>
<script>
    $(document).ready(function () {
        const success = ${success};
        if (success == 100) {
            alert('장바구니에 물건을 담았습니다.');
        }

        //유저 로그인 확인 true는 로그인 flase는 비로그인
        const isUser = ${not empty sessUser}

        const num = $('input[name=num]')

        const minus = $('.decrease')[0]
        const plus = $('.increase')[0]

        const discountPrice =
        ${kmProduct.discountPrice}
        const price =
        ${kmProduct.price}
        const point =
        ${kmProduct.point}
        const delivery =
        ${kmProduct.delivery}
        const totalPrice = $('.totalPrice')[0]


        const count = $('input[name=count]')
        const total = $('input[name=total]')
        const newPrice = $('input[name=price]')
        const newPoint = $('input[name=point]')


        let newTotalPrice = ${kmProduct.total};
        const stock = ${kmProduct.stock}

            //수량 변경시 formAction의 데이터 및 화면의 데이터 수정
            minus.addEventListener('click', function () {
                //1개 이하로 변경 불가
                if (num.val() > 1) {
                    num.val(parseInt(num.val()) - 1)
                }
                newTotalPrice = (delivery + num.val() * discountPrice - delivery)
                totalPrice.innerText = newTotalPrice.toLocaleString();

                count.val(parseInt(num.val()))
                total.val(newTotalPrice)
            })
        plus.addEventListener('click', function () {
            //최대 수량일시 추가 불가
            console.log(stock)
            if (parseInt(stock) <= parseInt(num.val())) {
                alert('상품 최대 수량입니다.')
            } else {
                num.val(parseInt(num.val()) + 1)

                newTotalPrice = (num.val() * discountPrice)
                totalPrice.innerText = newTotalPrice.toLocaleString();

                count.val(parseInt(num.val()))
                total.val(newTotalPrice)
            }
        })


        const formAction = $('#formAction')

        $('.order').on('click', function (e) {

            e.preventDefault()
            if (isUser == false) {
                alert('로그인 후 이용 가능합니다.')
            } else {
                formAction.attr("action", "${ctxPath}/product/order.do");
                formAction.submit();
            }
        })

        ////////////////////////////////////////////////////////////////////////
        // 카드 담기(동적 이벤트 바인딩 처리 -> 동적 생성되는 새로운 댓글목록 삭제링크가 동작함)
        ////////////////////////////////////////////////////////////////////////

        $('.cart').on('click', function (e) {
            e.preventDefault();

            if (isUser == false) {
                alert('로그인 후 이용 가능합니다.')
                e.preventDefault()
            }
            else{
            if (!confirm('정말 장바구니에 담으시겠습니까?')) {
                return;
            }
            const params = new URLSearchParams({
                'prodNo': ${kmProduct.prodNo},
                'uid': '${sessUser.uid}',
                'count': parseInt(num.val()),
                'price': ${kmProduct.price},
                'discount': ${kmProduct.discount},
                'delivery': ${kmProduct.delivery},
                'total': newTotalPrice,
                'point': ${kmProduct.point},
                'cate1': ${kmProduct.prodCate1},
                'cate2': ${kmProduct.prodCate2},
            });
            fetch('${ctxPath}/product/view.do', {
                method: 'POST',
                body: params
            })
                .then(res => res.json())
                .then(data => {
                    console.log('data : ' + data);

                    if (data.result > 0) {
                        alert('장바구니에 담았습니다.');
                    } else {
                        alert('장바구니 담기가 실패 했습니다.');
                    }

                })
            }
        })

        //상품평 보기
        const btn = document.querySelector('.rating > a')
        if(btn != null) {
            const review = document.querySelectorAll('.review')[0].offsetTop
            console.log(review)
            btn.addEventListener('click', function () {
                window.scrollTo({top: review, behavior: "smooth"});
            })
            <c:if test="${not empty scrollDown}">
                window.scrollTo({top: review, behavior: "smooth"});
            </c:if>
        }

    })
</script>
<main id="product">

    <aside>
        <!-- 카테고리 -->
        <%@ include file="../_category.jsp" %>
    </aside>

    <!-- 상품 상세페이지 시작 -->
    <section class="view">

        <!-- 제목, 페이지 네비게이션 -->
        <nav>
            <h1>상품보기</h1>
            <c:choose>
            <c:when test="${cate2!= 0}">
                <p>HOME > <span>${c1Name}</span> > <strong>${c2Name}</strong>
                </p>
            </c:when>
            <c:when test="${cate1!= 0}">
                <p>HOME > <span>${c1Name}</span></strong></p>
            </c:when>
            <c:otherwise>
            <p>상품목록
            <p>

                </c:otherwise>

                </c:choose>
        </nav>

        <!-- 상품 전체 정보 내용 -->
        <article class="info">
            <div class="image">
                <img src="${ctxPath}${kmProduct.thumb3}" alt="상품이미지"/>
            </div>
            <div class="summary">
                <nav>
                    <h1>${kmProduct.company}</h1>
                    <h2>상품번호&nbsp;:&nbsp;<span>${kmProduct.prodNo}</span></h2>
                </nav>
                <nav>
                    <h3>${kmProduct.prodName}</h3>
                    <p>${kmProduct.descript}</p>

                    <c:choose>
                        <c:when test="${kmProduct.rating >= 4.5}"><h5 class="rating star5"><a href='javascript:void(0);'>상품평보기</a>
                        </h5></c:when>
                        <c:when test="${kmProduct.rating >= 3.5}"><h5 class="rating star4"><a href='javascript:void(0);'>상품평보기</a>
                        </h5></c:when>
                        <c:when test="${kmProduct.rating >= 2.5}"><h5 class="rating star3"><a href='javascript:void(0);'>상품평보기</a>
                        </h5></c:when>
                        <c:when test="${kmProduct.rating >= 1.5}"><h5 class="rating star2"><a href='javascript:void(0);'>상품평보기</a>
                        </h5></c:when>
                        <c:when test="${kmProduct.rating >= 0.5}"><h5 class="rating star1"><a href='javascript:void(0);'>상품평보기</a>
                        </h5></c:when>
                        <c:otherwise>
                            <h6>상품평이 없습니다.</h6>
                        </c:otherwise>
                    </c:choose>
                </nav>

                <nav>
                    <c:if test="${kmProduct.discount ne 0}">
                        <div class="org_price">
                            <del>${kmProduct.priceWithComma}</del>
                            <span>${kmProduct.discountWithPer}</span>
                        </div>
                    </c:if>
                    <div class="dis_price">
                        <ins>${kmProduct.discountPriceWithComma}</ins>
                    </div>
                    <c:if test="${kmProduct.stock < 20}">
                        <div class="soldOut">
                            <span>품절임박!!</span><span> | </span><span> ${kmProduct.stock}개 남았습니다.</span>
                        </div>
                    </c:if>
                </nav>
                <nav>
                    <span class="delivery">${kmProduct.delivery eq 0 ? '무료배송': '배송비 '+=kmProduct.deliveryWithComma+=' 원'}</span>
                    <span class="arrival">모레 ${formatedNow} 도착예정</span>
                    <span class="desc">본 상품은 국내배송만 가능합니다.</span>
                </nav>
                <nav>
                    <span class="card cardfree"><i>아이콘</i>무이자할부</span>&nbsp;&nbsp;
                    <span class="card cardadd"><i>아이콘</i>카드추가혜택</span>
                </nav>
                <nav>
                    <span class="origin">원산지-상세설명 참조</span>
                </nav>
                <img src="../img/vip_plcc_banner.png" alt="100원만 결제해도 1만원 적립!" class="banner"/>

                <div class="count">
                    <button class="decrease">-</button>
                    <input type="text" name="num" value="1" readonly/>
                    <button class="increase">+</button>
                </div>

                <div class="total">
                    <span class="totalPrice">${kmProduct.discountPriceWithComma}</span>
                    <em>총 상품금액</em>
                </div>

                <div class="button">
                    <input type="button" class="cart" value="장바구니"/>
                    <input type="button" class="order" value="구매하기"/>
                </div>
                <form action="${ctxPath}/product/view.do" method="post" id="formAction">
                    <input type="hidden" name="prodNo" value="${kmProduct.prodNo}"/>
                    <input type="hidden" name="prodName" value="${kmProduct.prodName}"/>
                    <input type="hidden" name="descript" value="${kmProduct.descript}"/>
                    <input type="hidden" name="uid" value="${sessUser.uid}"/>
                    <input type="hidden" name="count" value="1"/>
                    <input type="hidden" name="price" value="${kmProduct.price}"/>
                    <input type="hidden" name="discount" value="${kmProduct.discount}"/>
                    <input type="hidden" name="delivery" value="${kmProduct.delivery}"/>
                    <input type="hidden" name="total" value="${kmProduct.total}"/>
                    <input type="hidden" name="point" value="${kmProduct.point}">
                    <input type="hidden" name="cate1" value="${cate1}">
                    <input type="hidden" name="cate2" value="${cate2}">
                    <input type="hidden" name="thumb1" value="${kmProduct.thumb1}">
                    <input type="hidden" name="type" value="order">
                </form>
            </div>

        </article>
        <!-- 상품 정보 내용 -->
        <article class="detail">
            <nav>
                <h1>상품정보</h1>
            </nav>
            <!-- 상품상세페이지 이미지 -->
            <img src="${ctxPath}${kmProduct.detail}" alt="상세페이지1">
        </article>

        <!-- 상품 정보 제공 고시 내용 -->
        <article class="notice">
            <nav>
                <h1>상품 정보 제공 고시</h1>
                <p>[전자상거래에 관한 상품정보 제공에 관한 고시] 항목에 의거 등록된 정보입니다.</p>
            </nav>
            <table border="0">
                <tr>
                    <td>상품번호</td>
                    <td>${kmProduct.prodNo}</td>
                </tr>
                <tr>
                    <td>상품상태</td>
                    <td>${kmProduct.status}</td>
                </tr>
                <tr>
                    <td>부가세 면세여부</td>
                    <td>${kmProduct.duty}</td>
                </tr>
                <tr>
                    <td>영수증발행</td>
                    <td>${kmProduct.receipt}</td>
                </tr>
                <tr>
                    <td>사업자구분</td>
                    <td>${kmProduct.bizType}</td>
                </tr>
                <tr>
                    <td>브랜드</td>
                    <td>블루포스</td>
                </tr>
                <tr>
                    <td>원산지</td>
                    <td>${kmProduct.origin}</td>
                </tr>
            </table>
            <table border="0">
                <tr>
                    <td>제품소재</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>색상</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>치수</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>제조자/수입국</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>제조국</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>취급시 주의사항</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>제조연월</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>품질보증기준</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>A/S 책임자와 전화번호</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td>주문후 예상 배송기간</td>
                    <td>상세정보 직접입력</td>
                </tr>
                <tr>
                    <td colspan="2">구매, 교환, 반품, 배송, 설치 등과 관련하여 추가비용, 제한조건 등의 특이사항이 있는 경우</td>
                </tr>
            </table>
            <p class="notice">
                소비자가 전자상거래등에서 소비자 보호에 관한 법률 제 17조 제1항 또는 제3항에 따라 청약철회를 하고
                동법 제 18조 제1항 에 따라 청약철회한 물품을 판매자에게 반환하였음에도 불구 하고 결제 대금의
                환급이 3영업일을 넘게 지연된 경우, 소비자 는 전자상거래등에서 소비자보호에 관한 법률 제18조
                제2항 및 동법 시행령 제21조 2에 따라 지연일수에 대하여 전상법 시행령으로 정하는 이율을 곱하여
                산정한 지연이자(“지연배상금”)를 신청할 수 있습니다. 아울러, 교환∙반품∙보증 및 결제대금의
                환급신청은 [나의쇼핑정보]에서 하실 수 있으며, 자세한 문의는 개별 판매자에게 연락하여 주시기 바랍니다.
            </p>
        </article>

        <!-- 상품 리뷰 내용 -->
        <article class="review">
            <nav>
                <h1>상품리뷰</h1>
            </nav>
            <ul>
                <c:forEach var="review" items="${kmProductReviews}">
                    <li>
                        <div>
                            <c:choose>
                                <c:when test="${review.rating >= 4.5}"><h5 class="rating star5"><a href="#">상품평</a>
                                </h5></c:when>
                                <c:when test="${review.rating >= 3.5}"><h5 class="rating star4"><a href="#">상품평</a>
                                </h5></c:when>
                                <c:when test="${review.rating >= 2.5}"><h5 class="rating star3"><a href="#">상품평</a>
                                </h5></c:when>
                                <c:when test="${review.rating >= 1.5}"><h5 class="rating star2"><a href="#">상품평</a>
                                </h5></c:when>
                                <c:when test="${review.rating >= 0.5}"><h5 class="rating star1"><a href="#">상품평</a>
                                </h5></c:when>
                                <c:otherwise>
                                    <h6>별점이 없습니다.</h6>
                                </c:otherwise>
                            </c:choose>
                            <span>${review.uidHidden} ${review.rDateYMD}</span>
                        </div>
                        <h3>${kmProduct.prodName}/BLUE/L</h3>
                        <p>
                                ${review.content}
                        </p>
                    </li>
                </c:forEach>
                <c:if test="${empty kmProductReviews}">
                    상품평이 없습니다.
                </c:if>
                <%--
                                <li>
                                    <div>
                                        <h5 class="rating star4">상품평</h5>
                                        <span>seo******	2018-07-10</span>
                                    </div>
                                    <h3>상품명1/BLUE/L</h3>
                                    <p>
                                        가격대비 정말 괜찮은 옷이라 생각되네요 핏은 음...제가 입기엔 어깨선이 맞고 루즈핏이라 하기도 좀 힘드네요.
                                        아주 약간 루즈한정도...?그래도 이만한 옷은 없다고 봅니다 깨끗하고 포장도 괜찮고 다음에도 여기서 판매하는
                                        제품들을 구매하고 싶네요 정말 만족하고 후기 남깁니다 많이 파시길 바래요 ~ ~ ~
                                    </p>
                                </li>--%>
            </ul>
            <div class="paging">
            <span class="prev">
                <c:if test="${pageGroupStart > 1}">
                    <a href="${ctxPath}/product/view.do?cate1=${kmProduct.prodCate1}&cate2=${kmProduct.prodCate2}&pg=1&prodNo=${kmProduct.prodNo}&scrollDown=1"
                       class="start">처음으로</a>
                    <a href="${ctxPath}/product/view.do?cate1=${kmProduct.prodCate1}&cate2=${kmProduct.prodCate2}&pg=${pageGroupStart - 1}&prodNo=${kmProduct.prodNo}&scrollDown=1"
                       class="prev">이전</a>
                </c:if>
            </span>
                <span class="num">
                <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                   <a href="${ctxPath}/product/view.do?cate1=${kmProduct.prodCate1}&cate2=${kmProduct.prodCate2}&pg=${i}&prodNo=${kmProduct.prodNo}&scrollDown=1"
                      class="num ${currentPage == i?'on':'off'}">${i}</a>
                </c:forEach>
            </span>
                <span class="next">
                <c:if test="${pageGroupEnd < lastPageNum}">
                    <a href="${ctxPath}/product/view.do?cate1=${kmProduct.prodCate1}&cate2=${kmProduct.prodCate2}&pg=${pageGroupEnd + 1}&prodNo=${kmProduct.prodNo}&scrollDown=1"
                       class="next">다음</a>
                    <a href="${ctxPath}/product/view.do?cate1=${kmProduct.prodCate1}&cate2=${kmProduct.prodCate2}&pg=${lastPageNum}&prodNo=${kmProduct.prodNo}&scrollDown=1"
                       class="last">마지막</a>
                </c:if>
            </span>
            </div>

        </article>

    </section>
    <!-- 상품 상세페이지 끝 -->
</main>
<%@ include file="../_footer.jsp" %>