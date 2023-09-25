<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../_header.jsp" %>
<%@ include file="./_aside.jsp" %>
      <article>
      	<div class="qnaView">
	        <nav>
	          <h2 class="title">[${dto.c2Name}] ${dto.title}</h2>                
	          <p>
	            <span>${dto.writerMarking}</span>
	            <span class="date">${dto.rdate}</span>
	          </p>
	        </nav>
	
	        <c:if test="${dto.answerComplete eq 0}">
		        <div class="answerStatus answer-0">
		        	[상태] 대기중
		        </div>
	        </c:if>
	        <c:if test="${dto.answerComplete eq 1}">
		        <div class="answerStatus answer-1">
		        	[상태] 검토중 
		        	<span>* 검토 중인 질문은 수정/삭제가 제한됩니다.</span>
		        </div>
	        </c:if>
	        <c:if test="${dto.answerComplete eq 2}">
		        <div class="answerStatus answer-2">
		        	[상태] 답변완료 
		        </div>
	        </c:if>
	        <div class="content">
	          <p>
	            ${dto.content}
	          </p>
	        </div>
	        <c:if test="${fn:length(dto.file) != 0}">
		        <div class="file">
		        	<h3>[ 첨부파일 ]</h3>
			        <c:set var="fileNo" value="0"/>
			        <c:forEach var="file" items="${dto.file}">
		        		<ul>
		        			<li>
		        				<span>파일${fileNo = fileNo +1} : &nbsp;</span>
		        				<div class="fileImg">
				        			<a href="${ctxPath}/cs/qna/fileDownload.do?fileName=${file}" id="downloadFile" data-fno="${file}">
				        				 <img src="${ctxPath += file}">
			        				 </a>
		        				 </div>
		        			</li>
		        		</ul>
			        </c:forEach>
		        </div>
       		</c:if>
	
	        <c:if test="${dto.answerComplete eq 2}">
		        <nav class="answerTitle">
		          <h2 class="title"> ☞ [ 답변 ] ${dto.title}</h2>                
		          <p>
		            <span>${answer.writerName}</span>
		            <span class="date">${answer.rdate}</span>
		          </p>
		        </nav>
		        <div class="content answerContent">
		          <p>
		            ${answer.content}
		          </p>
		        </div>
	    	    <c:if test="${fn:length(answer.file) != 0}">
			        <div class="file">
			        	<h3>[ 첨부파일 ]</h3>
				        <c:set var="fileNo" value="0"/>
				        <c:forEach var="file" items="${answer.file}">
			        		<ul>
			        			<li>
			        				<span>파일${fileNo = fileNo +1} : &nbsp;</span>
			        				
			        				<div class="fileImg">
					        			<a href="${ctxPath}/cs/qna/fileDownload.do?fileName=${file}" id="downloadFile" data-fno="${file}">
											<c:forTokens var="ext" items="${file}" delims="." varStatus="status">
												<c:if test="${status.last}">
													<c:choose>
														<c:when test="${ext eq 'pdf'}">
															
														</c:when>
														<c:otherwise>
															
														</c:otherwise>
													</c:choose>
												</c:if>
											</c:forTokens>
					        				 <img src="${ctxPath}/upload/${file}">
				        				 </a>
			        				 </div>
			        			</li>
			        		</ul>
				        </c:forEach>
			        </div>
	       		</c:if>
	        	
        	</c:if>
       	</div>
        <a href="${ctxPath}/cs/qna/list.do" class="btnList">목록보기</a>
        <c:if test="${sessUser.uid eq dto.writer && dto.answerComplete < 1}">
	        <a href="${ctxPath}/cs/qna/delete.do?no=${no}" class="btnList">문의 삭제</a>
	        <a href="${ctxPath}/cs/qna/modify.do?no=${no}" class="btnList">문의 수정</a>
        </c:if>
      </article>
    </section>
  </div>
<%@ include file="./../_footer.jsp" %>