<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./../_header.jsp" %>
        <main>
            <aside>
                <!-- Global Navigation Bar -->
                <ul id="gnb">
                    <li>
                        <a href="#"><i class="fa fa-cogs" aria-hidden="true"></i>환경설정</a>
                        <ol>
                            <li><a href="#">기본환경설정</a></li>
                            <li><a href="#">배너관리</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fas fa-store" aria-hidden="true"></i>상점관리</a>
                        <ol>
                            <li><a href="#">판매자현황</a></li>
                            <li><a href="#">재고관리</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-users" aria-hidden="true"></i>회원관리</a>
                        <ol>
                            <li><a href="#">회원현황</a></li>
                            <li><a href="#">포인트관리</a></li>
                            <li><a href="#">비회원관리</a></li>
                            <li><a href="#">접속자집계</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fas fa-box-open" aria-hidden="true"></i>상품관리</a>
                        <ol>
                            <li><a href="#">상품현황</a></li>
                            <li><a href="#">상품등록</a></li>
                            <li><a href="#">재고관리</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-credit-card" aria-hidden="true"></i>주문관리</a>
                        <ol>
                            <li><a href="#">주문현황</a></li>
                            <li><a href="#">매출현황</a></li>
                            <li><a href="#">결제관리</a></li>
                            <li><a href="#">배송관리</a></li>
                        </ol>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>게시판관리</a>
                        <ol>
                            <li><a href="#">게시판현황</a></li>
                            <li><a href="#">고객문의</a></li>
                        </ol>
                    </li>
                </ul>
            </aside>
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
                                <a href="/admin/product/list.jsp" class="productDelete">[삭제]</a>
                                <a href="#" class="productRegister">[수정]</a>
                            </td>
                        </tr>
                        </c:forEach>
                        <tr>
                            <td><input type="checkbox" name="상품코드"/></td>
                            <td><img src="../img/sample_thumb.jpg" class="thumb"></td>
                            <td>201603292</td>
                            <td>FreeMovement BLUEFORCE</td>
                            <td>36,000</td>
                            <td>10</td>
                            <td>360</td>
                            <td>400</td>
                            <td>홍길동</td>
                            <td>126</td>
                            <td>
                                <a href="productdelete.do?">[삭제]</a>
                                <a href="productmodify.do?">[수정]</a>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="상품코드"/></td>
                            <td><img src="../img/sample_thumb.jpg" class="thumb"></td>
                            <td>201603292</td>
                            <td>FreeMovement BLUEFORCE</td>
                            <td>36,000</td>
                            <td>10</td>
                            <td>360</td>
                            <td>400</td>
                            <td>홍길동</td>
                            <td>126</td>
                            <td>
                                <a href="${ctxPath}/list/productdelete.do?group=${group}&cate={cate}" class="btnDelete">[삭제]</a>
                                <a href="${ctxPath}/list/productmodify.do?group=${group}&cate={cate}" class="btnModify">[수정]</a>
                            </td>
                        </tr>
                         <c:forEach var="list" items="${product}">                    
				            <tr>
				                <td>${pageStartNum = pageStartNum - 1}</td>
				                <td><a href="./list.do?group=${group}&cate=${cate}&no=${list.prodNo}">${list.prodName}[${article.comment}]</a></td>
				                <td>${list.thumb1}</td>
				                <td>${list.prodNo}</td>
				                <td>${list.prodName}</td>
				                <td>${list.price}</td>
				                <td>${list.discount}</td>
				                <td>${list.point}</td>
				                <td>${list.stock}</td>
				                <td>${list.seller}</td>
				                <td>${list.hit}</td>
				            </tr>
			            </c:forEach>
                    </table>

                  
                    	<input type="button" value="선택삭제" method="post" />                          
                    	

                    <div class="paging">              
			        	<c:if test="${pageGroupStart > 1}">
			        	<span class="prev">
			            	<a href="${ctxPath}/product/list.do?group=${group}&cate=${cate}&pg=${pageGroupStart - 1}"><&nbsp;이전</a>
		            	</span>
			            </c:if>
			            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
			            	<span class="num ${currentPage == i?'on':'off'}">
			            		<a href="${ctxPath}/product/list.do?group=${group}&cate=${cate}&pg=${i}&">${i}</a>
			            	</span>
			            </c:forEach>
			            <c:if test="${pageGroupEnd < lastPageNum}">
	                        <span class="next">
			            		<a href="${ctxPath}/product/list.do?group=${group}&cate=${cate}&pg=${pageGroupEnd + 1}" >다음&nbsp;></a>
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