<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %> <!-- _header.jsp 에도 jstl 참조처리 없는데 어떻게 표현언어 실행됐지??? -->
<script> // 스크립트 작성해야함
$(function(){
	$('.agree').click(function(){
		
		const chk1 = $('input[name=agree1]').is(':checked');
		const chk2 = $('input[name=agree2]').is(':checked');
		const chk3 = $('input[name=agree3]').is(':checked');
		const chk4 = $('input[name=agree4]').is(':checked');
		
		if(!chk1){
			alert('사이트 이용약관에 동의체크 하시기 바랍니다.');
			return false;
		}
		
		if(!chk2){
			alert('전자금융거래 이용약관에 동의체크 하시기 바랍니다.');
			return false;
		}
		
		if(!chk3){
			alert('개인정보 취급방침 약관에 동의체크 하시기 바랍니다.');
			return false; // .agree click 할 때 false이니까 submit 실행 안됨
		}
		
		return true; // .agree click 할 때 true이면 submit 기능이 실행된다
	});
});

</script>
        <main id="member">
            <div class="signup">
				<nav>
					<h1>약관동의</h1>
				</nav>
				<!-- signup.jsp에 수신된 type 파라미터가 normal or seller 인지에 따라 여기서 폼 전송을 register.do or registerSeller.do 로 나뉜다-->
				<form action="${ctxPath}/member/${type eq 'normal'? 'register' : 'registerSeller' }.do" method="get">	<!-- 삼항연산자 -->
					<section>
						<h3><span class="essential">(필수)</span>케이마켓 이용약관</h3>
						<textarea class="terms" readonly>${type eq 'normal' ? dto.terms : dto.tax}</textarea> <!-- 삼항연산자 -->
						<label><input type="checkbox" name="agree1" />동의합니다.</label>
	
						<h3><span class="essential">(필수)</span>전자금융거래 이용약관</h3>
						<textarea class="financial" readonly>${dto.finance}</textarea>
						<label><input type="checkbox" name="agree2" />동의합니다.</label>
	
						<h3><span class="essential">(필수)</span>개인정보 수집동의</h3>
						<textarea class="privacy" readonly>${dto.privacy}</textarea>
						<label><input type="checkbox" name="agree3" />동의합니다.</label>
					</section>
	
					<c:if test="${type eq 'normal'}">
						<section>
							<h3><span class="optional">(선택)</span>위치정보 이용약관</h3>
							<textarea class="location" readonly>${dto.location}</textarea>
							<label><input type="checkbox" name="agree4" />동의합니다.</label>
						</section>
					</c:if>
					
					<div>
						<input type="submit" class="agree" value="동의하기" /> <!-- register로 어떻게 전송해야하지??? type=button이었는데 submit으로 수정함, button은 전송안됨! -->
					</div>
				</form>
            </div>
        </main>        
<%@ include file="./_footer.jsp" %>