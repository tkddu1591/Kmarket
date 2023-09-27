<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../../_header.jsp" %>
<%@include file="./../../_aside.jsp" %>
<script>
const ctx = '${ctxPath}';
</script>
<script src="${ctxPath}/cs/js/setType1Category.js"></script>
    <section id="admin-product-register">
        <nav>
            <h3>공지사항 조회</h3>
            <p>
                HOME > 고객센터 > <strong>공지사항</strong>
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
				            	<p>1차 유형 : [${dto.c1Name}]</p>
				            	<p>2차 유형 : [${dto.c2Name}]</p>
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
				          <tr>
                    </table>
                </section>
                
                <a class="linkBtn" href="${ctxPath}/admin/cs/notice/delete.do?no=${dto.noticeNo}">삭제</a>
                <a class="linkBtn" href="${ctxPath}/admin/cs/notice/update.do?no=${dto.noticeNo}">수정</a>
                <a class="linkBtn mainBtn" href="${ctxPath}/admin/cs/notice/list.do">목록</a>
        </article>

        <!-- 상품등록 컨텐츠 끝 -->
    </section>
</main>
<%@ include file="./../../_footer.jsp" %>
