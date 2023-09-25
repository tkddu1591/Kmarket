<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
 <div class="faq">
    <nav>
      <div>
        <p>홈<span>></span>자주묻는 질문</p>
      </div>
    </nav>
    <section class="${group}">
      <aside>
        <h2>자주묻는 질문</h2>
        <ul>
          <li class="${cate1 eq '20'? 'on' : ''}"><a href="${ctxPath}/cs/faq/list.do?cate1=20">회원</a></li>
          <li class="${cate1 eq '21'? 'on' : ''}"><a href="${ctxPath}/cs/faq/list.do?cate1=21">쿠폰/이벤트</a></li>
          <li class="${cate1 eq '22'? 'on' : ''}"><a href="${ctxPath}/cs/faq/list.do?cate1=22">주문/결제</a></li>
          <li class="${cate1 eq '23'? 'on' : ''}"><a href="${ctxPath}/cs/faq/list.do?cate1=23">배송</a></li>
          <li class="${cate1 eq '24'? 'on' : ''}"><a href="${ctxPath}/cs/faq/list.do?cate1=24">취소/반품/교환</a></li>
          <li class="${cate1 eq '25'? 'on' : ''}"><a href="${ctxPath}/cs/faq/list.do?cate1=25">여행/숙박/항공</a></li>
          <li class="${cate1 eq '26'? 'on' : ''}"><a href="${ctxPath}/cs/faq/list.do?cate1=26">e쿠폰/배달</a></li>
          <li class="${cate1 eq '27'? 'on' : ''}"><a href="${ctxPath}/cs/faq/list.do?cate1=27">기타</a></li>
        </ul>
      </aside>