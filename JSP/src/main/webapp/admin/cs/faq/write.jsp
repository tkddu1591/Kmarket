<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../../_header.jsp" %>
<%@include file="./../../_aside.jsp" %>
<script>
const ctx = '${ctxPath}';
</script>
<script src="${ctxPath}/cs/js/setType2Category.js"></script>
<script src="${ctxPath}/admin/js/setFaqWrite.js"></script>
    <section id="admin-product-register">
        <nav>
            <h3>자주묻는질문 등록</h3>
            <p>
                HOME > 고객센터 > <strong>자주묻는질문</strong>
            </p>
        </nav>
        <!-- 상품등록 컨텐츠 시작 -->
        <article>
            <form action="${ctxPath}/admin/cs/faq/write.do" method="post" >
		    	<input type="hidden" name="writer" value="${sessUser.uid}">
		    	<!-- <input type="hidden" name="seller" value="${sessUser.uid}"> -->
                <!-- 상품분류 -->
                <section>
                    <table class="csInputTable">
						<tr>
				            <td>유형</td>
				            <td>
				              <select name="cate1" id="cate1">
				                <option value="">대분류 선택</option>
				              </select>
				              <select name="cate2" id="cate2">
				                <option value="">상세유형 선택</option>
				              </select>
				            </td>
				         </tr>
				         <tr>
				            <td>제목</td>                  
				            <td>
				              <input type="text" name="title" placeholder="제목을 입력하세요."/>
				            </td>
				          </tr>                
				          <tr>
				            <td>내용</td>                  
				            <td>
				              <textarea name="content" placeholder="내용을 입력하세요."></textarea>
				            </td>
				          </tr>  
				          <tr>            
				            <td>자주 찾는 연관 문의</td>                  
				            <td>
		                        <select name="relatedCate1" id="relatedCate1">
				                	<option value="">대분류 선택</option>
		                        </select>
		                        <select name="relatedCate2" id="relatedCate2">
				                	<option value="">상세유형 선택</option>
		                        </select>
		                        <select name="relatedFaq" id="relatedTitle" style="width:500px">
		                        </select>
				            </td>
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
