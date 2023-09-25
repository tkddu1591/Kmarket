<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./../_header.jsp" %>

<%@include file="./../_aside.jsp" %>
<script>
	const success =  new URL(location.href).searchParams.get('success');
	
	if(success == 0){
	} else if(success == 100){
		alert('상품이 정상적으로 삭제되었습니다.');
	} else if(success == 200){
		alert('상품이 정상적으로 수정되었습니다.');
	}	
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
                    <div>
                        <select name="search">
                            <option value="search1">상품명</option>
                            <option value="search1">상품코드</option>
                            <option value="search1">제조사</option>
                            <option value="search1">판매자</option>                                    
                        </select>
                        <input type="text" name="search">
                    </div>
                    <table>
                        <tr>
                            <th><input type="checkbox" name="all"/></th>
                            <th>이미지</th>
                            <th>상품코드</th>
                            <th>상품명</th>
                            <th>판매가격</th>
                            <th>할인율</th>
                            <th>포인트</th>
                            <th>재고</th>
                            <th>판매자</th>
                            <th>조회</th>
                            <th>관리</th>
                        </tr>
						 <c:forEach var="dto" items="${kmProductDTOS}">
                        <tr>
                            <td><input type="checkbox" name="상품코드"/></td>
                            <td><img src="${ctxPath += dto.thumb1}" class="thumb1"></td>
                            <td>${dto.prodNo}</td>
                            <td>${dto.prodName}</td> 
                            <td>${dto.priceWithComma}</td>
                            <td>${dto.discount}</td>
                            <td>${dto.point}</td>
                            <td>${dto.stock}</td>
                            <td>${dto.seller}</td>
                            <td>${dto.hit}</td>
                            <td>
                                <a href="/admin/product/delete.do?no=${dto.prodNo}" class="productDelete">[삭제]</a>
                                <a href="/admin/product/update.do?no=${dto.prodNo}"  class="productRegister">[수정]</a>

                            </td>
                        </tr>
                        </c:forEach>
                        
                    </table>

                  
                    	<input type="button" value="선택삭제" method="post" />                          
                    	

                    <div class="paging">              
			        	<c:if test="${pageGroupStart > 1}">
			        	<span class="prev">
			            	<a href="${ctxPath}/admin/product/list.do?group=${group}&cate=${cate}&pg=${pageGroupStart - 1}"><&nbsp;이전</a>
		            	</span>
			            </c:if>
			            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
			            	<span class="num ${currentPage == i?'on':'off'}">
			            		<a href="${ctxPath}/admin/product/list.do?group=${group}&cate=${cate}&pg=${i}&">${i}</a>
			            	</span>
			            </c:forEach>
			            <c:if test="${pageGroupEnd < lastPageNum}">
	                        <span class="next">
			            		<a href="${ctxPath}/admin/product/list.do?group=${group}&cate=${cate}&pg=${pageGroupEnd + 1}" >다음&nbsp;></a>
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
<%@include file="./../_footer.jsp" %>