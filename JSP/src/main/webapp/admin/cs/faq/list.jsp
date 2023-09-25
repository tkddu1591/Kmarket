<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./../../_header.jsp" %>
<%@include file="./../../_aside.jsp" %>
<script>
const ctx = '${ctxPath}';
</script>
<script src="${ctxPath}/admin/js/setFaqList.js"></script>
<script src="${ctxPath}/admin/js/controllCsDelete.js"></script>
<script src="${ctxPath}/cs/js/setType2Category.js"></script>
            <section id="admin-product-list">
                <nav>
                    <h3>자주묻는질문 목록</h3>
                    <p>
                        HOME > 고객센터 > <strong>자주묻는질문</strong>
                    </p>
                </nav>
               
                <!-- 상품목록 컨텐츠 시작 -->                                
                <section>
                
                    <form action="${ctxPath}/admin/cs/faq/delete.do" method="post">
                    <div>
                        <select name="cate1" id="cate1">
                        </select>
                        <select name="cate2" id="cate2">
                        	<c:forEach var="cate2" items="${cate2List}">
                        		<option value="${cate2.cate2}">${cate2.c2Name}</option>
                        	</c:forEach>
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
                            <th>조회</th>
                            <th>날짜</th>
                            <th>관리</th>
                        </tr>
                        </thead>
                        <tbody class="listTbody">
						 <c:forEach var="dto" items="${faqList}">
                        <tr>
                            <td><input type="checkbox" name="chk" value="${dto.faqNo}"/></td>
                            <td>${dto.faqNo}</td>
                            <td>${dto.c1Name}</td>
                            <td>${dto.c2Name}</td>
                            <td><a href="${ctxPath}/admin/cs/faq/view.do?no=${dto.faqNo}">[ ${dto.c2Name} ] ${dto.title}</a></td>
                            <td>${dto.hit}</td>
                            <td>${dto.rdateSub}</td>
                            <td>
                                <a href="${ctxPath}/admin/cs/faq/delete.do?no=${dto.faqNo}" class="faqDelete">[삭제]</a>
                                <br/>
                                <a href="${ctxPath}/admin/cs/faq/update.do?no=${dto.faqNo}" class="faqUpdate">[수정]</a>
                            </td>
                        </tr>
                        </c:forEach>
                        <c:if test="${fn:length(faqList) eq 0}">
                        <tr>
                        	<td colspan="7">
                        	조회 결과가 없습니다.
                        	</td>
                        </tr>
                        </c:if>
                        </tbody>
                    </table>

                  
                    	<input type="submit" value="선택삭제" class="deleteAll" />   
                    	<a href="${ctxPath}/admin/cs/faq/write.do" class="writeBtn">작성하기</a>                       
                   	</form>
                    	
                </section>                

                

                <!-- 상품목록 컨텐츠 끝 -->
            </section>
        </main>
<%@include file="./../../_footer.jsp" %>