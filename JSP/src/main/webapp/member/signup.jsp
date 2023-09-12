<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %> <!-- _header.jsp 에도 jstl 참조처리 없는데 어떻게 표현언어 실행됐지??? -->
<script> // 스크립트 작성해야함


</script>
        <main id="member">
            <div class="signup">
				<nav>
					<h1>약관동의</h1>
				</nav>
				<form action=""> <!-- 노션 참고해서 작성하기 -->
					<section>
						<h3><span class="essential">(필수)</span>케이마켓 이용약관</h3>
						<textarea class="terms" readonly>${dto.terms}</textarea>
						<label><input type="checkbox" name="agree1" />동의합니다.</label>
	
						<h3><span class="essential">(필수)</span>전자금융거래 이용약관</h3>
						<textarea class="financial" readonly>${dto.finance}</textarea>
						<label><input type="checkbox" name="agree2" />동의합니다.</label>
	
						<h3><span class="essential">(필수)</span>개인정보 수집동의</h3>
						<textarea class="privacy" readonly>${dto.privacy}</textarea>
						<label><input type="checkbox" name="agree3" />동의합니다.</label>
					</section>
	
					<section>
						<h3><span class="optional">(선택)</span>위치정보 이용약관</h3>
						<textarea class="location" readonly>${dto.location}</textarea>
						<label><input type="checkbox" name="agree4" />동의합니다.</label>
					</section>
	
					<div>
						<input type="button" class="agree" value="동의하기" /> <!-- register로 어떻게 전송해야하지??? -->
					</div>
				</form>
            </div>
        </main>        
<%@ include file="./_footer.jsp" %>