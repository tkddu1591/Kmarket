<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../../_header.jsp" %>
<%@include file="./../../_aside.jsp" %>
<script>
const ctx = '${ctxPath}';
</script>
    <section id="admin-product-register">
        <nav>
            <h3>자주묻는질문 조회</h3>
            <p>
                HOME > 고객센터 > <strong>자주묻는질문</strong>
            </p>
        </nav>
        <!-- 상품등록 컨텐츠 시작 -->
        <article>
		    	<!-- <input type="hidden" name="seller" value="${sessUser.uid}"> -->
                <!-- 상품분류 -->
                <section>
                    <table class="csInputTable csViewTable">
				         <tr>
				            <td>유형</td>                  
				            <td>
				            	<p>[${dto.c1Name}] - [${dto.c2Name}]</p>
				            </td>
				          </tr>                
				          <tr>
				            <td>제목</td>                  
				            <td>
				            	<p>${dto.title}</p>
				            </td>
				          </tr>                
				          <tr>
				            <td>내용</td>                  
				            <td>
				            	<p>${dto.content}</p>
				            </td>
				          </tr> 
				          <c:if test="${dto.relatedFaq ne 0}">
				                     
				          <tr>
				            <td>자주 찾는 연관 문의</td>  
					          <td>
					              <p>
					               <a href="${ctxPath}/admin/cs/faq/view.do?no=${relatedDto.faqNo}">
					               	[${relatedDto.c1Name}] - [${relatedDto.c2Name}] ${relatedDto.title}
					               </a>
					               </p>
					          </td>
				          </tr>
				          </c:if>  
                    </table>
                </section>
                
                <a class="linkBtn" href="${ctxPath}/admin/cs/faq/delete.do?no=${dto.faqNo}">삭제</a>
                <a class="linkBtn" href="${ctxPath}/admin/cs/faq/update.do?no=${dto.faqNo}">수정</a>
                <a class="linkBtn mainBtn" href="${ctxPath}/admin/cs/faq/list.do">목록</a>
        </article>

        <!-- 상품등록 컨텐츠 끝 -->
    </section>
</main>
<%@ include file="./../../_footer.jsp" %>
