<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../_header.jsp" %>
<script>
    $(document).ready(function () {
        // 0: cartNo,
        // 1: uid,
        // 2: prodNo,
        // 3: count,
        // 4: price,
        // 5: discount,
        // 6: delivery,
        // 7: point,
        // 8: total,
        // 9: rDate
        // 10: thumb1
        // 11: prodName
        // 12: descript

        //id 사용시 check 박스 사용


        /*        let all = $('#all')
                console.log($('all').is(':checked')) = 체크 판별
                all.prop('checked', true); = 체크 변환
                */

        //class 사용시 check 박스 사용
        /* cart.checked = 체크 판별
        *
        * */


        let cartDatas = $(".cartData")
        let termCartDatas = [];
        let totalCartDatas = [];
        let isCheckAll = true;
        let checkbox = $('input:checkbox')
        let all = $('#all')


        setData()
        all.prop('checked', true);

        //개별 선택시
        $('.cartData').change(function () {
            //토탈 데이터 수정
            setData()
            isCheckAll = true;

            //전체 체크박스 셀렉트 변경
            for (cart of cartDatas) {
                if (!cart.checked) {
                    isCheckAll = false;
                    break;
                }
            }
            if (isCheckAll) {
                all.prop('checked', true);
            } else {
                all.prop('checked', false);
            }
        })



        //전체 선택
        all.change(function () {
            if ($(this).is(':checked')) {
                let check = $('input:checkbox')
                check.prop('checked', true);
                setData();
            } else {
                let check = $('input:checkbox')
                check.prop('checked', false);
                setData();
            }
        })


        //체크된 상품 데이터 수집 및 total으로 전송
        function setData() {
            for (let i = 3; i <= 8; i++) {
                totalCartDatas[i] = 0;
            }
            for (let cart of cartDatas) {
                if (cart.checked) {
                    termCartDatas = cart.value.split(',', 10)
                    console.log(cart.value)
                    for (let i = 4; i <= 8; i++) {
                        if (termCartDatas[i] == '0') {
                        }
                        if (i == 4 || i == 7) {
                            totalCartDatas[i] += parseInt(termCartDatas[i]) * parseInt(termCartDatas[3]);
                            continue
                        }
                        totalCartDatas[i] += parseInt(termCartDatas[i]);
                    }
                    totalCartDatas[3]++;
                }
            }
            console.log(totalCartDatas)
            const totalData = document.getElementsByClassName("totalData");
            const finalData = document.getElementsByClassName("finalData")
            let no = 3;
            let number = 0;
            let count = 0;
            for (let data of totalData) {
                if (totalCartDatas[no] == '0') {
                    data.innerText = '-';

                    finalData[number].value = 0;
                    no++;
                    number++;
                    continue;
                }
                if (no == 5) {
                    data.innerText = '-' + (totalCartDatas[4] - totalCartDatas[8] + totalCartDatas[6]).toLocaleString();
                    finalData[number].value = '-' + (totalCartDatas[4] - totalCartDatas[8] + totalCartDatas[6]).toLocaleString();
                    number++;
                    no++;
                    continue;
                }
                if (no == 7) {

                    data.innerText = totalCartDatas[no].toLocaleString() + ' P';
                    finalData[number].value = totalCartDatas[no].toLocaleString() + ' P';
                    number++;
                    no++;
                    continue;
                }
                data.innerText = totalCartDatas[no].toLocaleString();
                finalData[number].value = totalCartDatas[no].toLocaleString();
                no++;
                number++;
            }
        }

        //삭제


        let delBut = document.getElementsByName('del')[0];

        const cartDeleteForm = $('#cartDeleteForm')
        delBut.addEventListener('click', function (e) {
            if (confirm('삭제하시겠습니까?')) {

                // form 액션 구현하기
                e.preventDefault();
                cartDeleteForm.attr("action", "${ctxPath}/product/cart.do");
                cartDeleteForm.attr("method", "POST");
                cartDeleteForm.submit();

                /*let check = $('input:checkbox')
                check.prop('checked', false).val(0);
                alert('삭제되었습니다.')*/
            }
        })
        $('input[name="order"]').click(function (e) {

            if (!confirm('주문하시겠습니까?')) {
                e.preventDefault();
            }
        })


    })
</script>
<main id="product">

    <aside>
        <!-- 카테고리 -->
        <%@ include file="../_category.jsp" %>
    </aside>
    <!-- 장바구니 페이지 시작 -->
    <section class="cart">

        <!-- 제목, 페이지 네비게이션 -->
        <nav>
            <h1>장바구니</h1>
            <p>
                HOME > <strong>장바구니</strong>
            </p>
        </nav>

        <form action="${ctxPath}/product/order.do" method="POST" id="cartDeleteForm">
            <!-- 장바구니 목록 -->
            <table>
                <thead>
                    <tr>
                        <th><input type="checkbox" name="all" id="all"></th>
                        <th>상품명</th>
                        <th>총수량</th>
                        <th>판매가</th>
                        <th>할인</th>
                        <th>포인트</th>
                        <th>배송비</th>
                        <th>소계</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty kmProductCartDTOS}">
                            <tr class="empty">
                                <td colspan="7">장바구니에 상품이 없습니다.</td>
                            </tr>
                        </c:when>

                        <c:otherwise>
                            <c:forEach var="dto" items="${kmProductCartDTOS}">
                                <tr>
                                    <td><input type="checkbox" class="cartData" value="${dto}" name="dto" checked></td>
                                    <input type="hidden" name="prodName" value="${dto.prodName}">
                                    <input type="hidden" name="descript" value="${dto.descript}">
                                    <input type="hidden" name="thumb1" value="${dto.thumb1}">
                                    <td>
                                        <article>
                                            <a href="${ctxPath}/product/view.do?prodNo=${dto.prodNo}"> <img
                                                    src="${ctxPath}${dto.thumb1}" alt="상품이미지"></a>
                                            <div>
                                                <h2>
                                                    <a href="${ctxPath}/product/view.do?prodNo=${dto.prodNo}">${dto.prodName}</a>
                                                </h2>
                                                <p>${dto.descript}</p>
                                            </div>
                                        </article>
                                    </td>
                                    <td>${dto.count}</td>
                                    <td>${dto.priceWithComma}</td>
                                    <c:choose>
                                        <c:when test="${dto.discount ne 0}"><td>${dto.discountWithComma}%</td></c:when>
                                        <c:otherwise><td>-</td></c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${dto.point ne 0}"><td>${dto.priceWithComma}</td></c:when>
                                        <c:otherwise><td>-</td></c:otherwise>
                                    </c:choose>
                                    <td>${dto.delivery eq 0 ? '무료배송':dto.deliveryWithComma+=' 원'} </td>
                                    <td><p>${dto.totalWithComma}</p>
                                        <p>${dto.point*dto.count} P</p></td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

            <c:if test="${not empty kmProductCartDTOS}">
                <input type="button" name="del" value="선택삭제">

                <!-- 장바구니 전체합계 -->
                <div class="total">
                    <h2>전체합계</h2>
                    <table border="0">
                        <tr>
                            <td>상품수</td>
                            <td class="totalData">1</td>
                        </tr>
                        <tr>
                            <td>상품금액</td>
                            <td class="totalData">27,000</td>
                        </tr>
                        <tr>
                            <td>할인금액</td>
                            <td class="totalData">-1,000</td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td class="totalData">0</td>
                        </tr>
                        <tr>
                            <td>포인트적립</td>
                            <td class="totalData">260</td>
                        </tr>
                        <tr>
                            <td>전체주문금액</td>
                            <td class="totalData">26,000</td>
                        </tr>
                    </table>
                    <input class="final" type="submit" name="order" value="주문하기">
                    <input class="finalData" type="hidden" name="finalCount" value="0"/>
                    <input class="finalData" type="hidden" name="finalPrice" value="0"/>
                    <input class="finalData" type="hidden" name="finalDiscount" value="0"/>
                    <input class="finalData" type="hidden" name="finalDelivery" value="0"/>
                    <input class="finalData" type="hidden" name="finalPoint" value="0"/>
                    <input class="finalData" type="hidden" name="finalTotal" value="0"/>

                </div>
            </c:if>
        </form>

    </section>
    <!-- 장바구니 페이지 끝 -->
</main>
<%@ include file="../_footer.jsp" %>