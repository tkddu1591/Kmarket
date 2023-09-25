<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../../_header.jsp" %>
<%@include file="./../../_aside.jsp" %>

    <section id="admin-product-register">
        <nav>
            <h3>공지사항 수정</h3>
            <p>
                HOME > 고객센터 > <strong>공지사항</strong>
            </p>
        </nav>
        <!-- 상품등록 컨텐츠 시작 -->
        <article>
            <form action="${ctxPath}/admin/cs/notice/update.do" method="post" >
		    	<input type="hidden" name="no" value="${no}">
		    	<!-- <input type="hidden" name="seller" value="${sessUser.uid}"> -->
                <!-- 상품분류 -->
                <section>
                    <table>
                        <tr>
                            <td>유형</td>
                            <td>
                                <select name="cate1" id="cate1">
                                    <option value="">유형 선택</option>
   									<c:forEach var="sessCate1" items="${sessCoates1}">
                                    	<option value="${sessCate1.cate1}">${sessCate1.c1Name}</option> 
                                    </c:forEach>                                            
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>2차 분류</td>
                            <td>
                                <select name="prodCate2" id="prodCate2">
                                    <option value="10">2차 분류 선택</option>
                                    <option value="11">남성의류</option>
                                    <option value="12">여성의류</option>
                                    <option value="13">잡화</option>
                                    <option value="14">뷰티</option>                                                
                                </select>
                            </td>
                        </tr>
                    </table>
                </section>

                <!-- 기본정보 -->
                <section>
                    <h4>기본정보</h4>
                    <p>
                        기본정보는 반드시 입력해야 합니다.
                    </p>
                    <table>
                        <tr>
                            <td>상품명</td>
                            <td><input type="text" name="prodName"/></td>
                        </tr>
                        <tr>
                            <td>기본설명</td>
                            <td>
                                <span>상품명 하단에 상품에 대한 추가적인 설명이 필요한 경우에 입력</span>
                                <input type="text" name="descript"/>
                            </td>
                        </tr>
                        <tr>
                            <td>제조사</td>
                            <td><input type="text" name="company"/></td>
                        </tr>
                        <tr>
                            <td>판매가격</td>
                            <td><input type="text" name="price"/>원</td>
                        </tr>                                    
                        <tr>
                            <td>할인율</td>
                            <td>
                                <span>0을 입력하면 할인율 없음</span>
                                <input type="text" name="discount"/>
                            </td>
                        </tr>
                        <tr>
                            <td>포인트</td>
                            <td>
                                <span>0을 입력하면 포인트 없음</span>
                                <input type="text" name="point"/>점
                            </td>
                        </tr>
                        <tr>
                            <td>재고수량</td>
                            <td><input type="text" name="stock"/>개</td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td>
                                <span>0을 입력하면 배송비 무료</span>
                                <input type="text" name="delivery"/>원
                            </td>
                        </tr>
                        <tr>
                            <td>상품 썸네일</td>
                            <td>
                                <span>크기 190 x 190, 상품 목록에 출력될 이미지 입니다. </span>
                                <input type="file" name="thumb1"/>

                                <span>크기 230 x 230, 상품 메인에 출력될 이미지 입니다. </span>
                                <input type="file" name="thumb2"/>

                                <span>크기 456 x 456, 상품 상세에 출력될 이미지 입니다. </span>
                                <input type="file" name="thumb3"/>
                            </td>
                        </tr>
                        <tr>
                            <td>상세 상품정보</td>
                            <td>
                                <span>크기 가로 940px 높이 제약없음, 크기 최대 1MB, 상세페이지 상품정보에 출력될 이미지 입니다.</span>
                                <input type="file" name="detail"/>
                            </td>
                        </tr>
                    </table>                                
                </section>

                <!-- 상품정보 제공 고시 -->
                <section>
                    <h4>상품정보 제공고시</h4>
                    <p>
                        [전자상거래에 관한 상품정보 제공에 관한 고시] 항목에 의거 등록해야 되는 정보입니다.
                    </p>
                    <table>
                        <tr>
                            <td>상품상태</td>
                            <td><input type="text" name="status" value="새상품"/></td>
                        </tr>
                        <tr>
                            <td>부가세 면세여부</td>
                            <td><input type="text" name="duty" value="과세상품"/></td>
                        </tr>
                        <tr>
                            <td>영수증발행</td>
                            <td><input type="text" name="receipt" value="신용카드 전표, 온라인 현금영수증"/></td>
                        </tr>
                        <tr>
                            <td>사업자구분</td>
                            <td><input type="text" name="bizType" value="사업자 판매자"/></td>
                        </tr>                                
                        <tr>
                            <td>원산지</td>
                            <td><input type="text" name="origin" value="국내산"/></td>
                        </tr>                                
                    </table>                                
                </section>
                
                <input type="submit" value="등록하기" class="onclick"/>
            </form>
        </article>

        <!-- 상품등록 컨텐츠 끝 -->
    </section>
</main>
<%@ include file="./../../_footer.jsp" %>
