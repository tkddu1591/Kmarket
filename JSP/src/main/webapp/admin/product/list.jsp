<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./../_header.jsp" %>
<%@include file="./../_aside.jsp" %>
<!-- <script>
const success =  new URL(location.href).searchParams.get('success');

if(success == 0){
} else if(success == 100){
alert('상품이 정상적으로 삭제되었습니다.');
} else if(success == 200){
alert('상품이 정상적으로 수정되었습니다.');
}
</script> -->
<script>
    $(document).ready(function() {

    })
</script>
<section id="admin-product-list">
    <nav>
        <h3>상품목록</h3>
        <p>
            HOME > 상품관리 > <strong>상품목록</strong>
        </p>
    </nav>

    <!-- 상품목록 컨텐츠 시작 -->
    <section>

        <form action="${ctxPath}/admin/product/delete.do" method="post" id="adminListForm">
            <div>
                <select name="condition">
                    <option value="63" <c:if test="${condition eq '63'}">selected</c:if> >상품명</option>
                    <option value="73" <c:if test="${condition eq '73'}">selected</c:if> >상품코드</option>
                    <option value="83" <c:if test="${condition eq '83'}">selected</c:if> >제조사</option>
                    <option value="93" <c:if test="${condition eq '93'}">selected</c:if> >판매자</option>
                </select>
                <input type="text" name="search" minlength="2" value="${search}">

            </div>
            <table class="listTable">
                <thead>
                    <tr>
                        <th><input type="checkbox" name="all"/></th>
                        <th>이미지</th>
                        <th>상품코드</th>
                        <th>상품명</th>
                        <th>판매가격</th>
                        <th>할인율</th>
                        <th>포인트</th>
                        <th>재고</th>
                        <th>제조사</th>
                        <th>판매자</th>
                        <th>조회</th>
                        <th>관리</th>
                    </tr>
                </thead>
                <tbody class="listTbody">
                    <c:forEach var="dto" items="${kmProductDTOS}">
                        <tr>
                            <td><input type="checkbox" name="chk" value="${dto.prodNo}"/></td>
                            <td><img src="${ctxPath += dto.thumb1}" class="thumb1"></td>
                            <td>${dto.prodNo}</td>
                            <td>${dto.prodName}</td>
                            <td>${dto.priceWithComma}</td>
                            <td>${dto.discount}</td>
                            <td>${dto.point}</td>
                            <td>${dto.stock}</td>
                            <td>${dto.company}</td>
                            <td>${dto.seller}</td>
                            <td>${dto.hit}</td>
                            <td>
                                <a href="${ctxPath}/admin/product/delete.do?no=${dto.prodNo}" class="productDelete">[삭제]</a>
                                <a href="${ctxPath}/admin/product/update.do?no=${dto.prodNo}" class="productRegister">[수정]</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <input type="submit" value="선택삭제" class="deleteAll" />
        </form>

        <div class="paging">
            <c:if test="${pageGroupStart > 1}">
			        	<span class="prev">
			            	<a onclick="javascript:pagePrev()" href='javascript:void(0);'><&nbsp;이전</a>
		            	</span>
            </c:if>
            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
			            	<span class="num ${currentPage == i?'on':'off'}">
			            		<a  class="num ${currentPage == i?'on':''}" onclick="javascript:pageNum(${i})" href='javascript:void(0);'>${i}</a>
			            	</span>
            </c:forEach>
            <c:if test="${pageGroupEnd < lastPageNum}">
	                        <span class="next">
			            		<a  onclick="javascript:pageNext()" href='javascript:void(0);' >다음&nbsp;></a>
			            	</span>
            </c:if>
        </div>
    </section>

    <p class="ico info">
        <strong>Tip!</strong>
        전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
    </p>



    <!-- 상품목록 컨텐츠 끝 -->
</section>
</main>

<script>
    adminListForm = document.getElementById('adminListForm');

    function pageNext() {
        adminListForm.setAttribute('method', 'post');
        adminListForm.setAttribute('action', '${ctxPath}/admin/product/list.do?pg=${pageGroupEnd + 1}');
        adminListForm.submit();
    }

    function pagePrev() {
        adminListForm.setAttribute('method', 'post');
        adminListForm.setAttribute('action', '${ctxPath}/admin/product/list.do?pg=${pageGroupStart - 1}');
        adminListForm.submit();
    }

    function pageNum(e) {
        adminListForm.setAttribute('method', 'post');
        adminListForm.setAttribute('action', '${ctxPath}/admin/product/list.do?pg=' + e);
        adminListForm.submit();
    }
</script>
<%@include file="./../_footer.jsp" %>