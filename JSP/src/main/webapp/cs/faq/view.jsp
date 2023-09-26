<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../_header.jsp" %>
<%@ include file="./_aside.jsp" %>
<script>
const ctx = '${ctxPath}';
//유저가 로그인하였는가 
let isLogin = ${isLogin};
//유저가 평가하였는가 
let isRated = ${isRated};
//유저가 준 점수는 몇 점인가(평가 안 했다면 -1)
let userRate = ${userRate};
// 전체
let faqRatesAllY = ${faqRatesAll[0]};
let faqRatesAllN = ${faqRatesAll[1]};
console.log('faqRatesAllY : ' + faqRatesAllY + '/ faqRatesAllN : ' + faqRatesAllN);

$(function(){
	
	// 유저가 선택했다면 제출한 점수 보여주기  
	if(isRated){
		if(userRate==0){
			$('#btn_Help_Y').css('background', 'rgba(0,108,255,0.3)');
		} else if(userRate==1){
			$('#btn_Help_N').css('background', 'rgba(0,108,255,0.3)');
		}
	}

	// percent 계산
	const helpPercent = $('.text__percent');
	let percentVal = calPercent(faqRatesAllY,faqRatesAllN);
	helpPercent.text(percentVal + '%');
	
	// 버튼 클릭시 rate테이블 업데이트와 percent 변화 
	$(document).on("click", ".faqHelpResult", function(){
		if(!isLogin){
			alert('로그인 후 평가가 가능합니다.');
			return false;
		}
		//alert($(this).attr('id'));
		const clickedID = $(this).attr('id');
		if(clickedID == 'btn_Help_Y'){
			helpPercent.text(updateFaqPercentY()+'%');

			console.log('faqRatesAllY : ' + faqRatesAllY + '/ faqRatesAllN : ' + faqRatesAllN);
			updateFaqRate(0);
		}else if (clickedID == 'btn_Help_N'){
			helpPercent.text(updateFaqPercentN()+'%');
			console.log('faqRatesAllY : ' + faqRatesAllY + '/ faqRatesAllN : ' + faqRatesAllN);
			updateFaqRate(1);
		}
		//DB 업로드
		updateRateDB();
	});
	
});

function updateFaqRate(e){
}
function updateFaqPercentY(){
	if(!isRated){
		 isRated = !isRated;
		faqRatesAllY++;
		userRate = 0;
	} else{
		if(userRate != 0){
			faqRatesAllY++;
			faqRatesAllN--;
			userRate = 0;
		}
	}
	$('#btn_Help_N').css('background', 'rgba(255,255,255)');
	$('#btn_Help_Y').css('background', 'rgba(0,108,255,0.3)');
	return calPercent(faqRatesAllY,faqRatesAllN);
}
function updateFaqPercentN(){
	if(!isRated){
		isRated = !isRated;
		faqRatesAllN++;
		userRate = 1;
	} else{
		if(userRate != 1){
			faqRatesAllN++;
			faqRatesAllY--;
			userRate = 1;
		}
	}
	$('#btn_Help_Y').css('background', 'rgba(255,255,255)');
	$('#btn_Help_N').css('background', 'rgba(0,108,255,0.3)');
	return calPercent(faqRatesAllY,faqRatesAllN);
}
function calPercent(a, b){
	if((a+b)==0) {
		return 0;
	} else {
		return Math.round((a / (a+b)) * 100);
	}
}
function updateRateDB(){
	$.ajax({ 
		type : "get",
		url : ctx + "/cs/faq/updateRateDB.do",
		dataType : 'json',
		data :  {
			"userRate": userRate,
			"no" : '${dto.faqNo}',
			"uid" : '${sessUser.uid}'
		},
		success : function(result) {
			console.log(result);
			if(result.result == 1){
				alert('성공적으로 평점이 부여되었습니다.');
			}
		},
		error : (request, status, error) => { // 순서 체크해보기 
            console.log("상태코드: " + request.status);
            console.log("메세지: " + request.responseText);
            console.log("에러설명: " + error);
        }
	});
}
</script>
      <article>
        <nav>
          <h2 class="title"><span>Q.</span>${dto.title}</a></h2>                
        </nav>

        <div class="content">
          <p>
            ${dto.content}
          </p>
        </div>
        <!-- 연관문의 -->
        <div class="faq-guide">
			<h3 class="text__title">자주 찾는 연관 문의입니다</h3>
			<div class="box__question">
				<ul class="list__question">
					<li class="list-item">
						<c:if test="${dto.relatedFaq eq 0}">
							<span class="text__question">자주 찾는 연관 문의가 없습니다.</span>
						</c:if>
						<c:if test="${dto.relatedFaq ne 0}">
							<span class="text__q">Q.</span> 	
							<span class="text__question"><a href="${ctxPath}/cs/faq/view.do?no=${relatedFAQ.faqNo}">[${relatedFAQ.c2Name}] ${relatedFAQ.title}</a></span>
						</c:if>
					</li>
				</ul>
			</div>
		</div>
		<!--서비 -->
		<div class="box__component-service">
			<div class="box__component-inner">
				<div class="box__column-left">
					<h3 class="text__title">답변에 도움이 되셨나요?</h3>
					<div class="box__survey">
						<p class="text__sub" id="faqHelpResult">이 답변은 <span class="text__percent">0%</span>의 사용자에게 도움이 되었습니다.</p>
						<div class="box__button">
							<button type="button" id="btn_Help_N" class="button faqHelpResult">아니요, 아쉬워요</button>
							<button type="button" id="btn_Help_Y" class="button faqHelpResult">네, 도움이 되었어요</button>
						</div>
					</div>
				</div>
				<div class="box__column-right">
					<h3 class="text__title">1:1 상담이 필요하신가요?</h3>
					<ul class="list__counseling">
						<li class="list-item list-item__write">
							<a href="${ctxPath}/cs/qna/write.do" id="askFromFaq" class="link__item"><span class="sprite__help--before">문의글작성</span></a>
						</li>
						<li class="list-item list-item__remote">
							<a href="#" onclick="javascript: alert('고객센터로 연락 주세요. 010-1234-1234');" class="link__item"><span class="sprite__help--before">원격지원</span></a>
						</li>
						<li class="list-item list-item__web">
							<a href="#" onclick="javascript: alert('고객센터로 연락 주세요. 010-1234-1234');"  class="link__item" data-montelena-acode="200005984"><span class="sprite__help--before">웹접근성<br>신고센터</span></a>
						</li>
					</ul>
				</div>
			</div>
		</div>
        <a href="${ctxPath}/cs/faq/list.do?cate1=${dto.cate1}" class="btnList">목록보기</a>
      </article>
    </section>
  </div>
<%@ include file="./../_footer.jsp" %>