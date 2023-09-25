<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../_header.jsp" %>
<%@ include file="./_aside.jsp" %>
      <article>
        <nav>
          <h2 class="title">[${dto.c2Name}] ${dto.title}</h2>                
          <p>
            <span class="date">${dto.rdate}</span> <span class="hit"> / 조회수 : ${dto.hit}</span>
          </p>
        </nav>	

        <div class="content">
          <p>
            ${dto.content}
          </p>
        </div>
        <a href="${ctxPath}/cs/notice/list.do" class="btnList">목록보기</a>
      </article>
    </section>
  </div>
<%@ include file="./../_footer.jsp" %>