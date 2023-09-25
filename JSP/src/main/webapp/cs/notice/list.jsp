<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../_header.jsp" %>
<%@ include file="./_aside.jsp" %>
      <article>
        <nav>
           	<c:if test="${cate1 eq '0'}">
           		<h1>전체</h1>
           		<h2>공지사항 전체 내용입니다.</h2>
           	</c:if>
           	<c:if test="${cate1 ne '0'}">  		
              <h1>${c1Name}</h1>
              <h2>${c1Name} 관련 공지사항입니다.</h2>
           	</c:if>
        </nav>

        <table>
          <c:forEach var="notice" items="${noticeList}">
	   		<tr>
	   			<td>
	   				<a href="${ctxPath}/cs/notice/view.do?no=${notice.noticeNo}">
	   					[${cate1 eq '0' ? notice.c1Name : notice.c2Name}]&nbsp;${notice.title}
	   				</a>
	   			</td>
	   			<td>
	   				${notice.rdateSub}
	   			</td>
	   		</tr>
   	       </c:forEach>
	   	   <c:if test="${fn:length(noticeList) eq 0}">
	   			<tr>
	   				<td colspan="2">등록된 게시물이 없습니다.</td>
	   			</tr>
	   	   </c:if>
        </table>

        <div class="page">
          <c:if test="${pageGroupStart > 1}">
          	<a href="${ctxPath}/cs/notice/list.do?cate1=${cate1}&pg=${pageGroupStart - 1}" class="prevOn">이전</a>
          </c:if>
          <c:if test="${pageGroupStart eq 1 }">
          	<a href="${ctxPath}/cs/notice/list.do?cate1=${cate1}&pg=1" class="prevOff">이전</a>
          </c:if>
          <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
          	<a href="${ctxPath}/cs/notice/list.do?cate1=${cate1}&pg=${i}" 
          	class="num ${currentPage == i?'on':'off'}">${i}</a>
          </c:forEach>
          <c:if test="${pageGroupEnd < lastPageNum}">
          	<a href="${ctxPath}/cs/notice/list.do?cate1=${cate1}&pg=${pageGroupEnd + 1}" class="nextOn">다음</a>
          </c:if>
          <c:if test="${pageGroupEnd eq lastPageNum }">
          	<a href="${ctxPath}/cs/notice/list.do?cate1=${cate1}&pg=${pageGroupEnd}" class="nextOff">다음</a>
          </c:if>
        </div>

      </article>
    </section>
  </div>
<%@ include file="./../_footer.jsp" %>