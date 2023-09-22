<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
	const success =  new URL(location.href).searchParams.get('success');
	
	if(success == 0){
		alert('잘못된 경로입니다.');
	} 
</script>
        <div class="main">
          <h1 class="title"><strong>케이마켓</strong>이 도와드릴게요!</h1>              
          <section class="notice">
            <h1>공지사항<a href="${ctxPath}/cs/notice/list.do">전체보기</a></h1>
            <ul>
            	<c:forEach var="notice" items="${latestNotices}">
	              <li>
	                <a href="${ctxPath}/cs/notice/view.do?no=${notice.noticeNo}" class="title">[${notice.c1Name}] ${notice.title}</a>
	                <span class="date">${notice.rdateSub}</span>
	              </li>
              	</c:forEach>
            </ul>
          </section>
        
          <section class="faq">
            <h1>자주 묻는 질문<a href="${ctxPath}/cs/faq/list.do?cate1=20">전체보기</a>
            </h1>
            <ol>
              <li>
                <a href="${ctxPath}/cs/faq/list.do?cate1=20"><span>회원</span></a>
              </li>
              <li>
                <a href="${ctxPath}/cs/faq/list.do?cate1=21"><span>쿠폰/이벤트</span></a>
              </li>
              <li>
                <a href="${ctxPath}/cs/faq/list.do?cate1=22"><span>주문/결제</span></a>
              </li>
              <li>
                <a href="${ctxPath}/cs/faq/list.do?cate1=23"><span>배송</span></a>
              </li>
              <li>
                <a href="${ctxPath}/cs/faq/list.do?cate1=24"><span>취소/반품/교환</span></a>
              </li>
              <li>
                <a href="${ctxPath}/cs/faq/list.do?cate1=25"><span>여행/숙박/항공</span></a>
              </li>
              <li>
                <a href="${ctxPath}/cs/faq/list.do?cate1=27"><span>기타</span></a>
              </li>
            </ol>
          </section>
        
          <section class="qna">
            <h1>
              문의하기
              <a href="${ctxPath}/cs/qna/list.do">전체보기</a>
            </h1>
            <ul>
           		<c:forEach var="qna" items="${latestQnas}">
	              <li>
	                <a href="${ctxPath}/cs/qna/view.do?no=${qna.qnaNo}" class="title">[${qna.c1Name}] ${qna.title}</a>
	                <p>
	                	<span class="uid">${qna.writerMarking}</span>
	                	<span class="date">${qna.rdateSub}</span>
	                </p>
	              </li>
             	</c:forEach>
            </ul>
            <a href="${ctxPath}/cs/qna/write.do" class="ask">문의글 작성 ></a>
          </section>

          <section class="tel">
            <h1>
              1:1 상담이 필요하신가요?
            </h1>

            <article>
              <div>
                <h3>고객센터 이용안내</h3>
                <p>
                  <span>일반회원/비회원</span><br>
                  <strong>1566-0001</strong><span>(평일 09:00 ~ 18:00)</span>
                </p>
                <p>
                  <span>스마일클럽 전용</span><br>
                  <strong>1566-0002</strong><span>(365일 09:00 ~ 18:00)</span>
                </p>
              </div>
            </article>
            <article>
              <div>
                <h3>판매상담 이용안내</h3>
                <p>
                  <span>판매고객</span><br>
                  <strong>1566-5700</strong><span>(평일 09:00 ~ 18:00)</span>
                </p>
                <p>
                  <a href="#">판매자 가입 및 서류 접수 안내 〉</a>
                </p>                
              </div>
            </article>

          </section>
        
      </section>

<%@ include file="./_footer.jsp" %>
