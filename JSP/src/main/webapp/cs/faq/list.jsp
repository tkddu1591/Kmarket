<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../_header.jsp" %>
<%@ include file="./_aside.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	
  $('.more').click(function(e){
    e.preventDefault();

    var list = $(this).parents('.box__question').find('.list__question .list-item');
	if (!$(this).hasClass('button__more--active') && $(this).parents('.box__question').find('.list__question .list-item').length > 4) {
		$(this).parents('.box__question').find('.list__question').find('.list-item').slideDown(100);
		$(this).addClass('button__more--active').html('<a>간단히보기</a>')
	} else {
		$(this).parents('.box__question').find('.list__question > li:nth-child(n+4)').slideUp(100);
		$(this).removeClass('button__more--active').html('<a>더보기</a>')
	}
    

  });
});
</script>
 
      <article>              
        <nav>
          <h1>${c1Name}</h1>
          <h2>가장 자주 묻는 질문입니다.</h2>
        </nav>

        
        	<c:forEach var="questions" items="${faqList}">
        		<c:set var="i" value="${i+1}" />
        		<div class="box__question">
        			<h3>${c2NameList[i-1]}</h3>
	          		<ul class="list__question">
	          			<c:forEach var="item" items="${questions}">
	          				<li class="list-item">
	          					<a href="${ctxPath}/cs/faq/view.do?no=${item.faqNo}">
	          						<span>Q.</span>${item.title}
	          					</a>
	          				</li>
	          			</c:forEach>
	          			<c:if test="${fn:length(questions) > 3}">
	          				 <li class="more"><a>더보기</a></li>
	          			</c:if>
	          			<c:if test="${fn:length(questions) eq 0}">
	          				 <li><a>등록된 자주 묻는 질문이 없습니다.</a></li>
	          			</c:if>
	          		</ul>
          		</div>
        	</c:forEach>
       
      </article>
    </section>
  </div>
     
<%@ include file="./../_footer.jsp" %>