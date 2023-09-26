<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../_header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${ctxPath}/js/zipCode.js"></script>
<script>
    /*휴대폰 번호 자동 하이픈*/
    const hypenTel = (target) => {
        target.value = target.value
            .replace(/[^0-9]/g, '')
            .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
    }

    //max point 넘겨서 사용 하려 할 시 변환
    const point = parseInt(${sessUser.point})
    const maxPoint = (target) => {
        usePoint = parseInt(target.value)
        if (point <= usePoint) {
            target.value = point
        }
    }
    window.onload = () => {
        //토탈 값 쉼표 제거
        total = $('input[name=ordTotPrice]').val()
        total = total.replace(",", "");
        total = parseInt(total);




        const discountPoint = document.getElementsByClassName('discountPoint')[0]
        const discountPointBut = document.getElementsByClassName('discountPointBut')[0]
        let usePoint = 0;
        const newTotal = document.getElementsByClassName('total')[0]
        discountPointBut.addEventListener('click', (e) => {
            usePoint = parseInt($('input[name=usedPoint]').val());
            //포인트 체크
            if (usePoint < 5000) {
                alert('최소 5,000원 이상의 금액을 입력해주세요.')
            } else if(total-usePoint < 0){
                alert('전체 주문금액 보다 큰 금액 사용은 불가능합니다.')
            }else{
                discountPoint.innerText = '-' + usePoint.toLocaleString()
                console.log(total - usePoint)
                newTotal.innerText = (total - usePoint).toLocaleString()
                $('input[name=ordTotPrice]').val(total - usePoint);
            }
        })


        // 폼 데이터 검증결과 상태변수
        let isRecipNameOk = true;
        let isRecipHpOk = true;
        let isRecipAddrOk = true;
        let isOrdPaymentOk = false;

        // 데이터 검증에 사용하는 정규표현식
        let reName = /^[가-힣]{2,10}$/
        let reHp = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

        // 유효성 검증(Validation)

        // 이름 검사
        $('input[name=recipName]').focusout(function () {
            const name = $(this).val();

            if (name.match(reName)) {
                isRecipNameOk = true;
            } else {
                isRecipNameOk = false;
            }
        });

        // 번호 검사
        $('input[name=recipHp]').keyup(function () {
            const hp = $(this).val();
            if (hp.match(reHp)) {
                isRecipHpOk = true;
            } else {
                isRecipHpOk = false;
            }
        });
        // 주소 검사
        const addr = document.getElementsByName('recipAddr2');
        addr[0].focusout = function () {
            const addr = document.getElementsByName('recipAddr1');
            if (addr[0].value == '') {
                isRecipAddrOk = false;
            } else {
                isRecipAddrOk = true;
            }
        }
        // 결제수단 검사
        $('input[name=ordPayment]').click(function () {
            isOrdPaymentOk = true;
        });


        const payment = document.getElementsByName('payment')[0]
        payment.addEventListener('click', (e) => {

            if (!isRecipNameOk) {
                alert('수령인을 확인 하십시오.');
                return false; // 폼 전송 취소
            }


            if (!isRecipHpOk) {
                alert('전화번호를 확인 하십시오.');
                return false; // 폼 전송 취소
            }
            if (!isRecipAddrOk) {
                alert('주소를 확인 하십시오.');
                return false; // 폼 전송 취소
            }

            if (!isOrdPaymentOk) {
                alert('결제수단을 확인 하십시오.');
                return false; // 폼 전송 취소
            }



            if (!confirm('결제하시겠습니까?')) {
                e.preventDefault();
            } else {
                document.getElementById('orderForm').submit();
            }
        })


    }

</script>
<main id="product">
    <aside>
        <!-- 카테고리 -->
        <%@ include file="../_category.jsp" %>
    </aside>
    <!-- 주문 페이지 시작-->
    <section class="order">
        <!-- 제목, 페이지 네비게이션 -->
        <nav>
            <h1>주문결제</h1>
            <p>
                HOME > 장바구니 > <strong>주문결제</strong>
            </p>
        </nav>
        <form action="${ctxPath}/product/complete.do" method="post" id="orderForm">
            <!-- 주문 상품 목록 -->
            <table>
                <thead>
                    <tr>
                        <th>상품명</th>
                        <th>총수량</th>
                        <th>판매가</th>
                        <th>할인</th>
                        <th>배송비</th>
                        <th>소계</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="empty">
                        <td colspan="7">장바구니에 상품이 없습니다.</td>
                    </tr>
                    <c:forEach var="item" items="${kmProductOrderItemDTOS}">
                        <tr>
                            <td>
                                <input type="hidden" class="orderData" value="${item}" name="dto">
                                <input type="hidden" value="${item.descript}" name="descript">
                                <input type="hidden" value="${item.prodName}" name="prodName">
                                <input type="hidden" value="${item.thumb1}" name="thumb1">
                                <article>
                                    <a href="${ctxPath}/product/view.do?prodNo=${item.prodNo}"><img
                                            src="${ctxPath}${item.thumb1}" alt="상품이미지"></a>
                                    <div>
                                        <h2>
                                            <a href="${ctxPath}/product/view.do?prodNo=${item.prodNo}">${item.prodName}</a>
                                        </h2>
                                        <p>${item.descript}</p>
                                    </div>
                                </article>
                            </td>
                            <td>${item.count}</td>
                            <td>${item.priceWithComma}</td>

                            <td>${item.discount}%</td>

                            <td>${item.deliveryWithComma}</td>
                            <td><p>${item.totalWithComma}</p>
                                <p>${item.point * item.count} P</p></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- 최종 결제 정보 -->
            <div class="final">
                <h2>최종결제 정보</h2>
                <table border="0">
                    <tr>
                        <td>총</td>
                        <td>${finalCount eq '0' ? '-' : finalCount} 건</td>
                    </tr>
                    <tr>
                        <td>상품금액</td>
                        <td>${finalPrice  eq '0' ? '-' : finalPrice}</td>
                    </tr>
                    <tr>
                        <td>할인금액</td>
                        <td>${finalDiscount  eq '0' ? '-' : finalDiscount}</td>
                    </tr>
                    <tr>
                        <td>배송비</td>
                        <td>${finalDelivery  eq '0' ? '-' : finalDelivery}</td>
                    </tr>
                    <tr>
                        <td>포인트 할인</td>
                        <td class="discountPoint">-</td>
                    </tr>
                    <tr>
                        <td>포인트 적립</td>
                        <td>${finalPoint  eq '0' ? '-' : finalPoint}</td>
                    </tr>
                    <tr>
                        <td>전체주문금액</td>
                        <td class="total">${finalTotal  eq '0' ? '-' : finalTotal}</td>
                    </tr>
                </table>
                <input type="hidden" name="ordTotPrice" value="${finalTotal}">
                <input type="hidden" name="ordUid" value="${sessUser.uid}">
                <input type="hidden" name="ordCount" value="${finalCount}">
                <input type="hidden" name="ordDelivery" value="${finalDelivery}">
                <input type="hidden" name="ordDiscount" value="${finalDiscount}">
                <input type="hidden" name="savePoint" value="${finalPoint}">
                <input type="hidden" name="ordPrice" value="${finalPrice}">
                <input type="button" name="payment" value="결제하기">
            </div>
            <!-- 배송정보 -->
            <article class="delivery">
                <h1>배송정보</h1>
                <table>
                    <tr>
                        <td>주문자</td>
                        <td><input type="text" name="orderer" value="${sessUser.name}" readonly/></td>
                    </tr>
                    <tr>
                        <td>수령인</td>
                        <td><input type="text" name="recipName" value="${sessUser.name}"/></td>
                    </tr>
                    <tr>
                        <td>휴대폰</td>
                        <td>
                            <input type="text" oninput="hypenTel(this)" name="recipHp"
                                   minlength="13" maxlength="13" value="${sessUser.hp}"/>
                            <span class="resultHp"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>우편번호</td>
                        <td>
                            <input type="text" name="recipZip" readonly value="${sessUser.zip}"/>
                            <input type="button" value="검색" onclick="zipcode()"/>
                        </td>
                    </tr>
                    <tr>
                        <td>기본주소</td>
                        <td>
                            <input type="text" name="recipAddr1" readonly value="${sessUser.addr1}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>상세주소</td>
                        <td>
                            <input type="text" name="recipAddr2" value="${sessUser.addr2}"/>
                        </td>
                    </tr>
                </table>
            </article>
            <!-- 할인정보 -->
            <article class="discount">
                <h1>할인정보</h1>
                <div>
                    <p>현재 포인트 : <span>${sessUser.pointWithComma}</span>점</p>
                    <label>
                        <input type="text" name="usedPoint" onkeyup="maxPoint(this)"
                               maxlength="${fn:length(sessUser.pointWithComma)}"/>점
                        <input type="button" value="적용" class="discountPointBut"/>
                    </label>
                    <span>포인트 5,000점 이상이면 현금처럼 사용 가능합니다.</span>
                </div>
            </article>
            <!-- 결제방법 -->
            <article class="payment">
                <h1>결제방법</h1>
                <div>
                    <span>신용카드</span>
                    <p>
                        <label><input type="radio" name="ordPayment" value="1"/>신용카드 결제</label>
                        <label><input type="radio" name="ordPayment" value="2"/>체크카드 결제</label>
                    </p>
                </div>
                <div>
                    <span>계좌이체</span>
                    <p>
                        <label><input type="radio" name="ordPayment" value="3"/>실시간 계좌이체</label>
                        <label><input type="radio" name="ordPayment" value="4"/>무통장 입금</label>
                    </p>
                </div>
                <div>
                    <span>기타</span>
                    <p>
                        <label><input type="radio" name="ordPayment" value="5"/>휴대폰결제</label>
                        <label>
                            <input type="radio" name="ordPayment" value="6"/>카카오페이
                            <img src="../img/ico_kakaopay.gif" alt="카카오페이"/>
                        </label>
                    </p>
                </div>
            </article>
            <!-- 경고 -->
            <article class="alert">
                <ul>
                    <li>
                        <span>케이마켓의 모든 판매자는 안전거래를 위해 구매금액, 결제수단에 상관없이 모든거래에 대하여 케이마켓 유한책임회사의 구매안전서비스(에스크로)를 제공하고 있습니다.</span>
                    </li>
                    <li><span>케이마켓 유한책임회사의 전자금융거래법에 의해 결제대금예치업 등록번호는 02-006-00008 입니다.</span></li>
                    <li><span>등록여부는 금융감독원 홈페이지(www.fss.or.kr)의 업무자료>인허가업무안내>전자금융업등록현황에서 확인하실수 있습니다.</span></li>
                </ul>
            </article>
        </form>
    </section>
    <!-- 주문 페이지 끝-->
</main>
<%@ include file="../_footer.jsp" %>