<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./../../_header.jsp" %>
<%@include file="./../../_aside.jsp" %>
<script>
    const ctx = '${ctxPath}';
</script>
<script src="${ctxPath}/admin/js/setQnaList.js"></script>
<script src="${ctxPath}/admin/js/controllCsDelete.js"></script>
<script src="${ctxPath}/cs/js/setType2Category.js"></script>
<section id="admin-product-list">
    <nav>
        <h3>문의하기 목록</h3>
        <p>
            HOME > 고객센터 > <strong>문의하기</strong>
        </p>
    </nav>

    <!-- 상품목록 컨텐츠 시작 -->
    <section>

        <form action="${ctxPath}/admin/cs/qna/delete.do" method="post">
            <div>
                <select name="cate1" id="cate1">
                    <option value="">1차 유형 선택</option>
                </select>
                <select name="cate2" id="cate2">
                    <option value="">2차 유형 선택</option>
                </select>
            </div>
            <table class="listTable" id="faqListTable">
                <thead>
                    <tr>
                        <th><input type="checkbox" name="all"/></th>
                        <th>번호</th>
                        <th>1차유형</th>
                        <th>2차유형</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>상태</th>
                    </tr>
                </thead>
                <tbody class="listTbody">
                    <c:forEach var="dto" items="${qnaList}">
                        <tr>
                            <td><input type="checkbox" name="chk" value="${dto.qnaNo}"/></td>
                            <td>${dto.qnaNo}</td>
                            <td>${dto.c1Name}</td>
                            <td>${dto.c2Name}</td>
                            <td><a href="${ctxPath}/admin/cs/qna/view.do?no=${dto.qnaNo}"> ${dto.title}</a></td>
                            <td>${dto.writerMarking}</td>
                            <td>${dto.rdateSub}</td>
                            <td>
		           				<span class="answer${dto.answerComplete}">
                                        ${dto.answerComplete eq '0' ? '미확인' : dto.answerComplete eq '1' ? '검토중' : '답변완료'}
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(qnaList) eq 0}">
                        <tr>
                            <td colspan="7">
                                조회 결과가 없습니다.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>


            <input type="submit" value="선택삭제" class="deleteAll" />
        </form>


        <div class="paging">
            <c:if test="${pageGroupStart > 1}">
			        	<span class="prev">
			            	<a href="${ctxPath}/admin/cs/qna/list.do?cate1=${cate1}&cate2=${cate2}&pg=${pageGroupStart - 1}"><&nbsp;이전</a>
		            	</span>
            </c:if>
            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
			            	<span class="num ${currentPage == i?'on':'off'}">
			            		<a href="${ctxPath}/admin/cs/qna/list.do?cate1=${cate1}&cate2=${cate2}&pg=${i}">${i}</a>
			            	</span>
            </c:forEach>
            <c:if test="${pageGroupEnd < lastPageNum}">
	                        <span class="next">
			            		<a href="${ctxPath}/admin/cs/qna/list.do?cate1=${cate1}&cate2=${cate2}&pg=${pageGroupEnd + 1}" >다음&nbsp;></a>
			            	</span>
            </c:if>
        </div>

    </section>



    <!-- 상품목록 컨텐츠 끝 -->
</section>
</main>
<%@include file="./../../_footer.jsp" %>