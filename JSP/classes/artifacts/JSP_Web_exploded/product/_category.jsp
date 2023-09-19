<%--
  Created by IntelliJ IDEA.
  User: Java
  Date: 2023-09-12
  Time: 오후 5:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="category">
    <li><i class="fa fa-bars" aria-hidden="true"></i>카테고리</li>
    <li>
        <a href="${ctxPath}/product/list.do?cate1=10&condition=11"><i class="fas fa-tshirt"></i>브랜드패션</a>
        <ol>
            <li><a href="${ctxPath}/product/list.do?cate1=10&cate2=10&condition=11">브랜드 여성의류</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=10&cate2=11&condition=11">브랜드 남성의류</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=10&cate2=12&condition=11">브랜드 진/캐쥬얼</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=10&cate2=13&condition=11">브랜드 신발/가방</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=10&cate2=14&condition=11">브랜드 쥬얼리/시계</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=10&cate2=15&condition=11">브랜드 아웃도어</a></li>
        </ol>
    </li>
    <li>
        <a href="${ctxPath}/product/list.do?cate1=11"><i class="fas fa-tshirt"></i>패션의류/잡화/뷰티</a>
        <ol>
            <li><a href="${ctxPath}/product/list.do?cate1=11&cate2=10&condition=11">남성의류</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=11&cate2=11&condition=11">여성의류</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=11&cate2=12&condition=11">언더웨어</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=11&cate2=13&condition=11">신발</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=11&cate2=14&condition=11">가방/잡화</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=11&cate2=15&condition=11">쥬얼리/시계</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=11&cate2=16&condition=11">화장품/향수</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=11&cate2=17&condition=11">바디/헤어</a></li>
        </ol>
    </li>

    <li>
        <a href="${ctxPath}/product/list.do?cate1=12"><i class="fas fa-home"></i>유아동</a>
        <ol>
            <li><a href="${ctxPath}/product/list.do?cate1=12&cate2=10&condition=11">출산/육아</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=12&cate2=11&condition=11">장난감/완구</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=12&cate2=12&condition=11">유아동/의류</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=12&cate2=13&condition=11">유아동 신발/잡화</a></li>
        </ol>
    </li>
    <li>
        <a href="${ctxPath}/product/list.do?cate1=13"><i class="fas fa-utensils"></i>식품·생필품</a>
        <ol>
            <li><a href="${ctxPath}/product/list.do?cate1=13&cate2=10&condition=11">신선식품</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=13&cate2=11&condition=11">가공식품</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=13&cate2=12&condition=11">건강식품</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=13&cate2=13&condition=11">커피/음료</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=13&cate2=14&condition=11">생필품</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=13&cate2=15&condition=11">바디/헤어</a></li>
        </ol>
    </li>
    <li>
        <a href="${ctxPath}/product/list.do?cate1=14"><i class="fas fa-home"></i>홈·문구·취미</a>
        <ol>
            <li><a href="${ctxPath}/product/list.do?cate1=14&cate2=10&condition=11">가구/DIY</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=14&cate2=11&condition=11">침구/커튼</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=14&cate2=12&condition=11">조명/인테리어</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=14&cate2=13&condition=11">생활용품</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=14&cate2=14&condition=11">주방용품</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=14&cate2=15&condition=11">문구/사무용품</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=14&cate2=16&condition=11">사무기기</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=14&cate2=17&condition=11">악기/취미</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=14&cate2=18&condition=11">반려동물용품</a></li>
        </ol>
    </li>
    <li>
        <a href="${ctxPath}/product/list.do?cate1=15"><i class="fas fa-laptop"></i>컴퓨터·디지털·가전</a>
        <ol>
            <li><a href="${ctxPath}/product/list.do?cate1=15&cate2=10&condition=11">노트북/PC</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=15&cate2=11&condition=11">모니터/프린터</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=15&cate2=12&condition=11">PC주변기기</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=15&cate2=13&condition=11">모바일/태블릿</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=15&cate2=14&condition=11">카메라</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=15&cate2=15&condition=11">게임</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=15&cate2=16&condition=11">영상가전</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=15&cate2=17&condition=11">주방가전</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=15&cate2=18&condition=11">계절가전</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=15&cate2=19&condition=11">생활/미용가전</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=15&cate2=20&condition=11">음향가전</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=15&cate2=21&condition=11">건강가전</a></li>
        </ol>
    </li>

    <li>
        <a href="${ctxPath}/product/list.do?cate1=16"><i class="fas fa-home"></i>스포츠·건강·렌탈</a>
        <ol>
            <li><a href="${ctxPath}/product/list.do?cate1=16&cate2=10&condition=11">스포츠의류/운동화</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=16&cate2=11&condition=11">휘트니스/수영</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=16&cate2=12&condition=11">구기/라켓</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=16&cate2=13&condition=11">골프</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=16&cate2=14&condition=11">자전거/보드/기타레저</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=16&cate2=15&condition=11">캠핑/낚시</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=16&cate2=16&condition=11">등산/아웃도어</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=16&cate2=17&condition=11">건강/의료용품</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=16&cate2=18&condition=11">건강식품</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=16&cate2=19&condition=11">렌탈서비스</a></li>
        </ol>
    </li>


    <li>
        <a href="${ctxPath}/product/list.do?cate1=17"><i class="fas fa-home"></i>자동차·공구</a>
        <ol>
            <li><a href="${ctxPath}/product/list.do?cate1=17&cate2=10&condition=11">자동차용품</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=17&cate2=11&condition=11">공구/안전/산업용품</a></li>
        </ol>
    </li>

    <li>
        <a href="${ctxPath}/product/list.do?cate1=18"><i class="fas fa-home"></i>여행·도서·티켓·e쿠폰</a>
        <ol>
            <li><a href="${ctxPath}/product/list.do?cate1=18&cate2=10&condition=11">여행/항공권</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=18&cate2=11&condition=11">도서/음반/e교육</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=18&cate2=12&condition=11">공연티켓</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=18&cate2=13&condition=11">e쿠폰</a></li>
            <li><a href="${ctxPath}/product/list.do?cate1=18&cate2=14&condition=11">상품권</a></li>
        </ol>
    </li>

</ul>