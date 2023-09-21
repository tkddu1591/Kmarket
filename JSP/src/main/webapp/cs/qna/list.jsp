<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../_header.jsp" %>
<%@ include file="./_aside.jsp" %>
<script>
	const success =  new URL(location.href).searchParams.get('success');
	
	if(success == 0){
	} else if(success == 100){
		alert('정상적으로 글이 삭제되었습니다.');
	} else if(success == 101){
		alert('자신의 글만 삭제할 수 있습니다.');
	} else if(success == 200){
		alert('정상적으로 글이 수정되었습니다.');
	} else if (success == 201) {
		alert('자신의 글만 수정할 수 있습니다.');
	}else if (success == 301) {
		alert('자신의 글만 읽을 수 있습니다.');
	}
</script>
            <article>
              <nav>
              	<c:if test="${cate1 eq '0'}">
              		<h1>문의게시판</h1>
              		<h2>전체 문의 내용입니다.</h2>
              	</c:if>
              	<c:if test="${cate1 ne '0'}">  		
	                <h1>${c1Name}</h1>
	                <h2>${c1Name}관련 문의 내용입니다.</h2>
              	</c:if>
              </nav>
              <table>
           		<c:forEach var="qna" items="${qnaList}">
           		<tr>
           			<td>
           				<a href="${ctxPath}/cs/qna/view.do?no=${qna.qnaNo}">
           					[${cate1 eq '0' ? qna.c1Name : qna.c2Name}]&nbsp;${qna.title}
           				</a>
           			</td>
           			<td>
           				<span class="answer${qna.answerComplete}">
           					${qna.answerComplete eq 0 ? '미확인' : qna.answerComplete eq 1 ? '검토중' : '답변완료'}
           				</span>
           			</td>
           			<td>
           				${qna.writerMarking}
           			</td>
           			<td>
           				${qna.rdate}
           			</td>
           		</tr>
           		</c:forEach>
		   	   <c:if test="${fn:length(qnaList) eq 0}">
		   			<tr>
		   				<td colspan="4">등록된 게시물이 없습니다.</td>
		   			</tr>
		   	   </c:if>
              </table>

              <div class="page">
                <c:if test="${pageGroupStart > 1}">
                	<a href="${ctxPath}/cs/qna/list.do?cate1=${cate1}&pg=${pageGroupStart - 1}" class="prevOn">이전</a>
                </c:if>
                <c:if test="${pageGroupStart eq 1 }">
                	<a href="${ctxPath}/cs/qna/list.do?cate1=${cate1}&pg=1" class="prevOff">이전</a>
                </c:if>
                <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                	<a href="${ctxPath}/cs/qna/list.do?cate1=${cate1}&pg=${i}" 
                	class="num ${currentPage == i?'on':'off'}">${i}</a>
                </c:forEach>
                <c:if test="${pageGroupEnd < lastPageNum}">
                	<a href="${ctxPath}/cs/qna/list.do?cate1=${cate1}&pg=${pageGroupEnd + 1}" class="nextOn">다음</a>
                </c:if>
                <c:if test="${pageGroupEnd eq lastPageNum }">
                	<a href="${ctxPath}/cs/qna/list.do?cate1=${cate1}&pg=${pageGroupEnd}" class="nextOff">다음</a>
                </c:if>
              </div>

<%@ include file="./../_footer.jsp" %>