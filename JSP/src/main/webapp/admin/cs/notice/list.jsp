<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./../../_header.jsp" %>
<%@include file="./../../_aside.jsp" %>
<script>
const ctx = '${ctxPath}';
const controllerCate1 = '${cate1}';
</script>
<script src="${ctxPath}/admin/js/setNoticeList.js"></script>
<script src="${ctxPath}/admin/js/controllCsDelete.js"></script>
            <section id="admin-product-list">
                <nav>
                    <h3>공지사항 목록</h3>
                    <p>
                        HOME > 고객센터 > <strong>공지사항</strong>
                    </p>
                </nav>
               
                <!-- 상품목록 컨텐츠 시작 -->                                
                <section>
                
                    <form action="${ctxPath}/admin/cs/notice/delete.do" method="post">
                    <div>
                        <select name="searchCate1" id="searchCate1">
                            <option value="">유형선택</option>
                        </select>
                        <input type="text" name="searchKeyword" id="searchKeyword" value="${keyword}" placeholder="검색어를 입력하세요." >
                        <input type="hidden" name="searchPg" id="searchPg">
                    </div>
                    <table class="listTable">
                    	<thead>
                        <tr>
                            <th><input type="checkbox" name="all"/></th>
                            <th>번호</th>
                            <th>유형</th>
                            <th>제목</th>
                            <th>조회</th>
                            <th>날짜</th>
                            <th>관리</th>
                        </tr>
                        </thead>
                        <tbody class="listTbody">
						 <c:forEach var="dto" items="${noticeList}">
                        <tr>
                            <td><input type="checkbox" name="chk" value="${dto.noticeNo}"/></td>
                            <td>${dto.noticeNo}</td>
                            <td>${dto.c1Name}</td>
                            <td><a href="${ctxPath}/admin/cs/notice/view.do?no=${dto.noticeNo}">[ ${dto.c2Name} ] ${dto.title}</a></td>
                            <td>${dto.hit}</td>
                            <td>${dto.rdateSub}</td>
                            <td>
                                <a href="${ctxPath}/admin/cs/notice/delete.do?no=${dto.noticeNo}" class="noticeDelete">[삭제]</a>
                                <br/>
                                <a href="${ctxPath}/admin/cs/notice/update.do?no=${dto.noticeNo}" class="noticeUpdate">[수정]</a>
                            </td>
                        </tr>
                        </c:forEach>
                        <c:if test="${fn:length(noticeList) eq 0}">
                        <tr>
                        	<td colspan="7">
                        	조회 결과가 없습니다.
                        	</td>
                        </tr>
                        </c:if>
                        </tbody>
                    </table>

                  
                    	<input type="submit" value="선택삭제" class="deleteAll" />   
                    	<a href="${ctxPath}/admin/cs/notice/write.do" class="writeBtn">작성하기</a>                       
                   	</form>
                    	

                    <div class="paging">              
			        	<c:if test="${pageGroupStart > 1}">
			        	<span class="prev">
			            	<a href="${ctxPath}/admin/cs/notice/list.do?cate1=${cate1}&pg=${pageGroupStart - 1}"><&nbsp;이전</a>
		            	</span>
			            </c:if>
			            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
			            	<span class="num ${currentPage == i?'on':'off'}">
			            		<a href="${ctxPath}/admin/cs/notice/list.do?cate1=${cate1}&pg=${i}">${i}</a>
			            	</span>
			            </c:forEach>
			            <c:if test="${pageGroupEnd < lastPageNum}">
	                        <span class="next">
			            		<a href="${ctxPath}/admin/cs/notice/list.do?cate1=${cate1}&pg=${pageGroupEnd + 1}" >다음&nbsp;></a>
			            	</span>
			            </c:if>
                    </div>

                </section>                

                

                <!-- 상품목록 컨텐츠 끝 -->
            </section>
        </main>
<%@include file="./../../_footer.jsp" %>