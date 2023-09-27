<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../../_header.jsp" %>
<%@include file="./../../_aside.jsp" %>
<script>
const ctx = '${ctxPath}';
</script>
<script src="${ctxPath}/admin/js/setQnaView.js"></script>
    <section id="admin-product-register">
	    <nav>
	        <h3>문의하기 조회</h3>
	        <p>
	            HOME > 고객센터 > <strong>문의하기</strong>
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
				            <td>질문 제목</td>                  
				            <td>
				            	<p>${dto.title}</p>
				            	<input type="hidden" name="no" value="${dto.qnaNo}">
				            	<input type="hidden" name="writer" value="${sessUser.uid}">
				            </td>
				          </tr>
				          <tr>
				          	<td>상태</td>
				          	<td>
		           				<span class="answer${dto.answerComplete}" id="answerStatus">
		           					${dto.answerComplete eq '0' ? '미확인' : dto.answerComplete eq '1' ? '검토중' : '답변완료'}
		           				</span>
							</td>		           				
				          </tr>    
				          <tr>
				            <td>질문 내용</td>                  
				            <td>
				            	<p>${dto.content}</p>
				            </td>
				          </tr> 
				          <c:if test="${fn:length(dto.file) != 0}">
					        <tr class="file">
					        	<td>첨부파일</td>
					        	<td>
						        <c:set var="fileNo" value="0"/>
						        <c:forEach var="file" items="${dto.file}">
					        		<ul>
					        			<li>
					        				<span>파일${fileNo = fileNo +1} : &nbsp;</span>
					        				
					        				<div class="fileImg">
							        			<a href="${ctxPath}/cs/qna/fileDownload.do?fileName=${file}" id="downloadFile" data-fno="${file}">
		
							        				 <img src="${ctxPath+=file}">
						        				 </a>
					        				 </div>
					        			</li>
					        		</ul>
						        </c:forEach>
						        </td>
					        </tr>
			       		</c:if>
              				<c:if test="${dto.answerComplete ne '2'}">   
              				<tr id="updateStaus">
              					<td>상태 업데이트</td>
              					<td>
                					<label class="qnaRadio"><input type="radio" name="updateAnswerComplete" value="0" ${dto.answerComplete eq 0 ? 'checked' : ''}>'미확인'으로 변경</label>
                					<label class="qnaRadio"><input type="radio" name="updateAnswerComplete" value="1" ${dto.answerComplete eq 1 ? 'checked' : ''}>'검토중'으로 변경</label>
              					</td>
              				</tr>
              				</c:if>
				          <tr>
				            <td>답변</td>  
					          <td>
					              <textarea name="answer"><c:if test="${dto.answerComplete eq 2}">${answerDto.getContentTextArea()}</c:if></textarea>
					          </td>
				          </tr>
                    </table>
                </section>
                
                <a class="linkBtn" href="${ctxPath}/admin/cs/qna/delete.do?no=${dto.qnaNo}">삭제</a>
                <c:if test="${dto.answerComplete eq '2'}">                
                	<a class="linkBtn mainBtn answerBtn" id="updateAnswer" data-kind="update">답변수정</a>
                </c:if>
                <c:if test="${dto.answerComplete ne '2'}">
				    <a class="linkBtn mainBtn answerBtn" id="insertAnswer" data-kind="insert">답변등록</a>
                </c:if>
                <a class="linkBtn mainBtn" href="${ctxPath}/admin/cs/qna/list.do">목록</a>
        </article>

        <!-- 상품등록 컨텐츠 끝 -->
    </section>
</main>
<%@ include file="./../../_footer.jsp" %>
