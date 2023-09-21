<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

  <div class="notice">
    <nav>
      <div>
        <p>홈<span>></span><a href="${ctxPath}/cs/notice/list.do">공지사항</a></p>
      </div>
    </nav>
    <section class="${group}">
      <aside>
        <h2>공지사항</h2>
        <ul>
          <li class="${cate1 eq '0'? 'on' : ''}"><a href="${ctxPath}/cs/notice/list.do?cate1=0">전체</a></li>
          <li class="${cate1 eq '10'? 'on' : ''}"><a href="${ctxPath}/cs/notice/list.do?cate1=10">고객서비스</a></li>
          <li class="${cate1 eq '11'? 'on' : ''}"><a href="${ctxPath}/cs/notice/list.do?cate1=11">안전거래</a></li>
          <li class="${cate1 eq '12'? 'on' : ''}"><a href="${ctxPath}/cs/notice/list.do?cate1=12">위해상품</a></li>
          <li class="${cate1 eq '13'? 'on' : ''}"><a href="${ctxPath}/cs/notice/list.do?cate1=13">이벤트당첨</a></li>
        </ul>
      </aside>